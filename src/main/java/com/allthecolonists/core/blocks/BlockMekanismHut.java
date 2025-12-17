package com.allthecolonists.core.blocks;

import com.allthecolonists.core.init.ModBuildingEntries;
import com.minecolonies.api.blocks.AbstractBlockHut;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import net.minecraft.network.chat.Component;

public class BlockMekanismHut extends AbstractBlockHut
{
    @Override
    public BuildingEntry getBuildingEntry()
    {
        return ModBuildingEntries.MEKANISM_HUT;
    }

    @Override
    public Component getHutName()
    {
        return Component.translatable("hut.allthecolonists.mekanism");
    }
}