package com.allthecolonists.core.init;

import com.allthecolonists.core.AllTheColonists;
import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.allthecolonists.core.colony.jobs.JobMekanist;
import com.minecolonies.core.colony.jobs.views.CrafterJobView;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registers custom job entries for All The Colonists into MineColonies' job registry.
 */
public final class ModJobEntries {

    public static final DeferredRegister<JobEntry> JOBS =
            DeferredRegister.create(
                    ResourceLocation.fromNamespaceAndPath("minecolonies", "jobs"),
                    AllTheColonists.MODID
            );

    /**
     * Mekanist job – shares the Mechanic's crafter AI until a dedicated AI is written.
     * The display name is driven by the translation key com.allthecolonists.job.mekanist.
     */
    public static final DeferredHolder<JobEntry, JobEntry> MEKANIST =
            JOBS.register("mekanist", () -> new JobEntry.Builder()
                    .setJobProducer(JobMekanist::new)
                    .setJobViewProducer(() -> CrafterJobView::new)
                    .setRegistryName(ResourceLocation.fromNamespaceAndPath(AllTheColonists.MODID, "mekanist"))
                    .createJobEntry()
            );

    private ModJobEntries() {}
}
