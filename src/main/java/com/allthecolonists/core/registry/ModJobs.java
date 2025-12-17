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

    public static final DeferredRegister<JobEntry> JOBS =
            DeferredRegister.create(
                    IJobRegistry.getInstance().key(),
                    AllTheColonists.MODID
            );

    public static void register(final IEventBus bus)
    {
        JOBS.register(bus);
    }
}
