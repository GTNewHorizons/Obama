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

package com.gtnewhorizons.gtppnt.main.utils;

import gregtech.api.util.GT_ModHandler;
import net.minecraft.item.ItemStack;

public enum GTAFIC2CellGetter {
    EMTPY(0),
    WATER(1),
    LAVA(2),
    UUMATTER(3),
    CONSTRUCTIONFOAM(4),
    COMPRESSEDAIR(5),
    BIOMASS(6),
    BIOGAS(7),
    ELECTROLYZED_WATER(8),
    COOLANT(9),
    HOT_COOLANT(10),
    PAHOEHOELAVA(11),
    DISTILLED_WATER(12),
    SUPERHEATEDSTEAM(13),
    STEAM(14);

    private byte meta;

    GTAFIC2CellGetter(int meta) {
        this.meta = (byte) meta;
    }

    public int getMeta() {
        return meta;
    }

    ItemStack getCell() {
        return GT_ModHandler.getIC2Item("cell", 1, meta);
    }


    ItemStack getCells(int amount) {
        return GT_ModHandler.getIC2Item("cell", amount, meta);
    }

}
