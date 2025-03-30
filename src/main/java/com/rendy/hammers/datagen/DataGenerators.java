package com.rendy.hammers.datagen;

import com.rendy.hammers.HammerItem;
import com.rendy.hammers.Hammers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Hammers.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        BlockTagGenerator blockTagGenerator = generator.addProvider(true, new BlockTagGenerator(packOutput, lookupProvider));
        generator.addProvider(true, new TagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter()));
        generator.addProvider(true, new CraftingGenerator(packOutput,lookupProvider));
        generator.addProvider(true, new GenericModelProvider(packOutput, Hammers.MOD_ID));

    }
}