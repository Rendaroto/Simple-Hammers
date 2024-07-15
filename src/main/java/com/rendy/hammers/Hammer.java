package com.rendy.hammers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.event.RegistryEvent;

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import static com.rendy.hammers.Hammers.MOD_ID;

public class Hammer {

    public static final List<Item> HAMMERS = new ArrayList<Item>();

    public static final Item WOODEN_HAMMER = new HammerItem(ToolMaterial.WOOD, 1, -2.8f, "wooden_hammer");
    public static final Item STONE_HAMMER = new HammerItem(ToolMaterial.STONE, 1, -2.8f,"stone_hammer");
    public static final Item GOLDEN_HAMMER = new HammerItem(ToolMaterial.GOLD, 1, -2.8f,"golden_hammer");
    public static final Item IRON_HAMMER = new HammerItem(ToolMaterial.IRON, 1, -2.8f,"iron_hammer");
    public static final Item DIAMOND_HAMMER = new HammerItem(ToolMaterial.DIAMOND, 1, -2.8f,"diamond_hammer");
}
