package com.allthecolonists.core.init;

import com.allthecolonists.core.AllTheColonists;
import com.allthecolonists.core.colony.buildings.BuildingMekanistHut;
import com.allthecolonists.core.colony.buildings.views.BuildingMekanismHutView;
import com.allthecolonists.core.registry.ModBlocks;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBuildingEntries
{
    public static final DeferredRegister<BuildingEntry> BUILDINGS =
            DeferredRegister.create(
                    ResourceLocation.fromNamespaceAndPath("minecolonies", "buildings"),
                    AllTheColonists.MODID
            );

    public static final DeferredHolder<BuildingEntry, BuildingEntry> MEKANISM_HUT =
            BUILDINGS.register(
                    "mekanism_hut",
                    () -> new BuildingEntry.Builder()
                            .setBuildingBlock(ModBlocks.MEKANISM_HUT.get())
                            .setBuildingProducer(BuildingMekanistHut::new)
                            .setBuildingViewProducer(() -> BuildingMekanismHutView::new)
                            .setRegistryName(ResourceLocation.fromNamespaceAndPath(AllTheColonists.MODID, "mekanism_hut"))
                            .createBuildingEntry()
            );

    private ModBuildingEntries() {}
}
