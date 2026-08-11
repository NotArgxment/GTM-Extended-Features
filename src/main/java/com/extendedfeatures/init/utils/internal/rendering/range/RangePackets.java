package com.extendedfeatures.init.utils.internal.rendering.range;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RangePackets {

    private final BlockPos position;
    private final int range;
    private final int durationTicks;

    public RangePackets(BlockPos position, int range, int durationTicks) {
        this.position = position;
        this.range = range;
        this.durationTicks = durationTicks;
    }

    public static void encode(RangePackets msg, FriendlyByteBuf buf) {
        buf.writeBlockPos(msg.position);
        buf.writeVarInt(msg.range);
        buf.writeVarInt(msg.durationTicks);
    }

    public static RangePackets decode(FriendlyByteBuf buf) {
        return new RangePackets(buf.readBlockPos(), buf.readVarInt(), buf.readVarInt());
    }

    public static void handle(RangePackets msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            if (ctx.getDirection().getReceptionSide() != LogicalSide.CLIENT)
                return;
            RangeRenderer
                    .showBoxAtPositionWithRange(msg.position, msg.range, msg.durationTicks);
        });
        ctx.setPacketHandled(true);
    }
}