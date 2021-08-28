/*
 * Copyright 2021 The GTNH Team
 * 
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */

package com.gtnewhorizons.obama.preloader.postGT;

import com.gtnewhorizons.obama.main.ObamaMod;
import com.gtnewhorizons.obama.preloader.postGT.loaders.EnumExtender;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("unused")
@Mod(modid = ObamaPreloaderI.MODID, name = ObamaMod.NAME + " PostGT Preloader", version = ObamaMod.VERSION,
        dependencies =
                "required-after:IC2;"
                        + "required-after:gregtech;"
                        + "required-after:obamap2;"
                        + "required-before:tectech;"
                        + "required-before:bartworks;"
                        + "required-before:obama;"
)
public class ObamaPreloaderI {
    static final String MODID = "obamap1";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        EnumExtender.addOrePrefixEnums();
    }

}

