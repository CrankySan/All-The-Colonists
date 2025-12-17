package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import com.allthecolonists.core.colony.buildings.workerbuildings.BuildingMekanism;
import com.allthecolonists.core.blocks.custom.MekanismWorkerHutBlock;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.buildings.registry.IBuildingRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModHuts
{
    public static final DeferredRegister<BuildingEntry> BUILDINGS =
            DeferredRegister.create(
                    IBuildingRegistry.getInstance().key(),
                    AllTheColonists.MODID
            );

    public static final DeferredHolder<BuildingEntry, BuildingEntry> MEKANISM =
            BUILDINGS.register(
                    "mekanism",
                    () -> new BuildingEntry.Builder()
                            .setBuildingBlock(ModBlocks.MEKANISM_WORKER_HUT)
                            .setBuildingClass(BuildingMekanism.class)
                            .createBuildingEntry()
            );

    public static void register(final IEventBus bus)
    {
        BUILDINGS.register(bus);
    }
}