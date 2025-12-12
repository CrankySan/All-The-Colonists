package net.neoforged.neoforge.common;

import java.util.List;
import java.util.function.Predicate;

public class ModConfigSpec {
    public static class Builder {
        public Builder comment(String text) { return this; }
        public BooleanValue define(String name, boolean value) { return new BooleanValue(value); }
        public IntValue defineInRange(String name, int value, int min, int max) { return new IntValue(value); }
        public <T> ConfigValue<T> define(String name, T value) { return new ConfigValue<>(value); }
        public <T> ConfigValue<List<? extends String>> defineListAllowEmpty(String name, List<? extends String> defaults, java.util.function.Supplier<String> supplier, Predicate<Object> validator) {
            return new ConfigValue<>(defaults);
        }
        public ModConfigSpec build() { return new ModConfigSpec(); }
    }

    public static class BooleanValue extends ConfigValue<Boolean> {
        public BooleanValue(Boolean value) { super(value); }
    }

    public static class IntValue extends ConfigValue<Integer> {
        public IntValue(Integer value) { super(value); }
    }

    public static class ConfigValue<T> {
        private final T value;
        public ConfigValue(T value) { this.value = value; }
        public T get() { return value; }
    }
}
