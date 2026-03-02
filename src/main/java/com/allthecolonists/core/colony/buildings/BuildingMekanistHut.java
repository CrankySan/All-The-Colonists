package com.allthecolonists.core.colony.buildings;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.core.colony.buildings.AbstractBuilding;
import com.minecolonies.core.colony.buildings.modules.AbstractCraftingBuildingModule;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

/**
 * Mekanist hut building – MineColonies 1.21.x compliant.
 */
public class BuildingMekanistHut extends AbstractBuilding {

    public BuildingMekanistHut(final IColony colony, final BlockPos location) {
        super(colony, location);
    }

    /**
     * Must match the schematic folder name.
     */
    @Override
    public String getSchematicName() {
        return "mekanist_hut";
    }

    /**
     * Crafting module for Mekanism Metallurgic Infuser recipes.
     * Recipes whose intermediate block is the Metallurgic Infuser are accepted.
     */
    public static class InfuserCraftingModule extends AbstractCraftingBuildingModule.Crafting {

        private static final ResourceLocation METALLURGIC_INFUSER_ID =
                ResourceLocation.fromNamespaceAndPath("mekanism", "metallurgic_infuser");

        public InfuserCraftingModule(final JobEntry jobEntry) {
            super(jobEntry);
        }

        @Override
        public boolean isRecipeCompatible(@NotNull final IGenericRecipe recipe) {
            final var intermediate = recipe.getIntermediate();
            if (intermediate == null || intermediate == Blocks.AIR) return false;
            return METALLURGIC_INFUSER_ID.equals(BuiltInRegistries.BLOCK.getKey(intermediate));
        }

        @NotNull
        @Override
        public String getId() {
            return "crafting";
        }
    }
}
