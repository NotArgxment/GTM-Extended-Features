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
import com.extendedfeatures.init.utils.WorkableMultiblockLaser;
import com.extendedfeatures.init.utils.CoilWorkableMultiblockLaser;

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
Allows any multiblock to run parallels **without a parallel hatch part in its structure**. EU/t stays the same, but duration is multiplied ×2 for each parallel level achieved.

```java
// Addon as a dependency

// value: int between 0 and 256
.recipeModifiers(
        SIMPLE_PARALLEL.apply(value)
)
```
```javascript
const ExtendedModifier = Java.loadClass('com.extendedfeatures.init.utils.RecipeModifiers')

.recipeModifiers(
      ExtendedModifier.SIMPLE_PARALLEL.apply(value)
)
```

#### Tiered Parallel
Automatic parallels based on the machine's tier (currently used by the Advanced Fusion Reactors). **Must** be used on a tiered multiblock (`TieredMultiblockMachineDefinition` or similar):

- LuV → 4 parallels
- ZPM → 8 parallels
- UV → 16 parallels

```java
import com.extendedfeatures.init.utils.RecipeModifiers;

.recipeModifiers(
        RecipeModifiers.TIERED_PARALLEL
        )
```
```javascript
const RecipeModifiers = Java.loadClass('com.extendedfeatures.init.utils.RecipeModifiers')

.recipeModifiers(
      RecipeModifiers.TIERED_PARALLEL
)
```

### Gradients based on the GTCEu Energy Tiers (LV -> MAX)

<img width="482" height="458" alt="Howeachlooks-ezgif com-video-to-gif-converter (1)" src="https://github.com/user-attachments/assets/f8a2ef73-22e1-43cf-aab7-d034d8df3da2" />

### How to use
`TooltipHelper.RAINBOW_HSL_SLOW` can be replaced with `CustomTooltipStyles.(TIER)_GRADIENT`
> Check [CustomTooltipStyles.java](https://github.com/NotArgxment/GTM-Extended-Features/blob/main/src/main/java/com/argxment/extendedfeatures/client/init/utils/CustomTooltipStyles.java) for the tier you want to use as tooltip

### Examples
```java
    // Advanced Fusion Reactors tooltipBuilder example
    .tooltipBuilder((stack, list) -> list.add(
        Component.translatable("extendedfeatures.%s_advanced_fusion_reactor.tooltip.0"
            .formatted(VN[tier].toLowerCase(Locale.ROOT)))
        .append(
            Component.translatable("extendedfeatures.%s_advanced_fusion_reactor.tooltip.1"
                .formatted(VN[tier].toLowerCase(Locale.ROOT)))
                    .withStyle(CustomTooltipStyles.forTier(tier)))))
```
```java
    // Logic behind the "tiered tooltip" 
    public static final Map<Integer, UnaryOperator<Style>> TIER_GRADIENTS = Map.of(
            GTValues.LuV, LuV_GRADIENT,
            GTValues.ZPM, ZPM_GRADIENT,
            GTValues.UV, UV_GRADIENT
    );

    public static UnaryOperator<Style> forTier(int tier) {
        return TIER_GRADIENTS.getOrDefault(
                tier, LuV_GRADIENT
        );
    }
```
```java
    // Less complex example
    .tooltipBuilder((stack, list) -> {
        list.add(
            Component.translatable("extendedfeatures.fancytooltip.tooltip.0")
                .append(
                    Component.translatable("extendedfeatures.fancytooltip.tooltip.3")
                        .withStyle(CustomTooltipStyles.HV_GRADIENT))
        );
    })
```
