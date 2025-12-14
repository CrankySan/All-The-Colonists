package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import com.minecolonies.core.tileentities.TileEntityColonyBuilding;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Block entity registrations for AllTheColonists additions.
 */
public final class ModBlockEntities {

    private ModBlockEntities() {
    }

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, AllTheColonists.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileEntityColonyBuilding>> MEKANISM_WORKER_TILE =
            BLOCK_ENTITY_TYPES.register(
                    "mekanism_worker",
                    () -> BlockEntityType.Builder.of(TileEntityColonyBuilding::new, ModBlocks.BLOCKHUTMEKANISM.get())
                            .build(null)
            );

    public static void register(final IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
