package com.rendy.hammers;

import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;

import java.util.ArrayList;
import java.util.List;

import static com.rendy.hammers.Hammers.MOD_ID;

public class Hammer {

    public static Item WOODEN_HAMMER;
    public static Item STONE_HAMMER;
    public static Item GOLDEN_HAMMER;
    public static Item IRON_HAMMER;
    public static Item DIAMOND_HAMMER;

    public static final List<Item> HAMMERS = new ArrayList<>();

    public static void init(){

        WOODEN_HAMMER = new HammerItem(ToolMaterial.WOOD, 1, -2.8f, "wooden_hammer");
        STONE_HAMMER = new HammerItem(ToolMaterial.STONE, 1, -2.8f,"stone_hammer");
        GOLDEN_HAMMER = new HammerItem(ToolMaterial.GOLD, 1, -2.8f,"golden_hammer");
        IRON_HAMMER = new HammerItem(ToolMaterial.IRON, 1, -2.8f,"iron_hammer");
        DIAMOND_HAMMER = new HammerItem(ToolMaterial.EMERALD, 1, -2.8f,"diamond_hammer");

    }
}
