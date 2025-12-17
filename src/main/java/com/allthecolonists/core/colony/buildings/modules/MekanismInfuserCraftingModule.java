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
 * This module provides a single crafting tab for Mekanism Metallurgic Infuser
 * recipes and intentionally does NOT expose any Mechanic / Cutter recipes.
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

    /**
     * Which recipes this module loads.
     */
    @NotNull
    @Override
    public ResourceLocation getRecipeKey()
    {
        return RECIPE_KEY;
    }

    /**
     * Tab title shown in the hut GUI.
     */
    @NotNull
    @Override
    public Component getDisplayName()
    {
        return Component.translatable(
                "gui.allthecolonists.mekanism.infuser"
        );
    }

    /**
     * Ingredient filter.
     *
     * We intentionally allow all items here.
     * Validation of machine presence (Metallurgic Infuser)
     * is handled in canCraftRecipe().
     */
    @NotNull
    @Override
    public OptionalPredicate<ItemStack> getIngredientValidator()
    {
        return OptionalPredicate.ALWAYS_TRUE;
    }

    /**
     * Final compatibility check for recipes.
     * Only accept recipes that belong to our crafter key.
     */
    @Override
    public boolean isRecipeCompatible(@NotNull final IGenericRecipe recipe)
    {
        return RECIPE_KEY.equals(recipe.getRecipeType());
    }

    /**
     * 2.3 – Machine + Energy check.
     *
     * A recipe is only craftable if:
     * - the building has at least level 1
     * - a Metallurgic Infuser exists in the hut
     * - the Infuser has FE energy
     */
    @Override
    public boolean canCraftRecipe(@NotNull final IGenericRecipe recipe)
    {
        if (building == null || building.getBuildingLevel() < 1)
        {
            return false;
        }

        return building.getPosition()
                .map(pos -> MekanismMachineHelper.hasPoweredInfuser(
                        building.getColony().getWorld(),
                        pos
                ))
                .orElse(false);
    }
}