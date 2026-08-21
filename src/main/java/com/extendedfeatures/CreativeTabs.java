package com.extendedfeatures;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;

import static com.extendedfeatures.ExtendedFeaturesCore.ExtendedFeaturesRegister;
import static com.extendedfeatures.init.utils.Machines.*;
import static com.extendedfeatures.init.utils.Multiblocks.*;
import static com.extendedfeatures.init.utils.UniversalCircuits.*;
import static com.gregtechceu.gtceu.api.GTValues.*;

public class CreativeTabs {

    // Credits to Herr Jolo for making the first creative tab!

    public static RegistryEntry<CreativeModeTab> MULTIBLOCKS_TAB = ExtendedFeaturesRegister
            .defaultCreativeTab(ExtendedFeaturesCore.MOD_ID + "_multiblocks",
                    builder -> builder
                            .displayItems(
                                    new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(
                                            ExtendedFeaturesCore.MOD_ID + "_multiblocks",
                                            ExtendedFeaturesRegister)
                            )
                            .title(ExtendedFeaturesRegister.addLang(
                                    "itemGroup", ExtendedFeaturesCore.id("creative_tab_1"),
                                    "Ext. Features: Multiblocks")
                            )
                            .icon(DISASSEMBLER::asStack)
                            .build()
            )
            .register();

    public static RegistryEntry<CreativeModeTab> CIRCUITS_TAB = ExtendedFeaturesRegister
            .defaultCreativeTab(ExtendedFeaturesCore.MOD_ID + "_circuits",
                    builder -> builder
                            .displayItems(
                                    new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(
                                            ExtendedFeaturesCore.MOD_ID + "_circuits",
                                            ExtendedFeaturesRegister)
                            )
                            .title(ExtendedFeaturesRegister.addLang(
                                    "itemGroup", ExtendedFeaturesCore.id("creative_tab_2"),
                                    "Ext. Features: Universal Circuits"))
                            .icon(() -> UNIVERSAL_CIRCUITS[IV].asStack())
                            .build()
            )
            .register();

    public static RegistryEntry<CreativeModeTab> MACHINES_TAB = ExtendedFeaturesRegister
            .defaultCreativeTab(ExtendedFeaturesCore.MOD_ID + "_machines",
                    builder -> builder
                            .displayItems(
                                    new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(
                                            ExtendedFeaturesCore.MOD_ID + "_machines",
                                            ExtendedFeaturesRegister))
                            .title(ExtendedFeaturesRegister.addLang(
                                    "itemGroup", ExtendedFeaturesCore.id("creative_tab_3"),
                                    "Ext. Features: Machines")
                            )
                            .icon(UV_DATA_ACCESS_HATCH::asStack)
                            .build()
            )
            .register();

    public static RegistryEntry<CreativeModeTab> HIGH_AMP_MACHINES = ExtendedFeaturesRegister
            .defaultCreativeTab(ExtendedFeaturesCore.MOD_ID + "_high_amp_machines",
                    builder -> builder
                            .displayItems(
                                    new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(
                                            ExtendedFeaturesCore.MOD_ID + "_high_amp_machines",
                                            ExtendedFeaturesRegister))
                            .title(ExtendedFeaturesRegister.addLang(
                                    "itemGroup", ExtendedFeaturesCore.id("creative_tab_4"),
                                    "Ext. Features: High Amp Machines")
                            )
                            .icon(() -> GTMachines.LASER_INPUT_HATCH_4096[UV].asStack())
                            .build()
            )
            .register();

}