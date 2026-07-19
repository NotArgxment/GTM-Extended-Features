package com.extendedfeatures.client;

import com.extendedfeatures.ExtendedFeaturesCore;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.*;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.registry.registrate.*;

import java.util.Locale;
import java.util.function.BiFunction;

public class MachineUtils {

    public static MultiblockMachineDefinition[] TieredMultis(String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MultiblockControllerMachine> factory,
                                                             BiFunction<Integer, MultiblockMachineBuilder<?, ?>, MultiblockMachineDefinition> builder,
                                                             int... tiers) {
        return TieredMultis(ExtendedFeaturesCore.ExtendedFeaturesRegister, name, factory, builder, tiers);
    }

    public static MultiblockMachineDefinition[] TieredMultis(GTRegistrate registrate, String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MultiblockControllerMachine> factory,
                                                             BiFunction<Integer, MultiblockMachineBuilder<?, ?>, MultiblockMachineDefinition> builder,
                                                             int... tiers) {
        MultiblockMachineDefinition[] definitions = new MultiblockMachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            var register = registrate
                    .multiblock(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name,
                            holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }

    public static MachineDefinition[] TieredMachines(String name,
                                                     BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
                                                     BiFunction<Integer, MachineBuilder<MachineDefinition, ?>, MachineDefinition> builder,
                                                     int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            var register = ExtendedFeaturesCore.ExtendedFeaturesRegister
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name,
                            holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }
}
