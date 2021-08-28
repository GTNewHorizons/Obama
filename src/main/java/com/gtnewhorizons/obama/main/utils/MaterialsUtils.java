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

package com.gtnewhorizons.obama.main.utils;

import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;
import gregtech.api.enums.Materials;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MaterialsUtils {

    /**
     * Applies a filter to the Werkstoff &amp; Materials Map and executes a Consumer on it
     *
     * @param filter  a Predicate, (a bool function), like "materials -&gt; GT_OreDictUnificator.get(OrePrefixes.rod, materials, 1) != null"
     * @param toApply an Consumer ("a void function"), like "materials -&gt; concurrentmap.add(materials)"
     */
    public static void applyToAllMaterialsAndWerkstoffeAsync(Predicate<Materials> filter, Consumer<Materials> toApply) {
        new Thread(                                                     //create a new thread
                () -> {                                                 //suppy a "runnable"
                    applyToAllMaterialsAndWerkstoffe(filter, toApply);
                }
        ).start();                                                      //start the thread in the background
    }

    /**
     * Applies a filter to the Werkstoff &amp; Materials Map and executes a Consumer on it
     *
     * @param filter  a Predicate, (a bool function), like "materials -&gt; GT_OreDictUnificator.get(OrePrefixes.rod, materials, 1) != null"
     * @param toApply an Consumer ("a void function"), like "materials -&gt; concurrentmap.add(materials)"
     */
    public static void applyToAllMaterialsAndWerkstoffe(Predicate<Materials> filter, Consumer<Materials> toApply) {
        applyToAllWerkstoffe(filter, toApply);
        applyToAllMaterials(filter, toApply);
    }

    /**
     * Applies a filter to the Werkstoff &amp; Materials Map and executes a Consumer on it
     *
     * @param filter  a Predicate, (a bool function), like "materials -&gt; GT_OreDictUnificator.get(OrePrefixes.rod, materials, 1) != null"
     * @param toApply an Consumer ("a void function"), like "materials -&gt; concurrentmap.add(materials)"
     */
    public static void applyToAllWerkstoffe(Predicate<Materials> filter, Consumer<Materials> toApply) {
        Werkstoff.werkstoffHashSet.stream()                 //get ALL the Werkstoffe
                .map(Werkstoff::getBridgeMaterial)          //get the Materials adapter for them
                .filter(filter)                             //apply the filter (i.e. a nullcheck)
                .forEach(toApply);                          //apply the action (i.e. add them to a recipe map)
    }

    /**
     * Applies a filter to the Werkstoff &amp; Materials Map and executes a Consumer on it
     *
     * @param filter  a Predicate, (a bool function), like "materials -&gt; GT_OreDictUnificator.get(OrePrefixes.rod, materials, 1) != null"
     * @param toApply an Consumer ("a void function"), like "materials -&gt; concurrentmap.add(materials)"
     */
    public static void applyToAllMaterials(Predicate<Materials> filter, Consumer<Materials> toApply) {
        Materials.getMaterialsMap().values().stream()       //get ALL the Materials
                .filter(filter)                             //apply the filter (i.e. a nullcheck)
                .forEach(toApply);                          //apply the action (i.e. add them to a recipe map)
    }

    /**
     * Gets a FluidStack from a Material Definition
     *
     * @param materials the Materials you want to convert to a FluidStack
     * @return A FluidStack, a gas or a molten material, in that priority order.
     */
    public static FluidStack getFluidStackFromMaterials(Materials materials) {
        return Stream.of(materials.getFluid(1), materials.getGas(1), materials.getMolten(1))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * Gets a Fluid from a Material Definition
     *
     * @param materials the Materials you want to convert to a Fluid
     * @return A fluid, a gas or a molten material, in that priority order.
     */
    public static Fluid getFluidFromMaterials(Materials materials) {
        return getFluidStackFromMaterials(materials).getFluid();
    }
}