package com.argxment.extendedfeatures.init.recipes;

import com.argxment.extendedfeatures.init.Items;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;

import com.gregtechceu.gtceu.data.recipe.CustomTags;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;

public class ItemRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {

        ASSEMBLER_RECIPES.recipeBuilder("ulv_universal_circuit")
                .inputItems(CustomTags.ULV_CIRCUITS)
                .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.ULV])
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("lv_universal_circuit")
                .inputItems(CustomTags.LV_CIRCUITS)
                .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.LV])
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("mv_universal_circuit")
                .inputItems(CustomTags.MV_CIRCUITS)
                .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.MV])
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("hv_universal_circuit")
                .inputItems(CustomTags.HV_CIRCUITS)
                .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.HV])
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("ev_universal_circuit")
                .inputItems(CustomTags.EV_CIRCUITS)
                .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.EV])
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("iv_universal_circuit")
                .inputItems(CustomTags.IV_CIRCUITS)
                .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.IV])
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("luv_universal_circuit")
                .inputItems(CustomTags.LuV_CIRCUITS)
                .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.LuV])
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("zpm_universal_circuit")
                .inputItems(CustomTags.ZPM_CIRCUITS)
                .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.ZPM])
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("uv_universal_circuit")
                .inputItems(CustomTags.UV_CIRCUITS)
                .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.UV])
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        if (GTCEuAPI.isHighTier()) {
            ASSEMBLER_RECIPES.recipeBuilder("uhv_universal_circuit")
                    .inputItems(CustomTags.UHV_CIRCUITS)
                    .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.UHV])
                    .duration(20)
                    .EUt(GTValues.VA[GTValues.LV])
                    .circuitMeta(10)
                    .save(provider);

            ASSEMBLER_RECIPES.recipeBuilder("uev_universal_circuit")
                    .inputItems(CustomTags.UEV_CIRCUITS)
                    .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.UEV])
                    .duration(20)
                    .EUt(GTValues.VA[GTValues.LV])
                    .circuitMeta(10)
                    .save(provider);

            ASSEMBLER_RECIPES.recipeBuilder("uiv_universal_circuit")
                    .inputItems(CustomTags.UIV_CIRCUITS)
                    .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.UIV])
                    .duration(20)
                    .EUt(GTValues.VA[GTValues.LV])
                    .circuitMeta(10)
                    .save(provider);

            ASSEMBLER_RECIPES.recipeBuilder("uxv_universal_circuit")
                    .inputItems(CustomTags.UXV_CIRCUITS)
                    .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.UXV])
                    .duration(20)
                    .EUt(GTValues.VA[GTValues.LV])
                    .circuitMeta(10)
                    .save(provider);

            ASSEMBLER_RECIPES.recipeBuilder("opv_universal_circuit")
                    .inputItems(CustomTags.OpV_CIRCUITS)
                    .outputItems(Items.UNIVERSAL_CIRCUITS[GTValues.OpV])
                    .duration(20)
                    .EUt(GTValues.VA[GTValues.LV])
                    .circuitMeta(10)
                    .save(provider);
        }
    }
}