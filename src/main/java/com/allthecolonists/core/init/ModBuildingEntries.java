package com.allthecolonists.core.init;

import com.allthecolonists.core.AllTheColonists;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.bus.api.IEventBus;

public final class ModBuildingEntries
{
    public static final DeferredRegister<BuildingEntry> BUILDINGS =
            DeferredRegister.create(
                    BuildingEntry.REGISTRY_KEY,
                    AllTheColonists.MODID
            );

    public static final DeferredHolder<BuildingEntry, BuildingEntry> MEKANIST_HUT =
            BUILDINGS.register(
                    "mekanist_hut",
                    BuildingEntry::new
            );

    private ModBuildingEntries() {}

    public static void register(IEventBus bus)
    {
        BUILDINGS.register(bus);
    }
}
