package com.allthecolonists.core;

import org.slf4j.Logger;

import com.allthecolonists.core.init.ModBuildingEntries;
import com.allthecolonists.core.registry.ModBlocks;
import com.allthecolonists.core.registry.ModItems;
import com.mojang.logging.LogUtils;
import com.minecolonies.api.tileentities.MinecoloniesTileEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.fml.util.ObfuscationReflectionHelper;
import java.util.Set;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(AllTheColonists.MODID)
public class AllTheColonists {

    public static final String MODID = "allthecolonists";
    public static final Logger LOGGER = LogUtils.getLogger();

    // -------- CREATIVE TAB REGISTER --------
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COLONISTS_TAB =
            CREATIVE_MODE_TABS.register(
                    "colonists_tab",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.AllTheColonists"))
                            .withTabsBefore(CreativeModeTabs.COMBAT)
                            .icon(() -> ModItems.VOID_ICON.get().getDefaultInstance())
                            .displayItems((parameters, output) -> {
                                // bewusst leer – Hütten NICHT in Vanilla-Tabs
                            })
                            .build()
            );

    public AllTheColonists(IEventBus modEventBus, ModContainer modContainer) {

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModBuildingEntries.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        NeoForge.EVENT_BUS.register(this);

        LOGGER.info("AllTheColonists wurde initialisiert!");
    }

    /**
     * EXTREM WICHTIG FÜR MINECOLONIES
     * Ohne diesen Block crasht oder erkennt MineColonies den Hut NICHT.
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ModBuildingEntries::init);
        event.enqueueWork(() -> {
            final BlockEntityType<?> buildingType = MinecoloniesTileEntities.BUILDING.get();
            final var validBlocks = ObfuscationReflectionHelper.<Set<Block>, BlockEntityType<?>>getPrivateValue(
                    BlockEntityType.class,
                    buildingType,
                    "validBlocks"
            );

            if (validBlocks != null) {
                validBlocks.add(ModBlocks.MEKANISM_HUT.get());
            } else {
                LOGGER.warn("Konnte MineColonies gültige Blöcke nicht erweitern – Mekanism-Hütte fehlt eventuell.");
            }
        });
        LOGGER.info("Common Setup läuft für AllTheColonists...");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // KEINE Hütten in Vanilla-Tabs einfügen!
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Server Starting Event empfangen!");
    }
}
