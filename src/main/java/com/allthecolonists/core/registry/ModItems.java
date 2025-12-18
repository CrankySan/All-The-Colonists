package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    // ✅ KORREKTE Registry für NeoForge 1.21.1
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, AllTheColonists.MODID);

    public static final DeferredHolder<Item, Item> VOID_ICON =
            ITEMS.register("void_icon", () -> new Item(new Item.Properties()));

    // ✅ BlockItem für den Hut – craftbar & recipe-kompatibel
    public static final DeferredHolder<Item, BlockItem> MEKANISM_HUT_ITEM =
            ITEMS.register(
                    "mekanism_hut",
                    () -> new BlockItem(ModBlocks.MEKANISM_HUT.get(), new Item.Properties())
            );

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
