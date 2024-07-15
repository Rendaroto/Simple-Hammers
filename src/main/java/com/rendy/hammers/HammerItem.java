package com.rendy.hammers;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.Set;

import static com.rendy.hammers.Hammers.MOD_ID;

public class HammerItem extends ItemPickaxe implements  IHasModel{

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
            Blocks.COBBLESTONE, Blocks.STONE, Blocks.SAND, Blocks.GRAVEL,
            Blocks.DIRT, Blocks.GRASS, Blocks.SANDSTONE, Blocks.RED_SANDSTONE
    );

    public HammerItem(ToolMaterial material, float attackDamage, float attackSpeed,String name) {
        super( material);
        this.setRegistryName(MOD_ID, name);
        this.setUnlocalizedName(MOD_ID + "." + name);
        this.setCreativeTab(CreativeTabs.TOOLS);
        this.setMaxStackSize(1);
        Hammer.HAMMERS.add(this);

        System.out.println(getRegistryName());
        System.out.println(getUnlocalizedName());
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return this.toolMaterial.getRepairItemStack() == repair || super.getIsRepairable(toRepair, repair);
    }

    @Override
    public boolean canHarvestBlock(IBlockState state) {
        return this.toolMaterial.getHarvestLevel() >= state.getBlock().getHarvestLevel(state);
    }

    @Override
    public void registerModels() {
        Hammers.proxy.registerItemRenderer(this,0,"inventory");
    }
}