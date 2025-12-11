package com.allthecolonists.registry;

import com.allthecolonists.AllTheColonists;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(AllTheColonists.MODID);

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(AllTheColonists.MODID);

    // Mekanism Worker Hut Block
    public static final DeferredBlock<Block> MEKANISM_WORKER_HUT =
            BLOCKS.registerSimpleBlock(
                    "mekanism_worker_hut",
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.METAL)
                            .strength(2.0f)
            );

    public static final DeferredItem<BlockItem> MEKANISM_WORKER_HUT_ITEM =
            ITEMS.registerSimpleBlockItem("mekanism_worker_hut", MEKANISM_WORKER_HUT);

    // ✔ KORREKTE Registrierung mit EventBus
    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
}