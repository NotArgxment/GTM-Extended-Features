## Multiblocks:
- Robust alloy materializer [RAM]
- Advanced Cracking Unit [ACU]
- Enlarged Reaction Chamber [ERC]
- Large Pyrolysis Unit [LPU]
- Advanced Fusion Reactors [AFR]
- Compact Assembly Line [CAL]
- Rock Processing Plant [RPF]
- Industrial Greenhouse [IGh]
- Tree Growing Chamber [TGC] (IV Version of the IGh)

## Features:
- XOR logic for multiblock formation with laser hatches
  
| Laser | Energy Hatch | Multiblock Formation |
|:-----:|:------------:|:--------------------:|
|   0   |       0      |           0          |
|   1   |       0      |           1          |
|   0   |       1      |           1          |
|   1   |       1      |           0          |
- Custom parallel recipe modifier, limited to 64
  - SIMPLE_PARALLEL.apply(value)
  - TIERED_PARALLEL (Used on the AFRs)
    - LuV -> 4
    - ZPM -> 8
    - UV -> 16 
- LuV to UV color based gradients

## Future additions:
- Wireless Optical/Computation Components (Covers format)
- Large Air Reprocessor [LARp]
- Implossion Chamber [IC]
- Quantum Plasma Forge [QPF]
- Chemical skips for the ERC regarding specific lines required for progression
- Configuration integration for easier modpack customization
