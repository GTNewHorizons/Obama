/*
 * Copyright 2021 The GTNH Team
 * 
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */

package com.gtnewhorizons.obama.main.config;

import com.gtnewhorizons.obama.main.tileentities.single.generators.GT_MetaTileEntity_TieredBoiler;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static int IDOFFSET;
    private static Configuration configuration;

    public static void loadConfig(File file) {
        configuration = new Configuration(file);
        IDOFFSET = configuration.get("System", "ID offset for new Stuff", 17000).getInt(17000);
        GT_MetaTileEntity_TieredBoiler.cBasePollution = configuration.get("Singleblocks.Boiler", "Base Pollution", 15, "Base Pollution, every Tiered Boiler has at least this Pollution").getInt(15);
        GT_MetaTileEntity_TieredBoiler.cPollutionIncrease = configuration.get("Singleblocks.Boiler", "Pollution Increase", 5, "Pollution increase, tier² * this value").getInt(5);
        GT_MetaTileEntity_TieredBoiler.cBaseSteam = configuration.get("Singleblocks.Boiler", "Base Steam Rate", 150, "The Base Steam rate which will be influenced by tier etc.").getInt(150);
        GT_MetaTileEntity_TieredBoiler.cBaseTickrate = configuration.get("Singleblocks.Boiler", "Steam per X Ticks", 20, "How fast boilers will create steam").getInt(20);
    }


}