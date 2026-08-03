package com.extendedfeatures.init.utils;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.*;

public class RecipeModifiers {

    public static RecipeModifier CUSTOM_PARALLEL(int parallels) {
        if (parallels == 1) return RecipeModifier.NO_MODIFIER;

        return (MetaMachine machine, GTRecipe recipe) -> {
            int achievable = ParallelLogic.getParallelAmountWithoutEU(machine, recipe, parallels);

            if (achievable <= 1) return ModifierFunction.IDENTITY;
            return ModifierFunction.builder()
                    .modifyAllContents(ContentModifier.multiplier(achievable))
                    .durationMultiplier(2)
                    .parallels(achievable)
                    .build();
        };
    }

}
