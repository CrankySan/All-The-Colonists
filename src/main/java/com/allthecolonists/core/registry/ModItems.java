package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(net.minecraft.core.registries.Registries.ITEM, AllTheColonists.MODID);

    // -------- HUT ITEM (MEKANIST) --------
    public static final DeferredHolder<Item, Item> MEKANIST_HUT_ITEM =
            ITEMS.register(
                    "mekanist_hut",
                    () -> new BlockItem(
                            ModBlocks.MEKANIST_HUT.get(),
                            new Item.Properties()
                    )
            );

    // -------- MISC / ICON --------
    public static final DeferredHolder<Item, Item> VOID_ICON =
            ITEMS.register(
                    "void_icon",
                    () -> new Item(new Item.Properties())
            );

    private ModItems() {
    }

    public static void register(final IEventBus bus) {
        ITEMS.register(bus);
    }
}
