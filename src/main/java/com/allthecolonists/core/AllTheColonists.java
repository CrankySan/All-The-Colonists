package com.allthecolonists.core;

import com.allthecolonists.core.registry.ModBlocks;
import com.allthecolonists.core.registry.ModHuts;
import com.allthecolonists.core.registry.ModItems;
import com.allthecolonists.core.registry.ModJobs;
import com.mojang.logging.LogUtils;
import com.minecolonies.api.tileentities.MinecoloniesTileEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(AllTheColonists.MODID)
public class AllTheColonists
{
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

    public AllTheColonists(final IEventBus modEventBus, final ModContainer modContainer)
    {
        // Core registries
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModJobs.register(modEventBus);
        ModHuts.register(modEventBus);

        // Creative tabs
        CREATIVE_MODE_TABS.register(modEventBus);

        // Events
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::onRegisterBlockEntityValidBlocks);

        NeoForge.EVENT_BUS.register(this);

        LOGGER.info("AllTheColonists initialized");
    }

    private void onRegisterBlockEntityValidBlocks(final BlockEntityTypeAddBlocksEvent event)
    {
        event.modify(
                MinecoloniesTileEntities.BUILDING.get(),
                ModBlocks.BLOCKHUTMEKANISM.get()
        );
    }

    private void addCreative(final BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
        {
            event.accept(ModBlocks.BLOCKHUTMEKANISM_ITEM);
        }
    }

    @SubscribeEvent
    public void onServerStarting(final ServerStartingEvent event)
    {
        LOGGER.info("Server Starting Event received");
    }
}