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

import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;
import gregtech.api.enums.Materials;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class MaterialsUtils {

    /**
     * Applies a filter to the Werkstoff & Materials Map and executes a Consumer on it
     *
     * @param filter  a Predicate, (a bool function), like "materials -> GT_OreDictUnificator.get(OrePrefixes.rod, materials, 1) != null"
     * @param toApply an Consumer ("a void function"), like "materials -> concurrentmap.add(materials)"
     */
    public static void applyToAllMaterialsAndWerkstoffeAsync(Predicate<Materials> filter, Consumer<Materials> toApply) {
        new Thread(                                                     //create a new thread
                () -> {                                                 //suppy a "runnable"
                    applyToAllMaterialsAndWerkstoffe(filter, toApply);
                }
        ).start();                                                      //start the thread in the background
    }

    /**
     * Applies a filter to the Werkstoff & Materials Map and executes a Consumer on it
     *
     * @param filter  a Predicate, (a bool function), like "materials -> GT_OreDictUnificator.get(OrePrefixes.rod, materials, 1) != null"
     * @param toApply an Consumer ("a void function"), like "materials -> concurrentmap.add(materials)"
     */
    public static void applyToAllMaterialsAndWerkstoffe(Predicate<Materials> filter, Consumer<Materials> toApply) {
        applyToAllWerkstoffe(filter, toApply);
        applyToAlMaterials(filter, toApply);
    }

    /**
     * Applies a filter to the Werkstoff & Materials Map and executes a Consumer on it
     *
     * @param filter  a Predicate, (a bool function), like "materials -> GT_OreDictUnificator.get(OrePrefixes.rod, materials, 1) != null"
     * @param toApply an Consumer ("a void function"), like "materials -> concurrentmap.add(materials)"
     */
    public static void applyToAllWerkstoffe(Predicate<Materials> filter, Consumer<Materials> toApply) {
        Werkstoff.werkstoffHashSet.stream()                 //get ALL the Werkstoffe
                .map(Werkstoff::getBridgeMaterial)          //get the Materials adapter for them
                .filter(filter)                             //apply the filter (i.e. a nullcheck)
                .forEach(toApply);                          //apply the action (i.e. add them to a recipe map)
    }

    /**
     * Applies a filter to the Werkstoff & Materials Map and executes a Consumer on it
     *
     * @param filter  a Predicate, (a bool function), like "materials -> GT_OreDictUnificator.get(OrePrefixes.rod, materials, 1) != null"
     * @param toApply an Consumer ("a void function"), like "materials -> concurrentmap.add(materials)"
     */
    public static void applyToAlMaterials(Predicate<Materials> filter, Consumer<Materials> toApply) {
        Materials.getMaterialsMap().values().stream()       //get ALL the Materials
                .filter(filter)                             //apply the filter (i.e. a nullcheck)
                .forEach(toApply);                          //apply the action (i.e. add them to a recipe map)
    }

}