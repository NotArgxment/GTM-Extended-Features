package com.argxment.extendedfeatures.client.init.utils.optical;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IDataAccessHatch;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IInteractedMachine;
import com.gregtechceu.gtceu.api.machine.feature.IMachineLife;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.machine.multiblock.part.DataAccessHatchMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.OpticalDataHatchMachine;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class WirelessOpticalDataHatchMachine extends OpticalDataHatchMachine implements IMachineLife, IInteractedMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            WirelessOpticalDataHatchMachine.class, MultiblockPartMachine.MANAGED_FIELD_HOLDER);

    private static final int LINK_PARTICLE_DURATION_TICKS = 100; // 5 seconds
    private static final int LINK_PARTICLE_INTERVAL_TICKS = 4;

    public enum WirelessTier {
        LuV(GTValues.LuV, 16, 4),
        ZPM(GTValues.ZPM, 24, 8),
        UV(GTValues.UV, 32, 16);

        public final int gtTier;
        public final int range;
        public final int maxConnections;

        WirelessTier(int gtTier, int range, int maxConnections) {
            this.gtTier = gtTier;
            this.range = range;
            this.maxConnections = maxConnections;
        }

        public static WirelessTier byGTTier(int gtTier) {
            for (WirelessTier value : values()) {
                if (value.gtTier == gtTier) return value;
            }
            throw new IllegalArgumentException(
                    "No WirelessOpticalDataHatchMachine.WirelessTier registered for GT tier " + gtTier +
                            ". Only LuV, ZPM and UV are supported.");
        }
    }

    private final WirelessTier wirelessTier;

    /**
     * Receiver-only: the single transmitter this hatch is currently linked to, or {@code null}
     */
    @Persisted
    private BlockPos linkedTransmitterPos;

    /**
     * Transmitter-only: bookkeeping of who is currently listening (bounded by {@link WirelessTier#maxConnections})
     * */
    @Persisted
    private final List<BlockPos> linkedReceiverPositions = new ArrayList<>();

    /**
     * Transmitter-only: physical {@link DataAccessHatchMachine}s this transmitter reads research from.
     * Unbounded - does NOT count against {@link WirelessTier#maxConnections}, which only limits receivers.
     */
    @Persisted
    private final List<BlockPos> linkedDataHatchPositions = new ArrayList<>();

    private final List<TickableSubscription> particleSubscriptions = new ArrayList<>();

    public WirelessOpticalDataHatchMachine(IMachineBlockEntity holder, boolean isTransmitter, int gtTier) {
        super(holder, isTransmitter);
        this.wirelessTier = WirelessTier.byGTTier(gtTier);
    }

    public WirelessTier getWirelessTier() {
        return wirelessTier;
    }

    public boolean isLinked() {
        return isTransmitter()
                ? !linkedReceiverPositions.isEmpty() || !linkedDataHatchPositions.isEmpty()
                : linkedTransmitterPos != null;
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    // Lifecycle
    @Override
    public void onMachineRemoved() {
        for (TickableSubscription subscription : particleSubscriptions) {
            subscription.unsubscribe();
        }
        particleSubscriptions.clear();

        // Clean up bidirectional bookkeeping so stale links don't linger
        Level level = getLevel();
        if (level != null && isTransmitter()) {
            for (BlockPos receiverPos : linkedReceiverPositions) {
                if (MetaMachine.getMachine(level, receiverPos) instanceof WirelessOpticalDataHatchMachine receiver &&
                        getPos().equals(receiver.linkedTransmitterPos)) {
                    receiver.linkedTransmitterPos = null;
                }
            }
        } else if (level != null && linkedTransmitterPos != null &&
                MetaMachine.getMachine(level, linkedTransmitterPos) instanceof WirelessOpticalDataHatchMachine transmitter) {
            transmitter.linkedReceiverPositions.remove(getPos());
        }
    }

    // Recipe Logic
    @Override
    public boolean isRecipeAvailable(GTRecipe recipe, Collection<IDataAccessHatch> seen) {
        if (isTransmitter()) {
            seen.add(this);
            if (!isFormed()) return false;

            Level level = getLevel();
            if (level == null) return false;

            for (BlockPos dataHatchPos : linkedDataHatchPositions) {
                if (!level.isLoaded(dataHatchPos)) continue;
                if (!(MetaMachine.getMachine(level, dataHatchPos) instanceof DataAccessHatchMachine dataHatch)) continue;
                if (seen.contains(dataHatch)) continue;

                if (dataHatch.isRecipeAvailable(recipe, seen)) {
                    return true;
                }
            }
            return false;
        }

        seen.add(this);
        if (!isFormed()) return false;
        if (linkedTransmitterPos == null) return false;

        Level level = getLevel();
        if (level == null || !level.isLoaded(linkedTransmitterPos)) return false;

        if (!(MetaMachine.getMachine(level, linkedTransmitterPos) instanceof WirelessOpticalDataHatchMachine partner) ||
                !partner.isTransmitter()) {
            return false;
        }
        if (seen.contains(partner)) return false;

        return partner.isRecipeAvailable(recipe, seen);
    }

    // Scan and Link
    @Override
    public InteractionResult onUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand,
                                   BlockHitResult hit) {
        if (!player.getItemInHand(hand).isEmpty()) return InteractionResult.PASS;
        if (!isTransmitter()) return InteractionResult.PASS;
        if (world.isClientSide) return InteractionResult.SUCCESS;

        scanAndLink(player);
        return InteractionResult.SUCCESS;
    }

    /**
     * Scans a cubic area of {@code wirelessTier.range} blocks around this transmitter for:
     * <ul>
     *     <li>compatible, unlinked receiver hatches (bounded by the connection limit, closest first);</li>
     *     <li>physical {@link DataAccessHatchMachine}s not yet linked (unbounded).</li>
     * </ul>
     */
    private void scanAndLink(Player player) {
        if (!isFormed()) {
            player.sendSystemMessage(Component.translatable("extendedfeatures.machine.wireless_optical_hatch.not_formed"));
            return;
        }

        Level level = getLevel();
        if (!(level instanceof ServerLevel serverLevel)) return;

        BlockPos center = getPos();
        int range = wirelessTier.range;

        int newReceiverLinks = linkReceivers(serverLevel, center, range);
        int newDataHatchLinks = linkDataHatches(serverLevel, center, range);

        if (newReceiverLinks > 0 || newDataHatchLinks > 0) {
            player.sendSystemMessage(
                    Component.translatable("extendedfeatures.machine.wireless_optical_hatch.linked_summary",
                            newReceiverLinks, newDataHatchLinks));
        } else {
            player.sendSystemMessage(
                    Component.translatable("extendedfeatures.machine.wireless_optical_hatch.no_receivers_found"));
        }
    }

    private int linkReceivers(ServerLevel level, BlockPos center, int range) {
        int remainingSlots = wirelessTier.maxConnections - linkedReceiverPositions.size();
        if (remainingSlots <= 0) return 0;

        List<WirelessOpticalDataHatchMachine> candidates = findCandidateReceivers(level, center, range);
        candidates.sort((a, b) -> Double.compare(a.getPos().distSqr(center), b.getPos().distSqr(center)));

        int newLinks = 0;
        for (WirelessOpticalDataHatchMachine receiver : candidates) {
            if (remainingSlots <= 0) break;
            if (linkedReceiverPositions.contains(receiver.getPos())) continue;

            receiver.linkedTransmitterPos = center;
            linkedReceiverPositions.add(receiver.getPos());
            spawnLinkBeam(level, center, receiver.getPos(), ParticleTypes.END_ROD);
            remainingSlots--;
            newLinks++;
        }
        return newLinks;
    }

    private int linkDataHatches(ServerLevel level, BlockPos center, int range) {
        List<DataAccessHatchMachine> candidates = findCandidateDataHatches(level, center, range);

        int newLinks = 0;
        for (DataAccessHatchMachine dataHatch : candidates) {
            BlockPos dataHatchPos = dataHatch.getPos();
            if (linkedDataHatchPositions.contains(dataHatchPos)) continue;

            linkedDataHatchPositions.add(dataHatchPos);
            spawnLinkBeam(level, center, dataHatchPos, ParticleTypes.FLAME);
            newLinks++;
        }
        return newLinks;
    }

    private List<WirelessOpticalDataHatchMachine> findCandidateReceivers(ServerLevel level, BlockPos center,
                                                                         int range) {
        List<WirelessOpticalDataHatchMachine> found = new ArrayList<>();
        forEachBlockEntityInRange(level, center, range, (candidatePos, blockEntity) -> {
            if (MetaMachine.getMachine(level, candidatePos) instanceof WirelessOpticalDataHatchMachine other &&
                    !other.isTransmitter() &&
                    other.wirelessTier == this.wirelessTier &&
                    other.isFormed()) {
                found.add(other);
            }
        });
        return found;
    }

    private List<DataAccessHatchMachine> findCandidateDataHatches(ServerLevel level, BlockPos center, int range) {
        List<DataAccessHatchMachine> found = new ArrayList<>();
        forEachBlockEntityInRange(level, center, range, (candidatePos, blockEntity) -> {
            if (MetaMachine.getMachine(level, candidatePos) instanceof DataAccessHatchMachine dataHatch &&
                    dataHatch.isFormed()) {
                found.add(dataHatch);
            }
        });
        return found;
    }

    private interface BlockEntityVisitor {

        void visit(BlockPos pos, BlockEntity blockEntity);
    }

    private void forEachBlockEntityInRange(ServerLevel level, BlockPos center, int range, BlockEntityVisitor visitor) {
        int chunkMinX = (center.getX() - range) >> 4;
        int chunkMaxX = (center.getX() + range) >> 4;
        int chunkMinZ = (center.getZ() - range) >> 4;
        int chunkMaxZ = (center.getZ() + range) >> 4;

        for (int cx = chunkMinX; cx <= chunkMaxX; cx++) {
            for (int cz = chunkMinZ; cz <= chunkMaxZ; cz++) {
                if (!level.hasChunk(cx, cz)) continue;
                LevelChunk chunk = level.getChunk(cx, cz);

                for (BlockEntity blockEntity : chunk.getBlockEntities().values()) {
                    BlockPos candidatePos = blockEntity.getBlockPos();
                    if (candidatePos.distSqr(center) > (double) range * range) continue;
                    if (candidatePos.equals(center)) continue;

                    visitor.visit(candidatePos, blockEntity);
                }
            }
        }
    }

    // Feedback
    private void spawnLinkBeam(ServerLevel level, BlockPos from, BlockPos to, ParticleOptions particle) {
        int[] ticksElapsed = { 0 };
        TickableSubscription[] subscriptionHolder = new TickableSubscription[1];
        subscriptionHolder[0] = subscribeServerTick(() -> {
            if (ticksElapsed[0] >= LINK_PARTICLE_DURATION_TICKS || level.getServer() == null) {
                subscriptionHolder[0].unsubscribe();
                particleSubscriptions.remove(subscriptionHolder[0]);
                return;
            }
            if (ticksElapsed[0] % LINK_PARTICLE_INTERVAL_TICKS == 0) {
                emitParticleLine(level, from, to, particle);
            }
            ticksElapsed[0]++;
        });
        particleSubscriptions.add(subscriptionHolder[0]);
    }

    private void emitParticleLine(ServerLevel level, BlockPos from, BlockPos to, ParticleOptions particle) {
        Vec3 start = Vec3.atCenterOf(from);
        Vec3 end = Vec3.atCenterOf(to);
        double distance = start.distanceTo(end);
        int steps = Math.max(1, (int) (distance * 2));

        for (int i = 0; i <= steps; i++) {
            double t = (double) i / steps;
            Vec3 point = start.lerp(end, t);
            level.sendParticles(particle, point.x, point.y, point.z, 1, 0, 0, 0, 0);
        }
    }

    public String getWirelessTierName() {
        return wirelessTier.name().toLowerCase(Locale.ROOT);
    }
}