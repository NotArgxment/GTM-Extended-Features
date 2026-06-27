package com.argxment.extendedfeatures.init;

import com.argxment.extendedfeatures.ExtendedFeaturesCore;

import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

import static com.argxment.extendedfeatures.ExtendedFeaturesCore.ExtraFeaturesRegister;

public class Items {

    static {
        ExtraFeaturesRegister.creativeModeTab(() -> ExtendedFeaturesCore.EF_TAB);
    }

    // Huge thanks to witherschat (monifactory contributor) for allowing me to use the universal circuit textures

    public static final ItemEntry<Item> ULV_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("ulv_universal_circuit", Item::new)
            .lang("§8ULV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/ulv_universal_circuit")))
            .tag(CustomTags.ULV_CIRCUITS)
            .register();

    public static final ItemEntry<Item> LV_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("lv_universal_circuit", Item::new)
            .lang("§7LV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/lv_universal_circuit")))
            .tag(CustomTags.LV_CIRCUITS)
            .register();

    public static final ItemEntry<Item> MV_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("mv_universal_circuit", Item::new)
            .lang("§bMV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/mv_universal_circuit")))
            .tag(CustomTags.MV_CIRCUITS)
            .register();

    public static final ItemEntry<Item> HV_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("hv_universal_circuit", Item::new)
            .lang("§6HV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/hv_universal_circuit")))
            .tag(CustomTags.HV_CIRCUITS)
            .register();

    public static final ItemEntry<Item> EV_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("ev_universal_circuit", Item::new)
            .lang("§5EV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/ev_universal_circuit")))
            .tag(CustomTags.EV_CIRCUITS)
            .register();

    public static final ItemEntry<Item> IV_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("iv_universal_circuit", Item::new)
            .lang("§9IV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/iv_universal_circuit")))
            .tag(CustomTags.IV_CIRCUITS)
            .register();

    public static final ItemEntry<Item> LuV_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("luv_universal_circuit", Item::new)
            .lang("§dLuV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/luv_universal_circuit")))
            .tag(CustomTags.LuV_CIRCUITS)
            .register();

    public static final ItemEntry<Item> ZPM_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("zpm_universal_circuit", Item::new)
            .lang("§cZPM §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/zpm_universal_circuit")))
            .tag(CustomTags.ZPM_CIRCUITS)
            .register();

    public static final ItemEntry<Item> UV_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("uv_universal_circuit", Item::new)
            .lang("§3UV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/uv_universal_circuit")))
            .tag(CustomTags.UV_CIRCUITS)
            .register();
/* future use
    public static final ItemEntry<Item> UHV_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("uhv_universal_circuit", Item::new)
            .lang("§4UHV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/uhv_universal_circuit")))
            .tag(CustomTags.UHV_CIRCUITS)
            .register();

    public static final ItemEntry<Item> UEV_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("uev_universal_circuit", Item::new)
            .lang("§2UEV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/uev_universal_circuit")))
            .tag(CustomTags.UEV_CIRCUITS)
            .register();

    public static final ItemEntry<Item> UIV_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("uiv_universal_circuit", Item::new)
            .lang("§aUIV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/uiv_universal_circuit")))
            .tag(CustomTags.UIV_CIRCUITS)
            .register();

    public static final ItemEntry<Item> UXV_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("uxv_universal_circuit", Item::new)
            .lang("§eUXV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/uxv_universal_circuit")))
            .tag(CustomTags.UXV_CIRCUITS)
            .register();

    public static final ItemEntry<Item> OpV_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("opv_universal_circuit", Item::new)
            .lang("§l§9OpV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/opv_universal_circuit")))
            .tag(CustomTags.OpV_CIRCUITS)
            .register();

    public static final ItemEntry<Item> MAX_UNIVERSAL_CIRCUIT = ExtraFeaturesRegister
            .item("max_universal_circuit", Item::new)
            .lang("§l§cMAX §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/max_universal_circuit")))
            .tag(CustomTags.MAX_CIRCUITS)
            .register();
 */
    public static void register(IEventBus eventBus) {}
}