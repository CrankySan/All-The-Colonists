package com.allthecolonists.core.blocks.huts;

import com.allthecolonists.core.registry.ModBuildings;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.core.blocks.huts.BlockHutMechanic;

public class BlockHutMekanism extends BlockHutMechanic {

    @Override
    public String getHutName() {
        return "blockhutmekanism";
    }

    @Override
    public BuildingEntry getBuildingEntry() {
        return ModBuildings.MEKANISM_HUT.get();
    }
}
