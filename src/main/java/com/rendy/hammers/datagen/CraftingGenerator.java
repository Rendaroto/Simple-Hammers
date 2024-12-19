package com.rendy.hammers.datagen;

import com.rendy.hammers.Hammer;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class CraftingGenerator extends RecipeProvider.Runner {
    public CraftingGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider lookup, RecipeOutput output) {
        return new VanillaRecipeProvider(lookup, output)
        {
            @Override
            protected void buildRecipes()
            {
                shaped(RecipeCategory.TOOLS, Hammer.WOODEN_HAMMER)
                        .pattern("BPB")
                        .pattern(" S ")
                        .pattern(" T ")
                        .define('B', ItemTags.PLANKS).define('P', Items.WOODEN_PICKAXE).define('S', Items.WOODEN_SHOVEL).define('T', Items.STICK)
                        .unlockedBy("has_wooden_pickaxe", has(Items.WOODEN_PICKAXE)).save(output);
                shaped(RecipeCategory.TOOLS, Hammer.STONE_HAMMER)
                        .pattern("BPB")
                        .pattern(" S ")
                        .pattern(" T ")
                        .define('B', Tags.Items.COBBLESTONES).define('P', Items.STONE_PICKAXE).define('S', Items.STONE_SHOVEL).define('T', Items.STICK)
                        .unlockedBy("has_stone_pickaxe", has(Items.STONE_PICKAXE)).save(output);
                shaped(RecipeCategory.TOOLS, Hammer.IRON_HAMMER)
                        .pattern("BPB")
                        .pattern(" S ")
                        .pattern(" T ")
                        .define('B', Items.IRON_BLOCK).define('P', Items.IRON_PICKAXE) .define('S', Items.IRON_SHOVEL) .define('T', Items.STICK)
                        .unlockedBy("has_iron_pickaxe", has(Items.IRON_PICKAXE)).save(output);
                shaped(RecipeCategory.TOOLS, Hammer.GOLDEN_HAMMER)
                        .pattern("BPB")
                        .pattern(" S ")
                        .pattern(" T ")
                        .define('B', Items.GOLD_BLOCK).define('P', Items.GOLDEN_PICKAXE).define('S', Items.GOLDEN_SHOVEL).define('T', Items.STICK)
                        .unlockedBy("has_golden_pickaxe", has(Items.GOLDEN_PICKAXE)).save(output);
                shaped(RecipeCategory.TOOLS, Hammer.DIAMOND_HAMMER)
                        .pattern("BPB")
                        .pattern(" S ")
                        .pattern(" T ")
                        .define('B', Items.DIAMOND_BLOCK).define('P', Items.DIAMOND_PICKAXE).define('S', Items.DIAMOND_SHOVEL).define('T', Items.STICK)
                        .unlockedBy("has_diamond_pickaxe", has(Items.DIAMOND_PICKAXE)).save(output);

                netheriteSmithing(Hammer.DIAMOND_HAMMER.get(), RecipeCategory.TOOLS, Hammer.NETHERITE_HAMMER.get());
            }
        };
    }

    @Override
    public String getName() {
        return "Recipes";
    }
}
