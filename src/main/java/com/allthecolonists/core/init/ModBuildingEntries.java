package com.allthecolonists.core.init;

import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import net.minecraft.resources.ResourceLocation;

public final class ModBuildingEntries
{
    public static final BuildingEntry MEKANISM_HUT =
        new BuildingEntry.Builder()
            .setRegistryName(
                ResourceLocation.fromNamespaceAndPath(
                    "allthecolonists",
                    "mekanism_hut"
                )
            )
            .build();

    private ModBuildingEntries() {}
}