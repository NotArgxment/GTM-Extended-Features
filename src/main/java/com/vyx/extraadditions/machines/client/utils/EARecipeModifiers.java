package com.vyx.extraadditions.machines.client.utils;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;

import java.util.function.IntFunction;

public class EARecipeModifiers {

    private static final int MAX_SIMPLE_PARALLEL = 64;

    public static final IntFunction<RecipeModifier> SIMPLE_PARALLEL = parallels -> {
        if (parallels == 1) return RecipeModifier.NO_MODIFIER;

        return (machine, recipe) -> {
            int achievable = ParallelLogic.getParallelAmountWithoutEU(machine, recipe, parallels);
            if (achievable <= 1) return ModifierFunction.IDENTITY;
            return ModifierFunction.builder()
                    .modifyAllContents(ContentModifier.multiplier(achievable))
                    .durationMultiplier(2)
                    .parallels(achievable)
                    .build();
        };
    };

    public static final RecipeModifier TIERED_PARALLEL = (machine, recipe) -> {
        int tier = machine.self().getDefinition().getTier();
        int parallels = switch (tier) {
            case GTValues.LuV -> 4;
            case GTValues.ZPM -> 8;
            case GTValues.UV  -> 16;
            default -> 1;
        };

        if (parallels <= 1) return ModifierFunction.IDENTITY;

        int achievable = ParallelLogic.getParallelAmountWithoutEU(machine, recipe, parallels);
        if (achievable <= 1) return ModifierFunction.IDENTITY;

        return ModifierFunction.builder()
                .modifyAllContents(ContentModifier.multiplier(achievable))
                .durationMultiplier(2)
                .parallels(achievable)
                .build();
    };
}