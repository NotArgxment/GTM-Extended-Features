package com.argxment.extendedfeatures.init.recipes;

import com.gregtechceu.gtceu.api.GTValues;

import com.gregtechceu.gtceu.data.recipe.CustomTags;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.argxment.extendedfeatures.init.Items.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;

public class ItemRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {

        ASSEMBLER_RECIPES.recipeBuilder("ulv_universal_circuit")
                .inputItems(CustomTags.ULV_CIRCUITS)
                .outputItems(ULV_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("lv_universal_circuit")
                .inputItems(CustomTags.LV_CIRCUITS)
                .outputItems(LV_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("mv_universal_circuit")
                .inputItems(CustomTags.MV_CIRCUITS)
                .outputItems(MV_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("hv_universal_circuit")
                .inputItems(CustomTags.HV_CIRCUITS)
                .outputItems(HV_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("ev_universal_circuit")
                .inputItems(CustomTags.EV_CIRCUITS)
                .outputItems(EV_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("iv_universal_circuit")
                .inputItems(CustomTags.IV_CIRCUITS)
                .outputItems(IV_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("luv_universal_circuit")
                .inputItems(CustomTags.LuV_CIRCUITS)
                .outputItems(LuV_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("zpm_universal_circuit")
                .inputItems(CustomTags.ZPM_CIRCUITS)
                .outputItems(ZPM_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("uv_universal_circuit")
                .inputItems(CustomTags.UV_CIRCUITS)
                .outputItems(UV_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("uhv_universal_circuit")
                .inputItems(CustomTags.UHV_CIRCUITS)
                .outputItems(UHV_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("uev_universal_circuit")
                .inputItems(CustomTags.UEV_CIRCUITS)
                .outputItems(UIV_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("uiv_universal_circuit")
                .inputItems(CustomTags.UIV_CIRCUITS)
                .outputItems(UEV_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        /* Soon™

        ASSEMBLER_RECIPES.recipeBuilder("uxv_universal_circuit")
                .inputItems(CustomTags.UXV_CIRCUITS)
                .outputItems(UXV_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("opv_universal_circuit")
                .inputItems(CustomTags.OpV_CIRCUITS)
                .outputItems(OpV_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("max_universal_circuit")
                .inputItems(CustomTags.MAX_CIRCUITS)
                .outputItems(MAX_UNIVERSAL_CIRCUIT)
                .duration(20)
                .EUt(GTValues.VA[GTValues.LV])
                .circuitMeta(10)
                .save(provider);
         */
    }
}
