package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(AllTheColonists.MODID);

    // EXISTIERT bereits → OK
    public static final DeferredItem<Item> VOID_ICON =
            ITEMS.register("void_icon", () -> new Item(new Item.Properties()));

    // 🔴 KRITISCH WICHTIG 🔴
    // BlockItem für den Hut
    public static final DeferredItem<BlockItem> MEKANISM_HUT_ITEM =
            ITEMS.register(
                    "mekanism_hut",
                    () -> new BlockItem(ModBlocks.MEKANISM_HUT.get(), new Item.Properties())
            );

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
