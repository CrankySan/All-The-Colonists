package com.allthecolonists.core.colony.buildings.workerbuildings;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.buildings.modules.ICraftingBuildingModule;
import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.api.util.CraftingUtils;
import com.minecolonies.api.util.OptionalPredicate;
import com.minecolonies.core.colony.buildings.AbstractBuilding;
import com.minecolonies.core.colony.buildings.modules.AbstractCraftingBuildingModule;
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
 * Mekanist building (custom mechanic equivalent).
 */
public class BuildingMekanist extends AbstractBuilding
{
    /**
     * Schematic / blueprint base name.
     * Muss exakt zum Blueprint-Namen passen (mekanist1.blueprint etc.)
     */
    private static final String MEKANIST = "mekanist";

    public BuildingMekanist(final IColony colony, final BlockPos location)
    {
        super(colony, location);
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
     * Aktuell identisch zum Mechanic, aber bewusst separat gehalten.
     */
    public static class CraftingModule extends AbstractCraftingBuildingModule.Crafting
    {
        public CraftingModule(final JobEntry jobEntry)
        {
            super(jobEntry);
        }

        @NotNull
        @Override
        public OptionalPredicate<ItemStack> getIngredientValidator()
        {
            /*
             * WICHTIG:
             * Wenn du später eigene Tags willst:
             *  - erstelle z. B. CRAFTING_MEKANIST
             *  - und ersetze hier CRAFTING_MECHANIC
             */
            return CraftingUtils.getIngredientValidatorBasedOnTags(CRAFTING_MECHANIC)
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
                    CraftingUtils.isRecipeCompatibleBasedOnTags(recipe, CRAFTING_MECHANIC);

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
            return super.getMaxRecipes() + 5;
        }
    }

    /**
     * Domum Ornamentum crafting module.
     * Übernommen vom Mechanic.
     */
    public static class DOCraftingModule extends AbstractCraftingBuildingModule.Domum
    {
        public DOCraftingModule(final JobEntry jobEntry)
        {
            super(jobEntry);
        }

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

            // Mekanist akzeptiert alles, was sonst niemand verarbeitet
            return OptionalPredicate.of(handled.negate());
        }

        @Override
        public @NotNull OptionalPredicate<ItemStack> getIngredientValidator()
        {
            return getStaticIngredientValidator();
        }
    }
}