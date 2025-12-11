package com.allthecolonists.core.blocks.huts;

import com.minecolonies.api.blocks.AbstractBlockHut;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import org.jetbrains.annotations.NotNull;

/**
 * Hut for the mekanist. No different from {@link AbstractBlockHut}
 */
public class BlockHutMekanism extends AbstractBlockHut<BlockHutMekanism>
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
        // TODO: register a proper building entry for the Mekanism worker hut.
        return null;
    }
}
