package com.allthecolonists.core.blocks;

import com.allthecolonists.core.AllTheColonists;
import com.allthecolonists.core.init.ModBuildingEntries;
import com.minecolonies.api.blocks.AbstractBlockHut;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockMekanismHut extends AbstractBlockHut {

    public static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath(AllTheColonists.MODID, "mekanism_hut");

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
