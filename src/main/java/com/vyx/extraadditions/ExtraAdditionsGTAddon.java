package com.vyx.extraadditions;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import com.tterrag.registrate.providers.ProviderType;

import com.vyx.extraadditions.machines.EAMultis;
import com.vyx.extraadditions.machines.client.EALangHandler;
import com.vyx.extraadditions.machines.EARecipes;

import com.vyx.extraadditions.machines.client.EARecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@GTAddon
public class ExtraAdditionsGTAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return ExtraAdditionsCore.EXTRA_ADDITIONS_REGISTRATE;
    }

    @Override
    public void initializeAddon() {
        EAMultis.init();
        ExtraAdditionsCore.EXTRA_ADDITIONS_REGISTRATE.addDataGenerator(ProviderType.LANG, EALangHandler::init);
    }

    @Override
    public String addonModId() {
        return ExtraAdditionsCore.MOD_ID;
    }

    @Override
    public void registerTagPrefixes() {
        // CustomTagPrefixes.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        EARecipes.init(provider);
    }

    @Override
    public void registerElements() {
        // CustomElements.init();
    }

    // If you have custom ingredient types, uncomment this & change to match your capability.
    // KubeJS WILL REMOVE YOUR RECIPES IF THESE ARE NOT REGISTERED.
    /*
     * public static final ContentJS<Double> PRESSURE_IN = new ContentJS<>(NumberComponent.ANY_DOUBLE,
     * CustomRecipeCapabilities.PRESSURE, false);
     * public static final ContentJS<Double> PRESSURE_OUT = new ContentJS<>(NumberComponent.ANY_DOUBLE,
     * CustomRecipeCapabilities.PRESSURE, true);
     *
     * @Override
     * public void registerRecipeKeys(KJSRecipeKeyEvent event) {
     * event.registerKey(CustomRecipeCapabilities.PRESSURE, Pair.of(PRESSURE_IN, PRESSURE_OUT));
     * }
     */
}
