package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.buildings.registry.IBuildingRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModHuts
{
    public static final DeferredRegister<BuildingEntry> BUILDINGS =
            DeferredRegister.create(
                    IBuildingRegistry.getInstance().key(),
                    AllTheColonists.MODID
            );

    public static void register(final IEventBus bus)
    {
        BUILDINGS.register(bus);
    }
}