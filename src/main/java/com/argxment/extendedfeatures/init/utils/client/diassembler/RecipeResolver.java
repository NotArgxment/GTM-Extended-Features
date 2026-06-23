package com.argxment.extendedfeatures.init.utils.client.diassembler;

import com.argxment.extendedfeatures.init.Items;

import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RecipeResolver {

    // This piece of code will try to match any circuit tag in the recipe to convert them into Universal circuits
    // So avoids giving a bad/good circuit, instead gives all of them in just 1
    public static final Map<TagKey<Item>, ItemStack> CIRCUIT_TAG_TO_UNIVERSAL = Map.ofEntries(
            Map.entry(CustomTags.ULV_CIRCUITS, Items.ULV_UNIVERSAL_CIRCUIT.asStack()),
            Map.entry(CustomTags.LV_CIRCUITS,  Items.LV_UNIVERSAL_CIRCUIT.asStack()),
            Map.entry(CustomTags.MV_CIRCUITS,  Items.MV_UNIVERSAL_CIRCUIT.asStack()),
            Map.entry(CustomTags.HV_CIRCUITS,  Items.HV_UNIVERSAL_CIRCUIT.asStack()),
            Map.entry(CustomTags.EV_CIRCUITS,  Items.EV_UNIVERSAL_CIRCUIT.asStack()),
            Map.entry(CustomTags.IV_CIRCUITS,  Items.IV_UNIVERSAL_CIRCUIT.asStack()),
            Map.entry(CustomTags.LuV_CIRCUITS, Items.LuV_UNIVERSAL_CIRCUIT.asStack()),
            Map.entry(CustomTags.ZPM_CIRCUITS, Items.ZPM_UNIVERSAL_CIRCUIT.asStack()),
            Map.entry(CustomTags.UV_CIRCUITS,  Items.UV_UNIVERSAL_CIRCUIT.asStack()),
            Map.entry(CustomTags.UHV_CIRCUITS, Items.UHV_UNIVERSAL_CIRCUIT.asStack())
            /*
            Map.entry(CustomTags.UEV_CIRCUITS, Items.UEV_UNIVERSAL_CIRCUIT.asStack()),
            Map.entry(CustomTags.UIV_CIRCUITS, Items.UIV_UNIVERSAL_CIRCUIT.asStack()),
            Map.entry(CustomTags.UXV_CIRCUITS, Items.UXV_UNIVERSAL_CIRCUIT.asStack()),
            Map.entry(CustomTags.OpV_CIRCUITS, Items.OpV_UNIVERSAL_CIRCUIT.asStack()),
            Map.entry(CustomTags.MAX_CIRCUITS, Items.MAX_UNIVERSAL_CIRCUIT.asStack())
            */
    );

    public static Optional<List<ItemStack>> resolveFromGTRecipeType(ServerLevel level, GTRecipeType recipeType, ItemStack targetStack) {
        for (GTRecipe recipe : level.getRecipeManager().getAllRecipesFor(recipeType)) {
            if (!recipeProducesItem(recipe, targetStack)) continue;

            return Optional.of(extractItemInputs(recipe));
        }
        return Optional.empty();
    }

    private static boolean recipeProducesItem(GTRecipe recipe, ItemStack targetStack) {
        List<Content> outputContents = recipe.getOutputContents(ItemRecipeCapability.CAP);
        if (outputContents.isEmpty()) return false;

        for (Content content : outputContents) {
            if (!(content.content instanceof Ingredient ingredient)) continue;
            for (ItemStack stack : ingredient.getItems()) {
                if (ItemStack.isSameItem(stack, targetStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<ItemStack> extractItemInputs(GTRecipe recipe) {
        List<ItemStack> components = new ArrayList<>();

        for (Content content : recipe.getInputContents(ItemRecipeCapability.CAP)) {
            if (!(content.content instanceof Ingredient ingredient)) continue;
            if (ingredient.isEmpty()) continue;

            Optional<ItemStack> circuitReplacement = findUniversalCircuitReplacement(ingredient);
            if (circuitReplacement.isPresent()) {
                components.add(circuitReplacement.get().copy());
                continue;
            }

            ItemStack[] matches = ingredient.getItems();
            if (matches.length == 0) continue;

            ItemStack representative = matches[0].copy();

            if (ingredient instanceof SizedIngredient sized) {
                representative.setCount(sized.getAmount());
            }

            components.add(representative);
        }

        return components;
    }

    public static Optional<ItemStack> findUniversalCircuitReplacement(Ingredient ingredient) {
        for (ItemStack stack : ingredient.getItems()) {
            for (var entry : CIRCUIT_TAG_TO_UNIVERSAL.entrySet()) {
                if (stack.is(entry.getKey())) {
                    return Optional.of(entry.getValue());
                }
            }
        }
        return Optional.empty();
    }
}