package com.rendy.hammers;

import net.minecraft.block.BlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.common.ToolType;

public class HammerItem extends PickaxeItem {
    HammerItem(IItemTier tier, int attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties.addToolType(ToolType.PICKAXE, tier.getLevel())
                .addToolType(ToolType.SHOVEL, tier.getLevel()));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return true;
    }

    // The Hammer is both a Pickaxe and a Shovel
    @Override
    public boolean isCorrectToolForDrops(BlockState p_150897_1_) {
        return (p_150897_1_.getHarvestTool() == ToolType.PICKAXE || p_150897_1_.getHarvestTool() == ToolType.SHOVEL) && this.getTier().getLevel() >= p_150897_1_.getHarvestLevel();
    }
}