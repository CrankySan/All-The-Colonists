package com.allthecolonists.core.blocks;

import com.allthecolonists.core.init.ModBuildingEntries;
import com.minecolonies.api.blocks.AbstractBlockHut;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockMekanismHut extends AbstractBlockHut {

    public BlockMekanismHut(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public BuildingEntry getBuildingEntry() {
        return ModBuildingEntries.MEKANISM_HUT.get();
    }

    @Override
    public String getHutName() {
        return "hut.allthecolonists.mekanism";
    }
}