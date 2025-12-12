package net.neoforged.neoforge.registries;

import java.util.function.Supplier;

public class DeferredItem<T> extends DeferredHolder<T, T> {
    public DeferredItem(Supplier<T> supplier) { super(supplier); }
}
