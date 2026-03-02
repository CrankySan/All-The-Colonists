package com.allthecolonists.core.init;

import com.allthecolonists.core.AllTheColonists;
import com.allthecolonists.core.colony.buildings.BuildingMekanistHut;
import com.allthecolonists.core.colony.buildings.views.BuildingMekanismHutView;
import com.allthecolonists.core.registry.ModBlocks;
import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.colony.buildings.modules.IBuildingModuleView;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.entity.citizen.Skill;
import com.minecolonies.core.colony.buildings.modules.BuildingModules;
import com.minecolonies.core.colony.buildings.modules.CraftingWorkerBuildingModule;
import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;
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
    public static final BuildingEntry.ModuleProducer<CraftingWorkerBuildingModule, WorkerBuildingModuleView> MEKANIST_WORK =
            new BuildingEntry.ModuleProducer<>(
                    "worker",
                    () -> new CraftingWorkerBuildingModule(
                            ModJobEntries.MEKANIST.get(),
                            Skill.Knowledge,
                            Skill.Agility,
                            false,
                            (b) -> 1),
                    () -> WorkerBuildingModuleView::new
            );

    /** Crafting module for Mekanism Metallurgic Infuser recipes – provides the recipe tab. */
    public static final BuildingEntry.ModuleProducer<BuildingMekanistHut.InfuserCraftingModule, CraftingModuleView> MEKANIST_INFUSER_CRAFT =
            new BuildingEntry.ModuleProducer<>(
                    "crafting",
                    () -> new BuildingMekanistHut.InfuserCraftingModule(ModJobEntries.MEKANIST.get()),
                    () -> CraftingModuleView::new
            );


    /** Request-task tab for crafter jobs – shows crafting tasks/queue. */
    public static final BuildingEntry.ModuleProducer<IBuildingModule, IBuildingModuleView> MEKANIST_TASKS =
            BuildingModules.CRAFT_TASK_VIEW;

    /** Recipe settings tab (mirrors MineColonies mechanic hut behavior). */
    public static final BuildingEntry.ModuleProducer<IBuildingModule, IBuildingModuleView> MEKANIST_SETTINGS =
            BuildingModules.SETTINGS_CRAFTER_RECIPE;

    /** Building statistics tab. */
    public static final BuildingEntry.ModuleProducer<IBuildingModule, IBuildingModuleView> MEKANIST_STATS =
            BuildingModules.STATS_MODULE;

    public static final DeferredHolder<BuildingEntry, BuildingEntry> MEKANISM_HUT =
            BUILDINGS.register(
                    "mekanism_hut",
                    () -> new BuildingEntry.Builder()
                            .setBuildingBlock(ModBlocks.MEKANISM_HUT.get())
                            .setBuildingProducer(BuildingMekanistHut::new)
                            .setBuildingViewProducer(() -> BuildingMekanismHutView::new)
                            .setRegistryName(ResourceLocation.fromNamespaceAndPath(AllTheColonists.MODID, "mekanism_hut"))
                            .addBuildingModuleProducer(MEKANIST_WORK)
                            .addBuildingModuleProducer(MEKANIST_INFUSER_CRAFT)
                            .addBuildingModuleProducer(MEKANIST_TASKS)
                            .addBuildingModuleProducer(MEKANIST_SETTINGS)
                            .addBuildingModuleProducer(MEKANIST_STATS)
                            .createBuildingEntry()
            );

    private ModBuildingEntries() {}
}
