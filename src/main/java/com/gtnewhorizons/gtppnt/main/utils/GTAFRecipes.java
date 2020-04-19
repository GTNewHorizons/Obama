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

import com.github.bartimaeusnek.bartworks.util.NonNullWrappedHashSet;
import com.github.bartimaeusnek.bartworks.util.Pair;
import com.gtnewhorizons.gtppnt.main.GTAFMod;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Objects;

import static com.gtnewhorizons.gtppnt.main.utils.GTAFIC2CellGetter.*;
import static com.gtnewhorizons.gtppnt.main.utils.MaterialsUtils.applyToAllMaterialsAndWerkstoffe;
import static gregtech.api.enums.GT_Values.RES_PATH_GUI;

public class GTAFRecipes {

    public static final GT_Recipe.GT_Recipe_Map SIMPLE_WASHER_MAP =
            new GT_Recipe.GT_Recipe_Map(new NonNullWrappedHashSet<>(),
                    "GTAF.map.simpleWasher", "Simple Washer Recipes",
                    "Simple Washer Recipes", RES_PATH_GUI + "basicmachines/Default",
                    1, 1, 1, 1, 1,
                    "", 1, "", true, true
            );

    public static final GT_Recipe.GT_Recipe_Map_Fuel THERMALGENERATOR_FUELS =
            new GT_Recipe.GT_Recipe_Map_Fuel(new NonNullWrappedHashSet<>(),
                    "GTAF.map.thermal", "Thermal Generator Fuels",
                    "Thermal Generator Fuels", RES_PATH_GUI + "basicmachines/Default",
                    1, 1, 0, 0, 1,
                    "Fuel Value: ", 1000, " EU", true, true
            );

    private static void fillThermalGeneratorMap() {
        THERMALGENERATOR_FUELS.addFuel(LAVA.getCell(), PAHOEHOELAVA.getCell(), 20);
        THERMALGENERATOR_FUELS.addFuel(HOT_COOLANT.getCell(), COOLANT.getCell(), 15);
        THERMALGENERATOR_FUELS.addFuel(SUPERHEATEDSTEAM.getCell(), STEAM.getCell(), 10);
        THERMALGENERATOR_FUELS.addFuel(STEAM.getCell(), DISTILLED_WATER.getCell(), 5);
    }

    @SuppressWarnings("unchecked")
    private static void fillSimpleWasherMap() {
        Pair<OrePrefixes, OrePrefixes>[] arr = new Pair[]{ //create an array of in -> output
                new Pair<>(OrePrefixes.crushed, OrePrefixes.crushedPurified),
                new Pair<>(OrePrefixes.dustImpure, OrePrefixes.dustPure),
                new Pair<>(OrePrefixes.dustPure, OrePrefixes.dust),
        };
        for (Pair<OrePrefixes, OrePrefixes> orePrefixesPair : arr)
            applyToAllMaterialsAndWerkstoffe(
                    //make sure in- & output is not null
                    materials ->
                            Objects.nonNull(GT_OreDictUnificator.get(orePrefixesPair.getKey(), materials, 1)) &&
                                    Objects.nonNull(GT_OreDictUnificator.get(orePrefixesPair.getValue(), materials, 1)),
                    //add the recipe to the map
                    materials ->
                            SIMPLE_WASHER_MAP.addRecipe(true,
                                    new ItemStack[]{GT_OreDictUnificator.get(orePrefixesPair.getKey(), materials, 1)},
                                    new ItemStack[]{GT_OreDictUnificator.get(orePrefixesPair.getValue(), materials, 1)},
                                    null,
                                    null,
                                    new FluidStack[]{Materials.Water.getFluid(100)}, null,
                                    10, 7, 0
                            )
            );
    }

    public static void executeGTAFRecipes() {
        GTAFMod.LOGGER.info("Starting GTAFRecipes");
        synchronized (SIMPLE_WASHER_MAP) {   //lock the maps
            synchronized (THERMALGENERATOR_FUELS) {
                Thread gtaf_recipe_registration_thread = new Thread(() -> {
                    fillSimpleWasherMap();
                    fillThermalGeneratorMap();
                    GTAFMod.LOGGER.info("GTAF Recipe Registration Thread Ended!");
                });
                gtaf_recipe_registration_thread.setName("GTAF Recipe Registration Thread"); //rename the thread
                gtaf_recipe_registration_thread.start(); //start it in the background
                GTAFMod.LOGGER.info("GTAF Recipe Registration Thread started!");
            }
        }
    }
}