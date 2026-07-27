package com.extendedfeatures.client;

import com.extendedfeatures.ExtendedFeaturesCore;
import com.extendedfeatures.init.utils.internal.disassembler.DisassemblerRecipeLogic;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeSerializer;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;

public class RecipeTypes {

    public static GTRecipeType ROCK_PROCESSING_RECIPES;
    public static GTRecipeType DISASSEMBLER;
    public static GTRecipeType GREENHOUSE_WOOD;
    public static GTRecipeType GREENHOUSE_CROPS;
    public static GTRecipeType CHEMICAL_REDUCTION;
    public static GTRecipeType AIR_REPROCESSING;
    public static GTRecipeType OIL_REFINERY;
    public static GTRecipeType AIR_COLLECTOR;

    public static void init() {
        ROCK_PROCESSING_RECIPES = register("rock_processing_plant", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(1, 6, 1, 3)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.MACERATOR);

        GREENHOUSE_WOOD = register("greenhouse_wood_recipes", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(3, 3, 1, 0)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.MIXER);

        GREENHOUSE_CROPS = register("greenhouse_crop_recipes", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(3, 3, 1, 0)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.MIXER);

        DISASSEMBLER = register("disassembler_machine", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(1, 9, 0, 0)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.ASSEMBLER)
                .addCustomRecipeLogic(DisassemblerRecipeLogic.INSTANCE);

        CHEMICAL_REDUCTION = register("chemical_skips", MULTIBLOCK)
                 .setEUIO(IO.IN)
                 .setMaxIOSize(6, 6, 6, 6)
                 .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                 .setSound(GTSoundEntries.CHEMICAL);

        AIR_REPROCESSING = register("air_reprocessor", MULTIBLOCK)
            .setEUIO(IO.IN)
            .setMaxIOSize(0, 9, 1, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BATH);

        OIL_REFINERY = register("oil_refinery_machine", MULTIBLOCK)
            .setEUIO(IO.IN)
            .setMaxIOSize(0, 6, 6, 6)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL);

        AIR_COLLECTOR = register("air_collection", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(1, 0, 0, 1)
                .setProgressBar(GuiTextures.CIRCUIT_OVERLAY, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.COOLING);

    }

    public static GTRecipeType register(String name, String group, RecipeType<?>... proxyRecipes) {
        ResourceLocation id = ExtendedFeaturesCore.id(name);

        var recipeType = new GTRecipeType(id, group, proxyRecipes);
        GTRegistries.register(BuiltInRegistries.RECIPE_TYPE, id, recipeType);
        GTRegistries.register(BuiltInRegistries.RECIPE_SERIALIZER, id, new GTRecipeSerializer());
        GTRegistries.RECIPE_TYPES.register(id, recipeType);

        return recipeType;
    }
}
