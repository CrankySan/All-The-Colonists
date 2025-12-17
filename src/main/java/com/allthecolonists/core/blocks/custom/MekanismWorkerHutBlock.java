package com.allthecolonists.core.blocks.custom;

import com.allthecolonists.core.AllTheColonists;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.buildings.registry.IBuildingRegistry;
import com.minecolonies.core.blocks.huts.BlockHutMechanic;
import net.minecraft.resources.ResourceLocation;

/**
 * Mekanism worker hut that mirrors the MineColonies mechanist hut behavior but
 * uses AllTheColonists naming to avoid collisions with the upstream block.
 *
 * The class intentionally extends {@link BlockHutMechanic} to inherit all hut
 * placement, GUI and blueprint lookup logic while pointing to the existing
 * mechanic building entry from the MineColonies registry. This lets the new
 * hut open the familiar MineColonies UI when right-clicked, both in hand and
 * when placed in the world.
 */
public class MekanismWorkerHutBlock extends BlockHutMechanic {

    private static final ResourceLocation MEKANISM_ENTRY = ResourceLocation.fromNamespaceAndPath(AllTheColonists.MODID, "mekanism");
    private static final ResourceLocation MECHANIC_ENTRY = ResourceLocation.fromNamespaceAndPath("minecolonies", "mechanic");

    @Override
    public String getHutName() {
        return "mekanism";
    }

    @Override
    public BuildingEntry getBuildingEntry() {
        var registry = IBuildingRegistry.getInstance();
        return registry
                .getOptional(MEKANISM_ENTRY)
                .or(() -> registry.getOptional(MECHANIC_ENTRY))
                .orElse(null);
    }
}
