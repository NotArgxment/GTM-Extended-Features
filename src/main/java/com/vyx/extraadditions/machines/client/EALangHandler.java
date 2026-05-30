package com.vyx.extraadditions.machines.client;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.data.lang.LangHandler.replace;

public class EALangHandler {

    public static void init(RegistrateLangProvider provider) {
        initControllerNames(provider);
        initControllerTooltips(provider);
    }
    // color code references
    /*
        §0	black
        §1	dark_blue
        §2	dark_green
        §3	dark_aqua
        §4	dark_red
        §5	dark_purple
        §6	gold
        §7	gray
        §8	dark_gray
        §9	blue
        §a	green
        §b	aqua
        §c	red
        §d	light_purple
        §e	yellow
        §f	white
        §k	obfuscated
        §l	bold
        §m	strikethrough
        §n	underline
        §o	italic
        §r	reset
        §r§f  reset + add any color
       */

    private static void initControllerNames(RegistrateLangProvider provider) {
        replace(provider, "block.extraadditions.robust_alloy_materializer", "Robust Alloy Materializer [RAM]");
        replace(provider, "block.extraadditions.advanced_cracking_unit", "Advanced Cracking Unit [ACU]");
        replace(provider, "block.extraadditions.enlarged_reaction_chamber", "Enlarged Reaction Chamber [ERC]");
        replace(provider, "block.extraadditions.large_pyrolysis_unit", "Large Pyrolysis Unit [LPU]");
        replace(provider, "block.extraadditions.compact_assembly_line", "Compact Assembly Line [CAL]");
        // note: fusion reactor lang entries aren't required here at all, only tooltips
    }

    private static void initControllerTooltips(RegistrateLangProvider provider) {
        provider.add("extraadditions.machine.compact_assembly_line.tooltip.0", "§7This machine is able to perform the work of a regular Assembly Line without §6Ordered Inputs");
        provider.add("extraadditions.machine.compact_assembly_line.tooltip.1", "§fOnly allows §bone §fenergy hatch");

        provider.add("extraadditions.machine.enlarged_reaction_chamber.tooltip", "§fEven larger Black Box Reactor");

        provider.add("extraadditions.multiblock.luv_advanced_fusion_reactor.tooltip.0", "§7Runs");
        provider.add("extraadditions.multiblock.luv_advanced_fusion_reactor.tooltip.1", " 4 Parallels");

        provider.add("extraadditions.multiblock.zpm_advanced_fusion_reactor.tooltip.0", "§7Runs");
        provider.add("extraadditions.multiblock.zpm_advanced_fusion_reactor.tooltip.1", " 8 Parallels");

        provider.add("extraadditions.multiblock.uv_advanced_fusion_reactor.tooltip.0", "§7Runs");
        provider.add("extraadditions.multiblock.uv_advanced_fusion_reactor.tooltip.1", " 16 Parallels");

        provider.add("extraadditions.fancytooltip.tooltip.0", "§7Allows");
        provider.add("extraadditions.fancytooltip.tooltip.1", " Laser Hatches ");
        provider.add("extraadditions.fancytooltip.tooltip.2", "§7and");
        provider.add("extraadditions.fancytooltip.tooltip.3", " Parallel Hatches");

    }
}