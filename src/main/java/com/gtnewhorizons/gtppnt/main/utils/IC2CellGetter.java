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

import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public enum IC2CellGetter {
    EMTPY(0),
    WATER(1, FluidRegistry.WATER),
    LAVA(2, FluidRegistry.LAVA),
    UUMATTER(3, Materials.UUMatter),
    CONSTRUCTIONFOAM(4, Materials.ConstructionFoam),
    COMPRESSEDAIR(5, Materials.Air),
    BIOMASS(6, "biomass"),
    BIOGAS(7, "biogas"),
    ELECTROLYZED_WATER(8),
    COOLANT(9, "coolant"),
    HOT_COOLANT(10, "hotcoolant"),
    PAHOEHOELAVA(11, "pahoehoelava"),
    DISTILLED_WATER(12, "distilledwater"),
    SUPERHEATEDSTEAM(13, "superheatedsteam"),
    STEAM(14, "steam");

    private final byte META;
    private final Fluid LINKED_FLUID;

    IC2CellGetter(int META) {
        this.META = (byte) META;
        LINKED_FLUID = null;
    }

    IC2CellGetter(int META, Fluid fluid) {
        this.META = (byte) META;
        LINKED_FLUID = fluid;
    }

    IC2CellGetter(int META, Materials materials) {
        this.META = (byte) META;
        this.LINKED_FLUID = MaterialsUtils.getFluidFromMaterials(materials);
    }

    IC2CellGetter(int META, String fluidName) {
        this.META = (byte) META;
        LINKED_FLUID = FluidRegistry.getFluid("ic2" + fluidName);
    }

    /**
     * Gets a IC2-Fluid
     *
     * @return the fluid
     * @throws IllegalArgumentException if no linked fluid is found!
     */
    public Fluid getFluid() {
        if (this.LINKED_FLUID == null)
            throw new IllegalArgumentException("Cell \"" + this.name() + "\" doesn't have a linked Fluid!");
        return LINKED_FLUID;
    }

    /**
     * @return the meta value of the cell
     */
    public int getMeta() {
        return META;
    }

    /**
     * @return a stack with a single cell
     */
    public ItemStack getCell() {
        return getCells(1);
    }

    /**
     * Gets IC2 Fluid Cells
     *
     * @param amount of cells
     * @return an item stack consisting of cells
     */
    public ItemStack getCells(int amount) {
        return GT_ModHandler.getIC2Item("cell", amount, META);
    }

    /**
     * Gets a IC2-Fluid Stack
     *
     * @param amount amount of fluid
     * @return the stack
     * @throws IllegalArgumentException if no linked fluid is found!
     */
    public FluidStack getFluidStack(int amount) {
        if (this.LINKED_FLUID == null)
            throw new IllegalArgumentException("Cell \"" + this.name() + "\" doesn't have a linked Fluid!");
        return new FluidStack(this.LINKED_FLUID, amount);
    }
}
