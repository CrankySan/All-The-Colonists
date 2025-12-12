package net.neoforged.neoforge.event;

import net.minecraft.world.item.CreativeModeTab;

public class BuildCreativeModeTabContentsEvent {
    private final CreativeModeTab tabKey;
    public BuildCreativeModeTabContentsEvent(CreativeModeTab tabKey) {
        this.tabKey = tabKey;
    }
    public CreativeModeTab getTabKey() { return tabKey; }
    public void accept(Object obj) {}
}
