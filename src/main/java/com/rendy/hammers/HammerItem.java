package com.rendy.hammers;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;

public class HammerItem extends PickaxeItem {
        HammerItem(ToolMaterial pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
            super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        }

        @Override
        public boolean isRepairable(@NotNull ItemStack stack) {
            return true;
        }

        //The Hammer is both a Pickaxe and a Shovel
        @Override
        public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ItemAbility itemAbility) {
            return ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(itemAbility) || ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility);
        }
}
