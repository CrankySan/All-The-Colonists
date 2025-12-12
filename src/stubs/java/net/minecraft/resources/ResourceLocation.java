package net.minecraft.resources;

public class ResourceLocation {
    private final String location;
    public ResourceLocation(String location) { this.location = location; }
    public static ResourceLocation parse(String name) { return new ResourceLocation(name); }
    public static ResourceLocation fromNamespaceAndPath(String namespace, String path) { return new ResourceLocation(namespace + ":" + path); }
    @Override public String toString() { return location; }
}
