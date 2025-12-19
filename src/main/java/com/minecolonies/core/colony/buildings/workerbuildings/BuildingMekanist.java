package com.minecolonies.core.colony.buildings.workerbuildings;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.buildings.modules.ICraftingBuildingModule;
import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.api.util.CraftingUtils;
import com.minecolonies.api.util.OptionalPredicate;
import com.minecolonies.core.colony.buildings.AbstractBuilding;
import com.minecolonies.core.colony.buildings.modules.AbstractCraftingBuildingModule;
import com.minecolonies.core.colony.buildings.modules.AbstractDOCraftingBuildingModule;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.level.block.HopperBlock;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Predicate;

import static com.minecolonies.api.util.constant.BuildingConstants.CONST_DEFAULT_MAX_BUILDING_LEVEL;
import static com.minecolonies.api.util.constant.TagConstants.*;

/**
 * Class of the mekanist building.
 */
public class BuildingMekanist extends AbstractBuilding
{
    /**
     * Description string of the building.
     */
    private static final String MEKANIST = "mekanist";

    /**
     * Instantiates a new mekanist building.
     *
     * @param c the colony.
     * @param l the location
     */
    public BuildingMekanist(final IColony c, final BlockPos l)
    {
        super(c, l);
    }

    @NotNull
    @Override
    public String getSchematicName()
    {
        return MEKANIST;
    }

    @Override
    public int getMaxBuildingLevel()
    {
        return CONST_DEFAULT_MAX_BUILDING_LEVEL;
    }

    /**
     * Mekanist crafting module.
     */
    public static class CraftingModule extends AbstractCraftingBuildingModule.Crafting
    {
        /**
         * Create a new module.
         *
         * @param jobEntry the entry of the job.
         */
        public CraftingModule(final JobEntry jobEntry)
        {
            super(jobEntry);
        }

        @NotNull
        @Override
        public OptionalPredicate<ItemStack> getIngredientValidator()
        {
            return CraftingUtils.getIngredientValidatorBasedOnTags(CRAFTING_MEKANIST)
                    .combine(super.getIngredientValidator());
        }

        @Override
        public boolean isRecipeCompatible(@NotNull final IGenericRecipe recipe)
        {
            if (!super.isRecipeCompatible(recipe))
            {
                return false;
            }

            final Optional<Boolean> isRecipeAllowed =
                    CraftingUtils.isRecipeCompatibleBasedOnTags(recipe, CRAFTING_MEKANIST);

            if (isRecipeAllowed.isPresent())
            {
                return isRecipeAllowed.get();
            }

            final Item item = recipe.getPrimaryOutput().getItem();
            return item instanceof MinecartItem
                    || (item instanceof BlockItem
                        && ((BlockItem) item).getBlock() instanceof HopperBlock);
        }

        @Override
        protected int getMaxRecipes()
        {
            // grant a few extra to offset all the built-in recipes
            return super.getMaxRecipes() + 5;
        }
    }

    public static class DOCraftingModule extends AbstractDOCraftingBuildingModule
    {
        /**
         * Create a new module.
         *
         * @param jobEntry the entry of the job.
         */
        public DOCraftingModule(final JobEntry jobEntry)
        {
            super(jobEntry);
        }

        /**
         * See {@link ICraftingBuildingModule#getIngredientValidator}.
         *
         * @return the validator
         */
        public @NotNull static OptionalPredicate<ItemStack> getStaticIngredientValidator()
        {
            final OptionalPredicate<ItemStack> sawmill =
                    CraftingUtils.getIngredientValidatorBasedOnTags(CRAFTING_SAWMILL, true)
                            .combine(stack ->
                                    Optional.of(stack.is(ItemTags.PLANKS) || stack.is(ItemTags.LOGS)));

            final Predicate<ItemStack> handled = sawmill
                    .or(CraftingUtils.getIngredientValidatorBasedOnTags(CRAFTING_FLETCHER, true))
                    .or(CraftingUtils.getIngredientValidatorBasedOnTags(CRAFTING_STONEMASON, true))
                    .or(CraftingUtils.getIngredientValidatorBasedOnTags(CRAFTING_GLASSBLOWER, true))
                    .orElse(false);

            // mekanist accepts every ingredient not otherwise handled
            return OptionalPredicate.of(handled.negate());
        }

        @Override
        public @NotNull OptionalPredicate<ItemStack> getIngredientValidator()
        {
            return getStaticIngredientValidator();
        }
    }
}
