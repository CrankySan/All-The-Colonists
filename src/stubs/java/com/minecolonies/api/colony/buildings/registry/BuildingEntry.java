package com.minecolonies.api.colony.buildings.registry;

import net.minecraft.resources.ResourceLocation;

public class BuildingEntry {
    private final ResourceLocation registryName;
    public BuildingEntry(ResourceLocation registryName) { this.registryName = registryName; }
    public ResourceLocation getRegistryName() { return registryName; }
}
