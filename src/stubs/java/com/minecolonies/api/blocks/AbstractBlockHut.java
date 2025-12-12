package com.minecolonies.api.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public abstract class AbstractBlockHut<T extends AbstractBlockHut<T>> extends Block implements EntityBlock {
    public AbstractBlockHut(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public abstract String getHutName();
    public abstract Object newBlockEntity(net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state);
    public abstract com.minecolonies.api.colony.buildings.registry.BuildingEntry getBuildingEntry();
}
