package com.extendedfeatures.init.utils.internal.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.extendedfeatures.client.RecipeTypes.CHEMICAL_REDUCTION;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class ChemicalSkips {

    public static void init(Consumer<FinishedRecipe> provider) {

        // Platline
        CHEMICAL_REDUCTION.recipeBuilder("platinum_processing_line")
                .inputItems(dust, PlatinumGroupSludge, 16)
                .inputFluids(AquaRegia.getFluid(2000))
                .outputItems(dust, Platinum, 2)
                .outputItems(dust, Palladium, 2)
                .outputItems(dust, Ruthenium, 1)
                .outputItems(dust, Rhodium, 1)
                .outputItems(dust, Osmium, 1)
                .outputItems(dust, Iridium, 1)
                .outputFluids(NitricAcid.getFluid(2000))
                .outputFluids(HydrochloricAcid.getFluid(1000))
                .circuitMeta(1)
                .EUt(GTValues.VA[GTValues.LuV])
                .duration(700)
                .save(provider);

        // Naqline
        CHEMICAL_REDUCTION.recipeBuilder("naquadah_processing_line")
                .inputItems(dust, Naquadah, 12)
                .inputFluids(FluoroantimonicAcid.getFluid(4000))
                .outputItems(dust, NaquadahEnriched, 2)
                .outputItems(dust, Naquadria, 1)
                .outputItems(dust, Trinium, 2)
                .outputItems(dust, Antimony, 4)
                .outputItems(dust, Indium, 3)
                .outputFluids(Hydrogen.getFluid(2000))
                .outputFluids(Fluorine.getFluid(8000))
                .circuitMeta(4)
                .EUt(GTValues.VA[GTValues.LuV])
                .duration(2000)
                .save(provider);
    }
}
