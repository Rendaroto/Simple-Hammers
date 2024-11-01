package com.rendy.hammers;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;


public class HammerTier{
    /*
    *
    * All the tier for this mod are the same as vanilla but the uses are 9 times as I have to break 9 blocks at the time
    *
     */
    public static final ToolMaterial WOODEN_HAMMER = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, ToolMaterial.WOOD.durability()*9, ToolMaterial.WOOD.speed(), ToolMaterial.WOOD.attackDamageBonus(), ToolMaterial.WOOD.enchantmentValue(), ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial STONE_HAMMER = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, ToolMaterial.STONE.durability()*9, ToolMaterial.STONE.speed(), ToolMaterial.STONE.attackDamageBonus(), ToolMaterial.STONE.enchantmentValue(), ItemTags.STONE_TOOL_MATERIALS);
    public static final ToolMaterial GOLD_HAMMER = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, ToolMaterial.GOLD.durability()*9, ToolMaterial.GOLD.speed(), ToolMaterial.GOLD.attackDamageBonus(), ToolMaterial.IRON.enchantmentValue(), ItemTags.GOLD_TOOL_MATERIALS);
    public static final ToolMaterial IRON_HAMMER = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, ToolMaterial.IRON.durability()*9, ToolMaterial.IRON.speed(), ToolMaterial.IRON.attackDamageBonus(), ToolMaterial.IRON.enchantmentValue(), ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial DIAMOND_HAMMER = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, ToolMaterial.DIAMOND.durability()*9, ToolMaterial.DIAMOND.speed(), ToolMaterial.DIAMOND.attackDamageBonus(), ToolMaterial.DIAMOND.enchantmentValue(), ItemTags.DIAMOND_TOOL_MATERIALS);
    public static final ToolMaterial NETHERITE_HAMMER = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, ToolMaterial.NETHERITE.durability()*9, ToolMaterial.NETHERITE.speed(), ToolMaterial.NETHERITE.attackDamageBonus(), ToolMaterial.NETHERITE.enchantmentValue(), ItemTags.NETHERITE_TOOL_MATERIALS);

    private final TagKey<Block> incorrect;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final ItemTags ingredient;

    HammerTier(TagKey<Block> incorrect, int uses, float speed, float damage, int enchantmentValue, ItemTags ingredient) {
        this.incorrect = incorrect;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.ingredient = ingredient;
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrect;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull ItemTags getRepairIngredient() {
        return this.ingredient;
    }
}