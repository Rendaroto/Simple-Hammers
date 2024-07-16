package com.rendy.hammers;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemNameTag;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipesFunction {

    public static void registerCraftingTableRecipe(){
        GameRegistry.addRecipe(new ItemStack(Hammer.WOODEN_HAMMER), new Object[]
                {"ISI"," P "," B ",'I', new ItemStack(Blocks.PLANKS,1,0),'S', Items.WOODEN_SHOVEL,'P',Items.WOODEN_PICKAXE,'B',Items.STICK}
        );
        GameRegistry.addRecipe(new ItemStack(Hammer.WOODEN_HAMMER), new Object[]
                {"ISI"," P "," B ",'I', new ItemStack(Blocks.PLANKS,1,1),'S', Items.WOODEN_SHOVEL,'P',Items.WOODEN_PICKAXE,'B',Items.STICK}
        );
        GameRegistry.addRecipe(new ItemStack(Hammer.WOODEN_HAMMER), new Object[]
                {"ISI"," P "," B ",'I', new ItemStack(Blocks.PLANKS,1,2),'S', Items.WOODEN_SHOVEL,'P',Items.WOODEN_PICKAXE,'B',Items.STICK}
        );
        GameRegistry.addRecipe(new ItemStack(Hammer.WOODEN_HAMMER), new Object[]
                {"ISI"," P "," B ",'I', new ItemStack(Blocks.PLANKS,1,3),'S', Items.WOODEN_SHOVEL,'P',Items.WOODEN_PICKAXE,'B',Items.STICK}
        );
        GameRegistry.addRecipe(new ItemStack(Hammer.WOODEN_HAMMER), new Object[]
                {"ISI"," P "," B ",'I', new ItemStack(Blocks.PLANKS,1,4),'S', Items.WOODEN_SHOVEL,'P',Items.WOODEN_PICKAXE,'B',Items.STICK}
        );
        GameRegistry.addRecipe(new ItemStack(Hammer.WOODEN_HAMMER), new Object[]
                {"ISI"," P "," B ",'I', new ItemStack(Blocks.PLANKS,1,5),'S', Items.WOODEN_SHOVEL,'P',Items.WOODEN_PICKAXE,'B',Items.STICK}
        );
        GameRegistry.addRecipe(new ItemStack(Hammer.STONE_HAMMER), new Object[]
                {"ISI"," P "," B ",'I', Item.getItemFromBlock(Blocks.COBBLESTONE),'S', Items.STONE_SHOVEL,'P',Items.STONE_PICKAXE,'B',Items.STICK}
        );
        GameRegistry.addRecipe(new ItemStack(Hammer.GOLDEN_HAMMER), new Object[]
                {"ISI"," P "," B ",'I', Item.getItemFromBlock(Blocks.GOLD_BLOCK),'S', Items.GOLDEN_SHOVEL,'P',Items.GOLDEN_PICKAXE,'B',Items.STICK}
        );
        GameRegistry.addRecipe(new ItemStack(Hammer.IRON_HAMMER), new Object[]
                {"ISI"," P "," B ",'I', Item.getItemFromBlock(Blocks.IRON_BLOCK),'S', Items.IRON_SHOVEL,'P',Items.IRON_PICKAXE,'B',Items.STICK}
        );
        GameRegistry.addRecipe(new ItemStack(Hammer.DIAMOND_HAMMER), new Object[]
                {"ISI"," P "," B ",'I', Item.getItemFromBlock(Blocks.DIAMOND_BLOCK),'S', Items.DIAMOND_SHOVEL,'P',Items.DIAMOND_PICKAXE,'B',Items.STICK}
        );
    }

}
