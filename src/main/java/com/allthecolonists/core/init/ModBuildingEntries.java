package com.allthecolonists.core.init;

import com.allthecolonists.core.AllTheColonists;
import com.allthecolonists.core.colony.buildings.BuildingMekanismHut;
import com.allthecolonists.core.colony.buildings.views.BuildingMekanismHutView;
import com.allthecolonists.core.registry.ModBlocks;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.minecolonies.apiimp.CommonMinecoloniesAPIImpl;

public final class ModBuildingEntries {

    public static final DeferredRegister<BuildingEntry> BUILDING_ENTRIES =
            DeferredRegister.create(CommonMinecoloniesAPIImpl.BUILDINGS, AllTheColonists.MODID);

    public static final DeferredHolder<BuildingEntry, BuildingEntry> MEKANISM_HUT =
            BUILDING_ENTRIES.register(
                    "mekanism_hut",
                    () -> new BuildingEntry.Builder()
                            .setRegistryName(ResourceLocation.fromNamespaceAndPath(AllTheColonists.MODID, "mekanism_hut"))
                            .setBuildingBlock(ModBlocks.MEKANISM_HUT.get())
                            .setBuildingProducer((colony, pos) -> new BuildingMekanismHut(colony, pos))
                            .setBuildingViewProducer(() -> BuildingMekanismHutView::new)
                            .createBuildingEntry()
            );

    private ModBuildingEntries() {
    }

    public static void init() {
        // Registrierungen erfolgen über das DeferredRegister in BUILDING_ENTRIES
    }

    public static void register(IEventBus bus) {
        BUILDING_ENTRIES.register(bus);
    }
}