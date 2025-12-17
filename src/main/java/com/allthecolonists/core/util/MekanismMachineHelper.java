package com.allthecolonists.core.util;

import mekanism.api.energy.IEnergyContainer;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.tile.machine.TileEntityMetallurgicInfuser;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public final class MekanismMachineHelper
{
    private MekanismMachineHelper() {}

    /**
     * Checks if at least one powered Metallurgic Infuser exists
     * inside the given hut area.
     */
    public static boolean hasPoweredInfuser(final Level level, final Iterable<BlockPos> buildingPositions)
    {
        for (BlockPos pos : buildingPositions)
        {
            final BlockEntity be = level.getBlockEntity(pos);
            if (!(be instanceof TileEntityMetallurgicInfuser infuser))
            {
                continue;
            }

            final IEnergyContainer energyContainer =
                    infuser.getCapability(Capabilities.ENERGY_CONTAINER).orElse(null);

            if (energyContainer != null && energyContainer.getEnergy() > 0)
            {
                return true;
            }
        }

        return false;
    }
}