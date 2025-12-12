package com.minecolonies.core.tileentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityColonyBuilding extends BlockEntity {
    public net.minecraft.resources.ResourceLocation registryName;
    public TileEntityColonyBuilding() {}
    public TileEntityColonyBuilding(BlockPos pos, BlockState state) {}
}
