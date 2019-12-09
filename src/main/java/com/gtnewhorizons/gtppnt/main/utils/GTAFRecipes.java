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

package com.gtnewhorizons.gtppnt.main.utils;

import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

import static gregtech.api.enums.GT_Values.RES_PATH_GUI;

public class GTAFRecipes {
    public static final GT_Recipe.GT_Recipe_Map SIMPLE_WASHER_MAP =
            new GT_Recipe.GT_Recipe_Map(new HashSet<>(),
                    "GTAF.map.simpleWasher", "Simple Washer Recipes",
                    "Simple Washer Recipes", RES_PATH_GUI + "basicmachines/Default",
                    1, 1, 1, 1, 1,
                    "", 1, "", true, true
            );

    public static void fillSimpleWasherMap() {
        Arrays.stream(Materials.values())
                .filter(materials ->
                        Objects.nonNull(GT_OreDictUnificator.get(OrePrefixes.dustImpure, materials, 1))
                                && Objects.nonNull(GT_OreDictUnificator.get(OrePrefixes.dustPure, materials, 1))
                                && Objects.nonNull(GT_OreDictUnificator.get(OrePrefixes.dust, materials, 1))
                                && Objects.nonNull(GT_OreDictUnificator.get(OrePrefixes.crushed, materials, 1))
                                && Objects.nonNull(GT_OreDictUnificator.get(OrePrefixes.crushedPurified, materials, 1))
                )
                .forEach(materials -> {
                            SIMPLE_WASHER_MAP.addRecipe(true,
                                    new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.dustImpure, materials, 1)},
                                    new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.dustPure, materials, 1)},
                                    null,
                                    null,
                                    new FluidStack[]{Materials.Water.getFluid(100)}, null,
                                    10, 7, 0
                            );
                            SIMPLE_WASHER_MAP.addRecipe(true,
                                    new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.dustPure, materials, 1)},
                                    new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.dust, materials, 1)},
                                    null,
                                    null,
                                    new FluidStack[]{Materials.Water.getFluid(100)}, null,
                                    10, 7, 0
                            );
                            SIMPLE_WASHER_MAP.addRecipe(true,
                                    new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.crushed, materials, 1)},
                                    new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.crushedPurified, materials, 1)},
                                    null,
                                    null,
                                    new FluidStack[]{Materials.Water.getFluid(100)}, null,
                                    10, 7, 0
                            );
                        }
                );
        Werkstoff.werkstoffHashSet.stream()
                .filter(w -> w.getGenerationFeatures().hasOres())
                .forEach(w -> {
                            Materials materials = w.getBridgeMaterial();
                            SIMPLE_WASHER_MAP.addRecipe(true,
                                    new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.dustImpure, materials, 1)},
                                    new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.dustPure, materials, 1)},
                                    null,
                                    null,
                                    new FluidStack[]{Materials.Water.getFluid(100)}, null,
                                    10, 7, 0
                            );
                            SIMPLE_WASHER_MAP.addRecipe(true,
                                    new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.dustPure, materials, 1)},
                                    new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.dust, materials, 1)},
                                    null,
                                    null,
                                    new FluidStack[]{Materials.Water.getFluid(100)}, null,
                                    10, 7, 0
                            );
                            SIMPLE_WASHER_MAP.addRecipe(true,
                                    new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.crushed, materials, 1)},
                                    new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.crushedPurified, materials, 1)},
                                    null,
                                    null,
                                    new FluidStack[]{Materials.Water.getFluid(100)}, null,
                                    10, 7, 0
                            );
                        }
                );
    }

}
