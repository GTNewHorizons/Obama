package com.gtnewhorizons.gtppnt.main.items;

import gregtech.api.interfaces.IItemContainer;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static gregtech.api.enums.GT_Values.W;

public enum CustomItemList implements IItemContainer {
    tM_TestCasing_0, tM_TestCasing_1, tM_TestCasing_2, tM_TestCasing_3, tM_TestCasing_4, tM_TestCasing_5, tM_TestCasing_6, tM_TestCasing_7, tM_TestCasing_8, tM_TestCasing_9, tM_TestCasing_10, tM_TestCasing_11, tM_TestCasing_12, tM_TestCasing_13, tM_TestCasing_14, tM_TestCasing_15
    ;

    private ItemStack mStack;
    private boolean mHasNotBeenSet = true;

    //public static Fluid sOilExtraHeavy, sOilHeavy, sOilMedium, sOilLight, sNaturalGas;

    @Override
    public IItemContainer set(Item aItem) {
        mHasNotBeenSet = false;
        if (aItem == null) {
            return this;
        }
        ItemStack aStack = new ItemStack(aItem, 1, 0);
        mStack = GT_Utility.copyAmount(1, aStack);
        return this;
    }

    @Override
    public IItemContainer set(ItemStack aStack) {
        mHasNotBeenSet = false;
        mStack = GT_Utility.copyAmount(1, aStack);
        return this;
    }

    @Override
    public Item getItem() {
        if (mHasNotBeenSet) {
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        }
        if (GT_Utility.isStackInvalid(mStack)) {
            return null;
        }
        return mStack.getItem();
    }

    @Override
    public Block getBlock() {
        if (mHasNotBeenSet) {
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        }
        return GT_Utility.getBlockFromStack(new ItemStack(getItem()));
    }

    @Override
    public final boolean hasBeenSet() {
        return !mHasNotBeenSet;
    }

    @Override
    public boolean isStackEqual(Object aStack) {
        return isStackEqual(aStack, false, false);
    }

    @Override
    public boolean isStackEqual(Object aStack, boolean aWildcard, boolean aIgnoreNBT) {
        if (GT_Utility.isStackInvalid(aStack)) {
            return false;
        }
        return GT_Utility.areUnificationsEqual((ItemStack) aStack, aWildcard ? getWildcard(1) : get(1), aIgnoreNBT);
    }

    @Override
    public ItemStack get(long aAmount, Object... aReplacements) {
        if (mHasNotBeenSet) {
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        }
        if (GT_Utility.isStackInvalid(mStack)) {
            return GT_Utility.copyAmount(aAmount, aReplacements);
        }
        return GT_Utility.copyAmount(aAmount, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getWildcard(long aAmount, Object... aReplacements) {
        if (mHasNotBeenSet) {
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        }
        if (GT_Utility.isStackInvalid(mStack)) {
            return GT_Utility.copyAmount(aAmount, aReplacements);
        }
        return GT_Utility.copyAmountAndMetaData(aAmount, W, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getUndamaged(long aAmount, Object... aReplacements) {
        if (mHasNotBeenSet) {
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        }
        if (GT_Utility.isStackInvalid(mStack)) {
            return GT_Utility.copyAmount(aAmount, aReplacements);
        }
        return GT_Utility.copyAmountAndMetaData(aAmount, 0, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getAlmostBroken(long aAmount, Object... aReplacements) {
        if (mHasNotBeenSet) {
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        }
        if (GT_Utility.isStackInvalid(mStack)) {
            return GT_Utility.copyAmount(aAmount, aReplacements);
        }
        return GT_Utility.copyAmountAndMetaData(aAmount, mStack.getMaxDamage() - 1, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getWithName(long aAmount, String aDisplayName, Object... aReplacements) {
        ItemStack rStack = get(1, aReplacements);
        if (GT_Utility.isStackInvalid(rStack)) {
            return null;
        }
        rStack.setStackDisplayName(aDisplayName);
        return GT_Utility.copyAmount(aAmount, rStack);
    }

    @Override
    public ItemStack getWithCharge(long aAmount, int aEnergy, Object... aReplacements) {
        ItemStack rStack = get(1, aReplacements);
        if (GT_Utility.isStackInvalid(rStack)) {
            return null;
        }
        GT_ModHandler.chargeElectricItem(rStack, aEnergy, Integer.MAX_VALUE, true, false);
        return GT_Utility.copyAmount(aAmount, rStack);
    }

    @Override
    public ItemStack getWithDamage(long aAmount, long aMetaValue, Object... aReplacements) {
        if (mHasNotBeenSet) {
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        }
        if (GT_Utility.isStackInvalid(mStack)) {
            return GT_Utility.copyAmount(aAmount, aReplacements);
        }
        return GT_Utility.copyAmountAndMetaData(aAmount, aMetaValue, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public IItemContainer registerOre(Object... aOreNames) {
        if (mHasNotBeenSet) {
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        }
        for (Object tOreName : aOreNames) {
            GT_OreDictUnificator.registerOre(tOreName, get(1));
        }
        return this;
    }

    @Override
    public IItemContainer registerWildcardAsOre(Object... aOreNames) {
        if (mHasNotBeenSet) {
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        }
        for (Object tOreName : aOreNames) {
            GT_OreDictUnificator.registerOre(tOreName, getWildcard(1));
        }
        return this;
    }
}
