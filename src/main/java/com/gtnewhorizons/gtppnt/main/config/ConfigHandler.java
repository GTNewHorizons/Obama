/*
 * Copyright 2020 The GTNH Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT  OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.gtnewhorizons.gtppnt.main.config;

import com.gtnewhorizons.gtppnt.main.tileentites.single.generators.GT_MetaTileEntity_TieredBoiler;
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
