package com.allthecolonists.core.colony.jobs;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.jobs.AbstractJob;
import net.minecraft.core.BlockPos;

/**
 * Mekanism worker job.
 *
 * This job is intentionally minimal.
 * All crafting logic is handled via building modules
 * (e.g. MekanismInfuserCraftingModule).
 */
public class JobMekanism extends AbstractJob
{
    public JobMekanism(final IColony colony, final BlockPos location)
    {
        super(colony, location);
    }
}