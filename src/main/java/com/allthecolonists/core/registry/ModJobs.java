package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import com.minecolonies.api.colony.jobs.registry.IJobRegistry;
import com.minecolonies.api.colony.jobs.registry.JobEntry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Job registrations for All The Colonists.
 */
public final class ModJobs
{
    private ModJobs() {}

    public static void register(final IEventBus bus)
    {
        try
        {
            final DeferredRegister<JobEntry> jobs = DeferredRegister.create(
                    IJobRegistry.getInstance().key(),
                    AllTheColonists.MODID
            );

            jobs.register(bus);
        }
        catch (final NullPointerException exception)
        {
            AllTheColonists.LOGGER.warn(
                    "MineColonies job registry unavailable during initialization; skipping job registration."
            );
            AllTheColonists.LOGGER.debug("MineColonies API instance lookup failed", exception);
        }
    }
}
