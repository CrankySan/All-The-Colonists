package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import com.allthecolonists.core.blocks.huts.BlockHutMekanism;
import com.allthecolonists.core.colony.buildings.workerbuildings.BuildingMekanism;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.apiimp.CommonMinecoloniesAPIImpl;

import net.minecraft.resources.ResourceLocation;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBuildings {

    public static final ResourceLocation MEKANISM_HUT_ID =
            ResourceLocation.fromNamespaceAndPath(AllTheColonists.MODID, "mekanism_hut");

    private static final DeferredRegister<BuildingEntry> BUILDINGS =
            DeferredRegister.create(CommonMinecoloniesAPIImpl.BUILDINGS, AllTheColonists.MODID);

    public static final DeferredHolder<BuildingEntry, BuildingEntry> MEKANISM_HUT =
            BUILDINGS.register(
                    MEKANISM_HUT_ID.getPath(),
                    () -> {
                        final BuildingEntry mechanicEntry = com.minecolonies.api.colony.buildings.ModBuildings.mechanic.get();
                        final BuildingEntry.Builder builder = new BuildingEntry.Builder()
                                .setBuildingBlock(ModBlocks.BLOCKHUTMEKANISM.get())
                                .setBuildingProducer(BuildingMekanism::new)
                                .setBuildingViewProducer(
                                        () -> (colonyView, pos) -> mechanicEntry.produceBuildingView(pos, colonyView)
                                )
                                .setRegistryName(MEKANISM_HUT_ID);

                        mechanicEntry.getModuleProducers().forEach(builder::addBuildingModuleProducer);
                        return builder.createBuildingEntry();
                    }
            );

    private ModBuildings() {
    }

    public static void register(IEventBus eventBus) {
        BUILDINGS.register(eventBus);
    }
}
