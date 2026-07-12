package com.argxment.extendedfeatures.client;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.data.lang.LangHandler.replace;

public class LangHandler {

    public static void init(RegistrateLangProvider provider) {
        Common(provider);
        Tooltips(provider);
    }

    private static void Common(RegistrateLangProvider provider) {
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
        replace(provider, "config.screen.extendedfeatures", "§7Mod Configuration §4(Restart to Apply Changes)");
        replace(provider, "config.extendedfeatures.option.Multiblocks", "§7Multiblocks Module");
        replace(provider, "config.extendedfeatures.option.Miscellaneous", "§7Miscellaneous Module");
        replace(provider, "config.extendedfeatures.option.Wireless", "§7Wireless Module");
        replace(provider, "config.extendedfeatures.option.RamEnabled", "§7Robust Alloy Materializer");
        replace(provider, "config.extendedfeatures.option.AcuEnabled", "§7Advanced Cracking Unit");
        replace(provider, "config.extendedfeatures.option.ErcEnabled", "§7Enlarged Reaction Chamber");
        replace(provider, "config.extendedfeatures.option.LpuEnabled", "§7Large Pyrolysis Unit");
        replace(provider, "config.extendedfeatures.option.AfrEnabled", "§7Advanced Fusion Reactors");
        replace(provider, "config.extendedfeatures.option.CalEnabled", "§7Compact Assembly Line");
        replace(provider, "config.extendedfeatures.option.RpfEnabled", "§7Rock Processing Facility");
        replace(provider, "config.extendedfeatures.option.IghEnabled", "§7Industrial Greenhouse");
        replace(provider, "config.extendedfeatures.option.TgcEnabled", "§7Tree Growing Chamber");
        replace(provider, "config.extendedfeatures.option.DaEnabled", "§7Disassembler");
        replace(provider, "config.extendedfeatures.option.EdaEnabled", "§7Expanded Data Bank");
        replace(provider, "config.extendedfeatures.option.universalCircuits", "§7Universal Circuits");
        replace(provider, "config.extendedfeatures.option.expandedDataAccessHatches", "§7Expanded Data Access Hatches");
        replace(provider, "config.extendedfeatures.option.wirelessUtils",
                "§7Wireless Optical T/R & Cloud Transmission Database");

        // Optical
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.tooltip.range",
                "Scan range: §f%s blocks§7 (right-click with an empty hand)");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.tooltip.connections",
                "Max linked receivers: §f%s");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.tooltip.scan",
                "Links to nearby unlinked receivers of the same tier, and to any physical Data Access Hatch in range");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.tooltip.receiver",
                "Gets linked automatically when scanned by a Wireless Transmission Hatch of the same tier");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.not_formed",
                "This multiblock is not formed - form the structure before scanning");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.linked_summary",
                "Linked %s new receiver(s) and %s new data hatch(es)");
        replace(provider, "extendedfeatures.machine.wireless_optical_hatch.no_receivers_found",
                "No new compatible receivers or data hatches found in range");
    }

    private static void Tooltips(RegistrateLangProvider provider) {
        provider.add("extendedfeatures.compact_assembly_line.tooltip.0",
                "§7This machine performs the work of the Assembly Line without §9Ordered Inputs");
        provider.add("extendedfeatures.compact_assembly_line.tooltip.1",
                "§fOnly allows §bone §fenergy hatch and performs §b8 Recipes §fin parallel");

        provider.add("extendedfeatures.enlarged_reaction_chamber.tooltip.0", "§fEven larger Black Box Reactor");
        provider.add("extendedfeatures.enlarged_reaction_chamber.tooltip.1",
                "§7This machine is an improved version of the Large Chemical reactor");
        provider.add("extendedfeatures.enlarged_reaction_chamber_tooltip.2", "§7Runs §a16 recipes §7in parallel");

        provider.add("extendedfeatures.rock_processing_facility.tooltip.0",
                "§7An all in one processing facility, turns the rocks you normally get from the rock breaker into their direct processed outputs");
        provider.add("extendedfeatures.rock_processing_facility.tooltip.1", "§fAll recipes have §6Ranged Outputs");

        provider.add("extendedfeatures.greenhouse.tooltip.0", "§7Designed to make wood generation easier");
        provider.add("extendedfeatures.greenhouse.tooltip.1", "§fRuns §g8 Recipes §fin parallel");

        provider.add("extendedfeatures.disassembler.tooltip.0",
                "§7Allows any type of §emachine/controller §7to be recycled, returning what was used for that recipe");
        provider.add("extendedfeatures.disassembler.tooltip.1",
                "§7Each tier of machine has to be processed using its own energy hatch");

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
