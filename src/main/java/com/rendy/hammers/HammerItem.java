package com.rendy.hammers;

import com.rendy.hammers.tags.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.state.BlockState;

public class HammerItem extends Item {

    HammerItem(ToolMaterial pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pProperties.tool(pTier, ModTags.Blocks.MINEABLE_WITH_HAMMER,pAttackDamageModifier,pAttackSpeedModifier,0.0f));
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return (state.is(BlockTags.MINEABLE_WITH_SHOVEL)
                ? super.getDestroySpeed(stack, state) * 0.6F
                : super.getDestroySpeed(stack, state));
    }

}
