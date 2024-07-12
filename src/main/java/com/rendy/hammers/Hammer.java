package com.rendy.hammers;

import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Hammer {

    // Creating the HAMMER_ITEMS for registering all the HAMMERS
    public static final DeferredRegister<Item> HAMMER_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Hammers.MOD_ID);

    // Registering hammers with tier, attack damage, attack speed, and properties
    public static final RegistryObject<HammerItem> WOODEN_HAMMER = HAMMER_ITEMS.register("wooden_hammer",
            () -> new HammerItem(ItemTier.WOOD, 1, -2.8f,
                    new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<HammerItem> STONE_HAMMER = HAMMER_ITEMS.register("stone_hammer",
            () -> new HammerItem(ItemTier.STONE, 1, -2.8f,
                    new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<HammerItem> GOLDEN_HAMMER = HAMMER_ITEMS.register("golden_hammer",
            () -> new HammerItem(ItemTier.GOLD, 1, -2.8f,
                    new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<HammerItem> IRON_HAMMER = HAMMER_ITEMS.register("iron_hammer",
            () -> new HammerItem(ItemTier.IRON, 1, -2.8f,
                    new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<HammerItem> DIAMOND_HAMMER = HAMMER_ITEMS.register("diamond_hammer",
            () -> new HammerItem(ItemTier.DIAMOND, 1, -2.8f,
                    new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<HammerItem> NETHERITE_HAMMER = HAMMER_ITEMS.register("netherite_hammer",
            () -> new HammerItem(ItemTier.NETHERITE, 1, -2.8f,
                    new Item.Properties().fireResistant().rarity(Rarity.EPIC).tab(ItemGroup.TAB_TOOLS)));
}
