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
        // Use the existing Mekanist blueprint shipped with this pack so the
        // hut resolves a real layout without the newly generated schematics.
        return "mekanist";
    }
}
