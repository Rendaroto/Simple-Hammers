package com.rendy.hammers;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;


public enum HammerTier implements Tier {
    STONE_HAMMER(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 5f, 1f, 5, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)),
    IRON_HAMMER(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 7f, 2f, 14, () -> Ingredient.of(Items.IRON_INGOT)),
    DIAMOND_HAMMER(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 9f, 3f, 10, () -> Ingredient.of(Items.DIAMOND)),
    GOLD_HAMMER(BlockTags.INCORRECT_FOR_STONE_TOOL, 160, 13f, 0f, 22, () -> Ingredient.of(Items.GOLD_INGOT)),
    NETHERITE_HAMMER(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 10f, 4f, 15, () -> Ingredient.of(Items.NETHERITE_INGOT)),
    WOODEN_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 3f, 0.8f, 3, () -> Ingredient.of(ItemTags.PLANKS));


    private final TagKey<Block> incorrect;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> ingredient;

    HammerTier(TagKey<Block> incorrect, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> ingredient) {
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

    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrect;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.ingredient.get();
    }
}