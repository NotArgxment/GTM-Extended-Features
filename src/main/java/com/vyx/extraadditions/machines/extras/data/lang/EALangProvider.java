package com.vyx.extraadditions.machines.extras.data.lang;

import com.vyx.extraadditions.ExtraAdditionsCore;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class EALangProvider extends LanguageProvider {

    public EALangProvider(PackOutput output) {
        super(output, ExtraAdditionsCore.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("block.extraadditions.compact_assembly_line.tooltip.0", "§7Can run up to 4 parallels.");
        add("block.extraadditions.compact_assembly_line.tooltip.1", "§7Uses the same amount of EU as performing one recipe, but takes 3.5x the time.");
    }
}