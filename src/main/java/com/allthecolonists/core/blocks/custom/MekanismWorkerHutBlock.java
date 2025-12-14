package com.allthecolonists.core.blocks.custom;

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

    private static final ResourceLocation MECHANIC_ENTRY = ResourceLocation.fromNamespaceAndPath("minecolonies", "mechanic");

    @Override
    public String getHutName() {
        // Keep the user-facing name distinct from MineColonies' "mechanic" to
        // prevent resource collisions inside this mod.
        return "mekanismworker";
    }

    @Override
    public BuildingEntry getBuildingEntry() {
        // Reuse the mechanist registration from MineColonies to inherit all UI
        // wiring, crafting modules and AI logic.
        return IBuildingRegistry.getInstance().get(MECHANIC_ENTRY);
    }
}
