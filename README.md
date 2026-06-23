# What does this addon offers?
# New QoL Multiblocks
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
|      DA      |       Dissasembler        | A multiblock designed to recycle machines, returninig all components used on it, instead of the materials (arc scrapping/macerating)                                    |

# Utilities
**JAVA ONLY**: Custom formation logic for multiblocks that can use laser hatches, implemented for both Workable and CoilWorkable multiblock types

| Laser | Energy Hatch | Multiblock Formation |
|:-----:|:------------:|:--------------------:|
|   0   |       0      |           0          |
|   1   |       0      |           1          |
|   0   |       1      |           1          |
|   1   |       1      |           0          |
```java
import com.argxment.extraadditions.init.utils.CoilMultiblockLaser;
import com.argxment.extraadditions.init.utils.MultiblockLaserCapability;

public class Machines {
    
    public static MultiblockMachineDefinition TEST_MULTIBLOCK = REGISTER
                .multiblock("coil_laser_multiblock", CoilMultiblockLaserCapability::new) 
                // all the respective code below...
    
    public static MultiblockMachineDefinition TEST_MULTIBLOCK = REGISTER
                .multiblock("regular_laser_multiblock", MultiblockLaserCapability::new) 
                // all the respective code below...
```
*You may ask **"Why there is not a kubejs example?"**, the reason is that from kubejs side works perfectly fine, its an issue for multiblocks made on Java*

## Custom parallel recipes modifiers
### Simple parallels:
This recipe modifier allows the multiblock to run parallels without actually requiring a hatch, with an slightly different logic, EU/t remains the same, meanwhile the time is multiplied by 2
### Java
```java
// Addon as depedency
import com.argxment.extraadditions.init.utils.RecipeModifiers;

// Value its an int that goes from 0 to 256
.recipeModifiers(SIMPLE_PARALLEL.apply(value))
```
### KubeJS
```javascript
const RecipeModifiers = Java.loadClass('com.argxment.extraadditions.init.utils.RecipeModifiers')

.recipeModifiers(RecipeModifiers.SIMPLE_PARALLEL.apply(value))
```

### Tiered Parallels:
In this case, it's currently being used by the advanced fusion reactors;
- LuV > 4 Parallels
- ZPM > 8 Parallels
- UV  > 16 Parallels

MUST be used with a tiered multiblock
### Java
```java
import com.argxment.extraadditions.init.utils.RecipeModifiers;

.recipeModifiers(TIERED_PARALLEL)
```
### KubeJS
```javascript
const RecipeModifiers = Java.loadClass('com.argxment.extraadditions.init.utils.RecipeModifiers')

.recipeModifiers(RecipeModifiers.TIERED_PARALLEL)
```


### LV to MAX color based gradients

<img width="482" height="458" alt="Howeachlooks-ezgif com-video-to-gif-converter (1)" src="https://github.com/user-attachments/assets/f8a2ef73-22e1-43cf-aab7-d034d8df3da2" />

### Universal Circuits
<img width="440" height="42" alt="image" src="https://github.com/user-attachments/assets/4e347367-ca26-4c73-9624-fb84a8e2e53f" />

Textures made by Witherstar (Monifactory Contributor)

## Future additions:
### Features
- Wireless Optical/Computation Components (Covers format)
- Chemical skips for the ERC regarding specific lines required for progression
- Configuration integration for easier customization in modpacks

### Multiblocks
- Oil Refinery Facility: Skip the regular petrochem lines to get direct outputs
- Large Air Reprocessor: 1-Step air processing (Normal air, Nether air and Ender Air)
