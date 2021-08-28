package com.gtnewhorizons.obama.main.items;

import gregtech.api.interfaces.IItemContainer;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static gregtech.api.enums.GT_Values.W;

public enum CustomItemList implements IItemContainer {
    LARGE_CENTRIFUGE, LARGE_BENDING_MACHINE, LARGE_FORMING_PRESS, LARGE_ELECTROLYZER, LARGE_MACERATOR, LARGE_WIREMILL,
    LARGE_MIXER, LARGE_SIFTING_MACHINE, LARGE_THERMAL_CENTRIFUGE, LARGE_ORE_WASHING_PLANT, LARGE_CHEMICAL_BATH,
    LARGE_EXTRUDER, LARGE_ARC_FURNACE, LARGE_PLASMA_ARC_FURNACE, LARGE_FLUID_HEATER, LARGE_ASSEMBLING_MACHINE,
    LARGE_PACKAGER, LARGE_CUTTING_MACHINE, LARGE_SLICING_MACHINE, LARGE_COMPRESSOR, LARGE_LATHE,
    LARGE_PRECISION_LASER_ENGRAVER, LARGE_AUTOCLAVE,

    //LARGE_CIRCUIT_ASSEMBLING_MACHINE, LARGE_ALLOY_SMELTER, LARGE_FERMENTER, LARGE_EXTRACTOR, LARGE_FLUID_EXTRACTOR,
    //LARGE_POLARIZER, LARGE_FLUID_SOLIFIER, LARGE_FORMING_PRESS, LARGE_RECYCLER, LARGE_MICROWAVE, LARGE_PRINTER,
    //LARGE_ELECTROMAGNETIC_SEPARATOR, LARGE_FLUID_CANNER, LARGE_BREWERY, LARGE_CANNING_MACHINE, LARGE_FORGE_HAMMER
    //LARGE_CHEMICAL_REACTOR, LARGE_DISTILLERY

    GRINDING_CASING_LV, GRINDING_CASING_MV, GRINDING_CASING_HV,
    PISTON_CASING_LV, PISTON_CASING_MV, PISTON_CASING_HV,
    MOTOR_CASING_LV, MOTOR_CASING_MV, MOTOR_CASING_HV,
    CIRCUIT_CASING_LV, CIRCUIT_CASING_MV, CIRCUIT_CASING_HV,
    CONVEYOR_CASING_LV, CONVEYOR_CASING_MV, CONVEYOR_CASING_HV,
    PUMP_CASING_LV, PUMP_CASING_MV, PUMP_CASING_HV,
    EMITTER_CASING_LV, EMITTER_CASING_MV, EMITTER_CASING_HV,
    ROTOR_CASING_LV, ROTOR_CASING_MV, ROTOR_CASING_HV,
    ARM_CASING_LV, ARM_CASING_MV, ARM_CASING_HV,
    WIRE_LV, WIRE_MV, WIRE_HV,
    FILTER_LV, FILTER_MV, FILTER_HV,

    CATALYTIC_MUFFLER_EV, CATALYTIC_MUFFLER_IV, CATALYTIC_MUFFLER_LuV, CATALYTIC_MUFFLER_ZPM, CATALYTIC_MUFFLER_UV;

    private ItemStack mStack;
    private boolean mHasNotBeenSet = true;

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
