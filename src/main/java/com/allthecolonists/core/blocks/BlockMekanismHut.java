package com.allthecolonists.core.blocks;

import com.minecolonies.api.blocks.AbstractBlockHut;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockMekanismHut extends AbstractBlockHut {

    public BlockMekanismHut(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public String getHutName() {
        return "hut.allthecolonists.mekanism";
    }
}