package com.argxment.extendedfeatures;

import com.argxment.extendedfeatures.init.Multiblocks;
import com.argxment.extendedfeatures.init.Items;
import com.argxment.extendedfeatures.client.RecipeTypes;

import com.tterrag.registrate.util.entry.RegistryEntry;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.*;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.api.sound.SoundEntry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExtendedFeaturesCore.MOD_ID)
@SuppressWarnings("removal")
public class ExtendedFeaturesCore {

    public static final String MOD_ID = "extendedfeatures";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final GTRegistrate ExtraFeaturesRegister = GTRegistrate.create(ExtendedFeaturesCore.MOD_ID);

    // Credits to Herr Jolo for making the creative tab!
    public static RegistryEntry<CreativeModeTab> EF_TAB = ExtraFeaturesRegister
            .defaultCreativeTab(ExtendedFeaturesCore.MOD_ID,
                    builder -> builder
                            .displayItems(
                                    new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(ExtendedFeaturesCore.MOD_ID,
                                            ExtraFeaturesRegister))
                            .title(ExtraFeaturesRegister.addLang("itemGroup", ExtendedFeaturesCore.id("creative_tab"),
                                    "GTCEu Extended Features"))
                            .icon(GTBlocks.ANTIMATTER_HAZARD_SIGN_BLOCK::asStack)
                            .build())
            .register();

    public ExtendedFeaturesCore() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Items.register(modEventBus);

        ExtraFeaturesRegister.registerRegistrate();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::onBuildCreativeTab);

        modEventBus.addListener(this::addMaterialRegistries);
        modEventBus.addListener(this::addMaterials);
        modEventBus.addListener(this::modifyMaterials);

        modEventBus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
        modEventBus.addGenericListener(MachineDefinition.class, this::registerMachines);
        modEventBus.addGenericListener(SoundEntry.class, this::registerSounds);

        MinecraftForge.EVENT_BUS.register(this);

    }


    public static ResourceLocation id(String path) {
        return new ResourceLocation(ExtendedFeaturesCore.MOD_ID, path);
    }

    private void onBuildCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (GTCEuAPI.isHighTier()) return;
        if (!event.getTabKey().location().getNamespace().equals(ExtendedFeaturesCore.MOD_ID)) return;

        Items.getHighTierCircuits().forEach(entry ->
                event.getEntries().remove(entry.asStack())
        );
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LOGGER.info("Hello from common setup! This is *after* registries are done, so we can do this:");
            LOGGER.info("Look, I found a {}!", net.minecraft.world.item.Items.DIAMOND);
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("i hate java!!!!!!");
    }


    private void addMaterialRegistries(MaterialRegistryEvent event) {
        GTCEuAPI.materialManager.createRegistry(ExtendedFeaturesCore.MOD_ID);
    }

    private void addMaterials(MaterialEvent event) {
        // CustomMaterials.init();
    }

    private void modifyMaterials(PostMaterialEvent event) {
        // CustomMaterials.modify();
    }

    private void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        RecipeTypes.init();
    }

    private void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        Multiblocks.init();
    }

    public void registerSounds(GTCEuAPI.RegisterEvent<ResourceLocation, SoundEntry> event) {
        // CustomSounds.init();
    }
}