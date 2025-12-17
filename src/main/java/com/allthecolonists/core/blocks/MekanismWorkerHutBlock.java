package com.allthecolonists.core.blocks;

import com.minecolonies.core.blocks.huts.BlockHutMechanic;

/**
 * Mekanism worker hut block that reuses the MineColonies mechanist building entry while
 * exposing the Mekanism hut registry id.
 */
public class MekanismWorkerHutBlock extends BlockHutMechanic
{
    @Override
    public String getHutName()
    {
        return "blockhutmekanism";
    }
}
