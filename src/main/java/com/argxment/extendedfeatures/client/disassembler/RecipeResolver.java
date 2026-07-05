package com.argxment.extendedfeatures.client.disassembler;

import com.argxment.extendedfeatures.config.EFModulesConfig;
import com.argxment.extendedfeatures.init.Items;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RecipeResolver {

    // This piece of code will try to match any circuit tag in the recipe to convert them into Universal circuits
    // So avoids giving a bad/good circuit, instead gives all of them in just 1
    public static final Map<TagKey<Item>, ItemStack> CIRCUIT_TAG_TO_UNIVERSAL = buildCircuitTagMap();

    private static Map<TagKey<Item>, ItemStack> buildCircuitTagMap() {
        Map<TagKey<Item>, ItemStack> map = new HashMap<>();

        if (!EFModulesConfig.INSTANCE.features.universalCircuits) {
            return Map.of();
        }

        putIfPresent(map, CustomTags.ULV_CIRCUITS, GTValues.ULV);
        putIfPresent(map, CustomTags.LV_CIRCUITS, GTValues.LV);
        putIfPresent(map, CustomTags.MV_CIRCUITS, GTValues.MV);
        putIfPresent(map, CustomTags.HV_CIRCUITS, GTValues.HV);
        putIfPresent(map, CustomTags.EV_CIRCUITS, GTValues.EV);
        putIfPresent(map, CustomTags.IV_CIRCUITS, GTValues.IV);
        putIfPresent(map, CustomTags.LuV_CIRCUITS, GTValues.LuV);
        putIfPresent(map, CustomTags.ZPM_CIRCUITS, GTValues.ZPM);
        putIfPresent(map, CustomTags.UV_CIRCUITS, GTValues.UV);

        if (GTCEuAPI.isHighTier()) {
            putIfPresent(map, CustomTags.UHV_CIRCUITS, GTValues.UHV);
            putIfPresent(map, CustomTags.UEV_CIRCUITS, GTValues.UEV);
            putIfPresent(map, CustomTags.UIV_CIRCUITS, GTValues.UIV);
            putIfPresent(map, CustomTags.UXV_CIRCUITS, GTValues.UXV);
            putIfPresent(map, CustomTags.OpV_CIRCUITS, GTValues.OpV);
        }

        return Map.copyOf(map);
    }

    private static void putIfPresent(Map<TagKey<Item>, ItemStack> map, TagKey<Item> tag, int tier) {
        ItemEntry<Item> entry = Items.UNIVERSAL_CIRCUITS[tier];
        if (entry != null) {
            map.put(tag, entry.asStack());
        }
    }

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