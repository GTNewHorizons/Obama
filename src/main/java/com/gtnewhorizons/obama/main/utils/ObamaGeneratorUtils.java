/*
 * Copyright 2021 The GTNH Team
 * 
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */

package com.gtnewhorizons.obama.main.utils;

import com.github.bartimaeusnek.bartworks.util.MathUtils;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class ObamaGeneratorUtils {
    private ObamaGeneratorUtils() {
    }

    public static int getFuelValueGenerator(ItemStack aStack, GT_MetaTileEntity_BasicGenerator selfReference, Supplier<Double> customGeneratorLogic) {
        if (GT_Utility.isStackInvalid(aStack) || selfReference.getRecipes() == null)
            return 0;

        double rValue = customGeneratorLogic.get();

        if (rValue > Integer.MAX_VALUE) {
            throw new ArithmeticException("Integer LOOPBACK!");
        }
        return MathUtils.floorInt(rValue);
    }

}
