package com.vyx.extraadditions.machines;

import com.gregtechceu.gtceu.api.GTValues;

import com.gregtechceu.gtceu.common.data.machines.GCYMMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.FUSION_COIL;
import static com.gregtechceu.gtceu.common.data.GTBlocks.SUPERCONDUCTING_COIL;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.vyx.extraadditions.machines.EAMultis.*;

public class EARecipes {

    public static void init(Consumer<FinishedRecipe> provider) {

/*
        //.circuitMeta(int 1 > 32)
        // placeholder, not the actual recipe
        ASSEMBLY_LINE_RECIPES.recipeBuilder("robust_alloy_materializer")
                .inputItems(GCYMMachines.BLAST_ALLOY_SMELTER, 4)
                .inputItems(CustomTags.ZPM_CIRCUITS, 8)
                .inputItems(plate, TungstenCarbide, 16)
                .inputItems(ENERGY_MODULE, 4)
                .inputItems(ADVANCED_SMD_DIODE, 32)
                .inputItems(ADVANCED_SMD_CAPACITOR, 32)
                .inputItems(ADVANCED_SMD_RESISTOR, 32)
                .inputItems(ADVANCED_SMD_TRANSISTOR, 32)
                .inputItems(ADVANCED_SMD_INDUCTOR, 32)
                // --------------------------------------------------
                .inputFluids(SolderingAlloy.getFluid(576))
                .outputItems(ROBUST_ALLOY_MATERIALIZER)
                .stationResearch(b -> b
                        .researchStack(GCYMMachines.BLAST_ALLOY_SMELTER.asStack())
                        .CWUt(128)
                        .EUt(GTValues.VA[GTValues.ZPM]))
                .EUt(GTValues.VA[GTValues.UV])
                .duration(1500)
                .save(provider);

// =====  Reactors  ================================================================
        ASSEMBLY_LINE_RECIPES.recipeBuilder("advanced_fusion_reactor_mk1")
                .inputItems(SUPERCONDUCTING_COIL.asStack(), 4)
                .inputItems(CustomTags.ZPM_CIRCUITS, 8)
                .inputItems(plateDouble, Plutonium241, 4)
                .inputItems(plateDouble, Osmiridium, 4)
                .inputItems(FIELD_GENERATOR_IV, 8)
                .inputItems(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 64)
                .inputItems(wireGtSingle, IndiumTinBariumTitaniumCuprate, 64)
                .inputFluids(SolderingAlloy.getFluid(L * 16))
                .inputFluids(NiobiumTitanium.getFluid(L * 16))
                .outputItems(ADVANCED_FUSION_REACTOR[LuV].asStack())
                .duration(800)
                .EUt(GTValues.VA[GTValues.LuV])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("advanced_fusion_reactor_mk2")
                .inputItems(FUSION_COIL.asStack(), 4)
                .inputItems(CustomTags.UV_CIRCUITS, 8)
                .inputItems(plateDouble, Naquadria, 4)
                .inputItems(plateDouble, Europium, 4)
                .inputItems(FIELD_GENERATOR_LuV, 8)
                .inputItems(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 64)
                .inputItems(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 32)
                .inputItems(wireGtSingle, UraniumRhodiumDinaquadide, 64)
                .inputFluids(SolderingAlloy.getFluid(L * 16))
                .inputFluids(VanadiumGallium.getFluid(L * 16))
                .outputItems(ADVANCED_FUSION_REACTOR[ZPM].asStack())
                .duration(2000)
                .EUt(GTValues.VA[GTValues.ZPM])
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("advanced_fusion_reactor_mk3")
                .inputItems(FUSION_COIL.asStack(), 4)
                .inputItems(CustomTags.UHV_CIRCUITS, 8)
                .inputItems(QUANTUM_STAR, 8)
                .inputItems(plateDouble, Americium, 8)
                .inputItems(FIELD_GENERATOR_ZPM, 8)
                .inputItems(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 64)
                .inputItems(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 64)
                .inputItems(wireGtSingle, EnrichedNaquadahTriniumEuropiumDuranide, 64)
                .inputFluids(SolderingAlloy.getFluid(L * 16))
                .inputFluids(YttriumBariumCuprate.getFluid(L * 16))
                .outputItems(ADVANCED_FUSION_REACTOR[UV].asStack())
                .duration(3000)
                .EUt(GTValues.VA[GTValues.UV])
                .save(provider);
// =====================================================================================
*/
    }
}
