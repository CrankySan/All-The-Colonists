package com.allthecolonists.core.colony.buildings;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.core.colony.buildings.AbstractBuilding;
import net.minecraft.core.BlockPos;

/**
 * Mekanist hut building – MineColonies 1.21.x compliant.
 */
public class BuildingMekanistHut extends AbstractBuilding {

    public BuildingMekanistHut(final IColony colony, final BlockPos location) {
        super(colony, location);
    }

    /**
     * Must match the schematic folder name.
     */
    @Override
    public String getSchematicName() {
        return "mekanist_hut";
    }
}
