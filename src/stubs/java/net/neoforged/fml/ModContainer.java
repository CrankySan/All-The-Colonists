package net.neoforged.fml;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModContainer {
    private final Map<Class<?>, Object> extensions = new HashMap<>();

    public <T> void registerExtensionPoint(Class<T> cls, Supplier<T> supplier) {
        extensions.put(cls, supplier.get());
    }
}
