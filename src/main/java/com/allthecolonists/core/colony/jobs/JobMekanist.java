package com.allthecolonists.core.colony.jobs;

import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.core.colony.jobs.JobMechanic;

/**
 * Dedicated Mekanist job class.
 *
 * <p>Even though behaviour is currently identical to MineColonies' mechanic,
 * a distinct class is required so the MineColonies job factory can map this
 * profession to {@code allthecolonists:mekanist} instead of the base
 * {@code minecolonies:mechanic} entry.</p>
 */
public class JobMekanist extends JobMechanic {

    public JobMekanist(final ICitizenData citizenData) {
        super(citizenData);
    }
}

