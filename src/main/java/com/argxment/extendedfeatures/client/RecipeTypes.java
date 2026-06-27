package com.argxment.extendedfeatures.client;

import com.argxment.extendedfeatures.ExtendedFeaturesCore;
import com.argxment.extendedfeatures.client.disassembler.DisassemblerRecipeLogic;

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

    public static GTRecipeType ROCK_PROCESSING;
    public static GTRecipeType CHEMICAL_REDUCTION;
    public static GTRecipeType AIR_REPROCESSING;
    public static GTRecipeType DISASSEMBLER;
    public static GTRecipeType GREENHOUSE;

    public static void init() {

        ROCK_PROCESSING = register("rock_processing_facility", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(1, 6, 1, 3)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.MACERATOR);

        CHEMICAL_REDUCTION = register("chemical_skipping", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(6, 6, 6, 6)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.CHEMICAL);

        AIR_REPROCESSING = register("air_reprocessor", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(0, 9, 1, 0)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.BATH);

        GREENHOUSE = register("greenhouse_machine", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(3, 6, 2, 2)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.MIXER);

        DISASSEMBLER = register("disassembler_machine", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(1, 9, 0, 0)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.ASSEMBLER)
                .addCustomRecipeLogic(DisassemblerRecipeLogic.INSTANCE);
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