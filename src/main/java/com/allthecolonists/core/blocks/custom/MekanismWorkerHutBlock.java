package com.allthecolonists.core.blocks.custom;

import com.allthecolonists.core.registry.ModBlockEntities;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.buildings.registry.IBuildingRegistry;
import com.minecolonies.core.blocks.huts.BlockHutMechanic;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Mekanism worker hut that mirrors the MineColonies mechanist hut behavior but
 * uses AllTheColonists naming to avoid collisions with the upstream block.
 *
 * The class intentionally extends {@link BlockHutMechanic} to inherit all hut
 * placement, GUI and blueprint lookup logic while pointing to the existing
 * mechanic building entry from the MineColonies registry.
 */
public class MekanismWorkerHutBlock extends BlockHutMechanic {

    private static final ResourceLocation MECHANIC_ENTRY =
            ResourceLocation.fromNamespaceAndPath("minecolonies", "mechanic");

    @Override
    public String getHutName() {
        // Distinct internal hut name to avoid MineColonies collisions
        return "mekanismworker";
    }

    @Override
    public BuildingEntry getBuildingEntry() {
        // Reuse MineColonies mechanic building entry
        return IBuildingRegistry.getInstance().get(MECHANIC_ENTRY);
    }

    @Override
    public BlockEntity newBlockEntity(final BlockPos pos, final BlockState state) {
        // Custom block entity to satisfy MineColonies valid-hut checks
        return ModBlockEntities.MEKANISM_WORKER_TILE.get().create(pos, state);
    }
}