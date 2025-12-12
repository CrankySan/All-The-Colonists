package com.allthecolonists.core.blocks.huts;

import com.minecolonies.api.blocks.AbstractBlockHut;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.buildings.registry.IBuildingRegistry;
import com.minecolonies.core.tileentities.TileEntityColonyBuilding;
import com.allthecolonists.core.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

/**
 * Hut for the mekanist. No different from {@link AbstractBlockHut}
 */
public class BlockHutMekanism extends AbstractBlockHut<BlockHutMekanism>
{
    public BlockHutMekanism(final BlockBehaviour.Properties properties)
    {
        super(properties);
    }

    @NotNull
    @Override
    public String getHutName()
    {
        return "blockhutmekanism";
    }

    @Override
    public BlockEntity newBlockEntity(final BlockPos pos, final BlockState state)
    {
        final TileEntityColonyBuilding tile = ModBlockEntities.MEKANISM_COLONY_BUILDING.get().create(pos, state);
        if (tile != null)
        {
            tile.registryName = getBuildingEntry().getRegistryName();
        }
        return tile;
    }

    @Override
    public BuildingEntry getBuildingEntry()
    {
        // Fallback to an existing MineColonies building entry until a dedicated Mekanism hut is implemented.
        // Using a known entry prevents null-pointer issues when a block entity is created for this block.
        return IBuildingRegistry.getInstance().get(ResourceLocation.fromNamespaceAndPath("minecolonies", "warehouse"));
    }
}
