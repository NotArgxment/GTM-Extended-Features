package com.vyx.extraadditions;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.vyx.extraadditions.machines.EAMultis;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.*;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.api.sound.SoundEntry;

// import com.vyx.extraadditions.machines.client.parallel.ExtraParaHatches;
import com.vyx.extraadditions.machines.EARecipes;
import com.vyx.extraadditions.machines.client.EARecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExtraAdditionsCore.MOD_ID)
@SuppressWarnings("removal")
public class ExtraAdditionsCore {

    public static final String MOD_ID = "extraadditions";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final GTRegistrate EXTRA_ADDITIONS_REGISTRATE = GTRegistrate.create(ExtraAdditionsCore.MOD_ID);

    // Credits to Herr Jolo for making the creative tab!
    public static RegistryEntry<CreativeModeTab> EA_TAB = EXTRA_ADDITIONS_REGISTRATE
            .defaultCreativeTab(ExtraAdditionsCore.MOD_ID,
                    builder -> builder
                            .displayItems(
                                    new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(ExtraAdditionsCore.MOD_ID,
                                            EXTRA_ADDITIONS_REGISTRATE))
                            .title(EXTRA_ADDITIONS_REGISTRATE.addLang("itemGroup", ExtraAdditionsCore.id("creative_tab"),
                                    "GT:EA Multiblocks"))
                            .icon(GTBlocks.ANTIMATTER_HAZARD_SIGN_BLOCK::asStack)
                            .build())
            .register();

    public ExtraAdditionsCore() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EXTRA_ADDITIONS_REGISTRATE.registerRegistrate();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        modEventBus.addListener(this::addMaterialRegistries);
        modEventBus.addListener(this::addMaterials);
        modEventBus.addListener(this::modifyMaterials);

        modEventBus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
        modEventBus.addGenericListener(MachineDefinition.class, this::registerMachines);
        modEventBus.addGenericListener(SoundEntry.class, this::registerSounds);

        // Most other events are fired on Forge's bus.
        // If we want to use annotations to register event listeners,
        // we need to register our object like this!
        MinecraftForge.EVENT_BUS.register(this);

    }

    /**
     * Create a ResourceLocation in the format "modid:path"
     *
     * @param path
     * @return ResourceLocation with the namespace of your mod
     */
    public static ResourceLocation id(String path) {
        return new ResourceLocation(ExtraAdditionsCore.MOD_ID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LOGGER.info("Hello from common setup! This is *after* registries are done, so we can do this:");
            LOGGER.info("Look, I found a {}!", Items.DIAMOND);
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("i hate java!!!!!!");
    }

    /**
     * Create a material manager for your mod using GT's API.
     * You MUST have this if you have custom materials.
     * Remember to register them not to GT's namespace, but your own.
     *
     * @param event
     */
    private void addMaterialRegistries(MaterialRegistryEvent event) {
        GTCEuAPI.materialManager.createRegistry(ExtraAdditionsCore.MOD_ID);
    }

    /**
     * You will also need this for registering custom materials
     * Call init() from your Material class(es) here
     *
     * @param event
     */
    private void addMaterials(MaterialEvent event) {
        // CustomMaterials.init();
    }

    /**
     * (Optional) Used to modify pre-existing materials from GregTech
     *
     * @param event
     */
    private void modifyMaterials(PostMaterialEvent event) {
        // CustomMaterials.modify();
    }

    /**
     * Used to register your own new RecipeTypes.
     * Call init() from your RecipeType class(es) here
     *
     * @param event
     */
    private void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        EARecipeTypes.init();
    }

    /**
     * Used to register your own new machines.
     * Call init() from your Machine class(es) here
     *
     * @param event
     */
    private void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        EAMultis.init();
        // ExtraParaHatches.init();
    }

    /**
     * Used to register your own new sounds
     * Call init from your Sound class(es) here
     *
     * @param event
     */
    public void registerSounds(GTCEuAPI.RegisterEvent<ResourceLocation, SoundEntry> event) {
        // CustomSounds.init();
    }
}
