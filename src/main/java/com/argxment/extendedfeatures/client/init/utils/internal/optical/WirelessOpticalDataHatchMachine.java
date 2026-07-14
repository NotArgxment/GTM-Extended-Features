package com.argxment.extendedfeatures.client.init.utils.internal.optical;

import com.argxment.extendedfeatures.client.init.utils.internal.render.LinkedParticleAnimator;
import com.argxment.extendedfeatures.client.init.utils.internal.render.ParticleBeamRenderer;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IDataAccessHatch;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
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

    private static final int LINK_PARTICLE_DURATION_TICKS = 200; // 10 seconds
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

    @Persisted
    private BlockPos linkedTransmitterPos;

    @Persisted
    private final List<BlockPos> linkedReceiverPositions = new ArrayList<>();

    /**
     * Transmitter-only: physical {@link DataAccessHatchMachine}s this transmitter reads research from.
     * Unbounded - does NOT count against {@link WirelessTier#maxConnections}, which only limits receivers.
     */
    @Persisted
    private final List<BlockPos> linkedDataHatchPositions = new ArrayList<>();

    private final List<LinkedParticleAnimator> particleAnimators = new ArrayList<>();

    public WirelessOpticalDataHatchMachine(IMachineBlockEntity holder, boolean isTransmitter, int gtTier) {
        super(holder, isTransmitter);
        this.wirelessTier = WirelessTier.byGTTier(gtTier);
    }

    public WirelessTier getWirelessTier() {
        return wirelessTier;
    }

    /** Transmitter-only: read-only view of currently linked receiver positions. */
    public List<BlockPos> getLinkedReceiverPositions() {
        return List.copyOf(linkedReceiverPositions);
    }

    /** Transmitter-only: read-only view of currently linked physical Data Access Hatch positions. */
    public List<BlockPos> getLinkedDataHatchPositions() {
        return List.copyOf(linkedDataHatchPositions);
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
        for (LinkedParticleAnimator animator : particleAnimators) {
            animator.stop();
        }
        particleAnimators.clear();

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

    // Scan & Link
    @Override
    public InteractionResult onUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand,
                                   BlockHitResult hit) {
        if (!player.getItemInHand(hand).isEmpty()) return InteractionResult.PASS;
        if (!isTransmitter()) return InteractionResult.PASS;
        if (world.isClientSide) return InteractionResult.SUCCESS;

        if (player.isShiftKeyDown()) {
            showRangeCube(player);
        } else {
            scanAndLink(player);
        }
        return InteractionResult.SUCCESS;
    }

    private void showRangeCube(Player player) {
        if (!isFormed()) {
            player.sendSystemMessage(Component.translatable("extendedfeatures.machine.wireless_optical_hatch.not_formed"));
            return;
        }
        if (!(getLevel() instanceof ServerLevel serverLevel)) return;

        spawnRangeCubeBeam(serverLevel, getPos(), wirelessTier.range);
        player.sendSystemMessage(
                Component.translatable("extendedfeatures.machine.wireless_optical_hatch.range_shown", wirelessTier.range));
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
    private void spawnLinkBeam(ServerLevel level, BlockPos from, BlockPos to,
                               net.minecraft.core.particles.ParticleOptions particle) {
        Vec3 start = Vec3.atCenterOf(from);
        Vec3 end = Vec3.atCenterOf(to);
        LinkedParticleAnimator animator = new LinkedParticleAnimator(LINK_PARTICLE_DURATION_TICKS,
                LINK_PARTICLE_INTERVAL_TICKS, () -> ParticleBeamRenderer.emitLine(level, start, end, particle));
        particleAnimators.add(animator);
        animator.start(this::subscribeServerTick, () -> particleAnimators.remove(animator));
    }

    private void spawnRangeCubeBeam(ServerLevel level, BlockPos center, int range) {
        Vec3 centerVec = Vec3.atCenterOf(center);
        LinkedParticleAnimator animator = new LinkedParticleAnimator(LINK_PARTICLE_DURATION_TICKS,
                LINK_PARTICLE_INTERVAL_TICKS, () -> ParticleBeamRenderer.emitSquareOutline(level, centerVec, range,
                ParticleTypes.SOUL_FIRE_FLAME));
        particleAnimators.add(animator);
        animator.start(this::subscribeServerTick, () -> particleAnimators.remove(animator));
    }

    public String getWirelessTierName() {
        return wirelessTier.name().toLowerCase(Locale.ROOT);
    }
}