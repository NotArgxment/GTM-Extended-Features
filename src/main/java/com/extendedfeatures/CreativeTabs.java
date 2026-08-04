package com.extendedfeatures;

import com.extendedfeatures.init.utils.*;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;

import java.util.*;

import static com.extendedfeatures.ExtendedFeaturesCore.ExtendedFeaturesRegister;
import static com.extendedfeatures.init.utils.UniversalCircuits.UNIVERSAL_CIRCUITS;

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
                            .icon(Multiblocks.DISASSEMBLER::asStack)
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
                            .icon(() -> UNIVERSAL_CIRCUITS[GTValues.IV].asStack())
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
                            .icon(Machines.UV_DATA_ACCESS_HATCH::asStack)
                            .build()
            )
            .register();

}