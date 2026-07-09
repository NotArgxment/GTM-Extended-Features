package com.argxment.extendedfeatures.client.init;

import com.argxment.extendedfeatures.client.DataHatch;
import com.argxment.extendedfeatures.client.config.EFConfig;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;

import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import static com.argxment.extendedfeatures.ExtendedFeaturesCore.ExtendedFeaturesRegister;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties.IS_FORMED;

public class Machines {

    // Expanded Data Access Hatches

    public static MachineDefinition ZPM_DATA_ACCESS_HATCH = null;
    public static MachineDefinition UV_DATA_ACCESS_HATCH = null;
    public static MachineDefinition UHV_DATA_ACCESS_HATCH = null;

    static {
        if (EFConfig.INSTANCE.features.expandedDataAccessHatches || GTCEu.isDataGen()) {
            ZPM_DATA_ACCESS_HATCH = ExtendedFeaturesRegister
                    .machine("zpm_data_access_hatch", (holder) -> new DataHatch(holder, ZPM, false) {

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
                            Component.translatable("gtceu.machine.data_access_hatch.tooltip.1", 36))
                    .overlayTieredHullModel("expanded_data_access_hatch")
                    .register();

            UV_DATA_ACCESS_HATCH = ExtendedFeaturesRegister
                    .machine("uv_data_access_hatch", (holder) -> new DataHatch(holder, UV, false) {

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
                            Component.translatable("gtceu.machine.data_access_hatch.tooltip.1", 49))
                    .overlayTieredHullModel("expanded_data_access_hatch")
                    .register();

            UHV_DATA_ACCESS_HATCH = ExtendedFeaturesRegister
                    .machine("uhv_data_access_hatch", (holder) -> new DataHatch(holder, UHV, false) {

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
                            Component.translatable("gtceu.machine.data_access_hatch.tooltip.1", 64))
                    .overlayTieredHullModel("expanded_data_access_hatch")
                    .register();
        }
    }

    public static void init() {}
}