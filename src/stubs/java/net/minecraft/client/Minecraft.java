package net.minecraft.client;

public class Minecraft {
    private static final Minecraft INSTANCE = new Minecraft();
    private final User user = new User();

    public static Minecraft getInstance() { return INSTANCE; }
    public User getUser() { return user; }

    public static class User {
        public String getName() { return "player"; }
    }
}
