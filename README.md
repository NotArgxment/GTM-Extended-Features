# GTM Extended Features
Addon for GTCEu Modern (Minecraft 1.20.1, Forge) that adds "QoL" multiblocks based on existing GTCEu machines, with extra benefits (parallels, laser hatches, compressed recipes) at the cost of more resources to build. Designed for Gregtech-style progression modpacks that want to ease bottlenecks without breaking the original balance

# What does this addon offers?
## New QoL Multiblocks
| Abbreviation |         Machine           | Description                                                                                                                                                             |
|:------------:|:-------------------------:|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|      RAM     | Robust Alloy Materializer | Alloy Blast Smelter that allows parallel hatches and laser hatches                                                                                                      |
|      ACU     |   Advanced Cracking Unit  | Parallel Cracker                                                                                                                                                        |
|      ERC     | Enlarged Reaction Chamber | Parallel Large Chemical Reactor                                                                                                                                         |
|      LPU     |    Large Pyrolysis Unit   | Parallel Pyro Oven                                                                                                                                                      |
|      AFR     |  Advanced Fusion Reactors | Fusion Reactors that can perform recipes in parallel using custom parallel logic per tier                                                                               |
|      CAL     |   Compact Assembly Line   | Assembly line that doesnt need ordered inputs in order to work, and runs 4 parallels by itself                                                                          |
|      RPP     |   Rock Processing Plant   | Allows the player to turn common types of rocks into the inputs get by its normal processing, in simple words: Macerator > Centrifuge > Electrolyzer all in one machine |
|      IGh     |   Industrial Greenhouse   | Allows passive income of any type of wood                                                                                                                               |
|      TGC     |    Tree Growing Chamber   | IV Version of the IGh, can use parallel hatches                                                                                                                         |
|      DA      |       Disassembler        | A multiblock designed to recycle machines, returninig all components used on it, instead of the materials (arc scrapping/macerating)                                    |

## Developer utilities

If you're building your own GTCEu addon and want to depend on this one, it exposes reusable utilities in both Java and KubeJS.

### Laser Hatch-capable multiblocks (Java only)

Custom formation logic for multiblocks that can accept either Laser Hatches or Energy Hatches, available for both `Workable` and `CoilWorkable` multiblock types:

| Laser | Energy Hatch | Valid formation? |
|:---:|:---:|:---:|
| 0 | 0 | ❌ |
| 1 | 0 | ✅ |
| 0 | 1 | ✅ |
| 1 | 1 | ❌ (both at once isn't allowed) |

```java
import com.argxment.extendedfeatures.init.utils.WorkableMultiblockLaser;
import com.argxment.extendedfeatures.init.utils.CoilWorkableMultiblockLaser;

public class Machines {

    public static MultiblockMachineDefinition TEST_MULTIBLOCK = REGISTER
            .multiblock("coil_laser_multiblock", CoilWorkableMultiblockLaser::new)
            // rest of the definition...

    public static MultiblockMachineDefinition TEST_MULTIBLOCK_2 = REGISTER
            .multiblock("regular_laser_multiblock", WorkableMultiblockLaser::new)
            // rest of the definition...
}
```

> There's no KubeJS example for this because on the KubeJS side it already works fine — the formation issue only shows up for multiblocks defined in Java.

### Recipe Modifiers

#### Simple Parallel
Lets a multiblock run in parallel **without needing a physical parallel hatch**. EU/t stays the same, but duration is multiplied ×2 for each parallel level achieved.

```java
// Addon as a dependency
import com.argxment.extendedfeatures.init.utils.RecipeModifiers;

// value: int between 0 and 256
.recipeModifiers(RecipeModifiers.SIMPLE_PARALLEL.apply(value))
```
```javascript
const RecipeModifiers = Java.loadClass('com.argxment.extendedfeatures.init.utils.RecipeModifiers')

.recipeModifiers(RecipeModifiers.SIMPLE_PARALLEL.apply(value))
```

#### Tiered Parallel
Automatic parallels based on the machine's tier (currently used by the Advanced Fusion Reactors). **Must** be used on a tiered multiblock (`TieredMultiblockMachineDefinition` or similar):

- LuV → 4 parallels
- ZPM → 8 parallels
- UV → 16 parallels

```java
import com.argxment.extendedfeatures.init.utils.RecipeModifiers;

.recipeModifiers(RecipeModifiers.TIERED_PARALLEL)
```
```javascript
const RecipeModifiers = Java.loadClass('com.argxment.extendedfeatures.init.utils.RecipeModifiers')

.recipeModifiers(RecipeModifiers.TIERED_PARALLEL)
```

### LV to MAX color based gradients

<img width="482" height="458" alt="Howeachlooks-ezgif com-video-to-gif-converter (1)" src="https://github.com/user-attachments/assets/f8a2ef73-22e1-43cf-aab7-d034d8df3da2" />

### Universal Circuits
<img width="440" height="42" alt="image" src="https://github.com/user-attachments/assets/4e347367-ca26-4c73-9624-fb84a8e2e53f" />

Textures made by Witherstar (Monifactory Contributor)

## Future additions:
### Features
- Chemical skips for the ERC regarding specific lines required for progression
- Configuration integration for easier customization in modpacks

### Multiblocks
- Oil Refinery Facility: Skip the regular petrochem lines to get direct outputs
- Large Air Reprocessor: 1-Step air processing (Normal air, Nether air and Ender Air)
