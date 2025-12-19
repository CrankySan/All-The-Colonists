package com.minecolonies.core.entity.ai.workers.crafting;

import com.minecolonies.core.colony.buildings.workerbuildings.BuildingMekanist;
import com.minecolonies.core.colony.jobs.JobMekanist;
import com.minecolonies.core.entity.ai.workers.crafting.AbstractEntityAICrafting;
import org.jetbrains.annotations.NotNull;

/**
 * Crafts everything else basically (redstone stuff etc)
 */
public class EntityAIWorkMekanist extends AbstractEntityAICrafting<JobMekanist, BuildingMekanist>
{
    /**
     * Initialize the mekanist and add all his tasks.
     *
     * @param mekanist the job he has.
     */
    public EntityAIWorkMekanist(@NotNull final JobMekanist mekanist)
    {
        super(mekanist);
    }

    @Override
    public Class<BuildingMekanist> getExpectedBuildingClass()
    {
        return BuildingMekanist.class;
    }
}
