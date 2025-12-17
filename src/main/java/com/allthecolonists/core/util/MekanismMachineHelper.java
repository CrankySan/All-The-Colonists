package com.allthecolonists.core.util;

import mekanism.api.energy.IEnergyContainer;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.tile.machine.TileEntityMetallurgicInfuser;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * Helper for detecting powered Mekanism machines inside a MineColonies hut.
 */
public final class MekanismMachineHelper
{
    private MekanismMachineHelper() {}

    /**
     * Checks whether at least one powered Metallurgic Infuser exists
     * inside the building area.
     *
     * @param level world
     * @param buildingPositions all block positions belonging to the building
     * @return true if a powered infuser is found
     */
    public static boolean hasPoweredInfuser(
            final Level level,
            final Iterable<BlockPos> buildingPositions)
    {
        for (BlockPos pos : buildingPositions)
        {
            final BlockEntity be = level.getBlockEntity(pos);
            if (!(be instanceof TileEntityMetallurgicInfuser infuser))
            {
                continue;
            }

            final IEnergyContainer energy =
                    infuser.getCapability(Capabilities.ENERGY_CONTAINER).orElse(null);

            if (energy != null && energy.getEnergy() > 0)
            {
                return true;
            }
        }

        return false;
    }
}