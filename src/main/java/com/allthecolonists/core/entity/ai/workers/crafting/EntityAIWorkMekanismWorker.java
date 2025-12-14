package com.allthecolonists.core.entity.ai.workers.crafting;

import com.allthecolonists.core.jobs.MekanismWorker;
import com.minecolonies.core.entity.ai.workers.crafting.EntityAIWorkMechanic;

/**
 * AI wrapper that delegates to the MineColonies mechanist AI while expecting the
 * Mekanism worker building type.
 */
public class EntityAIWorkMekanismWorker extends EntityAIWorkMechanic {

    public EntityAIWorkMekanismWorker(MekanismWorker job) {
        super(job);
    }

}
