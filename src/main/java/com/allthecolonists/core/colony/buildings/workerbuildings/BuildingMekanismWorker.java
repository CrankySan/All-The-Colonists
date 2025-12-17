package com.allthecolonists.core.colony.buildings.workerbuildings;

import com.minecolonies.core.colony.buildings.workerbuildings.BuildingMechanic;
import com.minecolonies.api.colony.IColony;
import net.minecraft.core.BlockPos;

/**
 * Mekanism worker building that mirrors the mechanist building behavior while
 * keeping resource names inside the AllTheColonists namespace.
 */
public class BuildingMekanismWorker extends BuildingMechanic {

    public BuildingMekanismWorker(IColony colony, BlockPos location) {
        super(colony, location);
    }

    @Override
    public String getSchematicName() {
        // Reuse the mechanic schematics shipped with MineColonies so the hut
        // always resolves a real blueprint pack. Returning "mekanism" here
        // points to a non-existent folder in this pack, which makes
        // MineColonies fall back to the placeholder hut window and drop the
        // building view. By keeping the mechanic identifier, the placed hut
        // stays bound to the colony and loads the proper crafting UI assets.
        return "mechanic";
    }
}
