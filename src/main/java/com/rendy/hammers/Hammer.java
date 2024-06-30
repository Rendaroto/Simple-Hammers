package com.rendy.hammers;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Hammer {

    //Creating the HAMMER_ITEMS for registering all the HAMMERS
    public final static DeferredRegister<Item> HAMMER_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Hammers.MOD_ID);

    //Passing to the register name, supplier () -> (Tier, attackModifier, attackSpeed, properties)
    public static final RegistryObject<HammerItem> WOODEN_HAMMER = HAMMER_ITEMS.register  ("wooden_hammer",
            () -> new HammerItem(Tiers.WOOD,1,-2.8f,
                    new Item.Properties()));
    public static final RegistryObject<HammerItem> STONE_HAMMER = HAMMER_ITEMS.register  ("stone_hammer",
            () -> new HammerItem(Tiers.STONE,1,-2.8f,
                    new Item.Properties()));
    public static final RegistryObject<HammerItem> GOLDEN_HAMMER = HAMMER_ITEMS.register  ("golden_hammer",
            () -> new HammerItem(Tiers.GOLD,1,-2.8f,
                    new Item.Properties()));
    public static final RegistryObject<HammerItem> IRON_HAMMER = HAMMER_ITEMS.register  ("iron_hammer",
            () -> new HammerItem(Tiers.IRON,1,-2.8f,
                    new Item.Properties()));
    public static final RegistryObject<HammerItem> DIAMOND_HAMMER = HAMMER_ITEMS.register  ("diamond_hammer",
            () -> new HammerItem(Tiers.DIAMOND,1,-2.8f,
                    new Item.Properties()));
    public static final RegistryObject<HammerItem> NETHERITE_HAMMER = HAMMER_ITEMS.register  ("netherite_hammer",
            () -> new HammerItem(Tiers.NETHERITE,1,-2.8f,
                    new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

}
