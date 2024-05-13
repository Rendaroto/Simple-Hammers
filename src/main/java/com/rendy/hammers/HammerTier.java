package com.rendy.hammers;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;


public enum HammerTier implements Tier {
    /*
    *
    * All the tier for this mod are the same as vanilla but the uses are 9 times as i have to break 9 blocks at the time
    *
     */
    WOODEN_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, Tiers.WOOD.getUses()*9, Tiers.WOOD.getSpeed(), Tiers.WOOD.getAttackDamageBonus(), Tiers.WOOD.getEnchantmentValue(), Tiers.WOOD::getRepairIngredient),
    STONE_HAMMER(BlockTags.INCORRECT_FOR_STONE_TOOL, Tiers.STONE.getUses()*9, Tiers.STONE.getSpeed(), Tiers.STONE.getAttackDamageBonus(), Tiers.STONE.getEnchantmentValue(), Tiers.STONE::getRepairIngredient),
    GOLD_HAMMER(BlockTags.INCORRECT_FOR_STONE_TOOL, Tiers.GOLD.getUses()*9, Tiers.GOLD.getSpeed(), Tiers.GOLD.getAttackDamageBonus(), Tiers.IRON.getEnchantmentValue(), Tiers.GOLD::getRepairIngredient),
    IRON_HAMMER(BlockTags.INCORRECT_FOR_IRON_TOOL, Tiers.IRON.getUses()*9, Tiers.IRON.getSpeed(), Tiers.IRON.getAttackDamageBonus(), Tiers.GOLD.getEnchantmentValue(), Tiers.IRON::getRepairIngredient),
    DIAMOND_HAMMER(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, Tiers.DIAMOND.getUses()*9, Tiers.DIAMOND.getSpeed(), Tiers.DIAMOND.getAttackDamageBonus(), Tiers.DIAMOND.getEnchantmentValue(), Tiers.DIAMOND::getRepairIngredient),
    NETHERITE_HAMMER(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, Tiers.NETHERITE.getUses()*9, Tiers.NETHERITE.getSpeed(), Tiers.NETHERITE.getAttackDamageBonus(), Tiers.NETHERITE.getEnchantmentValue(), Tiers.NETHERITE::getRepairIngredient);



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

    public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrect;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.ingredient.get();
    }
}