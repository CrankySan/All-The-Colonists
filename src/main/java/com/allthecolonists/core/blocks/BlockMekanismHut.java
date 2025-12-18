package com.allthecolonists.core.blocks;

import com.minecolonies.api.blocks.AbstractBlockHut;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.buildings.registry.ModBuildings;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockMekanismHut extends AbstractBlockHut {

    public static final ResourceLocation ID =
            new ResourceLocation("allthecolonists", "mekanism_hut");

    public BlockMekanismHut(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public BuildingEntry getBuildingEntry() {
        // KORREKT für MineColonies 1.21.x
        return ModBuildings.getInstance().getBuilding(ID);
    }

    @Override
    public String getHutName() {
        return "hut.allthecolonists.mekanism";
    }
}
