package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import com.allthecolonists.core.blocks.BlockMekanistHut;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(net.minecraft.core.registries.Registries.BLOCK, AllTheColonists.MODID);

    public static final DeferredHolder<Block, Block> MEKANIST_HUT =
            BLOCKS.register(
                    "mekanist_hut",
                    () -> new BlockMekanistHut(BlockBehaviour.Properties.of())
            );
}
