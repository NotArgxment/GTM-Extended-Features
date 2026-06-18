# What does this addon offers?
## New Multiblocks
| Abbreviation |         Machine           | Description                                                                                                                                                             |
|:------------:|:-------------------------:|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|      RAM     | Robust Alloy Materializer | Alloy Blast Smelter that allows parallel hatches and laser hatches                                                                                                      |
|      ACU     |   Advanced Cracking Unit  | Parallel Cracker                                                                                                                                                        |
|      ERC     | Enlarged Reaction Chamber | Parallel Large Chemical Reactor                                                                                                                                         |
|      LPU     |    Large Pyrolysis Unit   | Parallel Pyro Oven                                                                                                                                                      |
|      AFR     |  Advanced Fusion Reactors | Fusion Reactors that can perform recipes in parallel using custom parallel logic per tier                                                                               |
|      CAL     |   Compact Assembly Line   | Assembly line that doesnt need ordered inputs in order to work, and runs 4 parallels by itself                                                                          |
|      RPP     |   Rock Processing Plant   | Allows the player to turn common types of rocks into the inputs get by its normal processing, in simple words: Macerator > Centrifuge > Electrolyzer all in one machine |
|      IGh     |   Industrial Greenhouse   | Allows passive income of any type of wood type                                                                                                                          |
|      TGC     |    Tree Growing Chamber   | IV Version of the IGh, can use parallel hatches                                                                                                                         |
|      DA      |       Dissasembler        | A multiblock designed to recycle machines, returninig all components used on it, instead of the materials (arc scrapping/macerating)                                    |

## Utilities
Special XOR logic for multiblock formation with laser hatches

| Laser | Energy Hatch | Multiblock Formation |
|:-----:|:------------:|:--------------------:|
|   0   |       0      |           0          |
|   1   |       0      |           1          |
|   0   |       1      |           1          |
|   1   |       1      |           0          |

### Custom parallel recipes modifiers:
### Indepedent Parallels - Used by the Compact Assembly Line and Industrial Greenhouse
```java
    private static final int MAX_SIMPLE_PARALLEL = 256;

    public static final IntFunction<RecipeModifier> SIMPLE_PARALLEL = parallels -> {
        if (parallels == 1) return RecipeModifier.NO_MODIFIER;

        return (machine, recipe) -> {
            int achievable = ParallelLogic.getParallelAmountWithoutEU(machine, recipe, parallels);
            if (achievable <= 1) return ModifierFunction.IDENTITY;
            return ModifierFunction.builder()
                    .modifyAllContents(ContentModifier.multiplier(achievable))
                    .durationMultiplier(2)
                    .parallels(achievable)
                    .build();
        };
    };
```
### Tiered Parallel - Used by the Advanced Fusion Reactors
```java
    public static final RecipeModifier TIERED_PARALLEL = (machine, recipe) -> {
        int tier = machine.self().getDefinition().getTier();
        int parallels = switch (tier) {
            case GTValues.LuV -> 4;
            case GTValues.ZPM -> 8;
            case GTValues.UV  -> 16;
            default -> 1;
        };
```

- ULV to UV color based gradients


- The addition of Universal Circuits

### credits to witherstar (monifactory contributor)

## Future additions:
- Wireless Optical/Computation Components (Covers format)
- Large Air Reprocessor [LARp]
- Implossion Chamber [IC]
- Quantum Plasma Forge [QPF]
- Chemical skips for the ERC regarding specific lines required for progression
- Configuration integration for easier customization regardless modpacks
