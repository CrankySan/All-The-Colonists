package com.allthecolonists.core.registry;

import com.allthecolonists.core.AllTheColonists;
import com.allthecolonists.core.registry.ModBlocks;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.buildings.registry.IBuildingRegistry;
import com.minecolonies.core.blocks.huts.BlockHutMechanic;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import java.lang.reflect.Field;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.buildings.IBuilding;
import com.minecolonies.api.colony.IColonyView;
import com.minecolonies.api.colony.buildings.views.IBuildingView;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.Set;

public final class ModHuts
{
    private ModHuts() {}

    public static void register(final IEventBus bus)
    {
        try
        {
            final DeferredRegister<BuildingEntry> buildings = DeferredRegister.create(
                    IBuildingRegistry.getInstance().key(),
                    AllTheColonists.MODID
            );

            buildings.register(ModBlocks.BLOCKHUTMEKANISM.getId().getPath(), ModHuts::copyMechanicEntry);

            buildings.register(bus);

            bus.addListener(ModHuts::addBlockEntityCompatibility);
        }
        catch (final NullPointerException exception)
        {
            AllTheColonists.LOGGER.warn(
                    "MineColonies building registry unavailable during initialization; skipping hut registration."
            );
            AllTheColonists.LOGGER.debug("MineColonies API instance lookup failed", exception);
        }
    }

    private static BuildingEntry copyMechanicEntry()
    {
        final BuildingEntry mechanicEntry = new BlockHutMechanic().getBuildingEntry();

        final BuildingEntry.Builder builder = new BuildingEntry.Builder()
                .setRegistryName(ModBlocks.BLOCKHUTMEKANISM.getId())
                .setBuildingBlock(ModBlocks.BLOCKHUTMEKANISM.get())
                .setBuildingProducer(extractBuildingProducer(mechanicEntry))
                .setBuildingViewProducer(extractBuildingViewProducer(mechanicEntry));

        for (final BuildingEntry.ModuleProducer moduleProducer : mechanicEntry.getModuleProducers())
        {
            builder.addBuildingModuleProducer(moduleProducer);
        }

        return builder.createBuildingEntry();
    }

    private static void addBlockEntityCompatibility(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            final Registry<BlockEntityType<?>> blockEntityRegistry = BuiltInRegistries.BLOCK_ENTITY_TYPE;
            final ResourceLocation colonyBuildingId = ResourceLocation.fromNamespaceAndPath("minecolonies", "colonybuilding");
            final BlockEntityType<?> colonyBuildingType = blockEntityRegistry.get(colonyBuildingId);

            if (colonyBuildingType != null)
            {
                try
                {
                    final Field validBlocksField = BlockEntityType.class.getDeclaredField("validBlocks");
                    validBlocksField.setAccessible(true);

                    @SuppressWarnings("unchecked")
                    final Set<Block> validBlocks = (Set<Block>) validBlocksField.get(colonyBuildingType);
                    validBlocks.add(ModBlocks.BLOCKHUTMEKANISM.get());

                    AllTheColonists.LOGGER.debug("Registered Mekanism hut block with MineColonies colony building block entity.");
                }
                catch (final ReflectiveOperationException exception)
                {
                    AllTheColonists.LOGGER.warn(
                            "Failed to extend MineColonies colony building block entity compatibility for Mekanism hut.",
                            exception
                    );
                }
            }
            else
            {
                AllTheColonists.LOGGER.warn("Unable to locate MineColonies colony building block entity type; Mekanism hut tile entity compatibility not applied.");
            }
        });
    }

    @SuppressWarnings("unchecked")
    private static BiFunction<IColony, BlockPos, IBuilding> extractBuildingProducer(final BuildingEntry sourceEntry)
    {
        return (BiFunction<IColony, BlockPos, IBuilding>) getFieldValue(
                sourceEntry,
                "buildingProducer",
                BiFunction.class
        );
    }

    @SuppressWarnings("unchecked")
    private static Supplier<BiFunction<IColonyView, BlockPos, IBuildingView>> extractBuildingViewProducer(
            final BuildingEntry sourceEntry
    )
    {
        final Supplier<BiFunction<IColonyView, BlockPos, IBuildingView>> viewProducer =
                (Supplier<BiFunction<IColonyView, BlockPos, IBuildingView>>) getFieldValue(
                        sourceEntry,
                        "buildingViewProducer",
                        Supplier.class
                );

        for (final BuildingEntry.ModuleProducer moduleProducer : sourceEntry.getModuleProducers())
        {
            // The module producers are stored on the copied entry via setBuildingProducer,
            // so there is no extra bookkeeping required here.
        }

        return viewProducer;
    }

    private static <T> T getFieldValue(final BuildingEntry sourceEntry, final String fieldName, final Class<T> expectedType)
    {
        try
        {
            final Field field = BuildingEntry.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return expectedType.cast(field.get(sourceEntry));
        }
        catch (final ReflectiveOperationException exception)
        {
            throw new IllegalStateException("Unable to clone MineColonies building entry for Mekanism hut", exception);
        }
    }
}
