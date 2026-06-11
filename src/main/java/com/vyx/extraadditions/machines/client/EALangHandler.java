package com.vyx.extraadditions.machines.client;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.data.lang.LangHandler.replace;

public class EALangHandler {

    public static void init(RegistrateLangProvider provider) {
        initControllerNames(provider);
        initControllerTooltips(provider);
    }

    private static void initControllerNames(RegistrateLangProvider provider) {
        replace(provider, "block.extraadditions.robust_alloy_materializer", "Robust Alloy Materializer [RAM]");
        replace(provider, "block.extraadditions.advanced_cracking_unit", "Advanced Cracking Unit [ACU]");
        replace(provider, "block.extraadditions.enlarged_reaction_chamber", "Enlarged Reaction Chamber [ERC]");
        replace(provider, "block.extraadditions.large_pyrolysis_unit", "Large Pyrolysis Unit [LPU]");
        replace(provider, "block.extraadditions.compact_assembly_line", "Compact Assembly Line [CAL]");
        replace(provider, "block.extraadditions.rock_processing_facility", "Rock Processing Facility [RPF]");
        replace(provider, "block.extraadditions.industrial_greenhouse", "Industrial Greenhouse [IGh]");
        replace(provider, "block.extraadditions.tree_growing_chamber", "Tree Growing Chamber [TGCh]");
    }

    private static void initControllerTooltips(RegistrateLangProvider provider) {
        provider.add("extraadditions.machine.compact_assembly_line.tooltip.0", "§7This machine is able to perform the work of a regular Assembly Line without requiring §6Ordered Inputs");
        provider.add("extraadditions.machine.compact_assembly_line.tooltip.1", "§7Only allows §bone §7energy hatch and performs §b4 Recipes in parallel");

        provider.add("extraadditions.machine.enlarged_reaction_chamber.tooltip", "§fEven larger Black Box Reactor");

        provider.add("extraadditions.machine.rock_processing_facility.tooltip.0", "§7An all in one processing facility, turns the rocks you normally get from the rock breaker into their direct processed outputs");
        provider.add("extraadditions.machine.rock_processing_facility.tooltip.1", "§7All recipes have §dRanged Outputs");

        provider.add("extraadditions.multiblock.luv_advanced_fusion_reactor.tooltip.0", "§fRuns");
        provider.add("extraadditions.multiblock.luv_advanced_fusion_reactor.tooltip.1", " 4 Parallels");

        provider.add("extraadditions.multiblock.zpm_advanced_fusion_reactor.tooltip.0", "§fRuns");
        provider.add("extraadditions.multiblock.zpm_advanced_fusion_reactor.tooltip.1", " 8 Parallels");

        provider.add("extraadditions.multiblock.uv_advanced_fusion_reactor.tooltip.0", "§fRuns");
        provider.add("extraadditions.multiblock.uv_advanced_fusion_reactor.tooltip.1", " 16 Parallels");

        provider.add("extraadditions.fancytooltip.tooltip.0", "§fAllows");
        provider.add("extraadditions.fancytooltip.tooltip.1", " Laser Hatches ");
        provider.add("extraadditions.fancytooltip.tooltip.2", "§fand");
        provider.add("extraadditions.fancytooltip.tooltip.3", " Parallel Hatches");

    }
}