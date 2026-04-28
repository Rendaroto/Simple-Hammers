package com.rendy.hammers.datagen;

import com.rendy.hammers.Hammer;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;

public class GenericModelProvider extends ModelProvider {

    public GenericModelProvider(PackOutput output, String modId) {
        super(output, modId);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        itemModel(itemModels, Hammer.WOODEN_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModel(itemModels, Hammer.STONE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModel(itemModels, Hammer.GOLDEN_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModel(itemModels, Hammer.COPPER_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModel(itemModels, Hammer.IRON_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModel(itemModels, Hammer.DIAMOND_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModel(itemModels, Hammer.NETHERITE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    }

    private void itemModel(ItemModelGenerators itemModels, Item item, ModelTemplate template) {
        itemModels.generateFlatItem(item, template);
    }
}