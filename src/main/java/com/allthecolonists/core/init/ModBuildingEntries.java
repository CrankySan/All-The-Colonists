package com.allthecolonists.core.init;

import com.allthecolonists.core.AllTheColonists;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.buildings.registry.BuildingEntryRegistry;
import net.minecraft.resources.ResourceLocation;

public final class ModBuildingEntries {

    public static BuildingEntry MEKANISM_HUT;

    private ModBuildingEntries() {
    }

    public static void init() {
        MEKANISM_HUT = BuildingEntryRegistry.getInstance().register(
                new ResourceLocation(AllTheColonists.MODID, "mekanism_hut")
        );
    }
}