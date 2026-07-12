## Available recipe types for custom recipes on KubeJS Scripts

1. Circuits go from 1 to 32
2. TIER = LV, MV, HV, EV, IV, LuV, ZPM, UV, UHV, UEV, UIV, UXV, OpV, MAX
3. Duration must be in ticks, 1t = 50ms
4. Most multiblocks inside the mod re-use the same recipe types as GTCEu, so any recipe added for those GTCEu multiblocks are mirrored into the main multiblocks!
5. Be careful when making recipes for the disassembler

### Wood Recipes (Greenhouse)
```javascript
ServerEvents.recipes(event => {
    event.recipes.extendedfeatures.greenhouse_wood_recipes('...')
        .notConsumable('...')
        .itemInputs('...')
        .inputFluids('...')
        .itemOutputs('...')
        .circuit(n)
        .duration(t)
        .EUt(GTValues.VA[GTValues.TIER])
});
```

### Crop Recipes (Greenhouse)
```javascript
ServerEvents.recipes(event => {
    event.recipes.extendedfeatures.greenhouse_crop_recipes('...')
        .itemInputs('...')
        .inputFluids('...')
        .itemOutputs('...')
        .notConsumable('...')
        .circuit(n)
        .duration(t)
        .EUt(GTValues.VA[GTValues.TIER])
});
```

### Disassembler 
```javascript
ServerEvents.recipes(event => {
    event.recipes.extendedfeatures.disassembler_machine('...')
        .itemInputs('...')
		// Allows 8 more outputs 
        .itemOutputs('...')
        .duration(t)
        .EUt(GTValues.VA[GTValues.TIER])
});
```

### Rock Processing Facility
```javascript
ServerEvents.recipes(event => {
    event.recipes.extendedfeatures.rock_processing_facility('...')
        .itemInputs('...')
		.inputFluids('...')
        .itemOutputs('...')
        .duration(t)
        .EUt(GTValues.VA[GTValues.TIER])
});
```
