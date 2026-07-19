package com.extendedfeatures;

import com.extendedfeatures.client.RecipeTypes;
import com.extendedfeatures.client.integrations.Configuration.EFConfig;
import com.extendedfeatures.init.utils.*;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.*;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.api.sound.SoundEntry;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.*;

@Mod(ExtendedFeaturesCore.MOD_ID)
@SuppressWarnings("removal")
public class ExtendedFeaturesCore {

    public static final String MOD_ID = "extendedfeatures";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final GTRegistrate ExtendedFeaturesRegister = GTRegistrate.create(ExtendedFeaturesCore.MOD_ID);

    public ExtendedFeaturesCore() {
        EFConfig.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        UniversalCircuits.register(modEventBus);

        ExtendedFeaturesRegister.registerRegistrate();

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

        UniversalCircuits.getHighTierCircuits().forEach(entry -> event.getEntries().remove(entry.asStack()));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LOGGER.info("Hello from common setup! This is *after* registries are done, so we can do this:");
            LOGGER.info("Look, I found a {}!", net.minecraft.world.item.Items.DIAMOND);
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("AI THIS, AI THAT, GET THE FUCK OUT WITH AI EVERYWHERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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
        OpticalMachines.init();
    }

    public void registerSounds(GTCEuAPI.RegisterEvent<ResourceLocation, SoundEntry> event) {
        // CustomSounds.init();
    }
}
