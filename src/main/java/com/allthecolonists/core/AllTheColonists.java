package com.allthecolonists.core;

import org.slf4j.Logger;

import com.allthecolonists.core.registry.ModBlocks;
import com.allthecolonists.core.registry.ModItems;
import com.allthecolonists.core.registry.ModBuildings;
import com.allthecolonists.core.registry.ModJobs;
import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
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
            CREATIVE_MODE_TABS.register("colonists_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.AllTheColonists"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModItems.VOID_ICON.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.BLOCKHUTMEKANISM_ITEM.get());
                    })
                    .build()
            );

    public AllTheColonists(IEventBus modEventBus, ModContainer modContainer) {

        // BLOCKS + ITEMS aus ModBlocks registrieren
        ModBlocks.register(modEventBus);

        // Buildings registrieren
        ModBuildings.register(modEventBus);

        // Items registrieren
        ModItems.register(modEventBus);

        // Jobs registrieren
        ModJobs.register(modEventBus);

        // Creative Tabs registrieren
        CREATIVE_MODE_TABS.register(modEventBus);

        // Events
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        NeoForge.EVENT_BUS.register(this);

        LOGGER.info("AllTheColonists wurde initialisiert!");
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Common Setup läuft für AllTheColonists...");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.BLOCKHUTMEKANISM_ITEM);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Server Starting Event empfangen!");
    }
}