package com.argxment.extraadditions.init.recipes;

import com.gregtechceu.gtceu.api.GTValues;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.argxment.extraadditions.init.EARecipeTypes.ROCK_PROCESSING;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class EAMultisRecipes {

    /*
     * Actual controller recipes are supposed to be made by modpack developers
     * This file will contain only recipes for the custom recipes types
    */

    public static void init(Consumer<FinishedRecipe> provider) {

        ROCK_PROCESSING.recipeBuilder("deepslate_processing")
                .inputItems(new ItemStack(Blocks.DEEPSLATE), 256)
                .perTick(true)
                    .inputFluids(DrillingFluid.getFluid(5))
                .perTick(false)
                .outputItemsRanged(dust, Potassium, UniformInt.of(1,128))
                .outputItemsRanged(dust, Magnesium, UniformInt.of(1,128))
                .outputItemsRanged(dust, Aluminium, UniformInt.of(1,128))
                .outputItemsRanged(dust, Silicon, UniformInt.of(1,128))
                .outputFluidsRanged(
                        new FluidStack(Fluorine.getFluid(), 1000),
                            UniformInt.of(1000, 16000)
                )
                .outputFluidsRanged(
                        new FluidStack(Oxygen.getFluid(), 1000),
                            UniformInt.of(1000, 16000)
                )
                .duration(5000)
                .EUt(GTValues.VA[GTValues.LuV])
                .save(provider);

        ROCK_PROCESSING.recipeBuilder("andesite_processing")
                .inputItems(new ItemStack(Blocks.ANDESITE), 256)
                .perTick(true)
                    .inputFluids(DrillingFluid.getFluid(5))
                .perTick(false)
                .outputItemsRanged(dust, Magnesium, UniformInt.of(1,128))
                .outputItemsRanged(dust, Silicon, UniformInt.of(1,128))
                .outputFluidsRanged(
                        new FluidStack(Hydrogen.getFluid(), 1000),
                        UniformInt.of(1000, 16000)
                )
                .outputFluidsRanged(
                        new FluidStack(Oxygen.getFluid(), 1000),
                        UniformInt.of(1000, 16000)
                )
                .duration(5000)
                .EUt(GTValues.VA[GTValues.LuV])
                .save(provider);

        ROCK_PROCESSING.recipeBuilder("diorite_processing")
                .inputItems(new ItemStack(Blocks.DIORITE), 256)
                .perTick(true)
                    .inputFluids(DrillingFluid.getFluid(5))
                .perTick(false)
                .outputItemsRanged(dust, Sodium, UniformInt.of(1,96))
                .outputItemsRanged(dust, Sulfur, UniformInt.of(1,64))
                .outputFluidsRanged(
                        new FluidStack(Water.getFluid(), 1000),
                        UniformInt.of(1000, 64000)
                )
                .outputFluidsRanged(
                        new FluidStack(Oxygen.getFluid(), 1000),
                        UniformInt.of(1000, 16000)
                )
                .duration(5000)
                .EUt(GTValues.VA[GTValues.LuV])
                .save(provider);

        ROCK_PROCESSING.recipeBuilder("obsidian_processing")
                .inputItems(new ItemStack(Blocks.OBSIDIAN), 256)
                .perTick(true)
                    .inputFluids(DrillingFluid.getFluid(5))
                .perTick(false)
                .outputItemsRanged(dust, Magnesium, UniformInt.of(1,96))
                .outputItemsRanged(dust, Iron, UniformInt.of(1,96))
                .outputItemsRanged(dust, Silicon, UniformInt.of(1,64))
                .outputFluidsRanged(
                        new FluidStack(Oxygen.getFluid(), 1000),
                        UniformInt.of(1000, 16000)
                )
                .duration(5000)
                .EUt(GTValues.VA[GTValues.LuV])
                .save(provider);
    }
}
