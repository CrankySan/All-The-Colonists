package com.allthecolonists.api.colony.buildings;

import com.allthecolonists.api.colony.buildings.registry.BuildingEntry;
import net.allthecolonists.registries.RegistryObject;

public final class ModBuildings
{


    public static final String mekanist_ID     = "mekanist";

    public static RegistryObject<BuildingEntry> mekanist;


    private ModBuildings()
    {
        throw new IllegalStateException("Tried to initialize: ModBuildings but this is a Utility class.");
    }
}