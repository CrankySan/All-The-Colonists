package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;

import net.minecraft.world.item.Item;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(AllTheColonists.MODID);

    public static final DeferredItem<Item> VOID_ICON =
            ITEMS.registerSimpleItem("void_icon", new Item.Properties());

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
