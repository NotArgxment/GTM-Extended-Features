# Mod features:
### Multiblocks
- Robust Alloy Materializer [RAM]
  - Alloy Blast Smelter that allows parallel hatches and laser hatches
    
- Advanced Cracking Unit [ACU]
  - Parallel Cracker
    
- Enlarged Reaction Chamber [ERC]
  - Parallel Large Chemical Reactor
    
- Large Pyrolysis Unit [LPU]
  - Parallel Pyro Oven
    
- Advanced Fusion Reactors [AFR]
  - Fusion Reactors that can perform recipes in parallel using custom parallel logic per tier
    
- Compact Assembly Line [CAL]
  - Assembly line that doesnt need ordered inputs in order to work, and runs 4 parallels by itself
    
- Rock Processing Plant [RPF]
  - Allows the player to turn common types of rocks into the inputs get by its normal processing
    - In simple words: Macerator > Centrifuge > Electrolyzer all in one machine
      
- Industrial Greenhouse [IGh]
  - Allows passive income of any type of wood type
- Tree Growing Chamber [TGC]
  - IV Version of the IGh, can use parallel hatches

### Utilities
- Special XOR logic for multiblock formation with laser hatches
  
| Laser | Energy Hatch | Multiblock Formation |
|:-----:|:------------:|:--------------------:|
|   0   |       0      |           0          |
|   1   |       0      |           1          |
|   0   |       1      |           1          |
|   1   |       1      |           0          |

- Custom parallel recipe modifier, limited to 256
  - SIMPLE_PARALLEL.apply(value)
  - TIERED_PARALLEL (Used on the AFRs)
    - LuV -> 4
    - ZPM -> 8
    - UV -> 16

- ULV to UV color based gradients

## Future additions:
- Wireless Optical/Computation Components (Covers format)
- Large Air Reprocessor [LARp]
- Implossion Chamber [IC]
- Quantum Plasma Forge [QPF]
- Chemical skips for the ERC regarding specific lines required for progression
- Configuration integration for easier customization regardless modpacks
