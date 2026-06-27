package com.argxment.extendedfeatures.init.recipes;

import com.gregtechceu.gtceu.api.GTValues;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.argxment.extendedfeatures.client.RecipeTypes.ROCK_PROCESSING;

import static com.gregtechceu.gtceu.api.GTValues.L;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class MultiblocksRecipes {

    /**
     * Actual controller recipes are supposed to be made by modpack developers
     * This file will contain only recipes for the custom recipes types
     */

    public static void init(Consumer<FinishedRecipe> provider) {

        ROCK_PROCESSING.recipeBuilder("deepslate_processing")
                .inputItems(new ItemStack(Blocks.DEEPSLATE), 1)
                .inputFluids(Lubricant.getFluid(L * 4))
                .outputItems(dust, Potassium, 1)
                .outputItems(dust, Magnesium, 1)
                .outputItems(dust, Aluminium, 1)
                .outputItems(dust, Silicon, 1)
                .outputFluids(Fluorine.getFluid(L * 2))
                .outputFluids(Oxygen.getFluid(L * 4))
                .duration(2500)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        ROCK_PROCESSING.recipeBuilder("andesite_processing")
                .inputItems(new ItemStack(Blocks.ANDESITE), 1)
                .inputFluids(Lubricant.getFluid(L * 4))
                .outputItems(dust, Magnesium, 1)
                .outputItems(dust, Silicon, 1)
                .outputFluids(Hydrogen.getFluid(L * 2))
                .outputFluids(Oxygen.getFluid(L * 4))
                .duration(2500)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        ROCK_PROCESSING.recipeBuilder("diorite_processing")
                .inputItems(new ItemStack(Blocks.DIORITE), 1)
                .inputFluids(Lubricant.getFluid(L * 4))
                .outputItems(dust, Sodium, 1)
                .outputItems(dust, Sulfur, 1)
                .outputFluids(Water.getFluid(L * 4))
                .outputFluids(Oxygen.getFluid(L * 2))
                .duration(2500)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);

        ROCK_PROCESSING.recipeBuilder("obsidian_processing")
                .inputItems(new ItemStack(Blocks.OBSIDIAN), 1)
                .inputFluids(Lubricant.getFluid(L * 4))
                .outputItems(dust, Magnesium, 1)
                .outputItems(dust, Iron, 1)
                .outputItems(dust, Silicon, 1)
                .outputFluids(Oxygen.getFluid(L * 3))
                .duration(2500)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);
    }

    private static int getAmount() {
        return 1000;
    }
}
