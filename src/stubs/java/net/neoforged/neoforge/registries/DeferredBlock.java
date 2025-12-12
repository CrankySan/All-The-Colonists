package net.neoforged.neoforge.registries;

import java.util.function.Supplier;

public class DeferredBlock<T> extends DeferredHolder<T, T> {
    public DeferredBlock(Supplier<T> supplier) { super(supplier); }
}
