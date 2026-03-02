import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.registries.BuiltInRegistries;
import org.jetbrains.annotations.NotNull;

public class BuildingMekanistHut extends AbstractBuilding {

    public BuildingMekanistHut(final IColony colony, final BlockPos location) {
        super(colony, location);
    }

    @Override
    public String getSchematicName() {
        return "mekanist_hut";
    }

    /**
     * Crafting module for Mekanism Metallurgic Infuser recipes.
     * Only recipes whose intermediate block is the Metallurgic Infuser are accepted.
     */
    public static class InfuserCraftingModule extends BuildingMechanic.CraftingModule {

        private static final ResourceLocation METALLURGIC_INFUSER_ID =
                ResourceLocation.fromNamespaceAndPath("mekanism", "metallurgic_infuser");

        public InfuserCraftingModule(final JobEntry jobEntry) {
            super(jobEntry);
        }

        @Override
        public boolean isRecipeCompatible(@NotNull final IGenericRecipe recipe) {
            if (!super.isRecipeCompatible(recipe)) {
                return false;
            }

            final var intermediate = recipe.getIntermediate();
            if (intermediate == null || intermediate == Blocks.AIR) {
                return false;
            }

            return METALLURGIC_INFUSER_ID.equals(
                    BuiltInRegistries.BLOCK.getKey(intermediate)
            );
        }
    }
}