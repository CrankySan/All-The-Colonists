package net.neoforged.bus.api;

import java.util.function.Consumer;

public interface IEventBus {
    default <T> void addListener(Consumer<T> consumer) {}
    default void register(Object obj) {}
}
