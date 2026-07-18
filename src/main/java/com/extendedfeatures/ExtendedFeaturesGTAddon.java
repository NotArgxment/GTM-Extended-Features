package com.extendedfeatures;

import com.extendedfeatures.init.utils.internal.recipes.*;
import com.gregtechceu.gtceu.api.addon.*;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;

import com.extendedfeatures.client.LangHandler;
import com.tterrag.registrate.providers.ProviderType;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@GTAddon
public class ExtendedFeaturesGTAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return ExtendedFeaturesCore.ExtendedFeaturesRegister;
    }

    @Override
    public void initializeAddon() {
        ExtendedFeaturesCore.ExtendedFeaturesRegister.addDataGenerator(ProviderType.LANG, LangHandler::init);
    }

    @Override
    public String addonModId() {
        return ExtendedFeaturesCore.MOD_ID;
    }

    @Override
    public void registerTagPrefixes() {
        // CustomTagPrefixes.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        RockProcessing.init(provider);
        UniversalCircuits.init(provider);
        Wood.init(provider);
        Crops.init(provider);
        Skips.init(provider);
    }

    @Override
    public void registerElements() {
        // CustomElements.init();
    }
}
