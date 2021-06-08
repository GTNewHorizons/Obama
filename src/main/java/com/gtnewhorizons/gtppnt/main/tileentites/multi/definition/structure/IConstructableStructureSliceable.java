package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure;

import com.github.technus.tectech.util.Vec3Impl;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.item.ItemStack;

public interface IConstructableStructureSliceable extends IConstructableStructureImpl {
    String TM_STRUCTURE_MIDDLE = "MIDDLE";

    @Override
    default int getMaxParalells() {
        return getSliceCount() * getParalellsPerSlice() + getMinParallel();
    }

    int getMinParallel();

    Vec3Impl getSliceStructureOffset();

    Vec3Impl getPerSliceOffset();

    Vec3Impl getCurrentStructureOffset();

    void setCurrentStructureOffset(Vec3Impl structureOffset);

    int getMaxSlices();

    int getMinSlices();

    int getParalellsPerSlice();

    int getSliceCount();

    void setSliceCount(int sliceCount);

    @Override
    default boolean checkMachine_TM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        if (!IConstructableStructureImpl.super.checkMachine_TM(iGregTechTileEntity, itemStack))
            return false;

        setSliceCount(0);
        setCurrentStructureOffset(getSliceStructureOffset());
        for (int i = 0; i < getMaxSlices(); i++) {
            if (structureCheck_TM(TM_STRUCTURE_MIDDLE, getCurrentStructureOffset())) {
                setSliceCount(getSliceCount() + 1);
                setCurrentStructureOffset(getCurrentStructureOffset().add(getPerSliceOffset()));
            } else {
                break;
            }
        }
        return getSliceCount() >= getMinSlices();
    }

    @Override
    default void construct(ItemStack itemStack, boolean hintsOnly) {
        IConstructableStructureImpl.super.construct(itemStack, hintsOnly);
        int blueprintCount = (itemStack.stackSize - 1) + getMinSlices();
        int sliceCount = Math.min(blueprintCount, getMaxSlices());
        setCurrentStructureOffset(getSliceStructureOffset());

        for (int i = 0; i < sliceCount; i++) {
            structureBuild_TM(TM_STRUCTURE_MIDDLE, getCurrentStructureOffset(), hintsOnly, itemStack);
            setCurrentStructureOffset(getCurrentStructureOffset().add(getPerSliceOffset()));
        }
    }
}
