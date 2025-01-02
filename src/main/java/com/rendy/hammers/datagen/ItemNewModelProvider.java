package com.rendy.hammers.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.NoSuchElementException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.rendy.hammers.Hammers.MOD_ID;

public class ItemNewModelProvider extends  ModelProviderBase{

    public ItemNewModelProvider(PackOutput output) {
        super(output,MOD_ID);
    }

    @Override
    protected BlockModelGenerators createBlockModelGenerators(Consumer<BlockStateGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        throw new NoSuchElementException("This Method is useless");
    }

    @Override
    protected ItemModelGenerators createItemModelGenerators(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        return new ItemNewModelGenerator(itemModelOutput,modelOutput);
    }
}
