package com.extendedfeatures.init.utils;

import com.extendedfeatures.CreativeTabs;
import com.extendedfeatures.client.RecipeTypes;
import com.extendedfeatures.client.integrations.Configuration.EFConfig;
import com.extendedfeatures.init.utils.internal.CustomShapeInfos;
import com.extendedfeatures.init.utils.internal.disassembler.DisassemblerMachine;
import com.extendedfeatures.init.utils.internal.optical.WirelessAbilities;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.*;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.pattern.*;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderHelper;
import com.gregtechceu.gtceu.client.util.TooltipHelper;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import java.util.Locale;

import static com.extendedfeatures.ExtendedFeaturesCore.*;
import static com.extendedfeatures.client.MachineUtils.*;
import static com.extendedfeatures.client.RecipeTypes.*;
import static com.extendedfeatures.init.utils.RecipeModifiers.*;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GCYMBlocks.*;
import static com.gregtechceu.gtceu.common.data.GCYMRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeModifiers.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.*;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toRomanNumeral;

public class Multiblocks {

    static {
        ExtendedFeaturesRegister.creativeModeTab(() -> CreativeTabs.MULTIBLOCKS_TAB);
    }

    public static MultiblockMachineDefinition ROBUST_ALLOY_MATERIALIZER = null;
    public static MultiblockMachineDefinition LARGE_CRACKING_MACHINE = null;
    public static MultiblockMachineDefinition SYNTHESIS_VESSEL = null;
    public static MultiblockMachineDefinition LARGE_PYROLYSE_OVEN = null;
    public static MultiblockMachineDefinition COMPACT_ASSEMBLY_LINE = null;
    public static MultiblockMachineDefinition ROCK_PROCESSING_PLANT = null;
    public static MultiblockMachineDefinition INDUSTRIAL_GREENHOUSE = null;
    public static MultiblockMachineDefinition TREE_GROWING_CHAMBER = null;
    public static MultiblockMachineDefinition DISASSEMBLER = null;
    public static MultiblockMachineDefinition[] ADVANCED_FUSION_REACTORS = null;

    static {
        if (EFConfig.INSTANCE.Multiblocks.RobustAlloyMaterializer || GTCEu.isDataGen()) {
            ROBUST_ALLOY_MATERIALIZER = ExtendedFeaturesRegister
                    .multiblock("robust_alloy_materializer", CoilWorkableMultiblockLaser::new)
                    .tooltips(
                            Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.0"),
                            Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.1"),
                            Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.2")
                    )
                    .tooltipBuilder((stack, list) -> list.add(
                            Component.translatable("extendedfeatures.regular.tooltip.1")
                            .append(Component.translatable("extendedfeatures.styled.tooltip.1")
                                    .withStyle(TooltipHelper.RAINBOW_HSL_SLOW))
                            .append(Component.translatable("extendedfeatures.regular.tooltip.2")
                                .append(Component.translatable("extendedfeatures.styled.tooltip.2")
                                    .withStyle(TooltipHelper.RAINBOW_HSL_SLOW))))
                    )
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(ALLOY_BLAST_RECIPES)
                    .recipeModifiers(
                            PARALLEL_HATCH,
                            OC_NON_PERFECT,
                            BATCH_MODE,
                            GTRecipeModifiers::ebfOverclock
                    )
                    .appearanceBlock(CASING_TUNGSTENSTEEL_ROBUST)
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
                            .where('X', heatingCoils())
                            .where('T', air())
                            .where(' ', any())
                            .where('C', blocks(CASING_TUNGSTENSTEEL_ROBUST.get())
                                    .or(Predicates.abilities(PartAbility.IMPORT_ITEMS))
                                    .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS))
                                    .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(1).setPreviewCount(1)
                                            .or(Predicates.abilities(PartAbility.INPUT_LASER).setMaxGlobalLimited(1).setPreviewCount(1)))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                            .where('B', Predicates.blocks(CASING_HIGH_TEMPERATURE_SMELTING.get()))
                            .where('F', Predicates.blocks(CASING_TUNGSTENSTEEL_PIPE.get()))
                            .where('E', Predicates.blocks(HEAT_VENT.get()))
                            .where('L', ability(PartAbility.MUFFLER).setExactLimit(1))
                            .build())
                    .recoveryItems(() -> new ItemLike[] {
                            GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash)
                    })
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                            GTCEu.id("block/multiblock/gcym/blast_alloy_smelter")
                    )
                    .additionalDisplay((controller, components) -> {
                        if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
                            components.add(
                                    Component.translatable("gtceu.multiblock.blast_furnace.max_temperature",
                                    Component.translatable(
                                            FormattingUtil
                                                    .formatNumbers(coilMachine.getCoilType().getCoilTemperature() + 100L * Math.max(0, coilMachine.getTier() - GTValues.MV)) + "K")
                                            .setStyle(Style.EMPTY.withColor(ChatFormatting.RED))));
                        }
                    })
                    .shapeInfos(CustomShapeInfos::RobustAlloyMaterializer)
                    .register();
        }
    }

    static {
        if (EFConfig.INSTANCE.Multiblocks.LargeCrackingMachine || GTCEu.isDataGen()) {
            LARGE_CRACKING_MACHINE = ExtendedFeaturesRegister
                    .multiblock("large_cracking_machine", CoilWorkableElectricMultiblockMachine::new)
                    .tooltips(
                            Component.translatable("gtceu.machine.cracker.tooltip"),
                            Component.translatable("gtceu.machine.cracker.tooltip.1")
                    )
                    .tooltipBuilder((stack, list) -> list.add(
                            Component.translatable("extendedfeatures.regular.tooltip.1")
                            .append(Component.translatable("extendedfeatures.styled.tooltip.2")
                                    .withStyle(TooltipHelper.RAINBOW_HSL_SLOW))))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(CRACKING_RECIPES)
                    .recipeModifiers(
                            PARALLEL_HATCH,
                            OC_NON_PERFECT,
                            BATCH_MODE,
                            GTRecipeModifiers::crackerOverclock)
                    .appearanceBlock(CASING_TUNGSTENSTEEL_ROBUST)
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
                            .where('I', blocks(CASING_LAMINATED_GLASS.get()))
                            .where('D', blocks(CASING_TUNGSTENSTEEL_PIPE.get()))
                            .where('F', blocks(CASING_TUNGSTENSTEEL_ROBUST.get())
                                    .or(Predicates.abilities(PartAbility.IMPORT_ITEMS))
                                    .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS))
                                    .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                            .where('Y', ability(PartAbility.MUFFLER).setExactLimit(1))
                            .build())
                    .recoveryItems(() -> new ItemLike[] {
                            GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash)
                    })
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                            GTCEu.id("block/multiblock/cracking_unit")
                    )
                    .additionalDisplay((controller, components) -> {
                        if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
                            components.add(Component.translatable("gtceu.multiblock.cracking_unit.energy",
                                    100 - 10 * coilMachine.getCoilTier()));
                        }
                    })
                    .shapeInfos(CustomShapeInfos::LargeCrackingMachine)
                    .register();
        }
    }

    static {
        if (EFConfig.INSTANCE.Multiblocks.SynthesisVessel || GTCEu.isDataGen()) {
            SYNTHESIS_VESSEL = ExtendedFeaturesRegister
                    .multiblock("synthesis_vessel", WorkableElectricMultiblockMachine::new)
                    .tooltips(
                            Component.translatable("extendedfeatures.enlarged_reaction_chamber.tooltip.0"),
                            Component.translatable("extendedfeatures.enlarged_reaction_chamber.tooltip.1"))
                    .tooltipBuilder((stack, list) -> list.add(
                            Component.translatable("extendedfeatures.regular.tooltip.1")
                            .append(Component.translatable("extendedfeatures.styled.tooltip.2")
                                    .withStyle(TooltipHelper.RAINBOW_HSL_SLOW))))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeTypes(LARGE_CHEMICAL_RECIPES, CHEMICAL_REDUCTION)
                    .recipeModifiers(
                            CUSTOM_PARALLEL.apply(8),
                            OC_PERFECT,
                            BATCH_MODE
                    )
                    .appearanceBlock(CASING_PTFE_INERT)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle(" FDDDF ", " FDDDF ", " FDDDF ")
                            .aisle("FDDDDDF", "FCK#KCF", "FDDDDDF")
                            .aisle("DDDDDDD", "DKK#KKD", "DDDDDDD")
                            .aisle("DDDDDDD", "D##K##D", "DDDDDDD")
                            .aisle("DDDDDDD", "DKK#KKD", "DDDDDDD")
                            .aisle("FDDDDDF", "FCK#KCF", "FDDDDDF")
                            .aisle(" FDDDF ", " FD@DF ", " FDDDF ")
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
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_inert_ptfe"),
                            GTCEu.id("block/multiblock/large_chemical_reactor"))
                    .register();
        }
    }

    static {
        if (EFConfig.INSTANCE.Multiblocks.LargePyrolysisOven || GTCEu.isDataGen()) {
            LARGE_PYROLYSE_OVEN = ExtendedFeaturesRegister
                    .multiblock("large_pyrolysis_oven", CoilWorkableElectricMultiblockMachine::new)
                    .tooltips(
                            Component.translatable("gtceu.machine.pyrolyse_oven.tooltip"),
                            Component.translatable("gtceu.machine.pyrolyse_oven.tooltip.1"))
                    .tooltipBuilder((stack, list) -> list.add(
                            Component.translatable("extendedfeatures.regular.tooltip.1")
                            .append(Component.translatable("extendedfeatures.styled.tooltip.2")
                                    .withStyle(TooltipHelper.RAINBOW_HSL_SLOW))))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(PYROLYSE_RECIPES)
                    .recipeModifiers(
                            PARALLEL_HATCH,
                            OC_PERFECT,
                            GTRecipeModifiers::pyrolyseOvenOverclock)
                    .appearanceBlock(CASING_TUNGSTENSTEEL_ROBUST)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("  EEE  ", "  HHH  ", "  HHH  ", "  HHH  ", "  EEE  ")
                            .aisle(" FGGGF ", " GKKKG ", " GKKKG ", " GKKKG ", " FGGGF ")
                            .aisle("EGEEEGE", "HK###KH", "HK###KH", "HK###KH", "EGEEEGE")
                            .aisle("EGEEEGE", "HK#O#KH", "HK#O#KH", "HK#O#KH", "EGEYEGE")
                            .aisle("EGEEEGE", "HK###KH", "HK###KH", "HK###KH", "EGEEEGE")
                            .aisle(" FGGGF ", " GKKKG ", " GKKKG ", " GKKKG ", " FGGGF ")
                            .aisle("  E@E  ", "  HHH  ", "  HHH  ", "  HHH  ", "  EEE  ")
                            .where('@', controller(blocks(definition.get())))
                            .where('#', air())
                            .where(' ', any())
                            .where('K', heatingCoils())
                            .where('G', blocks(CASING_HIGH_TEMPERATURE_SMELTING.get()))
                            .where('F', blocks(HEAT_VENT.get()))
                            .where('O', blocks(CASING_TUNGSTENSTEEL_PIPE.get()))
                            .where('H', blocks(CASING_LAMINATED_GLASS.get()))
                            .where('E', blocks(CASING_TUNGSTENSTEEL_ROBUST.get())
                                    .or(Predicates.abilities(PartAbility.IMPORT_ITEMS))
                                    .or(Predicates.abilities(PartAbility.EXPORT_ITEMS))
                                    .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS))
                                    .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                            .where('Y', ability(PartAbility.MUFFLER).setExactLimit(1))
                            .build())
                    .recoveryItems(() -> new ItemLike[] {
                            GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash)
                    })
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                            GTCEu.id("block/multiblock/pyrolyse_oven")
                    )
                    .additionalDisplay((controller, components) -> {
                        if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
                            components.add(Component.translatable("gtceu.multiblock.pyrolyse_oven.speed",
                                    coilMachine.getCoilTier() == 0 ? 75 : 50 * (coilMachine.getCoilTier() + 1)));
                        }
                    })
                    .shapeInfos(CustomShapeInfos::LargePyrolysisOven)
                    .register();
        }
    }

    static {
        if (EFConfig.INSTANCE.Multiblocks.AdvancedFusionReactors || GTCEu.isDataGen()) {
            ADVANCED_FUSION_REACTORS = TieredMultis("advanced_fusion_reactor",
                    FusionReactorMachine::new, (tier, builder) -> builder
                            .rotationState(RotationState.ALL)
                            .langValue("Advanced Fusion Reactor MK %s"
                                    .formatted(toRomanNumeral(tier - 5)))
                            .tooltips(
                                    Component.translatable("gtceu.machine.fusion_reactor.capacity",
                                            FusionReactorMachine.calculateEnergyStorageFactor(tier, 16) / 1000000L),
                                    Component.translatable("gtceu.machine.fusion_reactor.overclocking"))
                            .tooltipBuilder((stack, list) -> list.add(
                                    Component.translatable("extendedfeatures.%s_advanced_fusion_reactor.tooltip.0"
                                                    .formatted(VN[tier].toLowerCase(Locale.ROOT)))
                                            .append(Component.translatable("extendedfeatures.%s_advanced_fusion_reactor.tooltip.1"
                                                            .formatted(VN[tier].toLowerCase(Locale.ROOT)))
                                                    .withStyle(CustomTooltipStyles.forTier(tier)))))
                            .recipeType(GTRecipeTypes.FUSION_RECIPES)
                            .recipeModifiers(FusionReactorMachine::recipeModifier, TIERED_PARALLEL)
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
                                        .aisle("   FF     FF   ", "  FHH2CCC2HHF  ", "   FF     FF   ")
                                        .aisle("     1FFF1     ", "   GF#####FG   ", "     1FFF1     ")
                                        .aisle("               ", "     2C@C2     ", "               ")
                                        .where('@', controller(Predicates.blocks(definition.get())))
                                        .where('#', Predicates.air())
                                        .where(' ', Predicates.any())
                                        .where('C', casing.or(blocks(FUSION_GLASS.get())))
                                        .where('F', casing)
                                        .where('1', casing
                                                .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setMaxGlobalLimited(16)))
                                        .where('2', casing
                                                .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS).setMaxGlobalLimited(16)))
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
                                    createWorkableCasingMachineModel(
                                            FusionReactorMachine.getCasingType(tier).getTexture(),
                                            GTCEu.id("block/multiblock/fusion_reactor"))
                                            .andThen(b -> b
                                                    .addDynamicRenderer(DynamicRenderHelper::createFusionRingRender)))
                            .hasBER(true)
                            .shapeInfos(CustomShapeInfos::AdvancedFusionReactor)
                            .register(),
                    LuV, ZPM, UV);
        }
    }

    static {
        if (EFConfig.INSTANCE.Multiblocks.CompactAssemblyLine || GTCEu.isDataGen()) {
            COMPACT_ASSEMBLY_LINE = ExtendedFeaturesRegister
                    .multiblock("compact_assembly_line", WorkableElectricMultiblockMachine::new)
                    .tooltips(
                            Component.translatable("extendedfeatures.compact_assembly_line.tooltip.0"),
                            Component.translatable("extendedfeatures.compact_assembly_line.tooltip.1"),
                            Component.translatable("extendedfeatures.compact_assembly_line.tooltip.2")
                    )
                    .tooltipBuilder((stack, list) -> list.add(
                            Component.translatable("extendedfeatures.compact_assembly_line.tooltip.3")
                            .append(Component.translatable("extendedfeatures.styled.tooltip.3")
                                    .withStyle(TooltipHelper.RAINBOW_HSL_FAST))))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(ASSEMBLY_LINE_RECIPES)
                    .recipeModifiers(
                            CUSTOM_PARALLEL.apply(4),
                            OC_NON_PERFECT
                    )
                    .appearanceBlock(CASING_STEEL_SOLID)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("ENE", "EKE", "EHE")
                            .aisle("EDE", "RLR", "EHE")
                            .aisle("EDE", "RKR", "EHE")
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
                                    .or(Predicates.abilities(WirelessAbilities.WIRELESS_OPTICAL_RECEIVER).setMaxGlobalLimited(1))
                            )
                            .where('N', Predicates.abilities(PartAbility.EXPORT_ITEMS))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                            GTCEu.id("block/multiblock/assembly_line"))
                    .register();
        }
    }

    static {
        if (EFConfig.INSTANCE.Multiblocks.RockProcessingPlant || GTCEu.isDataGen()) {
            ROCK_PROCESSING_PLANT = ExtendedFeaturesRegister
                    .multiblock("rock_processing_plant", WorkableElectricMultiblockMachine::new)
                    .tooltips(Component.translatable("extendedfeatures.rock_processing_plant.tooltip.0"))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeTypes(ROCK_PROCESSING_RECIPES)
                    .recipeModifiers(OC_NON_PERFECT, BATCH_MODE)
                    .appearanceBlock(CASING_SECURE_MACERATION)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("JJJJJJJ", "JGJGJGJ", "JGJGJGJ", "JJJJJJJ", "       ")
                            .aisle("JKJKJKJ", "JKJKJKJ", "JKJKJKJ", "JKJKJKJ", "       ")
                            .aisle("JKJKJKJ", "JKJKJKJ", "JKJKJKJ", "JKJKJKJ", "       ")
                            .aisle("JKJKJKJ", "JKJKJKJ", "JKJKJKJ", "JKJKJKJ", "       ")
                            .aisle("JJJJJJJ", "JJJIJJJ", "JJJJJJJ", "JJJJJJJ", "       ")
                            .aisle("       ", "   I   ", "       ", "       ", "       ")
                            .aisle(" BCCCB ", " BCICB ", " BCCCB ", " BCCCB ", " BCCCB ")
                            .aisle(" CCCCC ", " CEEEC ", " CFFFC ", " C###C ", " CGGGC ")
                            .aisle(" CCCCC ", " CEEEC ", " HFFFH ", " C###C ", " CGGGC ")
                            .aisle(" CCCCC ", " CEEEC ", " CFFFC ", " C###C ", " CGGGC ")
                            .aisle(" BCCCB ", " BCCCB ", " BC@CB ", " BCCCB ", " BCCCB ")
                            .where('@', controller(blocks(definition.get())))
                            .where(' ', any())
                            .where('#', air())
                            .where('B', frames(GTMaterials.BlackSteel))
                            .where('E', blocks(CASING_TUNGSTENSTEEL_GEARBOX.get()))
                            .where('I', blocks(LD_ITEM_PIPE.get()))
                            .where('J', blocks(CASING_NONCONDUCTING.get()))
                            .where('K', blocks(ELECTROLYTIC_CELL.get()))
                            .where('F', blocks(CRUSHING_WHEELS.get()))
                            .where('G', blocks(CASING_LAMINATED_GLASS.get()))
                            .where('H', abilities(PartAbility.ROTOR_HOLDER))
                            .where('C', blocks(CASING_SECURE_MACERATION.get())
                                    .or(Predicates.abilities(PartAbility.IMPORT_ITEMS))
                                    .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS))
                                    .or(Predicates.abilities(PartAbility.EXPORT_ITEMS))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/gcym/secure_maceration_casing"),
                            GTCEu.id("block/multiblock/gcym/large_maceration_tower"))
                    .register();
        }
    }

    static {
        if (EFConfig.INSTANCE.Multiblocks.IndustrialGreenhouse || GTCEu.isDataGen()) {
            INDUSTRIAL_GREENHOUSE = ExtendedFeaturesRegister
                    .multiblock("industrial_greenhouse", WorkableElectricMultiblockMachine::new)
                    .tooltips(
                            Component.translatable("extendedfeatures.greenhouse.tooltip.0"),
                            Component.translatable("extendedfeatures.greenhouse.tooltip.1"))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeTypes(
                            GREENHOUSE_CROPS,
                            GREENHOUSE_WOOD)
                    .recipeModifiers(
                            CUSTOM_PARALLEL.apply(8),
                            OC_NON_PERFECT,
                            BATCH_MODE
                    )
                    .appearanceBlock(CASING_STEEL_SOLID)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("    BBB    ", "    DDD    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "           ", "           ", "           ")
                            .aisle("  BBDDDBB  ", "  DDCCCDD  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "    EDE    ", "    EDE    ", "           ")
                            .aisle(" BDDDDDDDB ", " DCCCCCCCD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", "  DE###ED  ", "  DEE#EED  ", "     D     ")
                            .aisle(" BDDDDDDDB ", " DCCGGGCCD ", " E#######E ", " E#######E ", " E#######E ", " E#######E ", " E##HHH##E ", " E##HHH##E ", " E##HHH##E ", "  E#####E  ", "  E#####E  ", "   DEDED   ")
                            .aisle("BDDDDDDDDDB", "DCCGGGGGCCD", "E###III###E", "E####I####E", "E#########E", "E####H####E", "E##HHHHH##E", "E##HHHHH##E", "E##HHHHH##E", " E###H###E ", " EE#####EE ", "   EDDDE   ")
                            .aisle("BDDDDDDDDDB", "DCCGGGGGCCD", "D###III###D", "D###III###D", "D####I####D", "D###HIH###D", "D##HHIHH##D", "D##HHIHH##D", "D##HHIHH##D", " D##HHH##D ", " D#######D ", "  DDDDDDD  ")
                            .aisle("BDDDDDDDDDB", "DCCGGGGGCCD", "E###III###E", "E####I####E", "E#########E", "E####H####E", "E##HHHHH##E", "E##HHHHH##E", "E##HHHHH##E", " E###H###E ", " EE#####EE ", "   EDDDE   ")
                            .aisle(" BDDDDDDDB ", " DCCGGGCCD ", " E#######E ", " E#######E ", " E#######E ", " E#######E ", " E##HHH##E ", " E##HHH##E ", " E##HHH##E ", "  E#####E  ", "  E#####E  ", "   DEDED   ")
                            .aisle(" BDDDDDDDB ", " DCCCCCCCD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", "  DE###ED  ", "  DEE#EED  ", "     D     ")
                            .aisle("  BBDDDBB  ", "  DDCCCDD  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "    EDE    ", "    EDE    ", "           ")
                            .aisle("    BBB    ", "    D@D    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "    EDE    ", "           ", "           ", "           ")
                            .where('@', controller(blocks(definition.get())))
                            .where(' ', any())
                            .where('#', air())
                            .where('C', blocks(Blocks.GRASS_BLOCK))
                            .where('G', blocks(Blocks.ROOTED_DIRT))
                            .where('I', blocks(Blocks.OAK_WOOD))
                            .where('H', blocks(Blocks.OAK_LEAVES))
                            .where('B', blocks(FIREBOX_STEEL.get()))
                            .where('E', blocks(CASING_TEMPERED_GLASS.get()))
                            .where('F', frames(GTMaterials.Steel))
                            .where('D', blocks(CASING_STEEL_SOLID.get())
                                    .or(Predicates.abilities(PartAbility.IMPORT_ITEMS))
                                    .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS))
                                    .or(Predicates.abilities(PartAbility.EXPORT_ITEMS))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                            GTCEu.id("block/multiblock/fusion_reactor"))
                    .register();
        }
    }

    static {
        if (EFConfig.INSTANCE.Multiblocks.TreeGrowingChamber || GTCEu.isDataGen()) {
            TREE_GROWING_CHAMBER = ExtendedFeaturesRegister
                    .multiblock("tree_growing_chamber", WorkableElectricMultiblockMachine::new)
                    .rotationState(RotationState.NON_Y_AXIS)
                    .tooltips(
                            Component.translatable("extendedfeatures.greenhouse.tooltip.0")
                    )
                    .tooltipBuilder((stack, list) -> list.add(
                            Component.translatable("extendedfeatures.regular.tooltip.1")
                            .append(Component.translatable("extendedfeatures.styled.tooltip.2")
                                    .withStyle(TooltipHelper.RAINBOW_HSL_SLOW)))
                    )
                    .recipeTypes(GREENHOUSE_CROPS, GREENHOUSE_WOOD)
                    .recipeModifiers(
                            PARALLEL_HATCH,
                            OC_NON_PERFECT,
                            BATCH_MODE
                    )
                    .appearanceBlock(CASING_TUNGSTENSTEEL_ROBUST)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("    BBB    ", "    DDD    ", "    EDE    ", "    EDE    ", "    EKE    ", "    EKE    ", "    EKE    ", "    EDE    ", "    EDE    ", "           ", "           ", "           ")
                            .aisle("  BBDDDBB  ", "  DDCCCDD  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "    EDE    ", "    EDE    ", "           ")
                            .aisle(" BDDDDDDDB ", " DCCCCCCCD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", "  DE###ED  ", "  DEE#EED  ", "     D     ")
                            .aisle(" BDDDDDDDB ", " DCCGGGCCD ", " E#######E ", " E#######E ", " E#######E ", " E#######E ", " E##HHH##E ", " E##HHH##E ", " E##HHH##E ", "  E#####E  ", "  E#####E  ", "   DEDED   ")
                            .aisle("BDDDDDDDDDB", "DCCGGGGGCCD", "E###III###E", "E####I####E", "E#########E", "E####H####E", "E##HHHHH##E", "E##HHHHH##E", "E##HHHHH##E", " E###H###E ", " EE#####EE ", "   EDDDE   ")
                            .aisle("BDDDDDDDDDB", "DCCGGGGGCCD", "D###III###D", "D###III###D", "K####I####K", "K###HIH###K", "K##HHIHH##K", "D##HHIHH##D", "D##HHIHH##D", " D##HHH##D ", " D#######D ", "  DDDDDDD  ")
                            .aisle("BDDDDDDDDDB", "DCCGGGGGCCD", "E###III###E", "E####I####E", "E#########E", "E####H####E", "E##HHHHH##E", "E##HHHHH##E", "E##HHHHH##E", " E###H###E ", " EE#####EE ", "   EDDDE   ")
                            .aisle(" BDDDDDDDB ", " DCCGGGCCD ", " E#######E ", " E#######E ", " E#######E ", " E#######E ", " E##HHH##E ", " E##HHH##E ", " E##HHH##E ", "  E#####E  ", "  E#####E  ", "   DEDED   ")
                            .aisle(" BDDDDDDDB ", " DCCCCCCCD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", " DF#####FD ", "  DE###ED  ", "  DEE#EED  ", "     D     ")
                            .aisle("  BBDDDBB  ", "  DDCCCDD  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "  DE###ED  ", "    EDE    ", "    EDE    ", "           ")
                            .aisle("    BBB    ", "    D@D    ", "    EDE    ", "    EDE    ", "    EKE    ", "    EKE    ", "    EKE    ", "    EDE    ", "    EDE    ", "           ", "           ", "           ")
                            .where('@', controller(blocks(definition.get())))
                            .where(' ', any())
                            .where('#', air())
                            .where('C', blocks(Blocks.GRASS_BLOCK))
                            .where('G', blocks(Blocks.ROOTED_DIRT))
                            .where('I', blocks(Blocks.OAK_WOOD))
                            .where('H', blocks(Blocks.OAK_LEAVES))
                            .where('K', blocks(CASING_EXTREME_ENGINE_INTAKE.get()))
                            .where('B', blocks(FIREBOX_TUNGSTENSTEEL.get()))
                            .where('E', blocks(CASING_LAMINATED_GLASS.get()))
                            .where('F', frames(GTMaterials.TungstenSteel))
                            .where('D', blocks(CASING_TUNGSTENSTEEL_ROBUST.get())
                                    .or(Predicates.abilities(PartAbility.IMPORT_ITEMS))
                                    .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS))
                                    .or(Predicates.abilities(PartAbility.EXPORT_ITEMS))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                            GTCEu.id("block/multiblock/fusion_reactor"))
                    .register();
        }
    }

    static {
        if (EFConfig.INSTANCE.Multiblocks.Disassembler || GTCEu.isDataGen()) {
            DISASSEMBLER = ExtendedFeaturesRegister
                    .multiblock("disassembler", DisassemblerMachine::new)
                    .tooltips(
                            Component.translatable("extendedfeatures.disassembler.tooltip.0"),
                            Component.translatable("extendedfeatures.disassembler.tooltip.1"))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(RecipeTypes.DISASSEMBLER)
                    .recipeModifiers(PARALLEL_HATCH, OC_NON_PERFECT)
                    .appearanceBlock(CASING_LARGE_SCALE_ASSEMBLING)
                    .pattern(definition -> FactoryBlockPattern.start()
                            .aisle("OOOOOOO", "OOOEEEO", "OOOEEEO")
                            .aisle("OOOOOOO", "OKKKKKO", "OCOEEEO")
                            .aisle("OOOOOOO", "O@OEEEO", "OOOEEEO")
                            .where('@', controller(blocks(definition.get())))
                            .where('#', air())
                            .where('C', blocks(CASING_GRATE.get()))
                            .where('E', blocks(CASING_TEMPERED_GLASS.get()))
                            .where('K', blocks(CASING_TUNGSTENSTEEL_GEARBOX.get()))
                            .where('O', blocks(CASING_LARGE_SCALE_ASSEMBLING.get())
                                    .or(Predicates.abilities(PartAbility.IMPORT_ITEMS))
                                    .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS))
                                    .or(Predicates.abilities(PartAbility.EXPORT_ITEMS))
                                    .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2))
                                    .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                                    .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                            .build())
                    .workableCasingModel(
                            GTCEu.id("block/casings/gcym/large_scale_assembling_casing"),
                            GTCEu.id("block/multiblock/gcym/large_assembler"))
                    .register();
        }
    }

    public static void init() {}
}
