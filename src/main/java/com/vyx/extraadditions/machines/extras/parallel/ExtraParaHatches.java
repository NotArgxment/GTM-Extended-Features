//package com.vyx.extraadditions.machines.extras.parallel;
//
//import com.gregtechceu.gtceu.GTCEu;
//import com.gregtechceu.gtceu.api.data.RotationState;
//import com.gregtechceu.gtceu.api.machine.MachineDefinition;
//import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
//import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
//import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
//import com.gregtechceu.gtceu.common.machine.multiblock.part.ParallelHatchPartMachine;
//import net.minecraft.network.chat.Component;
//
//import static com.gregtechceu.gtceu.api.GTValues.*;
//import static com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties.*;
//import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.*;
//
//import static com.vyx.extraadditions.machines.extras.utils.EAMachineUtils.TieredMachines;
//
//public class ExtraParaHatches {
//
//    public static void init() {}
//
//    public static final MachineDefinition[] PARALLEL_HATCH = TieredMachines("parallel_hatch",
//            ParallelHatchPartMachine::new,
//            (tier, builder) -> builder
//                    .langValue(switch (tier) {
//                        case 9 -> "Hyper";
//                        case 10 -> "Mega";
//                        case 11 -> "Ultra";
//                        case 12 -> "Giga";
//                        case 13 -> "Massive";
//                        case 14 -> "Absolute";
//                        default -> "Simple"; // Should never be hit.
//                    } + " Parallel Control Hatch")
//                    .rotationState(RotationState.ALL)
//                    .abilities(PartAbility.PARALLEL_HATCH)
//                    .modelProperty(IS_FORMED, false)
//                    .modelProperty(
//                            GTMachineModelProperties.RECIPE_LOGIC_STATUS,
//                            RecipeLogic.Status.IDLE)
//                    .model(createWorkableTieredHullMachineModel(
//                            GTCEu.id("block/machines/parallel_hatch_mk" + (tier - 4)))
//                            .andThen((ctx, prov, model) -> {
//                                model.addReplaceableTextures("bottom", "top", "side");
//                            }))
//                    .tooltips(Component.translatable("gtceu.machine.parallel_hatch_mk" + tier + ".tooltip"),
//                            Component.translatable("gtceu.part_sharing.disabled"))
//                    .register(),
//            UHV, UEV, UIV, UXV, OpV, MAX);
//}