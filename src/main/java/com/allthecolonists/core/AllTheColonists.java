package com.allthecolonists.core;

import com.allthecolonists.core.config.Config;
import com.allthecolonists.core.init.ModBuildingEntries;
import com.allthecolonists.core.init.ModJobEntries;
import com.allthecolonists.core.registry.ModBlocks;
import com.allthecolonists.core.registry.ModItems;
import com.minecolonies.api.tileentities.MinecoloniesTileEntities;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Set;

@Mod(AllTheColonists.MODID)
public class AllTheColonists {

    public static final String MODID = "allthecolonists";
    public static final Logger LOGGER = LogUtils.getLogger();

    /* ---------------- CREATIVE TAB ---------------- */

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB =
            CREATIVE_TABS.register(
                    "colonists_tab",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.allthecolonists"))
                            .withTabsBefore(CreativeModeTabs.COMBAT)
                            .icon(() -> ModItems.VOID_ICON.get().getDefaultInstance())
                            .displayItems((params, output) -> {
                                output.accept(ModItems.MEKANIST_HUT_ITEM.get());
                            })
                            .build()
            );

    /* ---------------- MOD INIT ---------------- */

    public AllTheColonists(final IEventBus modEventBus, final ModContainer container) {

        // Registries
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        CREATIVE_TABS.register(modEventBus);
        ModBuildingEntries.BUILDINGS.register(modEventBus);
        ModJobEntries.JOBS.register(modEventBus);

        // Lifecycle
        modEventBus.addListener(this::commonSetup);

        // Config
        container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        NeoForge.EVENT_BUS.register(this);

        LOGGER.info("AllTheColonists initialized");
    }

    /* ---------------- COMMON SETUP ---------------- */

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            final BlockEntityType<?> buildingType = MinecoloniesTileEntities.BUILDING.get();
            final Set<Block> expanded = new HashSet<>(buildingType.validBlocks);
            expanded.add(ModBlocks.MEKANISM_HUT.get());
            buildingType.validBlocks = expanded;
            LOGGER.info("Registered allthecolonists:mekanism_hut as valid MineColonies building block");
        });
    }

    /* ---------------- SERVER EVENT ---------------- */

    @SubscribeEvent
    public void onServerStarting(final ServerStartingEvent event) {
        LOGGER.info("Server starting with AllTheColonists loaded");
    }
}
