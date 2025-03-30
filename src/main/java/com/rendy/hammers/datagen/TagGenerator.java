package com.rendy.hammers.datagen;

import com.rendy.hammers.Hammer;
import com.rendy.hammers.Hammers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class TagGenerator extends ItemTagsProvider {

    public TagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture, CompletableFuture<TagLookup<Block>> tagLookupCompletableFuture) {
        super(packOutput, providerCompletableFuture, tagLookupCompletableFuture, Hammers.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.PICKAXES)
                .add(
                         Hammer.WOODEN_HAMMER.get()
                        ,Hammer.STONE_HAMMER.get()
                        ,Hammer.IRON_HAMMER.get()
                        ,Hammer.GOLDEN_HAMMER.get()
                        ,Hammer.DIAMOND_HAMMER.get()
                        ,Hammer.NETHERITE_HAMMER.get()
                );
        this.tag(ItemTags.SHOVELS)
                .add(
                        Hammer.WOODEN_HAMMER.get()
                        ,Hammer.STONE_HAMMER.get()
                        ,Hammer.IRON_HAMMER.get()
                        ,Hammer.GOLDEN_HAMMER.get()
                        ,Hammer.DIAMOND_HAMMER.get()
                        ,Hammer.NETHERITE_HAMMER.get()
                );
    }
}
