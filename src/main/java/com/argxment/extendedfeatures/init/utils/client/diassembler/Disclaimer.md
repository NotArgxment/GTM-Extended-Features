## The whole concept of the disassembler, including its logic, was written WITH HELP of claude, the following summary was made using this same tool, for easier understanding

## Overview

The Disassembler is a custom GTCEu multiblock that, given a GTCEu machine item placed in its input bus, **reverse-engineers the recipe that originally produced it** and returns its components — without needing a static, pre-registered recipe for every possible machine (which would mean hundreds of hand-written recipes).

The system is fully dynamic: recipes are computed on the fly at runtime, based on whatever item is scanned, by walking backward through GTCEu's own recipe data (crafting table, Assembler, Assembly Line).

---

## Components

### 1. `MachineUtil.java` — Is this a machine, and what tier?

```java
public static Optional<Integer> getMachineTier(ItemStack stack)
public static Optional<MachineDefinition> getMachineDefinition(ItemStack stack)
```

The most basic entry point. If the `ItemStack` being analyzed is a `MetaMachineItem` (any GTCEu machine, single-block or multiblock controller), it returns its `MachineDefinition` and, from there, its tier (0 = ULV, 1 = LV, ...). If the stack isn't a GTCEu machine, it returns `Optional.empty()` — this is what makes the disassembler **ignore items that aren't machines** in the first place.

### 2. `RecipeResolver.java` — Searches within a specific `GTRecipeType`

```java
public static Optional<List<ItemStack>> resolveFromGTRecipeType(ServerLevel level, GTRecipeType recipeType, ItemStack targetStack)
```

Takes a GTCEu recipe type (e.g. `ASSEMBLER_RECIPES`, `ASSEMBLY_LINE_RECIPES`) and searches through **all** registered recipes of that type for the one whose **output** exactly matches the `targetStack` being disassembled.

Once that recipe is found:
- `extractItemInputs()` walks its inputs and returns one representative `ItemStack` per ingredient, respecting the real count (`SizedIngredient`).
- **Special case — circuits**: if an ingredient matches one of the `forge:circuits_<tier>` tags (via GTCEu's `CustomTags`), instead of returning an arbitrary circuit from that tag, it returns the addon's own universal circuit for that tier (`findUniversalCircuitReplacement()`). This avoids showing, say, "Advanced Integrated Circuit" when any of the 4 HV circuits would have worked equally well — the universal circuit is always preferred for display.

### 3. `ComponentResolver.java` — The priority orchestrator

```java
public static List<ItemStack> resolve(ServerLevel level, ItemStack targetStack)
```

This is the main entry point. It tries, **in order**, three possible paths to find where the machine came from:

1. **Vanilla 3x3 crafting table** — most simple GTCEu machines are crafted this way. It searches all registered `CraftingRecipe`s for the one that produces `targetStack`, and extracts its ingredients (with the same universal-circuit fix applied here too).
2. **Assembler** — if no crafting-table recipe was found, it tries `GTRecipeTypes.ASSEMBLER_RECIPES` via `RecipeResolver`.
3. **Assembly Line** — if that also fails, it tries `GTRecipeTypes.ASSEMBLY_LINE_RECIPES` (typical for multiblocks/more advanced machines).

Whichever path finds something first wins — it does not keep searching the remaining paths, and there is no merging between sources.

**Duplicate merging**: before returning the final list, it runs `mergeStacks()`, which uses an internal `record StackKey(Item, CompoundTag)` as a key to detect identical items (same item + same NBT) and sum their counts into a single entry. This is what prevents seeing "1x HV Robot Arm" listed twice instead of a single "2x HV Robot Arm".

If none of the three paths find anything, an empty list is returned.

### 4. `DisassemblerRecipeLogic.java` — The brain connecting everything to the multiblock

This is an `enum` singleton (`INSTANCE`) implementing `GTRecipeType.ICustomRecipeLogic`, GTCEu's official interface for fully dynamic recipe logic (i.e. recipes with no fixed, pre-registered entries). It has two distinct responsibilities:

#### a) `createCustomRecipe(IRecipeCapabilityHolder holder)` — runtime, in-world

Runs every time the formed multiblock looks for work:
1. Pulls the `ServerLevel` from the holder (assuming it's a real `MetaMachine` in a world).
2. Iterates the items present in the input buses (`IO.IN`, `ItemRecipeCapability`).
3. For the first non-empty item found, calls `tryBuildRecipe(...)`.

#### b) `tryBuildRecipe(ServerLevel, GTRecipeType, ItemStack inputStack)` — building the dynamic recipe

1. `MachineUtil.getMachineTier(inputStack)` — if the item isn't a GTCEu machine, bail out (`return null`).
2. `ComponentResolver.resolve(serverLevel, inputStack)` — gets the components. If empty (no source recipe was found), bail out.
3. Calculates `EUt` based on the scanned machine's tier (`GTValues.VA[tier]`) and duration (`40 + tier * 40` ticks) — **the higher the tier, the more EU/t it consumes and the longer it takes**.
4. Builds a `GTRecipe` on the fly: input = 1x the scanned machine, outputs = the resolved components.
5. Returns that freshly-built `GTRecipe` — the multiblock runs it just like a normal static recipe, even though it was never stored anywhere.

#### c) `buildRepresentativeRecipes()` — so JEI/EMI have something to show

Since recipes are fully dynamic, JEI has nothing to pull from to build its display list. This method (part of GTCEu's official interface, modeled after GTCEu's own `ArcFurnaceLogic`) generates **one example recipe per existing machine**:

1. Obtains a "representative" `ServerLevel` via `GTCEu.getMinecraftServer().overworld()` — necessary because this method runs while JEI is loading, which doesn't necessarily happen with a real world already active.
2. Iterates **all** registered `MachineDefinition`s in GTCEu (`GTRegistries.MACHINES`).
3. Filters out any machine with no associated `GTRecipeType` (`getRecipeTypes().length == 0`) — this automatically excludes hatches, buses, etc., since those don't "process" anything.
4. Also filters by registry id (`isExcludedFromDisassembly`) to remove transformers, energy converters, buses, hatches, and diodes — even if one of them happened to declare a `recipeTypes` array by mistake, it still won't show up.
5. For every machine that survives both filters, it calls the same `tryBuildRecipe(...)` used above (reusing all the real logic, not a separate code path).
6. If a valid recipe was generated, its ID gets adjusted (`withPrefix("/")` to avoid colliding with real recipe IDs) and it's added to the recipe type's category via `recipeType.getCategory().addRecipe(recipe)`.

This runs **once**, when JEI builds its index — not on every tick.

### 5. `DisassemblyMachine.java` — The machine class itself

A simple subclass of `WorkableElectricMultiblockMachine` (no coils), existing only so it can be registered in `Multiblocks.java` as `DisassemblyMachine::new`. It carries no extra logic of its own — all the intelligence lives in `DisassemblerRecipeLogic`, not in the machine class.

---

## Full end-to-end flow

### Runtime (in-world) path

```
Player forms the Disassembler multiblock
        ↓
Places a machine (e.g. "HV Robot Arm") in the input bus, with power connected
        ↓
WorkableElectricMultiblockMachine looks for work → no static recipe found →
delegates to DisassemblerRecipeLogic.createCustomRecipe()
        ↓
Detects the item in the bus → tryBuildRecipe()
        ↓
MachineUtil.getMachineTier() → confirms it's a GTCEu machine, tier HV
        ↓
ComponentResolver.resolve() →
   1. Is there a crafting-table recipe? → yes → extract ingredients (with circuit fix)
   2. (if not) Is there an Assembler recipe? → RecipeResolver
   3. (if not) Is there an Assembly Line recipe? → RecipeResolver
        ↓
mergeStacks() → combines duplicates (2x HV Robot Arm instead of 1x + 1x)
        ↓
tryBuildRecipe builds the GTRecipe with EU/t and duration based on tier
        ↓
The multiblock executes that recipe as if it were static
```

### JEI/EMI indexing path (runs once, at load time)

```
JEI requests recipe categories →
DisassemblerRecipeLogic.buildRepresentativeRecipes() →
iterates ALL GTCEu machines →
generates one example recipe per machine (reusing tryBuildRecipe) →
adds them to the Disassembler's JEI category
```