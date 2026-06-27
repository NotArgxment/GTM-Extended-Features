package com.argxment.extendedfeatures.client.greenhouse;

import com.argxment.extendedfeatures.client.RecipeTypes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class GreenhouseRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        registerTreeRecipes(provider);
        registerCropRecipes(provider);
    }

    private static void registerTreeRecipes(Consumer<FinishedRecipe> provider) {

        addTreeRecipe(provider, "rubber_sapling", false,
                GTBlocks.RUBBER_SAPLING.asStack(),
                1000,
                GTBlocks.RUBBER_LOG.asStack(32),
                GTItems.STICKY_RESIN.asStack(8),
                GTBlocks.RUBBER_SAPLING.asStack(4));

        addTreeRecipe(provider, "rubber_sapling_boosted", true,
                GTBlocks.RUBBER_SAPLING.asStack(),
                1000,
                GTBlocks.RUBBER_LOG.asStack(64),
                GTItems.STICKY_RESIN.asStack(16),
                GTBlocks.RUBBER_SAPLING.asStack(4));

        addTreeRecipe(provider, "oak_sapling", false,
                new ItemStack(Items.OAK_SAPLING),
                1000,
                new ItemStack(Items.OAK_LOG, 64),
                new ItemStack(Items.OAK_SAPLING, 4));

        addTreeRecipe(provider, "oak_sapling_boosted", true,
                new ItemStack(Items.OAK_SAPLING),
                1000,
                new ItemStack(Items.OAK_LOG, 64),
                new ItemStack(Items.OAK_LOG, 64),
                new ItemStack(Items.OAK_SAPLING, 4));

        addTreeRecipe(provider, "dark_oak_sapling", false,
                new ItemStack(Items.DARK_OAK_SAPLING),
                1000,
                new ItemStack(Items.DARK_OAK_LOG, 64),
                new ItemStack(Items.DARK_OAK_SAPLING, 4));

        addTreeRecipe(provider, "dark_oak_sapling_boosted", true,
                new ItemStack(Items.DARK_OAK_SAPLING),
                1000,
                new ItemStack(Items.DARK_OAK_LOG, 64),
                new ItemStack(Items.DARK_OAK_LOG, 64),
                new ItemStack(Items.DARK_OAK_SAPLING, 4));

        addTreeRecipe(provider, "spruce_sapling", false,
                new ItemStack(Items.SPRUCE_SAPLING),
                1000,
                new ItemStack(Items.SPRUCE_LOG, 64),
                new ItemStack(Items.SPRUCE_SAPLING, 4));

        addTreeRecipe(provider, "spruce_sapling_boosted", true,
                new ItemStack(Items.SPRUCE_SAPLING),
                1000,
                new ItemStack(Items.SPRUCE_LOG, 64),
                new ItemStack(Items.SPRUCE_LOG, 64),
                new ItemStack(Items.SPRUCE_SAPLING, 4));

        addTreeRecipe(provider, "birch_sapling", false,
                new ItemStack(Items.BIRCH_SAPLING),
                1000,
                new ItemStack(Items.BIRCH_LOG, 64),
                new ItemStack(Items.BIRCH_SAPLING, 4));

        addTreeRecipe(provider, "birch_sapling_boosted", true,
                new ItemStack(Items.BIRCH_SAPLING),
                1000,
                new ItemStack(Items.BIRCH_LOG, 64),
                new ItemStack(Items.BIRCH_LOG, 64),
                new ItemStack(Items.BIRCH_SAPLING, 4));

        addTreeRecipe(provider, "acacia_sapling", false,
                new ItemStack(Items.ACACIA_SAPLING),
                1000,
                new ItemStack(Items.ACACIA_LOG, 64),
                new ItemStack(Items.ACACIA_SAPLING, 4));

        addTreeRecipe(provider, "acacia_sapling_boosted", true,
                new ItemStack(Items.ACACIA_SAPLING),
                1000,
                new ItemStack(Items.ACACIA_LOG, 64),
                new ItemStack(Items.ACACIA_LOG, 64),
                new ItemStack(Items.ACACIA_SAPLING, 4));

        addTreeRecipe(provider, "jungle_sapling", false,
                new ItemStack(Items.JUNGLE_SAPLING),
                1000,
                new ItemStack(Items.JUNGLE_LOG, 64),
                new ItemStack(Items.JUNGLE_SAPLING, 4));

        addTreeRecipe(provider, "jungle_sapling_boosted", true,
                new ItemStack(Items.JUNGLE_SAPLING),
                1000,
                new ItemStack(Items.JUNGLE_LOG, 64),
                new ItemStack(Items.JUNGLE_LOG, 64),
                new ItemStack(Items.JUNGLE_SAPLING, 4));

        addTreeRecipe(provider, "mangrove_propagule", false,
                new ItemStack(Items.MANGROVE_PROPAGULE),
                1000,
                new ItemStack(Items.MANGROVE_LOG, 64),
                new ItemStack(Items.MANGROVE_PROPAGULE, 4));

        addTreeRecipe(provider, "mangrove_propagule_boosted", true,
                new ItemStack(Items.MANGROVE_PROPAGULE),
                1000,
                new ItemStack(Items.MANGROVE_LOG, 64),
                new ItemStack(Items.MANGROVE_LOG, 64),
                new ItemStack(Items.MANGROVE_PROPAGULE, 4));
    }

    private static void registerCropRecipes(Consumer<FinishedRecipe> provider) {

        addCropRecipe(provider, "sugar_cane",        false, new ItemStack(Items.SUGAR_CANE),      1000, new ItemStack(Items.SUGAR_CANE,   24));
        addCropRecipe(provider, "sugar_cane_boosted", true, new ItemStack(Items.SUGAR_CANE),      1000, new ItemStack(Items.SUGAR_CANE,   48));

        addCropRecipe(provider, "kelp",              false, new ItemStack(Items.KELP),             2000, new ItemStack(Items.KELP,          24));
        addCropRecipe(provider, "kelp_boosted",       true, new ItemStack(Items.KELP),             2000, new ItemStack(Items.KELP,          48));

        addCropRecipe(provider, "bamboo",            false, new ItemStack(Items.BAMBOO),           1000, new ItemStack(Items.BAMBOO,        24));
        addCropRecipe(provider, "bamboo_boosted",     true, new ItemStack(Items.BAMBOO),           1000, new ItemStack(Items.BAMBOO,        48));

        addCropRecipe(provider, "cactus",            false, new ItemStack(Items.CACTUS),           1000, new ItemStack(Items.CACTUS,        24));
        addCropRecipe(provider, "cactus_boosted",     true, new ItemStack(Items.CACTUS),           1000, new ItemStack(Items.CACTUS,        48));

        addCropRecipe(provider, "wheat",             false, new ItemStack(Items.WHEAT_SEEDS),      1000, new ItemStack(Items.WHEAT,         24));
        addCropRecipe(provider, "wheat_boosted",      true, new ItemStack(Items.WHEAT_SEEDS),      1000, new ItemStack(Items.WHEAT,         48));

        addCropRecipe(provider, "carrot",            false, new ItemStack(Items.CARROT),           1000, new ItemStack(Items.CARROT,        24));
        addCropRecipe(provider, "carrot_boosted",     true, new ItemStack(Items.CARROT),           1000, new ItemStack(Items.CARROT,        48));

        addCropRecipe(provider, "potato",            false, new ItemStack(Items.POTATO),           1000, new ItemStack(Items.POTATO,        24));
        addCropRecipe(provider, "potato_boosted",     true, new ItemStack(Items.POTATO),           1000, new ItemStack(Items.POTATO,        48));

        addCropRecipe(provider, "beetroot",          false, new ItemStack(Items.BEETROOT_SEEDS),   1000, new ItemStack(Items.BEETROOT,      24));
        addCropRecipe(provider, "beetroot_boosted",   true, new ItemStack(Items.BEETROOT_SEEDS),   1000, new ItemStack(Items.BEETROOT,      48));

        addCropRecipe(provider, "melon",             false, new ItemStack(Items.MELON_SEEDS),      1000, new ItemStack(Items.MELON,         12));
        addCropRecipe(provider, "melon_boosted",      true, new ItemStack(Items.MELON_SEEDS),      1000, new ItemStack(Items.MELON,         24));

        addCropRecipe(provider, "pumpkin",           false, new ItemStack(Items.PUMPKIN_SEEDS),    1000, new ItemStack(Items.PUMPKIN,       12));
        addCropRecipe(provider, "pumpkin_boosted",    true, new ItemStack(Items.PUMPKIN_SEEDS),    1000, new ItemStack(Items.PUMPKIN,       24));

        addCropRecipe(provider, "nether_wart",       false, new ItemStack(Items.NETHER_WART),      1000, new ItemStack(Items.NETHER_WART,   12));
        addCropRecipe(provider, "nether_wart_boosted", true, new ItemStack(Items.NETHER_WART),     1000, new ItemStack(Items.NETHER_WART,   24));

        addCropRecipe(provider, "red_mushroom",      false, new ItemStack(Items.RED_MUSHROOM),     1000, new ItemStack(Items.RED_MUSHROOM,  12));
        addCropRecipe(provider, "red_mushroom_boosted", true, new ItemStack(Items.RED_MUSHROOM),   1000, new ItemStack(Items.RED_MUSHROOM,  24));

        addCropRecipe(provider, "brown_mushroom",    false, new ItemStack(Items.BROWN_MUSHROOM),   1000, new ItemStack(Items.BROWN_MUSHROOM, 12));
        addCropRecipe(provider, "brown_mushroom_boosted", true, new ItemStack(Items.BROWN_MUSHROOM), 1000, new ItemStack(Items.BROWN_MUSHROOM, 24));
    }

    private static void addTreeRecipe(Consumer<FinishedRecipe> provider, String id, boolean boosted, ItemStack sapling, int waterMb, ItemStack... outputs) {
        var builder = RecipeTypes.GREENHOUSE.recipeBuilder(id)
                .circuitMeta(boosted ? 2 : 1) // If boosted, use 2, else, 1
                .notConsumable(sapling)
                .inputFluids(GTMaterials.Water.getFluid(waterMb))
                .duration(boosted ? 320 : 640) // If boosted, last 320t, else, 640t)
                .EUt(GTValues.VA[GTValues.MV]);

        if (boosted) {
            builder.inputItems(GTItems.FERTILIZER.asStack(4));
        }

        for (ItemStack output : outputs) {
            builder.outputItems(output);
        }

        builder.save(provider);
    }

    private static void addCropRecipe(Consumer<FinishedRecipe> provider, String id, boolean boosted, ItemStack input, int waterMb, ItemStack output) {
        var builder = RecipeTypes.GREENHOUSE.recipeBuilder(id)
                .circuitMeta(boosted ? 2 : 1)
                .notConsumable(input)
                .inputFluids(GTMaterials.Water.getFluid(waterMb))
                .outputItems(output)
                .duration(boosted ? 320 : 640)
                .EUt(GTValues.VA[GTValues.MV]);

        if (boosted) {
            builder.inputItems(GTItems.FERTILIZER.asStack(4));
        }

        builder.save(provider);
    }
}