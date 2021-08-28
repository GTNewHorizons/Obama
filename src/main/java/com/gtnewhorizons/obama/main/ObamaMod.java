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

package com.gtnewhorizons.obama.main;

import com.github.bartimaeusnek.bartworks.API.WerkstoffAdderRegistry;
import com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass;
import com.gtnewhorizons.obama.main.compat.bartworks.MaterialsExtraRecipeLoader;
import com.gtnewhorizons.obama.main.config.ConfigHandler;
import com.gtnewhorizons.obama.main.loaders.CableAndWireLoader;
import com.gtnewhorizons.obama.main.loaders.CasingTextureLoader;
import com.gtnewhorizons.obama.main.loaders.HatchCasingLoader;
import com.gtnewhorizons.obama.main.loaders.MultiBlockLoader;
import com.gtnewhorizons.obama.main.loaders.SingleBlockLoadingClass;
import com.gtnewhorizons.obama.main.utils.ObamaRecipes;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import gregtech.api.GregTech_API;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.IntStream;

@SuppressWarnings("unused")
@Mod(modid = ObamaMod.MODID, name = ObamaMod.NAME, version = ObamaMod.VERSION,
        dependencies =
                "required-after:IC2;"
                        + "required-after:gregtech;"
                        + "required-after:structurelib;"
                        + "required-after:bartworks;"
                        + "required-after:obamap1;"
                        + "required-after:obamap2;"
)
public class ObamaMod {
    public static final String NAME = "Obama";
    public static final String VERSION = "@version@";
    public static final Logger LOGGER = LogManager.getLogger(ObamaMod.NAME);
    public static final String MODID = "obama";
    private static final boolean DEBUG = true;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
        WerkstoffAdderRegistry.addWerkstoffAdder(new MaterialsClass());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        SingleBlockLoadingClass.load();
        CableAndWireLoader.load();
        MultiBlockLoader.load();
        HatchCasingLoader.load();

        if (DEBUG) {
            IntStream.range(0, GregTech_API.METATILEENTITIES.length).
                    filter(i -> GregTech_API.METATILEENTITIES[i] != null).forEach(LOGGER::info);
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MaterialsExtraRecipeLoader.load();
        CasingTextureLoader.load();
        //MultiBlockRecipeLoader.load();
    }

    @Mod.EventHandler
    public void onModLoadingComplete(FMLLoadCompleteEvent event) {
        ObamaRecipes.executeObamaRecipes();
    }

    @EventHandler
    public void onServerStartedEvent(FMLServerStartedEvent event) {

    }

}
