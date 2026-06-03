package com.vyx.extraadditions.machines.client;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.register;

public class EARecipeTypes {

    public static GTRecipeType ROCK_PROCESSING;

    public static void init() {
    // credits to Herr Jolo for fixing it - wasn't loading because of wrong registry type
        ROCK_PROCESSING = register("rock_processing", MULTIBLOCK)
                .setEUIO(IO.IN)
                .setMaxIOSize(1, 9, 1, 3)
                .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTSoundEntries.MACERATOR);
    }
}