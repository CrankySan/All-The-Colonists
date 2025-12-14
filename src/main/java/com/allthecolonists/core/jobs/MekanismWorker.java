package com.allthecolonists.core.jobs;

import com.allthecolonists.core.entity.ai.workers.crafting.EntityAIWorkMekanismWorker;
import com.allthecolonists.core.AllTheColonists;
import com.minecolonies.core.colony.jobs.JobMechanic;
import com.minecolonies.api.colony.ICitizenData;
import net.minecraft.resources.ResourceLocation;

/**
 * Job wrapper that mirrors MineColonies' mechanist while using AllTheColonists
 * resource identifiers.
 */
public class MekanismWorker extends JobMechanic {

    private static final ResourceLocation MEKANISM_MODEL = ResourceLocation.fromNamespaceAndPath(
            AllTheColonists.MODID,
            "textures/entity/citizen/mekanismworker.png"
    );

    public MekanismWorker(ICitizenData citizenData) {
        super(citizenData);
    }

    @Override
    public EntityAIWorkMekanismWorker generateAI() {
        return new EntityAIWorkMekanismWorker(this);
    }

    @Override
    public ResourceLocation getModel() {
        // Use a namespaced model to avoid the MineColonies mechanist resource
        // names while still falling back to the same rendering pipeline.
        return MEKANISM_MODEL;
    }
}
