package com.extendedfeatures.init.utils.internal.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.extendedfeatures.client.RecipeTypes.AIR_REPROCESSING;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class AirProcessing {

    public static void init(Consumer<FinishedRecipe> provider) {

        AIR_REPROCESSING.recipeBuilder("air")
                .inputFluids(Air.getFluid(64000))
                .outputFluids(Nitrogen.getFluid(32000))
                .outputFluids(Oxygen.getFluid(16000))
                .outputFluids(CarbonDioxide.getFluid(6000))
                .outputFluids(Helium.getFluid(4000))
                .outputFluids(Argon.getFluid(4000))
                .outputFluids(Ice.getFluid(2000))
                .duration(1000)
                .EUt(GTValues.VA[GTValues.LuV])
                .save(provider);

        AIR_REPROCESSING.recipeBuilder("nether_air")
                .inputFluids(NetherAir.getFluid(128000))
                .outputFluids(CarbonMonoxide.getFluid(64000))
                .outputFluids(CoalGas.getFluid(16000))
                .outputFluids(HydrogenSulfide.getFluid(8000))
                .outputFluids(SulfurDioxide.getFluid(8000))
                .outputFluids(Helium3.getFluid(4000))
                .outputFluids(Neon.getFluid(2500))
                .duration(1000)
                .EUt(GTValues.VA[GTValues.ZPM])
                .save(provider);

        AIR_REPROCESSING.recipeBuilder("ender_air")
                .inputFluids(EnderAir.getFluid(256000))
                .outputFluids(NitrogenDioxide.getFluid(128500))
                .outputFluids(Deuterium.getFluid(75000))
                .outputFluids(Helium.getFluid(16500))
                .outputFluids(Tritium.getFluid(16000))
                .outputFluids(Krypton.getFluid(8000))
                .outputFluids(Radon.getFluid(8000))
                .outputFluids(Xenon.getFluid(4000))
                .duration(1000)
                .EUt(GTValues.VA[GTValues.UV])
                .save(provider);

    }
}