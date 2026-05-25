package com.vyx.extraadditions.machines.extras.parallel;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;

import net.minecraft.MethodsReturnNonnullByDefault;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class FusionReactorParallel extends FusionReactorMachine {

    private static final int[] PerfectTierParallel = {
    // acts like counter; ULV to IV aren't valid reactor tiers
            0, 0, 0, 0, 0, 0, 0,
            4, // LuV (MKI)   →  4 parallel
            8, // ZPM (MKII)  →  8 parallel
            16 // UV  (MKIII) →  16 parallel
    };

    public FusionReactorParallel(IMachineBlockEntity holder, int tier) {
        super(holder, tier);
    }

    public int getPerfectParallel() {
        int tier = getTier();
        if (tier < 0 || tier >= PerfectTierParallel.length) return 1;
        int p = PerfectTierParallel[tier];
        return Math.max(p, 1);
    }

    public static ModifierFunction perfectParallel(
            MetaMachine machine, GTRecipe recipe) {

        if (!(machine instanceof FusionReactorParallel fusion)) {
            return ModifierFunction.IDENTITY;
        }

        int parallels = fusion.getPerfectParallel();
        if (parallels <= 1) return ModifierFunction.IDENTITY;

        return ModifierFunction.builder()
                .outputModifier(ContentModifier.multiplier(parallels))
                .tickOutputModifier(ContentModifier.multiplier(parallels))
                .parallels(parallels)
                .build();
    }
}