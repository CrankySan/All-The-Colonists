package com.allthecolonists.core.blocks;

import com.allthecolonists.core.init.ModBuildingEntries;
import com.minecolonies.api.blocks.AbstractBlockHut;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

public class BlockMekanismHut extends AbstractBlockHut<BlockMekanismHut> {

    public BlockMekanismHut(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    public String getHutName() {
        return "mekanist";
    }

    @NotNull
    @Override
    public String getDescriptionId() {
        return "block.allthecolonists.mekanism_hut";
    }

    @Override
    public BuildingEntry getBuildingEntry() {
        return ModBuildingEntries.MEKANISM_HUT.get();
    }
}