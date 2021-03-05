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

import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;

//TODO Delete this whole got forsaken fucking utils class holy shit it is so fucking bad.
public class MultiBlockUtils {
    @Deprecated
    public static ItemStack[] sortItemStacks(ArrayList<ItemStack> tInputList) {
        int tInputList_sS = tInputList.size();
        for (int i = 0; i < tInputList_sS - 1; i++) {
            for (int j = i + 1; j < tInputList_sS; j++) {
                if (GT_Utility.areStacksEqual(tInputList.get(i), tInputList.get(j))) {
                    if ((tInputList.get(i)).stackSize >= (tInputList.get(j)).stackSize) {
                        tInputList.remove(j--);
                        tInputList_sS = tInputList.size();
                    } else {
                        tInputList.remove(i--);
                        tInputList_sS = tInputList.size();
                        break;
                    }
                }
            }
        }
        return tInputList.toArray(new ItemStack[0]);
    }

    public static ItemStack[] sortInputItemStacks(ArrayList<ItemStack> inputList) {
        //ArrayList<ItemStack> outputList = new ArrayList<>();
        //for (ItemStack itemStack : inputList) {
        //    OptionalInt outputIndex = IntStream.range(0, outputList.size())
        //            .filter(i -> GT_Utility.areStacksEqual(outputList.get(i), itemStack) ||
        //                    outputList.get(i).stackSize > itemStack.stackSize)
        //            .findFirst();
        //    if (outputIndex.isPresent()) {
        //        outputList.set(outputIndex.getAsInt(), itemStack);
        //    } else {
        //        outputList.add(itemStack);
        //    }
        //}
        //return outputList.toArray(new ItemStack[0]);
        return inputList.toArray(new ItemStack[0]);
    }

    public static ItemStack[] sortOutputItemStacks(ArrayList<ItemStack> inputList) {
        //ArrayList<ItemStack> outputList = new ArrayList<>();
        //for (ItemStack itemStack : inputList) {
        //    OptionalInt outputIndex = IntStream.range(0, outputList.size())
        //            .filter(i -> GT_Utility.areStacksEqual(outputList.get(i), itemStack))
        //            .findFirst();
        //    if (outputIndex.isPresent()) {
        //        outputList.get(outputIndex.getAsInt()).stackSize += itemStack.stackSize;
        //    } else {
        //        outputList.add(itemStack);
        //    }
        //}
        //return outputList.toArray(new ItemStack[0]);
        return inputList.toArray(new ItemStack[0]);
    }

    @Deprecated
    public static FluidStack[] sortFluidStacks(ArrayList<FluidStack> tFluidList) {
        int tFluidList_sS = tFluidList.size();
        for (int i = 0; i < tFluidList_sS - 1; i++) {
            for (int j = i + 1; j < tFluidList_sS; j++) {
                if (GT_Utility.areFluidsEqual(tFluidList.get(i), tFluidList.get(j))) {
                    if (tFluidList.get(i).amount >= tFluidList.get(j).amount) {
                        tFluidList.remove(j--);
                        tFluidList_sS = tFluidList.size();
                    } else {
                        tFluidList.remove(i--);
                        tFluidList_sS = tFluidList.size();
                        break;
                    }
                }
            }
        }
        return tFluidList.toArray(new FluidStack[0]);
    }

    public static FluidStack[] sortInputFluidStacks(ArrayList<FluidStack> inputList) {
        //ArrayList<FluidStack> outputList = new ArrayList<>();
        //for (FluidStack fluidStack : inputList) {
        //    OptionalInt outputIndex = IntStream.range(0, outputList.size())
        //            .filter(i -> GT_Utility.areFluidsEqual(outputList.get(i), fluidStack) ||
        //                    outputList.get(i).amount > fluidStack.amount)
        //            .findFirst();
        //    if (outputIndex.isPresent()) {
        //        outputList.set(outputIndex.getAsInt(), fluidStack);
        //    } else {
        //        outputList.add(fluidStack);
        //    }
        //}
        //return outputList.toArray(new FluidStack[0]);
        return inputList.toArray(new FluidStack[0]);
    }

    public static FluidStack[] sortOutputFluidStacks(ArrayList<FluidStack> inputList) {
        //ArrayList<FluidStack> outputList = new ArrayList<>();
        //for (FluidStack fluidStack : inputList) {
        //    OptionalInt outputIndex = IntStream.range(0, outputList.size())
        //            .filter(i -> GT_Utility.areFluidsEqual(outputList.get(i), fluidStack))
        //            .findFirst();
        //    if (outputIndex.isPresent()) {
        //        outputList.get(outputIndex.getAsInt()).amount += fluidStack.amount;
        //    } else {
        //        outputList.add(fluidStack);
        //    }
        //}
        //return outputList.toArray(new FluidStack[0]);
        return inputList.toArray(new FluidStack[0]);
    }
}
