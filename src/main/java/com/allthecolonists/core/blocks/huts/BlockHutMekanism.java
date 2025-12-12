package com.allthecolonists.core.blocks.huts;

import com.minecolonies.api.blocks.AbstractBlockHut;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.buildings.registry.IBuildingRegistry;
import net.minecraft.resources.ResourceLocation;
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
    public BuildingEntry getBuildingEntry()
    {
        // Fallback to an existing MineColonies building entry until a dedicated Mekanism hut is implemented.
        // Using a known entry prevents null-pointer issues when a block entity is created for this block.
        return IBuildingRegistry.getInstance().get(ResourceLocation.fromNamespaceAndPath("minecolonies", "warehouse"));
    }
}
