package com.extendedfeatures.init.utils;

import com.extendedfeatures.CreativeTabs;
import com.extendedfeatures.client.integrations.Configuration.EFConfig;
import com.extendedfeatures.init.utils.internal.optical.*;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.*;
import com.gregtechceu.gtceu.api.machine.multiblock.*;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.client.util.TooltipHelper;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.research.DataBankMachine;

import net.minecraft.network.chat.Component;

import static com.extendedfeatures.ExtendedFeaturesCore.ExtendedFeaturesRegister;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties.IS_FORMED;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.DUMMY_RECIPES;

public class OpticalMachines {

    static {
        ExtendedFeaturesRegister.creativeModeTab(() -> CreativeTabs.OPTICAL_TAB);
    }

    // Expanded Data Access Hatches
    public static MachineDefinition ZPM_DATA_ACCESS_HATCH = null;
    public static MachineDefinition UV_DATA_ACCESS_HATCH = null;
    public static MachineDefinition UHV_DATA_ACCESS_HATCH = null;

    static {
        if (EFConfig.INSTANCE.OpticalMachines.ExpandedDataAccessHatches || GTCEu.isDataGen()) {
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

    // Wireless Optical T/R Hatches
    public static MachineDefinition LUV_WIRELESS_TRANSMITTER = null;
    public static MachineDefinition LUV_WIRELESS_RECEIVER = null;

    public static MachineDefinition ZPM_WIRELESS_TRANSMITTER = null;
    public static MachineDefinition ZPM_WIRELESS_RECEIVER = null;

    public static MachineDefinition UV_WIRELESS_TRANSMITTER = null;
    public static MachineDefinition UV_WIRELESS_RECEIVER = null;

    static {
        if (EFConfig.INSTANCE.OpticalMachines.WirelessOptical || GTCEu.isDataGen()) {
            LUV_WIRELESS_TRANSMITTER = registerHatch("luv_wireless_data_transmitter",
                    "LuV Wireless Transmission Hatch", LuV, true)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.range", 16),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.connections", 4),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.scan"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .overlayTieredHullModel("luv_transmissor")
                    .register();

            LUV_WIRELESS_RECEIVER = registerHatch("luv_wireless_data_receiver",
                    "LuV Wireless Optical Reception Hatch", LuV, false)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.receiver"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .overlayTieredHullModel("luv_receiver")
                    .register();

            ZPM_WIRELESS_TRANSMITTER = registerHatch("zpm_wireless_data_transmitter",
                    "ZPM Wireless Transmission Hatch", ZPM, true)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.range", 24),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.connections", 8),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.scan"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .overlayTieredHullModel("zpm_transmissor")
                    .register();

            ZPM_WIRELESS_RECEIVER = registerHatch("zpm_wireless_data_receiver",
                    "ZPM Wireless Reception Hatch", ZPM, false)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.receiver"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .overlayTieredHullModel("zpm_receiver")
                    .register();

            UV_WIRELESS_TRANSMITTER = registerHatch("uv_wireless_data_transmitter",
                    "UV Wireless Transmission Hatch", UV, true)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.range", 32),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.connections", 16),
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.scan"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .overlayTieredHullModel("uv_transmissor")
                    .register();

            UV_WIRELESS_RECEIVER = registerHatch("uv_wireless_data_receiver",
                    "UV Wireless Reception Hatch", UV, false)
                    .tooltips(
                            Component.translatable("extendedfeatures.machine.wireless_optical_hatch.tooltip.receiver"),
                            Component.translatable("gtceu.part_sharing.disabled"))
                    .overlayTieredHullModel("uv_receiver")
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
                        : WirelessAbilities.WIRELESS_OPTICAL_RECEIVER);
    }

    // Related Multiblocks
    public static MultiblockMachineDefinition EXPANDED_DATABANK = null;
    public static MultiblockMachineDefinition OPTICAL_TRANSMISSION_NETWORK = null;

    static {
        if (EFConfig.INSTANCE.OpticalMachines.ExpandedDatabank || GTCEu.isDataGen()) {
            EXPANDED_DATABANK = ExtendedFeaturesRegister
                    .multiblock("expanded_databank", DataBankMachine::new)
                    .tooltips(
                            Component.translatable("gtceu.machine.data_bank.tooltip.0"),
                            Component.translatable("gtceu.machine.data_bank.tooltip.1")
                    )
                    .tooltipBuilder((stack, list) -> list.add(
                            Component.translatable("extendedfeatures.expanded_databank_tootip.1")
                                    .append(Component.translatable("extendedfeatures.styled.tooltip.5")
                                            .withStyle(CustomTooltipStyles.IV_GRADIENT))))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(DUMMY_RECIPES)
                    .appearanceBlock(ADVANCED_COMPUTER_CASING)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("   BBBBB   ", "   B   B   ", "   B   B   ", "   B   B   ", "   B   B   ", "   BBBBB   ")
                            .aisle(" BBBDBDBBB ", "           ", "           ", "           ", "           ", " BBBDBDBBB ")
                            .aisle("BBDDDBDDDBB", "B         B", "B         B", "B         B", "B         B", "BBDDDBDDDBB")
                            .aisle("BDBBBBBBBDB", "  E E E E  ", "  E E E E  ", "  E E E E  ", "  E E E E  ", "BDBBBBBBBDB")
                            .aisle("BBDDDBDDDBB", "B         B", "B         B", "B         B", "B         B", "BBDDDBDDDBB")
                            .aisle(" BBBDBDBBB ", "           ", "           ", "           ", "           ", " BBBDBDBBB ")
                            .aisle("   BB@BB   ", "   B   B   ", "   B   B   ", "   B   B   ", "   B   B   ", "   BBBBB   ")
                            .where('@', controller(blocks(definition.get())))
                            .where(' ', any())
                            .where('D', blocks(COMPUTER_CASING.get()))
                            .where('B', blocks(ADVANCED_COMPUTER_CASING.get())
                                    .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(abilities(PartAbility.DATA_ACCESS).setMinGlobalLimited(1)
                                            .setMaxGlobalLimited(6)))
                            .where('E', abilities(PartAbility.OPTICAL_DATA_TRANSMISSION).setMaxGlobalLimited(16))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/hpca/advanced_computer_casing/top"),
                            GTCEu.id("block/multiblock/data_bank"))
                    .register();
        }
    }

    static {
        if (EFConfig.INSTANCE.OpticalMachines.OpticalTransmissionNetwork || GTCEu.isDataGen()) {
            OPTICAL_TRANSMISSION_NETWORK = ExtendedFeaturesRegister
                    .multiblock("optical_transmission_network", DataBankMachine::new)
                    .tooltips(
                            Component.translatable("extendedfeatures.cloud_transmission_database.tooltip.1"),
                            Component.translatable("extendedfeatures.cloud_transmission_database.tooltip.2"),
                            Component.translatable("extendedfeatures.cloud_transmission_database.tooltip.3")
                    )
                    .tooltipBuilder((stack, list) -> list.add(Component.translatable("extendedfeatures.cloud_transmission_database.tooltip.4")
                            .append(Component.translatable("extendedfeatures.styled.tooltip.4")
                                    .withStyle(TooltipHelper.RAINBOW_HSL_SLOW))))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(DUMMY_RECIPES)
                    .appearanceBlock(HIGH_POWER_CASING)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle(" CCCCC ", " C   C ", " C   C ", " C   C ", " CCCCC ")
                            .aisle("CCDEDCC", "CC E CC", "CC E CC", "CC E CC", "CCDEDCC")
                            .aisle("CDDEDDC", "       ", "       ", "       ", "CDDEDDC")
                            .aisle("CEEEEEC", " E E E ", " E H E ", " E E E ", "CEEEEEC")
                            .aisle("CDDEDDC", "       ", "       ", "       ", "CDDEDDC")
                            .aisle("CCDEDCC", "CC E CC", "CC @ CC", "CC E CC", "CCDEDCC")
                            .aisle(" CCCCC ", " C   C ", " C   C ", " C   C ", " CCCCC ")
                            .where('@', controller(blocks(definition.get())))
                            .where(' ', any())
                            .where('#', air())
                            .where('D', blocks(ADVANCED_COMPUTER_CASING.get()))
                            .where('C', blocks(COMPUTER_CASING.get()))
                            .where('E', blocks(HIGH_POWER_CASING.get())
                                    .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(abilities(PartAbility.DATA_ACCESS).setExactLimit(1)))
                            .where('H', abilities(WirelessAbilities.WIRELESS_OPTICAL_TRANSMITTER).setExactLimit(1))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/hpca/high_power_casing"),
                            GTCEu.id("block/multiblock/fusion_reactor"))
                    .register();
        }
    }

    public static void init() {}
}
