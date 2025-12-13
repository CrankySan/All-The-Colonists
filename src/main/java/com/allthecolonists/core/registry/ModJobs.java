package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import com.allthecolonists.core.colony.jobs.JobMekanism;
import com.minecolonies.api.colony.jobs.registry.IJobRegistry;
import com.minecolonies.api.colony.jobs.registry.JobEntry;

import net.minecraft.resources.ResourceLocation;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModJobs {

    public static final ResourceLocation MEKANISM =
            ResourceLocation.fromNamespaceAndPath(AllTheColonists.MODID, "mekanism");

    private static final DeferredRegister<JobEntry> JOBS =
            DeferredRegister.create(IJobRegistry.getInstance().key(), AllTheColonists.MODID);

    public static final DeferredHolder<JobEntry, JobEntry> MEKANISM_JOB =
            JOBS.register(
                    MEKANISM.getPath(),
                    () -> {
                        final JobEntry mechanicEntry = com.minecolonies.api.colony.jobs.ModJobs.mechanic.get();
                        final JobEntry.Builder builder = new JobEntry.Builder()
                                .setJobProducer(JobMekanism::new)
                                .setJobViewProducer(mechanicEntry.getJobViewProducer())
                                .setRegistryName(MEKANISM);
                        return builder.createJobEntry();
                    }
            );

    private ModJobs() {
    }

    public static void register(IEventBus eventBus) {
        JOBS.register(eventBus);
    }
}
