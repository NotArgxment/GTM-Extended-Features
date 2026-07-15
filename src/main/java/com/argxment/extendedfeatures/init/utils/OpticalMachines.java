package com.argxment.extendedfeatures.init.utils;

import com.argxment.extendedfeatures.init.utils.internal.optical.ExpandedDataHatchLogic;
import com.argxment.extendedfeatures.init.utils.internal.optical.WirelessAbilities;
import com.argxment.extendedfeatures.init.utils.internal.optical.WirelessOpticalDataHatchMachine;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;

import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import net.minecraft.network.chat.Component;

import com.argxment.extendedfeatures.client.integrations.Configuration.EFConfig;

import static com.argxment.extendedfeatures.ExtendedFeaturesCore.ExtendedFeaturesRegister;
import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties.IS_FORMED;

public class OpticalMachines {

    // Expanded Data Access Hatches
    public static MachineDefinition ZPM_DATA_ACCESS_HATCH = null;
    public static MachineDefinition UV_DATA_ACCESS_HATCH = null;
    public static MachineDefinition UHV_DATA_ACCESS_HATCH = null;

    static {
        if (EFConfig.INSTANCE.Miscellaneous.expandedDataAccessHatches || GTCEu.isDataGen()) {
            ZPM_DATA_ACCESS_HATCH = ExtendedFeaturesRegister
                    .machine("zpm_data_access_hatch", (holder) -> new ExpandedDataHatchLogic(holder, ZPM, false) {
                        @Override
                        protected int getInventorySize() {
                            return 36;
                        }
                    })
                    .tier(ZPM)
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.DATA_ACCESS)
                    .modelProperty(IS_FORMED, false)
                    .tooltips(
                            Component.translatable("gtceu.machine.data_access_hatch.tooltip.0"),
                            Component.translatable("gtceu.machine.data_access_hatch.tooltip.1", 36),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .overlayTieredHullModel("expanded_data_access_hatch")
                    .register();

            UV_DATA_ACCESS_HATCH = ExtendedFeaturesRegister
                    .machine("uv_data_access_hatch", (holder) -> new ExpandedDataHatchLogic(holder, UV, false) {
                        @Override
                        protected int getInventorySize() {
                            return 49;
                        }
                    })
                    .tier(UV)
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.DATA_ACCESS)
                    .modelProperty(IS_FORMED, false)
                    .tooltips(
                            Component.translatable("gtceu.machine.data_access_hatch.tooltip.0"),
                            Component.translatable("gtceu.machine.data_access_hatch.tooltip.1", 49),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .overlayTieredHullModel("expanded_data_access_hatch")
                    .register();

            UHV_DATA_ACCESS_HATCH = ExtendedFeaturesRegister
                    .machine("uhv_data_access_hatch", (holder) -> new ExpandedDataHatchLogic(holder, UHV, false) {
                        @Override
                        protected int getInventorySize() {
                            return 64;
                        }
                    })
                    .tier(UHV)
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.DATA_ACCESS)
                    .modelProperty(IS_FORMED, false)
                    .tooltips(
                            Component.translatable("gtceu.machine.data_access_hatch.tooltip.0"),
                            Component.translatable("gtceu.machine.data_access_hatch.tooltip.1", 64),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .overlayTieredHullModel("expanded_data_access_hatch")
                    .register();
        }
    }

    // Wireless Optical Hatches
    public static MachineDefinition LUV_WIRELESS_TRANSMITTER = null;
    public static MachineDefinition LUV_WIRELESS_RECEIVER = null;

    public static MachineDefinition ZPM_WIRELESS_TRANSMITTER = null;
    public static MachineDefinition ZPM_WIRELESS_RECEIVER = null;

    public static MachineDefinition UV_WIRELESS_TRANSMITTER = null;
    public static MachineDefinition UV_WIRELESS_RECEIVER = null;

    static {
        if (EFConfig.INSTANCE.Wireless.wirelessUtils || GTCEu.isDataGen()) {
            LUV_WIRELESS_TRANSMITTER = registerHatch("luv_wireless_data_transmitter",
                    "LuV Wireless Transmission Hatch", LuV, true)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.range", 16),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.connections", 4),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.scan"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .register();

            LUV_WIRELESS_RECEIVER = registerHatch("luv_wireless_data_receiver",
                    "LuV Wireless Optical Reception Hatch", LuV, false)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.receiver"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .register();

            ZPM_WIRELESS_TRANSMITTER = registerHatch("zpm_wireless_data_transmitter",
                    "ZPM Wireless Transmission Hatch", ZPM, true)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.range", 24),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.connections", 8),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.scan"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .register();

            ZPM_WIRELESS_RECEIVER = registerHatch("zpm_wireless_data_receiver",
                    "ZPM Wireless Reception Hatch", ZPM, false)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.receiver"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .register();

            UV_WIRELESS_TRANSMITTER = registerHatch("uv_wireless_data_transmitter",
                    "UV Wireless Transmission Hatch", UV, true)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.range", 32),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.connections", 16),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.scan"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .register();

            UV_WIRELESS_RECEIVER = registerHatch("uv_wireless_data_receiver",
                    "UV Wireless Reception Hatch", UV, false)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.receiver"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .register();
        }
    }

    private static MachineBuilder<MachineDefinition, ?> registerHatch(String name, String displayName, int tier, boolean isTransmitter) {
        return ExtendedFeaturesRegister
                .machine(name, (holder) -> new WirelessOpticalDataHatchMachine(holder, isTransmitter, tier))
                .langValue(displayName)
                .tier(tier)
                .rotationState(RotationState.ALL)
                /*
                 * if -> IsTransmitter = true: Register a WIRELESS_OPTICAL_TRASMITTER
                 * Or else, IsTransmitter = false -> Register a WIRELESS_OPTICAL_RECEIVER
                 */
                .abilities(isTransmitter
                        ? WirelessAbilities.WIRELESS_OPTICAL_TRANSMITTER
                        : WirelessAbilities.WIRELESS_OPTICAL_RECEIVER)
                .overlayTieredHullModel(isTransmitter
                        ? "wireless_transmitter"
                        : "wireless_receiver"
                );
    }

    public static void init() {}
}
