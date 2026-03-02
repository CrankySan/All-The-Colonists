package com.allthecolonists.core.init;

import com.allthecolonists.core.AllTheColonists;
import com.allthecolonists.core.colony.buildings.BuildingMekanistHut;
import com.allthecolonists.core.colony.buildings.views.BuildingMekanismHutView;
import com.allthecolonists.core.registry.ModBlocks;
import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.colony.buildings.modules.IBuildingModuleView;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.entity.citizen.Skill;
import com.minecolonies.core.colony.buildings.modules.WorkerBuildingModule;
import com.minecolonies.core.colony.buildings.moduleviews.WorkerBuildingModuleView;
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

    /** Worker-assignment module – provides "Manage Workers" and "Recall Worker" buttons. */
    public static final BuildingEntry.ModuleProducer<WorkerBuildingModule, WorkerBuildingModuleView> MEKANIST_WORK =
            new BuildingEntry.ModuleProducer<>(
                    "mekanist_work",
                    () -> new WorkerBuildingModule(
                            ModJobEntries.MEKANIST.get(),
                            Skill.Knowledge,
                            Skill.Agility,
                            false,
                            (b) -> 1),
                    () -> WorkerBuildingModuleView::new
            );

    public static final DeferredHolder<BuildingEntry, BuildingEntry> MEKANISM_HUT =
            BUILDINGS.register(
                    "mekanism_hut",
                    () -> new BuildingEntry.Builder()
                            .setBuildingBlock(ModBlocks.MEKANISM_HUT.get())
                            .setBuildingProducer(BuildingMekanistHut::new)
                            .setBuildingViewProducer(() -> BuildingMekanismHutView::new)
                            .setRegistryName(ResourceLocation.fromNamespaceAndPath(AllTheColonists.MODID, "mekanism_hut"))
                            .addBuildingModuleProducer(MEKANIST_WORK)
                            .createBuildingEntry()
            );

    private ModBuildingEntries() {}
}
