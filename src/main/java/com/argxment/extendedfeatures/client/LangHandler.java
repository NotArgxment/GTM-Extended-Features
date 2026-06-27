package com.argxment.extendedfeatures.client;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.data.lang.LangHandler.replace;

public class LangHandler {

    public static void init(RegistrateLangProvider provider) {
        Names(provider);
        Tooltips(provider);
    }

    private static void Names(RegistrateLangProvider provider) {
        replace(provider, "block.extendedfeatures.robust_alloy_materializer", "Robust Alloy Materializer [RAM]");
        replace(provider, "block.extendedfeatures.advanced_cracking_unit", "Advanced Cracking Unit [ACU]");
        replace(provider, "block.extendedfeatures.enlarged_reaction_chamber", "Enlarged Reaction Chamber [ERC]");
        replace(provider, "block.extendedfeatures.large_pyrolysis_unit", "Large Pyrolysis Unit [LPU]");
        replace(provider, "block.extendedfeatures.compact_assembly_line", "Compact Assembly Line [CAL]");
        replace(provider, "block.extendedfeatures.rock_processing_facility", "Rock Processing Facility [RPF]");
        replace(provider, "block.extendedfeatures.industrial_greenhouse", "Industrial Greenhouse [IGh]");
        replace(provider, "block.extendedfeatures.tree_growing_chamber", "Tree Growing Chamber [TGCh]");
        replace(provider, "block.extendedfeatures.luv_advanced_fusion_reactor", "§dLuV §rAdvanced Fusion Reactor");
        replace(provider, "block.extendedfeatures.zpm_advanced_fusion_reactor", "§cZPM §rAdvanced Fusion Reactor");
        replace(provider, "block.extendedfeatures.uv_advanced_fusion_reactor", "§3UV §rAdvanced Fusion Reactor");
        replace(provider, "extendedfeatures.greenhouse_machine", "Greenhouse");
        replace(provider, "extendedfeatures.disassembler", "Disassembly");
        replace(provider, "extendedfeatures.rock_processing_facility", "Rock Processing");
    }

    private static void Tooltips(RegistrateLangProvider provider) {
        provider.add("extendedfeatures.machine.compact_assembly_line.tooltip.0", "§7This machine is able to perform the work of a regular Assembly Line without requiring §9Ordered Inputs");
        provider.add("extendedfeatures.machine.compact_assembly_line.tooltip.1", "§fOnly allows §bone §fenergy hatch and performs §b8 Recipes §fin parallel");

        provider.add("extendedfeatures.machine.enlarged_reaction_chamber.tooltip.0", "§fEven larger Black Box Reactor");
        provider.add("extendedfeatures.machine.enlarged_reaction_chamber.tooltip.1", "§fThis machine is an improved version of the Large Chemical reactor");

        provider.add("extendedfeatures.machine.rock_processing_facility.tooltip.0", "§7An all in one processing facility, turns the rocks you normally get from the rock breaker into their direct processed outputs");
        provider.add("extendedfeatures.machine.rock_processing_facility.tooltip.1", "§fAll recipes have §6Ranged Outputs");

        provider.add("extendedfeatures.machine.industrial_greenhouse.tooltip.0", "§7Designed to make wood generation easier");
        provider.add("extendedfeatures.machine.industrial_greenhouse.tooltip.1", "§fRuns §g8 Recipes §fin parallel");

        provider.add("extendedfeatures.machine.disassembler.tooltip.0", "§7Allows any type of §emachine/controller §7to be recycled, returning what was used for that recipe");
        provider.add("extendedfeatures.machine.disassembler.tooltip.1", "§fEach tier of machine has to be processed using its §grespective energy hatch");

        provider.add("extendedfeatures.multiblock.luv_advanced_fusion_reactor.tooltip.0", "§fRuns");
        provider.add("extendedfeatures.multiblock.luv_advanced_fusion_reactor.tooltip.1", " 4 Parallels");

        provider.add("extendedfeatures.multiblock.zpm_advanced_fusion_reactor.tooltip.0", "§fRuns");
        provider.add("extendedfeatures.multiblock.zpm_advanced_fusion_reactor.tooltip.1", " 8 Parallels");

        provider.add("extendedfeatures.multiblock.uv_advanced_fusion_reactor.tooltip.0", "§fRuns");
        provider.add("extendedfeatures.multiblock.uv_advanced_fusion_reactor.tooltip.1", " 16 Parallels");

        provider.add("extendedfeatures.fancytooltip.tooltip.0", "§fAllows");
        provider.add("extendedfeatures.fancytooltip.tooltip.1", " Laser Hatches ");
        provider.add("extendedfeatures.fancytooltip.tooltip.2", "§fand");
        provider.add("extendedfeatures.fancytooltip.tooltip.3", " Parallel Hatches");
    }
}