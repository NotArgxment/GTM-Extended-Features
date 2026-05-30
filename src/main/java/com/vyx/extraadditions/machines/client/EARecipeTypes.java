package com.vyx.extraadditions.machines.client;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.vyx.extraadditions.ExtraAdditionsCore;

public class EARecipeTypes {

    public static GTRecipeType ROCK_PROCESSING;

    public static void init() {
        ROCK_PROCESSING = new GTRecipeType(
                ExtraAdditionsCore.id("rock_processing_facility"),
                "rock_processing_facility"
        )
                .setMaxIOSize(3, 6, 0, 6)
                .setEUIO(IO.IN);

        GTRegistries.RECIPE_TYPES.register(
                ExtraAdditionsCore.id("rock_processing_facility"),
                ROCK_PROCESSING
        );
    }
}