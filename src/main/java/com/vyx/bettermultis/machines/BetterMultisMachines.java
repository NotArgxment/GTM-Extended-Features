package com.vyx.bettermultis.machines;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.*;
import com.gregtechceu.gtceu.api.pattern.*;
import com.gregtechceu.gtceu.common.data.*;
import com.vyx.bettermultis.machines.extras.LaserLogic;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.*;
import static com.gregtechceu.gtceu.common.data.GCYMRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

import static com.vyx.bettermultis.BetterMultis.BMREGISTRATE;

public class BetterMultisMachines {

    public static void init() {}

    public static MultiblockMachineDefinition ROBUST_ALLOY_MATERIALIZER = BMREGISTRATE
            .multiblock("robust_alloy_materializer", LaserLogic::new)
            .langValue("Robust Alloy Materializer [RAM]")
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

    public static MultiblockMachineDefinition ADVANCED_CRACKING_UNIT = BMREGISTRATE
            .multiblock("advanced_cracking_unit", CoilWorkableElectricMultiblockMachine::new)
            .langValue("Advanced Cracking Unit [ACU]")
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

    public static MultiblockMachineDefinition ENLARGED_REACTION_CHAMBER = BMREGISTRATE
            .multiblock("enlarged_reaction_chamber", WorkableElectricMultiblockMachine::new)
            .langValue("Enlarged Reaction Chamber [ERC]")
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

    public static MultiblockMachineDefinition LARGE_PYROLYSIS_UNIT = BMREGISTRATE
            .multiblock("large_pyrolysis_unit", CoilWorkableElectricMultiblockMachine::new)
            .langValue("Large Pyrolysis Unit [LPU]")
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
}