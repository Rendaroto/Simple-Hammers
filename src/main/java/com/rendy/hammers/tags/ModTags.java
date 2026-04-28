package com.rendy.hammers.tags;

import com.rendy.hammers.Hammer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static com.rendy.hammers.Hammers.MOD_ID;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> MINEABLE_WITH_HAMMER =
                TagKey.create(
                        Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(MOD_ID, "mineable/hammer")
                );
    }
}
