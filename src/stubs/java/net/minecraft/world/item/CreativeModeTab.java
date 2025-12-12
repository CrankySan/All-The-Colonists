package net.minecraft.world.item;

import java.util.function.BiConsumer;
import java.util.function.Supplier;
import net.minecraft.network.chat.Component;

public class CreativeModeTab {
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        public Builder title(Component component) { return this; }
        public Builder withTabsBefore(CreativeModeTab tab) { return this; }
        public Builder icon(Supplier<ItemStack> supplier) { return this; }
        public Builder displayItems(BiConsumer<ItemDisplayParameters, Output> consumer) { return this; }
        public CreativeModeTab build() { return new CreativeModeTab(); }
    }

    public record ItemDisplayParameters() {}

    public interface Output {
        void accept(Object obj);
    }
}
