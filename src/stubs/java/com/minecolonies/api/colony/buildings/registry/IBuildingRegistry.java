package com.minecolonies.api.colony.buildings.registry;

import net.minecraft.resources.ResourceLocation;

public class IBuildingRegistry {
    private static final IBuildingRegistry INSTANCE = new IBuildingRegistry();
    public static IBuildingRegistry getInstance() { return INSTANCE; }
    public BuildingEntry get(ResourceLocation location) { return new BuildingEntry(location); }
}
