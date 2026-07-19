package com.extendedfeatures.client.integrations.Jade;

import com.extendedfeatures.ExtendedFeaturesCore;
import com.extendedfeatures.init.utils.internal.optical.WirelessOpticalDataHatchMachine;

import com.gregtechceu.gtceu.api.machine.*;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Shows, for {@link WirelessOpticalDataHatchMachine} transmitters, the linked physical
 * Data Access Hatches (count) and linked Wireless Optical Receivers (positions) in the Jade tooltip.
 */
public class WirelessOpticalHatchProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {

    private static final String TAG_IS_TRANSMITTER = "IsTransmitter";
    private static final String TAG_DATA_HATCH_COUNT = "DataHatchCount";
    private static final String TAG_RECEIVERS = "Receivers";

    @Override
    public ResourceLocation getUid() {
        return ExtendedFeaturesCore.id("wireless_optical_hatch");
    }

    // Server side: gather data
    @Override
    public void appendServerData(CompoundTag data, BlockAccessor blockAccessor) {
        if (!(blockAccessor.getBlockEntity() instanceof IMachineBlockEntity be)) return;

        MetaMachine machine = be.getMetaMachine();
        if (!(machine instanceof WirelessOpticalDataHatchMachine hatch)) return;
        if (!hatch.isTransmitter()) return; // only transmitters expose this info

        data.putBoolean(TAG_IS_TRANSMITTER, true);
        data.putInt(TAG_DATA_HATCH_COUNT, hatch.getLinkedDataHatchPositions().size());

        ListTag receivers = new ListTag();
        for (BlockPos pos : hatch.getLinkedReceiverPositions()) {
            receivers.add(new IntArrayTag(new int[] { pos.getX(), pos.getY(), pos.getZ() }));
        }
        data.put(TAG_RECEIVERS, receivers);
    }

    // Client side: build the tooltip
    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor blockAccessor, IPluginConfig config) {
        CompoundTag data = blockAccessor.getServerData();
        if (!data.getBoolean(TAG_IS_TRANSMITTER)) return;

        int dataHatchCount = data.getInt(TAG_DATA_HATCH_COUNT);
        tooltip.add(Component.translatable("extendedfeatures.jade.wireless_optical_hatch.linked_data_hatches",
                dataHatchCount).withStyle(ChatFormatting.GRAY));

        List<BlockPos> receivers = readReceivers(data.getList(TAG_RECEIVERS, net.minecraft.nbt.Tag.TAG_INT_ARRAY));

        if (receivers.isEmpty()) {
            tooltip.add(Component
                    .translatable("extendedfeatures.jade.wireless_optical_hatch.linked_receivers_header")
                    .withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("extendedfeatures.jade.wireless_optical_hatch.no_receivers")
                    .withStyle(ChatFormatting.DARK_GRAY));
            return;
        }

        tooltip.add(Component.translatable("extendedfeatures.jade.wireless_optical_hatch.linked_receivers_header")
                .withStyle(ChatFormatting.GRAY));

        int index = 1;
        for (BlockPos pos : receivers) {
            Component coords = Component.literal(pos.getX() + " " + pos.getY() + " " + pos.getZ())
                    .withStyle(ChatFormatting.GOLD);
            tooltip.add(Component.translatable("extendedfeatures.jade.wireless_optical_hatch.receiver_entry",
                    index, coords).withStyle(ChatFormatting.WHITE));
            index++;
        }
    }

    private List<BlockPos> readReceivers(ListTag list) {
        List<BlockPos> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int[] coords = ((IntArrayTag) list.get(i)).getAsIntArray();
            if (coords.length == 3) {
                result.add(new BlockPos(coords[0], coords[1], coords[2]));
            }
        }
        return result;
    }
}