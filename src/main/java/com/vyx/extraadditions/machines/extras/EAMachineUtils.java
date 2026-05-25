import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.api.registry.registrate.MultiblockMachineBuilder;

import java.util.Locale;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.common.registry.GTRegistration.REGISTRATE;

public static MultiblockMachineDefinition[] registerTieredMultis(String name,
                                                                 BiFunction<IMachineBlockEntity, Integer, MultiblockControllerMachine> factory,
                                                                 BiFunction<Integer, MultiblockMachineBuilder<?, ?>, MultiblockMachineDefinition> builder,
                                                                 int... tiers) {
    return registerTieredMultis(REGISTRATE, name, factory, builder, tiers);
}

public static MultiblockMachineDefinition[] registerTieredMultis(GTRegistrate registrate, String name,
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
