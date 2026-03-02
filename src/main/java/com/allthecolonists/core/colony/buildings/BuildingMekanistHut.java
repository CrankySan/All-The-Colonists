package com.allthecolonists.core.colony.buildings;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.core.colony.buildings.AbstractBuilding;
import com.minecolonies.core.colony.buildings.modules.AbstractCraftingBuildingModule;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
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
     * Crafting module that only allows teaching the Infused Alloy recipe.
     * Uses the vanilla 3×3 crafting grid; only accepts recipes whose output
     * is {@code mekanism:infused_alloy}.
     */
    public static class InfuserCraftingModule extends AbstractCraftingBuildingModule.Crafting {

        private static final ResourceLocation INFUSED_ALLOY_ID =
                ResourceLocation.fromNamespaceAndPath("mekanism", "infused_alloy");

        public InfuserCraftingModule(final JobEntry jobEntry) {
            super(jobEntry);
        }

        @Override
        public boolean isRecipeCompatible(@NotNull final IGenericRecipe recipe) {
            if (!super.isRecipeCompatible(recipe)) return false; // must be a vanilla crafting recipe
            final var output = recipe.getPrimaryOutput();
            if (output.isEmpty()) return false;
            return INFUSED_ALLOY_ID.equals(BuiltInRegistries.ITEM.getKey(output.getItem()));
        }

        @NotNull
        @Override
        public String getId() {
            return "crafting";
        }
    }
}
