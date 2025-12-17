package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;

import net.minecraft.world.item.BlockItem;
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
    public static final DeferredBlock<BlockMekanismHut> BLOCKHUTMEKANISM =
            BLOCKS.register(
                    "blockhutmekanism",
                    () -> new BlockMekanismHut(
                            BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    )
            );

    public static final DeferredItem<BlockItem> BLOCKHUTMEKANISM_ITEM =
            ITEMS.registerSimpleBlockItem("blockhutmekanism", BLOCKHUTMEKANISM);

    // Registrierung
    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
}