package com.argxment.extendedfeatures.client;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.data.lang.LangHandler.replace;

public class LangHandler {

    public static void init(RegistrateLangProvider provider) {
        Names(provider);
        Tooltips(provider);
    }

    private static void Names(RegistrateLangProvider provider) {

        // Regular item/block lang
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
        replace(provider, "block.extendedfeatures.zpm_data_access_hatch", "ZPM Data Access Hatch");
        replace(provider, "block.extendedfeatures.uv_data_access_hatch", "UV Data Access Hatch");
        replace(provider, "block.extendedfeatures.uhv_data_access_hatch", "UHV Data Access Hatch");

        // Recipe types lang
        replace(provider, "extendedfeatures.greenhouse_wood_recipes", "Tree Growing");
        replace(provider, "extendedfeatures.greenhouse_crop_recipes", "Crop Growing ");
        replace(provider, "extendedfeatures.disassembler_machine", "Disassembly");
        replace(provider, "extendedfeatures.rock_processing_facility", "Rock Processing");

        // Configuration lang
        replace(provider, "config.screen.extendedfeatures", "§7Client Configuration");
        replace(provider, "config.extendedfeatures.option.features", "§7Modules");
        replace(provider, "config.extendedfeatures.option.RamEnabled", "§7Enable the Robust Alloy Materializer §4(Requires restart)");
        replace(provider, "config.extendedfeatures.option.AcuEnabled", "§7Enable the Advanced Cracking Unit §4(Requires restart)");
        replace(provider, "config.extendedfeatures.option.ErcEnabled", "§7Enable the Enlarged Reaction Chamber §4(Requires restart)");
        replace(provider, "config.extendedfeatures.option.LpuEnabled", "§7Enable the Large Pyrolysis Unit §4(Requires restart)");
        replace(provider, "config.extendedfeatures.option.AfrEnabled", "§7Enable the Advanced Fusion Reactor §4(Requires restart)");
        replace(provider, "config.extendedfeatures.option.CalEnabled", "§7Enable the Compact Assembly Line §4(Requires restart)");
        replace(provider, "config.extendedfeatures.option.RpfEnabled", "§7Enable the Rock Processing Facility §4(Requires restart)");
        replace(provider, "config.extendedfeatures.option.IghEnabled", "§7Enable the Industrial Greenhouse §4(Requires restart)");
        replace(provider, "config.extendedfeatures.option.TgcEnabled", "§7Enable the Tree Growing Chamber §4(Requires restart)");
        replace(provider, "config.extendedfeatures.option.DaEnabled", "§7Enable the Disassembler §4(Requires restart)");
        replace(provider, "config.extendedfeatures.option.universalCircuits", "§7Enable Universal Circuits §4(Requires restart)");
        replace(provider, "config.extendedfeatures.option.expandedDataAccessHatches", "§7Enable the Expanded Data Access Hatches §4(Requires restart)");
    }

    private static void Tooltips(RegistrateLangProvider provider) {
        provider.add("extendedfeatures.compact_assembly_line.tooltip.0", "§7This machine performs the work of the Assembly Line without §9Ordered Inputs");
        provider.add("extendedfeatures.compact_assembly_line.tooltip.1", "§fOnly allows §bone §fenergy hatch and performs §b8 Recipes §fin parallel");

        provider.add("extendedfeatures.enlarged_reaction_chamber.tooltip.0", "§fEven larger Black Box Reactor");
        provider.add("extendedfeatures.enlarged_reaction_chamber.tooltip.1", "§7This machine is an improved version of the Large Chemical reactor");
        provider.add("extendedfeatures.enlarged_reaction_chamber_tooltip.2", "§7Runs §a16 recipes §7in parallel");

        provider.add("extendedfeatures.rock_processing_facility.tooltip.0", "§7An all in one processing facility, turns the rocks you normally get from the rock breaker into their direct processed outputs");
        provider.add("extendedfeatures.rock_processing_facility.tooltip.1", "§fAll recipes have §6Ranged Outputs");

        provider.add("extendedfeatures.greenhouse.tooltip.0", "§7Designed to make wood generation easier");
        provider.add("extendedfeatures.greenhouse.tooltip.1", "§fRuns §g8 Recipes §fin parallel");

        provider.add("extendedfeatures.disassembler.tooltip.0", "§7Allows any type of §emachine/controller §7to be recycled, returning what was used for that recipe");
        provider.add("extendedfeatures.disassembler.tooltip.1", "§7Each tier of machine has to be processed using its own energy hatch");

        provider.add("extendedfeatures.luv_advanced_fusion_reactor.tooltip.0", "§fRuns");
        provider.add("extendedfeatures.luv_advanced_fusion_reactor.tooltip.1", " 4 Parallels");

        provider.add("extendedfeatures.zpm_advanced_fusion_reactor.tooltip.0", "§fRuns");
        provider.add("extendedfeatures.zpm_advanced_fusion_reactor.tooltip.1", " 8 Parallels");

        provider.add("extendedfeatures.uv_advanced_fusion_reactor.tooltip.0", "§fRuns");
        provider.add("extendedfeatures.uv_advanced_fusion_reactor.tooltip.1", " 16 Parallels");

        provider.add("extendedfeatures.fancytooltip.tooltip.0", "§fAllows");
        provider.add("extendedfeatures.fancytooltip.tooltip.1", " Laser Hatches ");
        provider.add("extendedfeatures.fancytooltip.tooltip.2", "§fand");
        provider.add("extendedfeatures.fancytooltip.tooltip.3", " Parallel Hatches");
    }
}