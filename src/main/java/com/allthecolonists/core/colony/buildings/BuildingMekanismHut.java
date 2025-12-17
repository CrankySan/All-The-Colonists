package com.allthecolonists.core.colony.buildings;

import com.allthecolonists.core.init.ModBuildingEntries;
import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.buildings.AbstractBuildingBase;
import net.minecraft.core.BlockPos;

public class BuildingMekanismHut extends AbstractBuildingBase
{
    public BuildingMekanismHut(final IColony colony, final BlockPos pos)
    {
        super(ModBuildingEntries.MEKANISM_HUT, colony, pos);
    }

    @Override
    public int getMaxBuildingLevel()
    {
        return 5;
    }
}