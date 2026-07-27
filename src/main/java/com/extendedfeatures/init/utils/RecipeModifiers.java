package com.extendedfeatures.init.utils;

import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.*;

import java.util.function.*;

public class RecipeModifiers {

    private static final int MAX_CUSTOM_PARALLEL = 2147483647; // 2^32-1

    public static final IntFunction<RecipeModifier> CUSTOM_PARALLEL = parallels -> {
        if (parallels == 1) return RecipeModifier.NO_MODIFIER;

        return (machine, recipe) -> {

            int achievable = ParallelLogic.getParallelAmountWithoutEU(machine, recipe, parallels);

            if (achievable <= 1) return ModifierFunction.IDENTITY;
            return ModifierFunction.builder()
                    .modifyAllContents(ContentModifier.multiplier(achievable))
                    .durationMultiplier(2)
                    .eutMultiplier(1)
                    .parallels(achievable)
                    .build();
        };
    };

}
