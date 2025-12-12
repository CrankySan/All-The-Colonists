package net.minecraft.core.registries;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class BuiltInRegistries {
    public static final Registry<Item> ITEM = new Registry<>();

    public static class Registry<T> {
        private final Set<String> keys = new HashSet<>();
        public boolean containsKey(ResourceLocation location) { return keys.contains(location.toString()); }
    }
}
