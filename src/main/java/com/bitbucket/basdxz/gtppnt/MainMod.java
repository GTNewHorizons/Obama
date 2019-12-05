package com.bitbucket.basdxz.gtppnt;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = MainMod.MODID, name = MainMod.NAME, version = MainMod.VERSION,
        dependencies = "required-after:IC2; "
        + "required-after:gregtech; ")
public class MainMod
{
    public static final String NAME = "GT-AF";
    public static final String MODID = "gtppnt";
    public static final String VERSION = "@version@";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
}
