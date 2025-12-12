package net.neoforged.neoforge.registries;

import java.util.function.Supplier;

public class DeferredRegister<T> {
    public static class Blocks extends DeferredRegister<Object> {
        public Blocks(String modid) { super(modid); }
    }
    public static class Items extends DeferredRegister<Object> {
        public Items(String modid) { super(modid); }
        public <I> DeferredItem<I> registerSimpleItem(String name, Object properties) { return new DeferredItem<>(() -> (I) new Object()); }
        public <I> DeferredItem<I> registerSimpleBlockItem(String name, DeferredHolder<?, ?> block) { return new DeferredItem<>(() -> (I) new Object()); }
    }

    private final String modid;
    public DeferredRegister(String modid) { this.modid = modid; }

    public static Blocks createBlocks(String modid) { return new Blocks(modid); }
    public static Items createItems(String modid) { return new Items(modid); }
    public static <T> DeferredRegister<T> create(Object registryKey, String modid) { return new DeferredRegister<>(modid); }

    @SuppressWarnings("unchecked")
    public <I> DeferredHolder<T, I> register(String name, Supplier<I> sup) {
        if (this instanceof Blocks) {
            return (DeferredHolder<T, I>) new DeferredBlock<>(sup);
        }
        if (this instanceof Items) {
            return (DeferredHolder<T, I>) new DeferredItem<>(sup);
        }
        return new DeferredHolder<>(sup);
    }
    public void register(Object bus) {}
}
