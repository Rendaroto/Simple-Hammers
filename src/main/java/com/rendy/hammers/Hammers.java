package com.rendy.hammers;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Hammers.MOD_ID)
public class Hammers
{
    public static final String MOD_ID = "simple_hammers";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Hammers(IEventBus modEventBus)
    {
        NeoForge.EVENT_BUS.register(HammerEvents.class);

        Hammer.HAMMER_ITEMS.register(modEventBus);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM SIMPLE HAMMER SETUP");
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
            event.accept(Hammer.WOODEN_HAMMER);
            event.accept(Hammer.STONE_HAMMER);
            event.accept(Hammer.GOLDEN_HAMMER);
            event.accept(Hammer.IRON_HAMMER);
            event.accept(Hammer.DIAMOND_HAMMER);
            event.accept(Hammer.NETHERITE_HAMMER);
    }
}
