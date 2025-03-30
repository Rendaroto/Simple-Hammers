package com.rendy.hammers.datagen;

import com.rendy.hammers.Hammers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Hammers.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
    }
}
