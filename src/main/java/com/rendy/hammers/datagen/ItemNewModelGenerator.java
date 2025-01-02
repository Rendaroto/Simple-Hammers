package com.rendy.hammers.datagen;

import com.rendy.hammers.Hammer;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class ItemNewModelGenerator extends ItemModelGenerators {

    public ItemNewModelGenerator(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        generateFlatItem(Hammer.WOODEN_HAMMER.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(Hammer.STONE_HAMMER.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(Hammer.IRON_HAMMER.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(Hammer.GOLDEN_HAMMER.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(Hammer.DIAMOND_HAMMER.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(Hammer.NETHERITE_HAMMER.get(), ModelTemplates.FLAT_ITEM);
    }
}
