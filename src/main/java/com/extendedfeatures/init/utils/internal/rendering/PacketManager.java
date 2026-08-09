package com.extendedfeatures.init.utils.internal.rendering;

import com.extendedfeatures.ExtendedFeaturesCore;

import com.extendedfeatures.init.utils.internal.rendering.range.RangePackets;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketManager {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry
            .newSimpleChannel(new ResourceLocation(
                    ExtendedFeaturesCore.MOD_ID, "main"), () -> PROTOCOL_VERSION, // Network
                    PROTOCOL_VERSION::equals, // Client
                    PROTOCOL_VERSION::equals); // Server

    private static int nextId = 0;

    public static void register() {
        CHANNEL.registerMessage(nextId++,
                RangePackets.class,
                RangePackets::encode,
                RangePackets::decode,
                RangePackets::handle);
    }
}