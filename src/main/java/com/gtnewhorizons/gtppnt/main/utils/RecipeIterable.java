package com.gtnewhorizons.gtppnt.main.utils;

import gregtech.api.GregTech_API;
import gregtech.api.objects.GT_ItemStack;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import scala.actors.threadpool.Arrays;

import java.util.Collection;
import java.util.Iterator;

import static gregtech.api.enums.GT_Values.W;

public class RecipeIterable implements Iterable<GT_Recipe> {

    private GT_Recipe.GT_Recipe_Map mRecipeMap;
    private ItemStack[] mItems;
    private FluidStack[] mFluids;
    private long mVoltage;
    private GT_Recipe mBufferdRecipe;
    private boolean mDontCheckStackSize;
    private boolean mHasNext = false;
    private Iterable<ItemStack> mItemIterateble;
    private Iterable<FluidStack> mFluidIterateble;

    public RecipeIterable(GT_Recipe.GT_Recipe_Map aRecipeMap, GT_Recipe aRecipe, boolean aNotUnificated, boolean aDontCheckStackSizes, long aVoltage, FluidStack[] aFluids, ItemStack... aItems) {
        // No Recipes? Well, nothing to be found then.
        if (!aRecipeMap.mRecipeList.isEmpty()) {
            if (isValid(aRecipeMap,aItems,aFluids)) {
                if (aRecipeMap.mUsualInputCount > 0 && aItems != null)
                    mItemIterateble = Arrays.asList(aItems);

                if (aRecipeMap.mMinimalInputItems == 0 && aFluids != null)
                    mFluidIterateble = Arrays.asList(aFluids);

                if (mItemIterateble != null || mFluidIterateble != null) {
                    // Unification happens here in case the Input isn't already unificated.
                    if (aNotUnificated) aItems = GT_OreDictUnificator.getStackArray(true, aItems);
                    mItems = aItems;
                    mFluids = aFluids;
                    mVoltage = aVoltage;
                    mBufferdRecipe = aRecipe;
                    mDontCheckStackSize = aDontCheckStackSizes;
                    mRecipeMap = aRecipeMap;
                    mHasNext = true;
                }
            }
        }
    }

    private boolean isValid(GT_Recipe.GT_Recipe_Map recipeMap,ItemStack[] aItems, FluidStack[] aFluids) {
        // Some Recipe Classes require a certain amount of Inputs of certain kinds. Like "at least 1 Fluid + 1 Stack" or "at least 2 Stacks" before they start searching for Recipes.
        // This improves Performance massively, especially if people leave things like Circuits, Molds or Shapes in their Machines to select Sub Recipes.
        if (GregTech_API.sPostloadFinished) {
            if (recipeMap.mMinimalInputFluids > 0) {
                if (aFluids == null) return false;
                int tAmount = 0;
                for (FluidStack aFluid : aFluids) if (aFluid != null) tAmount++;
                if (tAmount < recipeMap.mMinimalInputFluids) return false;
            }
            if (recipeMap.mMinimalInputItems > 0) {
                if (aItems == null) return false;
                int tAmount = 0;
                for (ItemStack aInput : aItems) if (aInput != null) tAmount++;
                if (tAmount < recipeMap.mMinimalInputItems) return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<GT_Recipe> iterator() {
        return new RecipeIterator();
    }
    private class RecipeIterator implements Iterator<GT_Recipe> {

        Iterator<ItemStack> iItemIterator;
        Iterator<FluidStack> iFluidIterator;
        Iterator<GT_Recipe> iRecipeIterator;
        boolean iOnFluid = false;
        boolean iWithMeta = false;
        boolean iHasNext = mHasNext;
        GT_Recipe iBufferd = mBufferdRecipe;
        ItemStack iItemStack;
        FluidStack iFluidStack;

        RecipeIterator() {
            if (mFluidIterateble != null) {
                iFluidIterator = mFluidIterateble.iterator();
            }
            if (mItemIterateble != null) {
                iItemIterator = mItemIterateble.iterator();
            } else if (iFluidIterator != null) {
                iOnFluid = true;
            }
        }

        public GT_Recipe findRecipe() {
            if (iRecipeIterator == null)
                return null;
            while (iRecipeIterator.hasNext()) {
                GT_Recipe tRecipe = iRecipeIterator.next();
                if (tRecipe != null
                        && !tRecipe.mFakeRecipe
                        && tRecipe.isRecipeInputEqual(false, mDontCheckStackSize, mFluids, mItems))
                    return tRecipe.mEnabled && mVoltage * mRecipeMap.mAmperage >= tRecipe.mEUt ? tRecipe : null;
            }
            iRecipeIterator = null;
            return null;
        }

        @Override
        public GT_Recipe next() {
            // Now look for the Recipes inside the Item HashMaps, but only when the Recipes usually have Items.

            //decide between fluid or item
            //get next item if recipe is emprty
            // if item with or withou meta iteration
            // get next recipe until empty or found recipe
            if (iBufferd != null) {
                if (!iBufferd.mFakeRecipe && iBufferd.mCanBeBuffered && iBufferd.isRecipeInputEqual(false, mDontCheckStackSize, mFluids, mItems)) {
                    GT_Recipe temp = iBufferd.mEnabled && mVoltage * mRecipeMap.mAmperage >= iBufferd.mEUt ? iBufferd : null;
                    iBufferd = null;
                    return temp;
                }
            }

            while (iHasNext) {
                if (iOnFluid) {
                    while (iFluidStack == null) {
                        if (iFluidIterator.hasNext()) {
                            iFluidStack = iFluidIterator.next();
                        } else {
                            iHasNext = false;
                            return null;
                        }
                    }
                    if (iRecipeIterator == null)
                        iRecipeIterator = mRecipeMap.mRecipeFluidMap.get(iFluidStack.getFluid()).iterator();
                    GT_Recipe tRecipe = findRecipe();
                    if (tRecipe == null) {
                        iFluidStack = null;
                    } else {
                        return tRecipe;
                    }
                } else {
                    while (iItemStack == null) {
                        if (iItemIterator.hasNext()) {
                            iItemStack = iItemIterator.next();
                        } else {
                            if (iFluidIterator != null) {
                                iOnFluid = true;
                                break;
                            } else {
                                iHasNext = false;
                                return null;
                            }
                        }
                    }
                    if (iWithMeta) {
                        if (iRecipeIterator == null) {
                            Collection<GT_Recipe> tRecipeColl = mRecipeMap.mRecipeItemMap.get(GT_Utility.copyMetaData(W, iItemStack));
                            if (tRecipeColl != null)
                                iRecipeIterator = tRecipeColl.iterator();
                        }
                        GT_Recipe tRecipe = findRecipe();
                        if (tRecipe == null) {
                            iItemStack = null;
                            iWithMeta = false;
                        } else {
                            return tRecipe;
                        }
                    } else {
                        if (iRecipeIterator == null) {
                            Collection<GT_Recipe> tRecipeColl = mRecipeMap.mRecipeItemMap.get(new GT_ItemStack(iItemStack));
                            if (tRecipeColl != null)
                                iRecipeIterator = tRecipeColl.iterator();
                        }
                        GT_Recipe tRecipe = findRecipe();
                        if (tRecipe == null) {
                            iWithMeta = true;
                        } else {
                            return tRecipe;
                        }
                    }
                }
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            return iHasNext;
        }
    }
}
