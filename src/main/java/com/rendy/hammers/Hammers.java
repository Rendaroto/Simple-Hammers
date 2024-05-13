package com.rendy.hammers;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

// The value here should match an entry in the META-INF/
// mods.toml file for 1.20.4 and below
// neoforge.mods.toml file for 1.20.5 and above
@Mod(Hammers.MOD_ID)
public class Hammers
{
    public static final String MOD_ID = "simple_hammers";

    public Hammers(IEventBus modEventBus)
    {
        NeoForge.EVENT_BUS.register(HammerEvents.class);
        Hammer.HAMMER_ITEMS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        //Adding the items to the existing Creative Tab
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(Hammer.WOODEN_HAMMER);
            event.accept(Hammer.STONE_HAMMER);
            event.accept(Hammer.GOLDEN_HAMMER);
            event.accept(Hammer.IRON_HAMMER);
            event.accept(Hammer.DIAMOND_HAMMER);
            event.accept(Hammer.NETHERITE_HAMMER);
        }
    }
}
