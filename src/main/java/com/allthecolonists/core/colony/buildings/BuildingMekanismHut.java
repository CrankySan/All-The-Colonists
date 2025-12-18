package com.allthecolonists.core.colony.buildings;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.core.colony.buildings.AbstractBuilding;
import net.minecraft.core.BlockPos;

public class BuildingMekanismHut extends AbstractBuilding
{
    public BuildingMekanismHut(IColony colony, BlockPos pos)
    {
        super(colony, pos);
    }

@Override
public String getSchematicName()
{
    return "mekanism_hut";
}
}