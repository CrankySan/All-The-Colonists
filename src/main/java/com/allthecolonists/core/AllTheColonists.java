package com.allthecolonists.core;

import org.slf4j.Logger;

import com.allthecolonists.core.registry.ModBlocks;
import com.allthecolonists.core.registry.ModItems;
import com.mojang.logging.LogUtils;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.buildings.registry.IBuildingRegistry;
import com.minecolonies.api.tileentities.MinecoloniesTileEntities;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
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
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
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

        // Items registrieren
        ModItems.register(modEventBus);

        // Creative Tabs registrieren
        CREATIVE_MODE_TABS.register(modEventBus);

        // Events
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::onRegisterBlockEntityValidBlocks);

        NeoForge.EVENT_BUS.register(this);

        LOGGER.info("AllTheColonists wurde initialisiert!");
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Common Setup läuft für AllTheColonists...");

        var registry = IBuildingRegistry.getInstance();
        ResourceLocation mekanismId = ResourceLocation.fromNamespaceAndPath(MODID, "mekanism");
        BuildingEntry mechanicEntry = registry.get(ResourceLocation.fromNamespaceAndPath("minecolonies", "mechanic"));

        if (mechanicEntry != null && !registry.containsKey(mekanismId)) {
            Registry.register(registry, mekanismId, mechanicEntry);
            LOGGER.info("Registered mekanism building entry as a local copy of the MineColonies mechanic hut.");
        }
    }

    private void onRegisterBlockEntityValidBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(MinecoloniesTileEntities.BUILDING.get(), ModBlocks.BLOCKHUTMEKANISM.get());
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