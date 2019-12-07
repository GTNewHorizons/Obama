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
import net.minecraft.item.ItemStack;

import static gregtech.api.enums.GT_Values.VN;
import static gregtech.api.enums.GT_Values.VOLTAGE_NAMES;

@SuppressWarnings("ALL")
public class SingleBlockLoadingClass {

    public static ItemStack[] GTBasicTanks = new ItemStack[VN.length];
    public static ItemStack[] GTTieredBoilers = new ItemStack[VN.length];

    public static void load() {
        try {
            loadOverrideIDs();
            loadNewIDs();
        } catch (Exception e) {
            MainMod.LOGGER.catching(e);
        }
    }

    public static void loadOverrideIDs() throws Exception {
        for (int i = 0; i < 4; i++) {
            GTBasicTanks[i] = new GT_MetaTileEntity_TieredTank(817 + i, "GTAF.basic.tank." + i, VOLTAGE_NAMES[i] + " Fluid Tank", i).getStackForm(1L);
            if (i != 0)
                GTTieredBoilers[i] = new GT_MetaTileEntity_TieredBoiler(752 + i, "GTAF.tiered.boiler." + i, VOLTAGE_NAMES[i] + " Boiler", i).getStackForm(1L);
        }
    }

    public static void loadNewIDs() throws Exception {
        for (int i = 4; i < VN.length; i++) {
            GTBasicTanks[i] = new GT_MetaTileEntity_TieredTank(ConfigHandler.IDOFFSET - 4 + i, "GTAF.basic.tank." + i, VOLTAGE_NAMES[i] + " Fluid Tank", i).getStackForm(1L);
            GTTieredBoilers[i] = new GT_MetaTileEntity_TieredBoiler(ConfigHandler.IDOFFSET + 9 + i, "GTAF.tiered.boiler." + i, VOLTAGE_NAMES[i] + " Boiler", i).getStackForm(1L);
        }
        GTTieredBoilers[0] = new GT_MetaTileEntity_TieredBoiler(ConfigHandler.IDOFFSET + 12, "GTAF.tiered.boiler.0", VOLTAGE_NAMES[0] + " Boiler", 0).getStackForm(1L);

    }


}
