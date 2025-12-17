package com.allthecolonists.core.blocks.custom;

import com.allthecolonists.core.AllTheColonists;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.buildings.registry.IBuildingRegistry;
import net.minecraft.resources.ResourceLocation;

/**
 * Mekanism worker hut block.
 *
 * This hut is fully independent from the MineColonies mechanic hut.
 * It intentionally does NOT fall back to the mechanic building entry,
 * to avoid pulling in cutter recipes and mechanic GUI tabs.
 */
public class MekanismWorkerHutBlock extends AbstractBlockHut
{
    private static final ResourceLocation MEKANISM_ENTRY =
            ResourceLocation.fromNamespaceAndPath(AllTheColonists.MODID, "mekanism");

    @Override
    public String getHutName()
    {
        return "mekanism";
    }

    @Override
    public BuildingEntry getBuildingEntry()
    {
        return IBuildingRegistry.getInstance()
                .getOptional(MEKANISM_ENTRY)
                .orElseThrow(() -> new IllegalStateException(
                        "Mekanism building entry not registered: " + MEKANISM_ENTRY
                ));
    }
}