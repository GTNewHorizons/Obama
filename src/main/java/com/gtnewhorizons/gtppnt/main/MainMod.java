/*
 * Copyright 2019 The GTNH Team
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

package com.gtnewhorizons.gtppnt.main;

import com.github.bartimaeusnek.bartworks.API.WerkstoffAdderRegistry;
import com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass;
import com.gtnewhorizons.gtppnt.main.config.ConfigHandler;
import com.gtnewhorizons.gtppnt.main.loaders.SingleBlockLoadingClass;
import com.gtnewhorizons.gtppnt.main.utils.GTAFRecipes;
import com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsExtraRecipeLoader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = MainMod.MODID, name = MainMod.NAME, version = MainMod.VERSION,
        dependencies =
                "required-after:IC2;"
                        + "required-after:gregtech;"
                        + "required-after:tectech;"
                        + "required-after:bartworks;"
)
public class MainMod {

    static final String NAME = "GT-AF";
    public static final Logger LOGGER = LogManager.getLogger(MainMod.NAME);
    static final String MODID = "gtppnt";
    static final String VERSION = "@version@";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
        WerkstoffAdderRegistry.addWerkstoffAdder(new MaterialsClass());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        SingleBlockLoadingClass.load();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MaterialsExtraRecipeLoader.executeExtraRecipes();
    }

    @Mod.EventHandler
    public void onModLoadingComplete(FMLLoadCompleteEvent event) {
        GTAFRecipes.fillSimpleWasherMap();
    }

    @EventHandler
    public void onServerStartedEvent(FMLServerStartedEvent event) {

    }

}
