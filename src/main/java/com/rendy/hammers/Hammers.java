package com.rendy.hammers;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Hammers.MOD_ID, name = Hammers.NAME, version = Hammers.VERSION)
public class Hammers {
    public static final String MOD_ID = "simple_hammers";
    public static final String NAME = "Simple Hammers";
    public static final String VERSION = "0.0.1-BACKPORT";

    @Mod.Instance
    public static Hammers instance;

    @SidedProxy(clientSide = "com.rendy.hammers.ClientProxy", serverSide = "com.rendy.hammers.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }
}
