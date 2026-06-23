package com.argxment.extendedfeatures.init.utils.client.diassembler;

import com.argxment.extendedfeatures.init.utils.client.RecipeTypes;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public enum DisassemblerRecipeLogic implements GTRecipeType.ICustomRecipeLogic {

    INSTANCE;

    private static final int BASE_DURATION = 40;
    private static final int TICKS_PER_TIER = 40;

    @Override
    public @Nullable GTRecipe createCustomRecipe(IRecipeCapabilityHolder holder) {
        ServerLevel serverLevel = getServerLevel(holder);
        if (serverLevel == null) return null;

        if (!(holder instanceof IRecipeLogicMachine recipeLogicMachine)) return null;
        GTRecipeType recipeType = recipeLogicMachine.getRecipeType();

        var recipeHandlers = holder.getCapabilitiesFlat(IO.IN, ItemRecipeCapability.CAP);

        for (var handler : recipeHandlers) {
            for (var content : handler.getContents()) {
                if (!(content instanceof ItemStack stack)) continue;
                if (stack.isEmpty()) continue;

                GTRecipe recipe = tryBuildRecipe(serverLevel, recipeType, stack);
                if (recipe != null) return recipe;
            }
        }
        return null;
    }

    private @Nullable GTRecipe tryBuildRecipe(ServerLevel serverLevel, GTRecipeType recipeType,
                                              ItemStack inputStack) {

        Integer tier = MachineUtil.getMachineTier(inputStack).orElse(null);
        if (tier == null) return null;

        List<ItemStack> components = ComponentResolver.resolve(serverLevel, inputStack);
        if (components.isEmpty()) return null;

        long euT = GTValues.VA[tier];
        int duration = BASE_DURATION + (tier * TICKS_PER_TIER);

        GTRecipeBuilder builder = recipeType
                .recipeBuilder("disassemble/" + inputStack.getItem().builtInRegistryHolder().key().location().getPath())
                .inputItems(inputStack.copyWithCount(1))
                .EUt(euT)
                .duration(duration);

        for (ItemStack component : components) {
            builder.outputItems(component);
        }

        return builder.buildRawRecipe();
    }

    private @Nullable ServerLevel getServerLevel(IRecipeCapabilityHolder holder) {
        if (holder instanceof MetaMachine machine && machine.getLevel() instanceof ServerLevel serverLevel) {
            return serverLevel;
        }
        return null;
    }

    @Override
    public void buildRepresentativeRecipes() {

        GTRecipeType recipeType = RecipeTypes.DISASSEMBLER;

        ServerLevel serverLevel = getRepresentativeServerLevel();
        if (serverLevel == null) return;

        for (MachineDefinition definition : GTRegistries.MACHINES) {

            if (definition.getRecipeTypes().length == 0) continue;

            if (isExcludedFromDisassembly(definition)) continue;

            ItemStack stack = definition.asStack();
            if (stack.isEmpty()) continue;

            GTRecipe recipe = tryBuildRecipe(serverLevel, recipeType, stack);
            if (recipe == null) continue;

            recipe.setId(recipe.getId().withPrefix("/"));
            recipeType.getCategory().addRecipe(recipe);
        }
    }

    private static boolean isExcludedFromDisassembly(MachineDefinition definition) {
        String path = definition.getId().getPath();

        boolean isEnergyHatch = path.contains("energy_input_hatch") || path.contains("energy_output_hatch");
        if (isEnergyHatch) return false;

        return path.contains("transformer")
                || path.contains("energy_converter")
                || path.contains("_bus")
                || path.contains("hatch")
                || path.contains("diode");
    }

    private @Nullable ServerLevel getRepresentativeServerLevel() {
        var server = com.gregtechceu.gtceu.GTCEu.getMinecraftServer();
        return server == null ? null : server.overworld();
    }
}