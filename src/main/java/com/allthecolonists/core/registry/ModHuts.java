package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.buildings.registry.IBuildingRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModHuts
{
    private ModHuts() {}

    public static void register(final IEventBus bus)
    {
        try
        {
            final DeferredRegister<BuildingEntry> buildings = DeferredRegister.create(
                    IBuildingRegistry.getInstance().key(),
                    AllTheColonists.MODID
            );

            buildings.register(bus);
        }
        catch (final NullPointerException exception)
        {
            AllTheColonists.LOGGER.warn(
                    "MineColonies building registry unavailable during initialization; skipping hut registration."
            );
            AllTheColonists.LOGGER.debug("MineColonies API instance lookup failed", exception);
        }
    }
}
