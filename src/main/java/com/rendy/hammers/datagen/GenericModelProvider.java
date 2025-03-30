package com.rendy.hammers.datagen;

import com.rendy.hammers.Hammer;
import com.rendy.hammers.HammerItem;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Collections;

public class GenericModelProvider extends ModelProvider {
    public GenericModelProvider(PackOutput output, String modId) {
        super(output, modId);
    }
    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels)
    {

        /* Axes */
        itemModel(itemModels, Hammer.WOODEN_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModel(itemModels, Hammer.STONE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModel(itemModels, Hammer.GOLDEN_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModel(itemModels, Hammer.IRON_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModel(itemModels, Hammer.DIAMOND_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModel(itemModels, Hammer.NETHERITE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    }

    public void itemModel(ItemModelGenerators itemModels, Item item, ModelTemplate template)
    {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
        ResourceLocation textureLoc = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath());
        TextureMapping textureMapping = new TextureMapping().put(TextureSlot.LAYER0, textureLoc);
        itemModels.itemModelOutput.accept(item, new BlockModelWrapper.Unbaked(template.create(item, textureMapping, itemModels.modelOutput), Collections.emptyList()));
    }
}