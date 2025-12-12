package net.minecraft.world.level.block.state;

import net.minecraft.world.level.material.MapColor;

public class BlockBehaviour {
    public static class Properties {
        public static Properties of() { return new Properties(); }
        public Properties mapColor(MapColor color) { return this; }
    }
}
