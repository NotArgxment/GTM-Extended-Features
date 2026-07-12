package com.argxment.extendedfeatures.client.init.utils.optical;

import com.argxment.extendedfeatures.client.config.EFConfig;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import net.minecraft.network.chat.Component;

import static com.argxment.extendedfeatures.ExtendedFeaturesCore.ExtendedFeaturesRegister;
import static com.gregtechceu.gtceu.api.GTValues.*;

public class WirelessOpticalDataMachines {

    public static MachineDefinition LUV_WIRELESS_TRANSMITTER = null;
    public static MachineDefinition LUV_WIRELESS_RECEIVER = null;

    public static MachineDefinition ZPM_WIRELESS_TRANSMITTER = null;
    public static MachineDefinition ZPM_WIRELESS_RECEIVER = null;

    public static MachineDefinition UV_WIRELESS_TRANSMITTER = null;
    public static MachineDefinition UV_WIRELESS_RECEIVER = null;

    static {
        if (EFConfig.INSTANCE.Wireless.wirelessUtils || GTCEu.isDataGen()) {
            LUV_WIRELESS_TRANSMITTER = registerHatch(
                    "luv_wireless_data_transmitter", "LuV Wireless Optical Transmission Hatch",
                    LuV, true)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.range", 16),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.connections", 4),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.scan"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .register();

            LUV_WIRELESS_RECEIVER = registerHatch(
                    "luv_wireless_data_receiver", "LuV Wireless Optical Reception Hatch",
                    LuV, false)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.receiver"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .register();

            ZPM_WIRELESS_TRANSMITTER = registerHatch(
                    "zpm_wireless_data_transmitter", "ZPM Wireless Optical Transmission Hatch",
                    ZPM, true)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.range", 24),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.connections", 8),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.scan"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .register();

            ZPM_WIRELESS_RECEIVER = registerHatch(
                    "zpm_wireless_data_receiver", "ZPM Wireless Optical Reception Hatch",
                    ZPM, false)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.receiver"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .register();

            UV_WIRELESS_TRANSMITTER = registerHatch(
                    "uv_wireless_data_transmitter", "UV Wireless Optical Transmission Hatch",
                    UV, true)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.range", 32),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.connections", 16),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.scan"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .register();

            UV_WIRELESS_RECEIVER = registerHatch(
                    "uv_wireless_data_receiver", "UV Wireless Optical Reception Hatch",
                    UV, false)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.receiver"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .register();
        }
    }

    private static MachineBuilder<MachineDefinition, ?> registerHatch(String name, String displayName, int tier,
                                                                      boolean isTransmitter) {
        return ExtendedFeaturesRegister.machine(name, (holder) -> new WirelessOpticalDataHatchMachine(holder, isTransmitter, tier))
                .langValue(displayName)
                .tier(tier)
                .rotationState(RotationState.ALL)
                /*
                if -> IsTransmitter = true: Register a WIRELESS_OPTICAL_TRASMITTER
                Or else, IsTransmitter = false -> Register a WIRELESS_OPTICAL_RECEIVER
                 */
                .abilities(isTransmitter
                        ? WirelessAbilities.WIRELESS_OPTICAL_TRANSMITTER
                        : WirelessAbilities.WIRELESS_OPTICAL_RECEIVER)
                .overlayTieredHullModel(isTransmitter
                        ? "wireless_optical_transmission"
                        : "wireless_optical_reception");
    }

    public static void init() {
    }

}