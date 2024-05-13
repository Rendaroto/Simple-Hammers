package com.rendy.hammers;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.ToolAction;
import net.neoforged.neoforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;

public class HammerItem extends PickaxeItem {
        HammerItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
            super(pTier, pProperties.attributes((createAttributes(pTier, pAttackDamageModifier, pAttackSpeedModifier))));
        }

        @Override
        public boolean isEnchantable(@NotNull ItemStack pStack) {
            return true;
        }

        @Override
        public boolean isRepairable(@NotNull ItemStack stack) {
            return true;
        }

        //The Hammer is both a Pickaxe and a Shovel
        @Override
        public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ToolAction toolAction) {
            return ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction) || ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction);
        }
}
