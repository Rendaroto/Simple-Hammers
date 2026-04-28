package com.rendy.hammers.datagen;

import com.mojang.datafixers.types.templates.Tag;
import com.rendy.hammers.Hammers;
import com.rendy.hammers.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Hammers.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Blocks.MINEABLE_WITH_HAMMER)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(BlockTags.MINEABLE_WITH_SHOVEL);
    }
}
