package com.rendy.hammers.datagen;

import com.rendy.hammers.Hammer;
import com.rendy.hammers.Hammers;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, Hammers.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(Hammer.WOODEN_HAMMER.get());
        handheldItem(Hammer.STONE_HAMMER.get());
        handheldItem(Hammer.IRON_HAMMER.get());
        handheldItem(Hammer.GOLDEN_HAMMER.get());
        handheldItem(Hammer.DIAMOND_HAMMER.get());
        handheldItem(Hammer.NETHERITE_HAMMER.get());
    }
}
