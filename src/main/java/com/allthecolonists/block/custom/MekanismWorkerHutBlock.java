package com.crankgpt.allthecolonists.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class MekanismWorkerHutBlock extends Block {
    public MekanismWorkerHutBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.METAL));
    }
}