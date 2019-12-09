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

package com.gtnewhorizons.gtppnt.main.loaders;

import com.gtnewhorizons.gtppnt.main.MainMod;
import com.gtnewhorizons.gtppnt.main.config.ConfigHandler;
import com.gtnewhorizons.gtppnt.main.tileentites.single.GT_MetaTileEntity_TieredBoiler;
import com.gtnewhorizons.gtppnt.main.tileentites.single.GT_MetaTileEntity_TieredTank;
import com.gtnewhorizons.gtppnt.main.utils.GTAFRecipes;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_GT_Recipe;
import net.minecraft.item.ItemStack;

import static gregtech.api.enums.GT_Values.VN;
import static gregtech.api.enums.GT_Values.VOLTAGE_NAMES;

@SuppressWarnings("ALL")
public class SingleBlockLoadingClass {

    public static ItemStack[] GTBasicTanks = new ItemStack[VN.length];
    public static ItemStack[] GTTieredBoilers = new ItemStack[VN.length];
    private static final String[] ROMAN_LETTERS = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
    public static ItemStack[] SimpleWashing = new ItemStack[VN.length];

    public static void load() {
        try {
            loadOverrideIDs();
            loadNewIDs();
        } catch (Exception e) {
            MainMod.LOGGER.catching(e);
        }
    }

    //TODO: Textures for the Simple Washer & own GUI Texture

    public static void loadOverrideIDs() throws Exception {
        for (int i = 0; i < 4; i++) {
            GTBasicTanks[i] = new GT_MetaTileEntity_TieredTank(817 + i, "GTAF.basic.tank." + i, VOLTAGE_NAMES[i] + " Fluid Tank", i).getStackForm(1L);
            SimpleWashing[1 + i] = new GT_MetaTileEntity_BasicMachine_GT_Recipe(31017 + i, "GTAF.simple.washer." + (1 + i), "Simple Washer " + ROMAN_LETTERS[1 + i], 1 + i, "", GTAFRecipes.SIMPLE_WASHER_MAP, 1, 1, 8000 * (1 + i), 0, 0, "Default.png", "", false, false, 0, "", null).getStackForm(1);
            if (i != 0)
                GTTieredBoilers[i] = new GT_MetaTileEntity_TieredBoiler(752 + i, "GTAF.tiered.boiler." + i, VOLTAGE_NAMES[i] + " Boiler", i).getStackForm(1L);
        }
        SimpleWashing[0] = new GT_MetaTileEntity_BasicMachine_GT_Recipe(767, "GTAF.simple.washer.0", "Simple Washer " + ROMAN_LETTERS[0], 0, "", GTAFRecipes.SIMPLE_WASHER_MAP, 1, 1, 8000, 0, 0, "Default.png", "", false, false, 0, "", null).getStackForm(1);
    }

    public static void loadNewIDs() throws Exception {
        for (int i = 4; i < VN.length; i++) {
            GTBasicTanks[i] = new GT_MetaTileEntity_TieredTank(ConfigHandler.IDOFFSET - 4 + i, "GTAF.basic.tank." + i, VOLTAGE_NAMES[i] + " Fluid Tank", i).getStackForm(1L);
            GTTieredBoilers[i] = new GT_MetaTileEntity_TieredBoiler(ConfigHandler.IDOFFSET + 9 + i, "GTAF.tiered.boiler." + i, VOLTAGE_NAMES[i] + " Boiler", i).getStackForm(1L);
            SimpleWashing[i] = new GT_MetaTileEntity_BasicMachine_GT_Recipe(ConfigHandler.IDOFFSET + 21 + i, "GTAF.simple.washer." + (i), "Simple Washer " + ROMAN_LETTERS[i], i, "", GTAFRecipes.SIMPLE_WASHER_MAP, 1, 1, 8000 * i, 0, 0, "Default.png", "", false, false, 0, "", null).getStackForm(1);
        }
        GTTieredBoilers[0] = new GT_MetaTileEntity_TieredBoiler(ConfigHandler.IDOFFSET + 12, "GTAF.tiered.boiler.0", VOLTAGE_NAMES[0] + " Boiler", 0).getStackForm(1L);

    }


}
