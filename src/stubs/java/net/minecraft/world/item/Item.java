package net.minecraft.world.item;

public class Item {
    public static class Properties {}
    public Item(Properties properties) {}
    public ItemStack getDefaultInstance() { return new ItemStack(this); }
}
