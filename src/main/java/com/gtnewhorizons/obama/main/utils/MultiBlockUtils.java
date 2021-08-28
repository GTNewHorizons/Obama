/*
 * Copyright 2021 The GTNH Team
 * 
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */

package com.gtnewhorizons.obama.main.utils;

import com.gtnewhorizons.obama.main.tileentites.multi.definition.RecipeProgresion;
import gregtech.api.enums.ItemList;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import ic2.core.Ic2Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;

public class MultiBlockUtils {

    public static int isRecipeEqualAndRemoveParallel(GT_Recipe recipe, ItemStack[] actaulItemInput, ItemStack[] inputItemsCombined, FluidStack[] inputFluids, int maxParrallel, boolean removeItems) {
        if (recipe.mFluidInputs.length > 0 && inputFluids == null) return 0;
        if (recipe.mInputs.length > 0 && inputItemsCombined == null) return 0;


        maxParrallel = checkFluidParrallel(recipe, inputFluids, maxParrallel);
        if (maxParrallel == 0) return 0;
        maxParrallel = checkItemParrallel(recipe, inputItemsCombined, maxParrallel);
        if (maxParrallel == 0) return 0;

        if (removeItems) {
            removeInputParallel(recipe, inputItemsCombined, actaulItemInput, inputFluids, maxParrallel);
        }
        return maxParrallel;
    }

    public static RecipeProgresion getRecipeProgressionWithOC(GT_Recipe recipe, int recipeVoltage, int inputVolatge, int amount) {
        int recipeTime = recipe.mDuration;
        int newVolatge = recipeVoltage << 2;
        while (newVolatge < inputVolatge && recipeTime > 1) {
            recipeVoltage = newVolatge;
            newVolatge <<= 2;
            recipeTime >>= 1;
        }
        return new RecipeProgresion(recipe, amount, recipeTime, recipeVoltage * amount);
    }


    public static void addItemOutputToList(GT_Recipe recipe, ArrayList<ItemStack> outputItems, int parrallel) {
        for (int i = 0; i < recipe.mOutputs.length; ++i) {
            ItemStack outputStack = recipe.getOutput(i);
            if (outputStack != null) {
                int amount = outputStack.stackSize * parrallel;
                int maxStackSize = outputStack.getMaxStackSize();
                while (maxStackSize < amount) {
                    ItemStack out = outputStack.copy();
                    out.stackSize = maxStackSize;
                    outputItems.add(out);
                    amount -= maxStackSize;
                }
                if (amount > 0) {
                    outputStack.stackSize = amount;
                    outputItems.add(outputStack);
                }
            }
        }
    }

    public static void addFluidoutputToList(GT_Recipe recipe, ArrayList<FluidStack> outputFluids, int parrallel) {
        for (int i = 0; i < recipe.mFluidOutputs.length; ++i) {
            FluidStack outputFluid = recipe.getFluidOutput(i);
            if (outputFluid != null) {
                outputFluid.amount = parrallel * outputFluid.amount;
                outputFluids.add(outputFluid);
            }
        }
    }

    // remove fluids and items with parallel
    private static void removeInputParallel(GT_Recipe recipe, ItemStack[] combinedStacks, ItemStack[] actaulItemInput, FluidStack[] inputFluids, int parrallel) {
        ItemStack[] toRemoveItems = removeItemList(recipe, parrallel);
        FluidStack[] toRemoveFluids = removeFluidList(recipe, parrallel);
        removeFromCombinedStack(toRemoveItems, combinedStacks, parrallel);
        removeItems(recipe, actaulItemInput, toRemoveItems);
        removeFluids(recipe, inputFluids, toRemoveFluids);
    }

    //items should already be unified here
    private static void removeFromCombinedStack(ItemStack[] itemRemoveList, ItemStack[] combined, int parrallel) {
        int removedItems = 0;
        int itemsToRemove = itemRemoveList.length;
        for (int i = 0; i < combined.length; i++) {
            ItemStack toRemove = combined[i];
            if (toRemove == null)
                continue;
            for (ItemStack removeListStack : itemRemoveList) {
                if (GT_Utility.areStacksEqual(removeListStack, toRemove, true)) {
                    toRemove.stackSize -= removeListStack.stackSize;
                    if (toRemove.stackSize == 0)
                        combined[i] = null;
                    itemsToRemove--;
                    break;
                }
            }
            if (itemsToRemove == 0)
                break;
        }
    }

    //genrate list to what and how many of the items needs to be removed
    private static ItemStack[] removeItemList(GT_Recipe recipe, int parrallel) {
        ItemStack[] recipeItems = recipe.mInputs;
        ItemStack[] toRemoveItems = new ItemStack[recipeItems.length];
        for (int i = 0; i < recipeItems.length; ++i) {
            ItemStack item = getUnifiedWithStackSize(recipeItems[i]);
            if (item != null) {
                item = shallowItemCopy(item);
                item.stackSize *= parrallel;
                toRemoveItems[i] = item;
            }
        }
        return toRemoveItems;
    }

    //genrate list to what and how many of the fluids needs to be removed
    private static FluidStack[] removeFluidList(GT_Recipe recipe, int parrallel) {
        FluidStack[] recipeFluids = recipe.mFluidInputs;
        FluidStack[] toRemoveFluids = new FluidStack[recipeFluids.length];
        for (int i = 0; i < recipeFluids.length; ++i) {
            FluidStack fluid = recipeFluids[i];
            if (fluid != null) {
                fluid = fluid.copy();
                fluid.amount *= parrallel;
                toRemoveFluids[i] = fluid;
            }
        }
        return toRemoveFluids;
    }

    private static void removeItems(GT_Recipe recipe, ItemStack[] actaulItemInput, ItemStack[] toRemoveItems) {
        boolean checkNBT = GT_Recipe.GTppRecipeHelper;
        int completeCount = toRemoveItems.length;
        for (ItemStack inputItem : actaulItemInput) {
            ItemStack unified = GT_OreDictUnificator.get_nocopy(inputItem);
            if (unified == null)
                unified = inputItem;
            for (int i = 0; i < toRemoveItems.length; ++i) {
                ItemStack toRemove = toRemoveItems[i];
                if (GT_Utility.areStacksEqual(toRemove, unified, false)) {
                    if (checkNBT && !specialItemCheck(toRemove, unified)) {
                        continue;
                    }
                    if (inputItem.stackSize < toRemove.stackSize) {
                        toRemove.stackSize -= inputItem.stackSize;
                        inputItem.stackSize = 0;
                    } else {
                        inputItem.stackSize -= toRemove.stackSize;
                        toRemoveItems[i] = null;
                        --completeCount;
                    }
                    break;
                }
            }
            if (completeCount < 1)
                break;
        }
    }

    private static void removeFluids(GT_Recipe recipe, FluidStack[] inputFluids, FluidStack[] toRemoveFluids) {
        int completeCount = toRemoveFluids.length;
        for (FluidStack inputFluid : inputFluids) {
            for (int i = 0; i < toRemoveFluids.length; ++i) {
                FluidStack toRemove = toRemoveFluids[i];
                if (GT_Utility.areFluidsEqual(inputFluid, toRemove)) {
                    if (inputFluid.amount < toRemove.amount) {
                        toRemove.amount -= inputFluid.amount;
                        inputFluid.amount = 0;
                    } else {
                        toRemoveFluids[i] = null;
                        inputFluid.amount -= toRemove.amount;
                        --completeCount;
                    }
                    break;
                }
            }
            if (completeCount == 0)
                break;
        }
    }

    // checks if has all fluids and return how much it can do in parrallel
    private static int checkFluidParrallel(GT_Recipe recipe, FluidStack[] inputFluids, int maxParrallel) {
        for (FluidStack recipeFluid : recipe.mFluidInputs) {
            if (recipeFluid != null) {
                int recipeAmount = recipeFluid.amount;
                boolean fondFluid = false;
                for (FluidStack inputFluid : inputFluids) {
                    if (inputFluid != null && inputFluid.isFluidEqual(recipeFluid)) {
                        if (recipeAmount > 0) {
                            int parrallel = inputFluid.amount / recipeAmount;
                            if (parrallel < maxParrallel)
                                maxParrallel = parrallel;
                            if (parrallel > 0) {
                                fondFluid = true;
                                break;
                            }
                        } else {
                            fondFluid = true;
                            break;
                        }
                    }
                }
                if (!fondFluid) return 0;
            }
        }
        return maxParrallel;
    }

    // check if all items are there and how much it can do in parrallel
    // asumes inputItems is already unified and no duplicate stacks
    private static int checkItemParrallel(GT_Recipe recipe, ItemStack[] inputItems, int maxParrallel) {
        for (ItemStack recipeItem : recipe.mInputs) {
            recipeItem = getUnifiedWithStackSize(recipeItem);
            boolean foundItem = false;
            if (recipeItem != null) {
                int recipeAmount = recipeItem.stackSize;
                for (ItemStack inputItem : inputItems) {
                    if (GT_Utility.areStacksEqual(inputItem, recipeItem, true)) {
                        if (GT_Recipe.GTppRecipeHelper) {//mentions somthing about removing when fix is out ask bart what it is
                            if (!specialItemCheck(recipeItem, inputItem))
                                continue;
                        }

                        if (recipeAmount > 0) {
                            int parrallel = inputItem.stackSize / recipeAmount;
                            if (parrallel < maxParrallel)
                                maxParrallel = parrallel;
                            if (parrallel > 0) {
                                foundItem = true;
                                break;
                            }
                        } else {
                            foundItem = true;
                            break;
                        }
                    }
                }
                if (!foundItem) return 0;
            }
        }
        return maxParrallel;
    }

    // combines itemSTacks that are the same and unifie them
    public static ItemStack[] combineStacks(ItemStack[] inputs) {
        ArrayList<ItemStack> combined = new ArrayList<>();
        for (ItemStack item : inputs) {
            int stackSize = item.stackSize;
            item = getUnifiedWithStackSize(item);
            if (item != null) {
                boolean added = false;
                for (ItemStack itemComb : combined) {
                    if (itemComb != null && GT_Utility.areStacksEqual(itemComb, item)) {
                        itemComb.stackSize += item.stackSize;
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    combined.add(shallowItemCopy(item));
                }
            }
        }
        return combined.toArray(new ItemStack[0]);
    }

    public static Boolean specialItemCheck(ItemStack recipeItem, ItemStack inputItem) {
        if (GT_Utility.areStacksEqual(inputItem, Ic2Items.FluidCell.copy(), true) || GT_Utility.areStacksEqual(inputItem, ItemList.Tool_DataStick.get(1L), true) || GT_Utility.areStacksEqual(inputItem, ItemList.Tool_DataOrb.get(1L), true)) {
            return !GT_Utility.areStacksEqual(inputItem, recipeItem, false);
        }
        return true;
    }

    public static ItemStack getUnifiedWithStackSize(ItemStack item) {
        if (item != null) {
            int stackSize = item.stackSize;
            item = GT_OreDictUnificator.get_nocopy(item);
            if (item != null) {
                item.stackSize = stackSize;
            }
        }
        return item;
    }

    //make copy of item without copyingthe nbt
    public static ItemStack shallowItemCopy(ItemStack item) {
        ItemStack copy = new ItemStack(item.getItem(), item.stackSize, item.getItemDamage());
        if (item.hasTagCompound())
            copy.stackTagCompound = item.stackTagCompound;
        return copy;
    }


    //TODO Delete, old Bart code.
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

    //TODO Delete, old Bart code.
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
}
