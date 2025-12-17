package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(AllTheColonists.MODID);

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(AllTheColonists.MODID);

    // Registrierung
    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
}