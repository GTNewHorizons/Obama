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

package com.gtnewhorizons.gtppnt.preloader.postGT.loaders;

import gregtech.api.enums.OrePrefixes;
import net.minecraftforge.common.util.EnumHelper;

public class EnumExtender {
    public static OrePrefixes pipeHexadecuple;
    public static OrePrefixes pipeDodecuple;

    private EnumExtender() {
    }

    public static void addOrePrefixEnums() {
        pipeHexadecuple = addNewOrePrefix("pipeHexadecuple", "Hexadecuple Pipes", "Hexadecuple ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, 29030400L, 64, 85);
        pipeDodecuple = addNewOrePrefix("pipeDodecuple", "Dodecuple Pipes", "Dodecuple ", " Pipe", true, true, false, false, true, false, true, false, false, false, 0, 21772800L, 64, 85);
    }

    private static OrePrefixes addNewOrePrefix(String enumName, String aRegularLocalName, String aLocalizedMaterialPre, String aLocalizedMaterialPost,
                                               boolean aIsUnificatable, boolean aIsMaterialBased, boolean aIsSelfReferencing,
                                               boolean aIsContainer, boolean aDontUnificateActively, boolean aIsUsedForBlocks,
                                               boolean aAllowNormalRecycling, boolean aGenerateDefaultItem, boolean aIsEnchantable,
                                               boolean aIsUsedForOreProcessing, int aMaterialGenerationBits, long aMaterialAmount,
                                               int aDefaultStackSize, int aTextureindex) {
        return EnumHelper.addEnum(OrePrefixes.class, enumName, new Class<?>[]{
                String.class, String.class, String.class,
                boolean.class, boolean.class, boolean.class,
                boolean.class, boolean.class, boolean.class,
                boolean.class, boolean.class, boolean.class,
                boolean.class, int.class, long.class,
                int.class, int.class
        }, new Object[]{
                aRegularLocalName, aLocalizedMaterialPre, aLocalizedMaterialPost,
                aIsUnificatable, aIsMaterialBased, aIsSelfReferencing,
                aIsContainer, aDontUnificateActively, aIsUsedForBlocks,
                aAllowNormalRecycling, aGenerateDefaultItem, aIsEnchantable,
                aIsUsedForOreProcessing, aMaterialGenerationBits, aMaterialAmount,
                aDefaultStackSize, aTextureindex
        });
    }

}
