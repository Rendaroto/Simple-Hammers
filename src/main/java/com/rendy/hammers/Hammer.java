package com.rendy.hammers;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Hammer {

    //Creating the HAMMER_ITEMS for registering all the HAMMERS
    public final static DeferredRegister.Items HAMMER_ITEMS = DeferredRegister.createItems(Hammers.MOD_ID);

    //Passing to the register name, supplier () -> (Tier, attackModifier, attackSpeed, properties)
    public static final DeferredItem<HammerItem> WOODEN_HAMMER = HAMMER_ITEMS.registerItem  (
            "wooden_hammer",
            properties -> new HammerItem(HammerTier.WOODEN_HAMMER,1,-2f, properties),
            () -> new Item.Properties());
    public static final DeferredItem<HammerItem> STONE_HAMMER = HAMMER_ITEMS.registerItem  (
            "stone_hammer",
            properties -> new HammerItem(HammerTier.STONE_HAMMER,1,-2.25f,properties),
            () -> new Item.Properties());
    public static final DeferredItem<HammerItem> GOLDEN_HAMMER = HAMMER_ITEMS.registerItem  (
            "golden_hammer",
            properties -> new HammerItem(HammerTier.GOLD_HAMMER,1,-2f,properties),
            () -> new Item.Properties());
    public static final DeferredItem<HammerItem> COPPER_HAMMER = HAMMER_ITEMS.registerItem  (
            "copper_hammer",
            properties -> new HammerItem(HammerTier.COPPER_HAMMER,1,-2.5f,properties),
            () -> new Item.Properties());
    public static final DeferredItem<HammerItem> IRON_HAMMER = HAMMER_ITEMS.registerItem  (
            "iron_hammer",
            properties -> new HammerItem(HammerTier.IRON_HAMMER,1,-2.5f,properties),
            () -> new Item.Properties());
    public static final DeferredItem<HammerItem> DIAMOND_HAMMER = HAMMER_ITEMS.registerItem  (
            "diamond_hammer",
            properties -> new HammerItem(HammerTier.DIAMOND_HAMMER,1,-2.9f,properties),
            () -> new Item.Properties());
    public static final DeferredItem<HammerItem> NETHERITE_HAMMER = HAMMER_ITEMS.registerItem  (
            "netherite_hammer",
            properties -> new HammerItem(HammerTier.NETHERITE_HAMMER,1,-2.8f,properties),
            () -> new Item.Properties().fireResistant().rarity(Rarity.EPIC));
}
