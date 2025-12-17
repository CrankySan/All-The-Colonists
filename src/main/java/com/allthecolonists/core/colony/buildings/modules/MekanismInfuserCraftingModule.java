package com.allthecolonists.core.colony.buildings.modules;

import com.allthecolonists.core.util.MekanismMachineHelper;
import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.api.util.OptionalPredicate;
import com.minecolonies.core.colony.buildings.modules.AbstractCraftingBuildingModule;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Crafting module for the Mekanism hut.
 *
 * Provides a single GUI tab for Metallurgic Infuser recipes
 * and enforces machine + energy availability.
 */
public class MekanismInfuserCraftingModule
        extends AbstractCraftingBuildingModule.Crafting
{
    /**
     * Crafter / recipe key.
     * Must match:
     * data/allthecolonists/crafterrecipes/mekanism/
     */
    public static final ResourceLocation RECIPE_KEY =
            new ResourceLocation("allthecolonists", "mekanism");

    public MekanismInfuserCraftingModule(final JobEntry jobEntry)
    {
        super(jobEntry);
    }

    @NotNull
    @Override
    public ResourceLocation getRecipeKey()
    {
        return RECIPE_KEY;
    }

    @NotNull
    @Override
    public Component getDisplayName()
    {
        return Component.translatable(
                "gui.allthecolonists.mekanism.infuser"
        );
    }

    @NotNull
    @Override
    public OptionalPredicate<ItemStack> getIngredientValidator()
    {
        return OptionalPredicate.ALWAYS_TRUE;
    }

    @Override
    public boolean isRecipeCompatible(@NotNull final IGenericRecipe recipe)
    {
        return RECIPE_KEY.equals(recipe.getRecipeType());
    }

    /**
     * Enforces:
     * - building level >= 1
     * - at least one powered Metallurgic Infuser in the hut
     */
    @Override
    public boolean canCraftRecipe(@NotNull final IGenericRecipe recipe)
    {
        if (building == null || building.getBuildingLevel() < 1)
        {
            return false;
        }

        return MekanismMachineHelper.hasPoweredInfuser(
                building.getColony().getWorld(),
                building.getBuildingLevelPositions()
        );
    }
}