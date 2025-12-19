package com.minecolonies.core.blocks.huts;

import com.minecolonies.api.blocks.AbstractBlockHut;
import com.minecolonies.api.colony.buildings.ModBuildings;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import org.jetbrains.annotations.NotNull;

/**
 * Hut for the mekanist. No different from {@link AbstractBlockHut}
 */
public class BlockHutMekanist extends AbstractBlockHut<BlockHutMekanist>
{
    @NotNull
    @Override
    public String getHutName()
    {
        return "blockhutmekanist";
    }

    @Override
    public BuildingEntry getBuildingEntry()
    {
        return ModBuildings.mekanist.get();
    }
}
