package com.allthecolonists.core.blocks;

import com.allthecolonists.core.AllTheColonists;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.buildings.registry.IBuildingRegistry;
import com.minecolonies.core.blocks.huts.BlockHutMechanic;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

/**
 * Mekanism worker hut block.
 *
 * This block intentionally reuses the MineColonies mechanic hut logic.
 * All identification (name, GUI title, blueprint lookup) is handled via
 * BuildingEntry, registry id and stylepack blueprints.
 */
public class MekanismWorkerHutBlock extends BlockHutMechanic
{
    private static final ResourceLocation MEKANISM_HUT_ID = ResourceLocation.fromNamespaceAndPath(
            AllTheColonists.MODID,
            "blockhutmekanism"
    );

    @Override
    public String getHutName()
    {
        return MEKANISM_HUT_ID.getPath();
    }

    @Override
    public BuildingEntry getBuildingEntry()
    {
        return getRegisteredEntry(MEKANISM_HUT_ID);
    }

    private BuildingEntry getRegisteredEntry(final ResourceLocation registryName)
    {
        final net.minecraft.core.Registry<BuildingEntry> buildingRegistry = IBuildingRegistry.getInstance();
        if (buildingRegistry != null)
        {
            return buildingRegistry.getOptional(registryName).orElseGet(super::getBuildingEntry);
        }

        return super.getBuildingEntry();
    }
}