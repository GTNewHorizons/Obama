/*
 * Copyright 2021 The GTNH Team
 * 
 *  See LICENSE.md, COPYING, and COPYING.LESSER
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
