package com.rendy.hammers;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

import java.util.Set;

public class HammerItem extends Item {
    HammerItem(ToolMaterial pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pProperties.tool(pTier,BlockTags.MINEABLE_WITH_PICKAXE,pAttackDamageModifier,pAttackSpeedModifier,0.0f));
    }
}
