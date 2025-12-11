package com.allthecolonists.core.blocks.huts;

import com.allthecolonists.api.blocks.AbstractBlockHut;
import com.allthecolonists.api.colony.buildings.ModBuildings;
import com.allthecolonists.api.colony.buildings.registry.BuildingEntry;
import org.jetbrains.annotations.NotNull;

/**
 * Hut for the mekanist. No different from {@link AbstractBlockHut}
 */
public class BlockHutMechanic extends AbstractBlockHut<BlockHutMekanism>
{
    @NotNull
    @Override
    public String getHutName()
    {
        return "blockhutmekanism";
    }

    @Override
    public BuildingEntry getBuildingEntry()
    {
        return ModBuildings.mekanist.get();
    }
}