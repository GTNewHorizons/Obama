/*
 * Copyright 2021 The GTNH Team
 * 
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */

package com.gtnewhorizons.obama.preloader.postGT.loaders;

import com.github.bartimaeusnek.bartworks.util.EnumUtils;
import gregtech.api.enums.OrePrefixes;

public class EnumExtender {
    public static OrePrefixes pipeHexadecuple;
    public static OrePrefixes pipeDodecuple;

    private EnumExtender() {
    }

    public static void addOrePrefixEnums() {
        pipeHexadecuple = EnumUtils.addNewOrePrefix("pipeHexadecuple", "Hexadecuple Pipes", "Hexadecuple ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, 29030400L, 64, 85);
        pipeDodecuple = EnumUtils.addNewOrePrefix("pipeDodecuple", "Dodecuple Pipes", "Dodecuple ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, 21772800L, 64, 85);
    }

}
