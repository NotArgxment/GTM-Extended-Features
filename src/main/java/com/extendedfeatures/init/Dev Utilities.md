# Developer utilities
### Laser Hatch-capable multiblocks (Java only)

Custom formation logic for multiblocks that can accept either Laser Hatches or Energy Hatches, available for both `Workable` and `CoilWorkable` multiblock types:

| Laser | Energy Hatch | Valid formation? |
|:---:|:---:|:---:|
| 0 | 0 | ❌ |
| 1 | 0 | ✅ |
| 0 | 1 | ✅ |
| 1 | 1 | ❌ (both at once isn't allowed) |

```java
    public static MultiblockMachineDefinition TEST_MULTIBLOCK = REGISTER
            .multiblock("coil_laser_multiblock", CoilWorkableMultiblockLaser::new)

    public static MultiblockMachineDefinition TEST_MULTIBLOCK_2 = REGISTER
            .multiblock("regular_laser_multiblock", WorkableMultiblockLaser::new)
}
```

> There's no KubeJS example for this because on the KubeJS side it already works fine — the formation issue only shows up for multiblocks defined in Java.

## Recipe Modifiers

### Machine Parallels
Allows any multiblock to run parallels **without a parallel hatch part in its structure**. EU/t stays the same, but duration is multiplied ×2 for each parallel level achieved.

```java
// Java
.recipeModifiers(MACHINE_PARALLEL(n))
```
```javascript
// KubeJS
const CustomParallel = Java.loadClass('com.extendedfeatures.init.utils.RecipeModifiers')

.recipeModifiers(CustomParallel.MACHINE_PARALLEL(value))
```

### Gradients based on the GTCEu Energy Tiers (LV -> MAX)

<img width="482" height="458" alt="Howeachlooks-ezgif com-video-to-gif-converter (1)" src="https://github.com/user-attachments/assets/f8a2ef73-22e1-43cf-aab7-d034d8df3da2" />

### How to use
`TooltipHelper.RAINBOW_HSL_SLOW` can be replaced with `CustomTooltipStyles.(TIER)_GRADIENT`
> Check [CustomTooltipStyles.java](https://github.com/NotArgxment/GTM-Extended-Features/blob/main/src/main/java/com/argxment/extendedfeatures/client/init/utils/CustomTooltipStyles.java) for the tier you want to use as tooltip

### Example
```java
    // Basic tooltip builder
    .tooltipBuilder((stack, list) -> {
        list.add(
            Component.translatable("extendedfeatures.fancytooltip.tooltip.0")
                .append(
                    Component.translatable("extendedfeatures.fancytooltip.tooltip.3")
                        .withStyle(CustomTooltipStyles.HV_GRADIENT))
        );
    })
```
