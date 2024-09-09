package com.rendy.hammers;

import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum HammerTier implements Tier {
    /*
     *
     * All the tier for this mod are the same as vanilla but the uses are 9 times as I have to break 9 blocks at the time
     *
     */
    WOODEN_HAMMER(Tiers.WOOD.getLevel(), Tiers.WOOD.getUses()*9, Tiers.WOOD.getSpeed(), Tiers.WOOD.getAttackDamageBonus(), Tiers.WOOD.getEnchantmentValue(), Tiers.WOOD::getRepairIngredient),
    STONE_HAMMER(Tiers.STONE.getLevel(),Tiers.STONE.getUses()*9, Tiers.STONE.getSpeed(), Tiers.STONE.getAttackDamageBonus(), Tiers.STONE.getEnchantmentValue(), Tiers.STONE::getRepairIngredient),
    GOLD_HAMMER(Tiers.GOLD.getLevel(),Tiers.GOLD.getUses()*9, Tiers.GOLD.getSpeed(), Tiers.GOLD.getAttackDamageBonus(), Tiers.IRON.getEnchantmentValue(), Tiers.GOLD::getRepairIngredient),
    IRON_HAMMER(Tiers.IRON.getLevel(),Tiers.IRON.getUses()*9, Tiers.IRON.getSpeed(), Tiers.IRON.getAttackDamageBonus(), Tiers.GOLD.getEnchantmentValue(), Tiers.IRON::getRepairIngredient),
    DIAMOND_HAMMER(Tiers.DIAMOND.getLevel(),Tiers.DIAMOND.getUses()*9, Tiers.DIAMOND.getSpeed(), Tiers.DIAMOND.getAttackDamageBonus(), Tiers.DIAMOND.getEnchantmentValue(), Tiers.DIAMOND::getRepairIngredient),
    NETHERITE_HAMMER(Tiers.NETHERITE.getLevel(),Tiers.NETHERITE.getUses()*9, Tiers.NETHERITE.getSpeed(), Tiers.NETHERITE.getAttackDamageBonus(), Tiers.NETHERITE.getEnchantmentValue(), Tiers.NETHERITE::getRepairIngredient);

    private  final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    HammerTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue(repairIngredient);
    }

    public int getLevel(){
        return this.level;
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


    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}