package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import com.allthecolonists.core.blocks.BlockMekanismHut;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(AllTheColonists.MODID);

    public static final DeferredBlock<BlockMekanismHut> MEKANISM_HUT =
            BLOCKS.register(
                    "mekanism_hut",
                    () -> new BlockMekanismHut(BlockBehaviour.Properties.of())
            );

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}