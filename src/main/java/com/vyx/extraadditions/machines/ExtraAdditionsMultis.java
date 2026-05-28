package com.vyx.extraadditions.machines;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.*;
import com.gregtechceu.gtceu.api.machine.multiblock.*;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.pattern.*;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderHelper;
import com.gregtechceu.gtceu.client.util.TooltipHelper;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;

import com.vyx.extraadditions.machines.extras.utils.LaserLogic;
import com.vyx.extraadditions.machines.extras.utils.EARecipeModifiers;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;

import java.util.Locale;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.*;
import static com.gregtechceu.gtceu.common.data.GCYMRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeModifiers.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.*;
import static com.gregtechceu.gtceu.utils.FormattingUtil.*;

import static com.vyx.extraadditions.ExtraAdditionsCore.EXTRA_ADDITIONS_REGISTRATE;
import static com.vyx.extraadditions.machines.extras.utils.EAMachineUtils.TieredMultis;

public class ExtraAdditionsMultis {

    public static void init() {}

    public static MultiblockMachineDefinition ROBUST_ALLOY_MATERIALIZER = EXTRA_ADDITIONS_REGISTRATE
            .multiblock("robust_alloy_materializer", LaserLogic::new)
            .tooltips(Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.0"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.1"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.2"))
            .tooltipBuilder((stack, list) -> {
                list.add(Component.translatable("extraadditions.machine.robust_alloy_materializer.tooltip.0")
                    .append(Component.translatable("extraadditions.machine.robust_alloy_materializer.tooltip.1")
                            .withStyle(TooltipHelper.RAINBOW_HSL_SLOW))
                        .append(Component.translatable("extraadditions.machine.robust_alloy_materializer.tooltip.2"))
                            .append(Component.translatable("extraadditions.machine.robust_alloy_materializer.tooltip.3")
                                    .withStyle(TooltipHelper.RAINBOW_HSL_SLOW))
                );
            })
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(ALLOY_BLAST_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers::ebfOverclock)
            .appearanceBlock(CASING_STRESS_PROOF)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("   CCC   ", "   XXX   ", "   XXX   ", "   EEE   ", "   XXX   ", "   XXX   ", "   CCC   ")
                    .aisle(" BBBCBBB ", " XXTTTXX ", " XXTTTXX ", " EETFTEE ", " XXTTTXX ", " XXTTTXX ", " BBBCBBB ")
                    .aisle(" BBBCBBB ", " XETTTEX ", " XETTTEX ", " EETFTEE ", " XETTTEX ", " XETTTEX ", " BEECEEB ")
                    .aisle("CBBCCCBBC", "XTTTTTTTX", "XTTTTTTTX", "ETTTFTTTE", "XTTTTTTTX", "XTTTTTTTX", "CBECCCEBC")
                    .aisle("CCCCECCCC", "XTTTFTTTX", "XTTTFTTTX", "EFFFFFFFE", "XTTTFTTTX", "XTTTFTTTX", "CCCCLCCCC")
                    .aisle("CBBCCCBBC", "XTTTTTTTX", "XTTTTTTTX", "ETTTFTTTE", "XTTTTTTTX", "XTTTTTTTX", "CBECCCEBC")
                    .aisle(" BBBCBBB ", " XETTTEX ", " XETTTEX ", " EETFTEE ", " XETTTEX ", " XETTTEX ", " BEECEEB ")
                    .aisle(" BBBCBBB ", " XXTTTXX ", " XXTTTXX ", " EETFTEE ", " XXTTTXX ", " XXTTTXX ", " BBBCBBB ")
                    .aisle("   C@C   ", "   XXX   ", "   XXX   ", "   EEE   ", "   XXX   ", "   XXX   ", "   CCC   ")
                    .where('@', controller(blocks(definition.get())))
                    .where('C', blocks(CASING_STRESS_PROOF.get())
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS, PartAbility.IMPORT_FLUIDS, PartAbility.EXPORT_FLUIDS))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2).setPreviewCount(2))
                            .or(Predicates.abilities(PartAbility.INPUT_LASER).setMaxGlobalLimited(1).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                    .where('B', Predicates.blocks(CASING_HIGH_TEMPERATURE_SMELTING.get()))
                    .where('F', Predicates.blocks(CASING_TUNGSTENSTEEL_PIPE.get()))
                    .where('E', Predicates.blocks(HEAT_VENT.get()))
                    .where('X', heatingCoils())
                    .where('T', air())
                    .where(' ', any())
                    .where('L', ability(PartAbility.MUFFLER).setExactLimit(1))
                    .build())
            .workableCasingModel(
                    GTCEu.id("block/casings/gcym/stress_proof_casing"),
                    GTCEu.id("block/multiblock/gcym/blast_alloy_smelter"))
            .register();

    public static MultiblockMachineDefinition ADVANCED_CRACKING_UNIT = EXTRA_ADDITIONS_REGISTRATE
            .multiblock("advanced_cracking_unit", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CRACKING_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers::crackerOverclock)
            .appearanceBlock(CASING_STAINLESS_CLEAN)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("FIIFIIF", "FIIFIIF", "FFFYFFF", "FIIFIIF", "FIIFIIF")
                    .aisle("FIIFIIF", "FKKFKKF", "FKKDKKF", "FKKFKKF", "FIIFIIF")
                    .aisle("FIIFIIF", "FKKDKKF", "D##D##D", "FKKDKKF", "FIIFIIF")
                    .aisle("FIIFIIF", "FKKFKKF", "FKKDKKF", "FKKFKKF", "FIIFIIF")
                    .aisle("FIIFIIF", "FIIFIIF", "FFF@FFF", "FIIFIIF", "FIIFIIF")
                    .where('@', controller(blocks(definition.get())))
                    .where('K', heatingCoils())
                    .where('#', air())
                    .where(' ', any())
                    .where('I', blocks(CASING_TEMPERED_GLASS.get()))
                    .where('D', blocks(CASING_TITANIUM_PIPE.get()))
                    .where('F', blocks(CASING_STAINLESS_CLEAN.get())
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS))
                            .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                    .where('Y', ability(PartAbility.MUFFLER).setExactLimit(1))
                    .build())
            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                    GTCEu.id("block/multiblock/cracking_unit"))
            .register();

    public static MultiblockMachineDefinition ENLARGED_REACTION_CHAMBER = EXTRA_ADDITIONS_REGISTRATE
            .multiblock("enlarged_reaction_chamber", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(LARGE_CHEMICAL_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_PERFECT, GTRecipeModifiers.OC_PERFECT_SUBTICK)
            .appearanceBlock(CASING_PTFE_INERT)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle(" FDDDF "," FDDDF "," FDDDF ")
                    .aisle("FDDDDDF","FCK#KCF","FDDDDDF")
                    .aisle("DDDDDDD","DKK#KKD","DDDDDDD")
                    .aisle("DDDDDDD","D##K##D","DDDDDDD")
                    .aisle("DDDDDDD","DKK#KKD","DDDDDDD")
                    .aisle("FDDDDDF","FCK#KCF","FDDDDDF")
                    .aisle(" FDDDF "," FD@DF "," FDDDF ")
                    .where('@', controller(blocks(definition.get())))
                    .where('#', air())
                    .where(' ', any())
                    .where('F', frames(GTMaterials.Polytetrafluoroethylene))
                    .where('K', blocks(CASING_POLYTETRAFLUOROETHYLENE_PIPE.get()))
                    .where('C', blocks(MOLYBDENUM_DISILICIDE_COIL_BLOCK.get()))
                    .where('D', blocks(CASING_PTFE_INERT.get())
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS))
                            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS))
                            .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                    .build())
            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_inert_ptfe"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();

    public static MultiblockMachineDefinition LARGE_PYROLYSIS_UNIT = EXTRA_ADDITIONS_REGISTRATE
            .multiblock("large_pyrolysis_unit", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(PYROLYSE_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_PERFECT, GTRecipeModifiers::pyrolyseOvenOverclock)
            .appearanceBlock(MACHINE_CASING_ULV)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("  EEE  ", "  HHH  ", "  HHH  ", "  HHH  ", "  EEE  ")
                    .aisle(" FGGGF ", " LKKKL ", " LKKKL ", " LKKKL ", " FGGGF ")
                    .aisle("EGEEEGE", "HK###KH", "HK###KH", "HK###KH", "EGEEEGE")
                    .aisle("EGEEEGE", "HK#O#KH", "HK#O#KH", "HK#O#KH", "EGEYEGE")
                    .aisle("EGEEEGE", "HK###KH", "HK###KH", "HK###KH", "EGEEEGE")
                    .aisle(" FGGGF ", " LKKKL ", " LKKKL ", " LKKKL ", " FGGGF ")
                    .aisle("  E@E  ", "  HHH  ", "  HHH  ", "  HHH  ", "  EEE  ")
                    .where('@', controller(blocks(definition.get())))
                    .where('#', air())
                    .where(' ', any())
                    .where('K', heatingCoils())
                    .where('L', frames(GTMaterials.Steel))
                    .where('G', blocks(CASING_STEEL_SOLID.get()))
                    .where('F', blocks(FIREBOX_STEEL.get()))
                    .where('O', blocks(CASING_STEEL_PIPE.get()))
                    .where('H', blocks(CASING_TEMPERED_GLASS.get()))
                    .where('E', blocks(MACHINE_CASING_ULV.get())
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS))
                            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS))
                            .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                    .where('Y', ability(PartAbility.MUFFLER).setExactLimit(1))
                    .build())
            .workableCasingModel(
                    GTCEu.id("block/casings/voltage/ulv/side") ,
                    GTCEu.id("block/multiblock/pyrolyse_oven"))
            .register();

    // registered using the addon namespace from EAMachineUtils
    public static MultiblockMachineDefinition[] ADVANCED_FUSION_REACTOR = TieredMultis("advanced_fusion_reactor",
            FusionReactorMachine::new, (tier, builder) -> builder
                    .rotationState(RotationState.ALL)
                    .langValue("Advanced Fusion Reactor MK %s"
                            .formatted(toRomanNumeral(tier - 5)))
                    .tooltips(
                            Component.translatable("gtceu.machine.fusion_reactor.capacity",
                                    FusionReactorMachine.calculateEnergyStorageFactor(tier, 16) / 1000000L),
                            Component.translatable("gtceu.machine.fusion_reactor.overclocking"),
                            Component.translatable("gtceu.multiblock.%s_fusion_reactor.description"
                                    .formatted(VN[tier].toLowerCase(Locale.ROOT))))
                    .recipeType(GTRecipeTypes.FUSION_RECIPES)
                    .recipeModifiers(DEFAULT_ENVIRONMENT_REQUIREMENT,
                            FusionReactorMachine::recipeModifier,
                            EARecipeModifiers.TIERED_PARALLEL)
                    // each reactor has its own parallel modifier, LuV -> 4, ZPM -> 8, UV -> 16
                    .appearanceBlock(() -> FusionReactorMachine.getCasingState(tier))
                    .pattern((definition) -> {

                        var casing = blocks(FusionReactorMachine.getCasingState(tier));

                        return FactoryBlockPattern.start()
                                .aisle("               ", "     2CCC2     ", "               ")
                                .aisle("     1FFF1     ", "   GF#####FG   ", "     1FFF1     ")
                                .aisle("   FF     FF   ", "  FHH2CCC2HHF  ", "   FF     FF   ")
                                .aisle("  F         F  ", " GHFG     GFHG ", "  F         F  ")
                                .aisle("  F         F  ", " FHG       GHF ", "  F         F  ")
                                .aisle(" 1           1 ", "2#2         2#2", " 1           1 ")
                                .aisle(" F           F ", "C#C         C#C", " F           F ")
                                .aisle(" F           F ", "C#C         C#C", " F           F ")
                                .aisle(" F           F ", "C#C         C#C", " F           F ")
                                .aisle(" 1           1 ", "2#2         2#2", " 1           1 ")
                                .aisle("  F         F  ", " FHG       GHF ", "  F         F  ")
                                .aisle("  F         F  ", " GHFG     GFHG ", "  F         F  ")
                                .aisle("   FF     FF   ", "  FHH2FFF2HHF  ", "   FF     FF   ")
                                .aisle("     1FFF1     ", "   GF#####FG   ", "     1FFF1     ")
                                .aisle("               ", "     2C@C2     ", "               ")
                                .where('@', controller(Predicates.blocks(definition.get())))
                                .where('#', Predicates.air())
                                .where(' ', Predicates.any())
                                .where('C', blocks(FUSION_GLASS.get()).or(casing))
                                .where('F', casing)
                                .where('1', casing.or(
                                        Predicates.abilities(PartAbility.IMPORT_FLUIDS).setMaxGlobalLimited(16)))
                                .where('2', casing.or(
                                        Predicates.abilities(PartAbility.EXPORT_FLUIDS).setMaxGlobalLimited(16)))
                                .where('G', casing
                                        .or(blocks(PartAbility.INPUT_ENERGY.getBlockRange(tier, UV)
                                                .toArray(Block[]::new))
                                                    .setMinGlobalLimited(1)
                                                    .setPreviewCount(16)))
                                .where('H', blocks(FusionReactorMachine.getCoilState(tier)))
                                .build();
                    })
                    .modelProperty(GTMachineModelProperties.RECIPE_LOGIC_STATUS, RecipeLogic.Status.IDLE)
                    .model(
                            createWorkableCasingMachineModel(FusionReactorMachine.getCasingType(tier).getTexture(),
                            GTCEu.id("block/multiblock/fusion_reactor"))
                            .andThen(b -> b
                                    .addDynamicRenderer(DynamicRenderHelper::createFusionRingRender)))
                    .hasBER(true)
                    .register(),
            LuV, ZPM, UV);

    public static MultiblockMachineDefinition COMPACT_ASSEMBLY_LINE = EXTRA_ADDITIONS_REGISTRATE
            .multiblock("compact_assembly_line", WorkableElectricMultiblockMachine::new)
            .tooltips(Component.translatable("extraadditions.machine.compact_assembly_line.tooltip.0"))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(ASSEMBLY_LINE_RECIPES)
            .recipeModifiers(EARecipeModifiers.SIMPLE_PARALLEL.apply(4), GTRecipeModifiers.OC_NON_PERFECT)
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("ENE", "EKE", "EHE")
                    .aisle("EDE", "RLR", "EHE")
                    .aisle("EDE", "RKR", "EHE")
                    .aisle("EDE", "RLR", "EHE")
                    .aisle("EDE", "RKR", "EHE")
                    .aisle("EDE", "RLR", "EHE")
                    .aisle("E@E", "EKE", "EHE")
                    .where('@', controller(blocks(definition.get())))
                    .where('E', blocks(CASING_STEEL_SOLID.get()))
                    .where('L', blocks(CASING_ASSEMBLY_CONTROL.get()))
                    .where('K', blocks(CASING_ASSEMBLY_LINE.get()))
                    .where('H', blocks(CASING_GRATE.get()))
                    .where('R', blocks(CASING_LAMINATED_GLASS.get()))
                    .where('D', blocks(CASING_STEEL_SOLID.get())
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(1))
                            .or(dataHatchPredicate(blocks(CASING_STEEL_SOLID.get()))))
                    .where('N', Predicates.abilities(PartAbility.EXPORT_ITEMS))
                    .build())
            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_solid_steel") ,
                    GTCEu.id("block/multiblock/assembly_line"))
            .register();
}