package com.allthecolonists.core.colony.buildings.workerbuildings;

import com.allthecolonists.core.colony.buildings.modules.MekanismInfuserCraftingModule;
import com.allthecolonists.core.registry.ModJobs;
import com.minecolonies.api.colony.IColony;
import com.minecolonies.core.colony.buildings.AbstractBuilding;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Mekanism worker building.
 *
 * Fully custom MineColonies building that does NOT inherit from the
 * mechanic/mechanist building to avoid cutter recipes and mechanic GUI tabs.
 * This building only exposes Mekanism-specific crafting modules
 * (e.g. Metallurgic Infuser).
 */
public class BuildingMekanism extends AbstractBuilding
{
    /**
     * Blueprint / schematic key.
     * Must match resources/blueprints/mekanism/
     */
    private static final String SCHEMATIC = "mekanism";

    public BuildingMekanism(final IColony colony, final BlockPos location)
    {
        super(colony, location);
    }

    /**
     * Blueprint name used by MineColonies.
     */
    @NotNull
    @Override
    public String getSchematicName()
    {
        return SCHEMATIC;
    }

    /**
     * Job used by this building.
     *
     * This MUST point to the Mekanism job entry,
     * otherwise MineColonies will fall back to mechanic behavior.
     */
    @NotNull
    @Override
    public ResourceLocation getJobEntry()
    {
        return ModJobs.MEKANISM.getId();
    }

    /**
     * Register all building modules.
     *
     * This is where GUI tabs are attached.
     * We intentionally ONLY register the Mekanism Infuser crafting module.
     */
    @Override
    public void registerModules()
    {
        super.registerModules();

        registerModule(
                new MekanismInfuserCraftingModule(getJobEntry())
        );
    }
}