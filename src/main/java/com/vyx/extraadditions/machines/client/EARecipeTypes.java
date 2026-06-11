package com.vyx.extraadditions.machines.client;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.register;

public class EARecipeTypes {

    // credits to Herr Jolo for fixing it - wasn't loading because of wrong registry type

    public static GTRecipeType ROCK_PROCESSING;
    public static GTRecipeType CHEMICAL_REDUCTION;
    public static GTRecipeType AIR_REPROCESSING;

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
                .setSound(GTSoundEntries.MACERATOR);


    }
}