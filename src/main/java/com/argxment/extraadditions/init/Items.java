package com.argxment.extraadditions.init;

import com.argxment.extraadditions.ExtraAdditions;

import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

import static com.argxment.extraadditions.ExtraAdditions.EARegistry;

public class Items {

    static {
        EARegistry.creativeModeTab(() -> ExtraAdditions.EA_TAB);
    }

    // Huge thanks to witherschat (monifactory contributor) for allowing me to use the universal circuit textures!!!

    public static final ItemEntry<Item> ULV_UNIVERSAL_CIRCUIT = EARegistry
            .item("ulv_universal_circuit", Item::new)
            .lang("§8ULV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/ulv_universal_circuit")))
            .register();

    public static final ItemEntry<Item> LV_UNIVERSAL_CIRCUIT = EARegistry
            .item("lv_universal_circuit", Item::new)
            .lang("§7LV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/lv_universal_circuit")))
            .register();

    public static final ItemEntry<Item> MV_UNIVERSAL_CIRCUIT = EARegistry
            .item("mv_universal_circuit", Item::new)
            .lang("§bMV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/mv_universal_circuit")))
            .register();

    public static final ItemEntry<Item> HV_UNIVERSAL_CIRCUIT = EARegistry
            .item("hv_universal_circuit", Item::new)
            .lang("§6HV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/hv_universal_circuit")))
            .register();

    public static final ItemEntry<Item> EV_UNIVERSAL_CIRCUIT = EARegistry
            .item("ev_universal_circuit", Item::new)
            .lang("§5EV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/ev_universal_circuit")))
            .register();

    public static final ItemEntry<Item> IV_UNIVERSAL_CIRCUIT = EARegistry
            .item("iv_universal_circuit", Item::new)
            .lang("§9IV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/iv_universal_circuit")))
            .register();

    public static final ItemEntry<Item> LuV_UNIVERSAL_CIRCUIT = EARegistry
            .item("luv_universal_circuit", Item::new)
            .lang("§dLuV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/luv_universal_circuit")))
            .register();

    public static final ItemEntry<Item> ZPM_UNIVERSAL_CIRCUIT = EARegistry
            .item("zpm_universal_circuit", Item::new)
            .lang("§cZPM §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/zpm_universal_circuit")))
            .register();

    public static final ItemEntry<Item> UV_UNIVERSAL_CIRCUIT = EARegistry
            .item("uv_universal_circuit", Item::new)
            .lang("§3UV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/uv_universal_circuit")))
            .register();

    public static final ItemEntry<Item> UHV_UNIVERSAL_CIRCUIT = EARegistry
            .item("uhv_universal_circuit", Item::new)
            .lang("§4UHV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/uhv_universal_circuit")))
            .register();

    public static final ItemEntry<Item> UEV_UNIVERSAL_CIRCUIT = EARegistry
            .item("uev_universal_circuit", Item::new)
            .lang("§aUEV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/uev_universal_circuit")))
            .register();

    public static final ItemEntry<Item> UIV_UNIVERSAL_CIRCUIT = EARegistry
            .item("uiv_universal_circuit", Item::new)
            .lang("§2UIV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/uiv_universal_circuit")))
            .register();

/* future use
    public static final ItemEntry<Item> UXV_UNIVERSAL_CIRCUIT = ExtAddRegistrate
            .item("uxv_universal_circuit", Item::new)
            .lang("§eUXV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/uxv_universal_circuit")))
            .register();

    public static final ItemEntry<Item> OpV_UNIVERSAL_CIRCUIT = ExtAddRegistrate
            .item("opv_universal_circuit", Item::new)
            .lang("§l§9OpV §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/opv_universal_circuit")))
            .register();

    public static final ItemEntry<Item> MAX_UNIVERSAL_CIRCUIT = ExtAddRegistrate
            .item("max_universal_circuit", Item::new)
            .lang("§l§cMAX §rUniversal Circuit")
            .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/universal/max_universal_circuit")))
            .register();
 */
    public static void register(IEventBus eventBus) {

    }
}