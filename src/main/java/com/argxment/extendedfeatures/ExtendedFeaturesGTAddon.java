package com.argxment.extendedfeatures;

import com.argxment.extendedfeatures.client.LangHandler;
import com.argxment.extendedfeatures.client.init.utils.recipes.ItemRecipes;
import com.argxment.extendedfeatures.client.init.utils.recipes.MultiblockRecipes;
import com.argxment.extendedfeatures.client.init.utils.recipes.multiblocks.greenhouse.CropRecipes;
import com.argxment.extendedfeatures.client.init.utils.recipes.multiblocks.greenhouse.WoodRecipes;
import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.data.recipes.FinishedRecipe;

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
        MultiblockRecipes.init(provider);
        ItemRecipes.init(provider);
        WoodRecipes.init(provider);
        CropRecipes.init(provider);
    }

    @Override
    public void registerElements() {
        // CustomElements.init();
    }
}
