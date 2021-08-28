/*
 * Copyright 2021 The GTNH Team
 * 
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */

package com.gtnewhorizons.obama.preloader.preGT;

import com.gtnewhorizons.obama.main.ObamaMod;
import cpw.mods.fml.common.Mod;

@SuppressWarnings("unused")
@Mod(modid = ObamaPreloaderII.MODID, name = ObamaMod.NAME + " PreGT Preloader", version = ObamaMod.VERSION,
        dependencies =
                "required-after:IC2;"
                        + "required-before:gregtech;"
                        + "required-before:tectech;"
                        + "required-before:bartworks;"
                        + "required-before:obama;"
                        + "required-before:obamap1;"
)
public class ObamaPreloaderII {
    static final String MODID = "obamap2";
}
