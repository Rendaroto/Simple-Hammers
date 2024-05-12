package com.rendy.hammers;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;


public enum HammerTier implements Tier {
    WOODEN_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, Tiers.WOOD.getUses()*9, Tiers.WOOD.getSpeed(), 0.8f, 15, () -> Ingredient.of(ItemTags.PLANKS)),
    STONE_HAMMER(BlockTags.INCORRECT_FOR_STONE_TOOL, Tiers.STONE.getUses()*9, Tiers.STONE.getSpeed(), 1f, 5,() -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)),
    GOLD_HAMMER(BlockTags.INCORRECT_FOR_STONE_TOOL, Tiers.GOLD.getUses()*9, Tiers.GOLD.getSpeed(), 0f, 22, () -> Ingredient.of(Items.GOLD_INGOT)),
    IRON_HAMMER(BlockTags.INCORRECT_FOR_IRON_TOOL, Tiers.IRON.getUses()*9, Tiers.IRON.getSpeed(), 2f, 14, () -> Ingredient.of(Items.IRON_INGOT)),
    DIAMOND_HAMMER(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, Tiers.DIAMOND.getUses()*9, Tiers.DIAMOND.getSpeed(), 3f, 10, () -> Ingredient.of(Items.DIAMOND)),
    NETHERITE_HAMMER(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, Tiers.NETHERITE.getUses()*9, Tiers.NETHERITE.getSpeed(), 4f, 15, () -> Ingredient.of(Items.NETHERITE_INGOT));



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