package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import com.github.technus.tectech.util.Vec3Impl;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.item.ItemStack;

public interface IStructureProviderSliceable extends IStructureProviderImpl {
    String TM_SLICE_MIDDLE = "MIDDLE";

    Vec3Impl getSliceStructureOffset();

    Vec3Impl getPerSliceOffset();

    int getMaxSlices();

    int getMinSlices();

    int getParalellsPerSlice();

    int getSliceCount();

    void setSliceCount(int sliceCount);

    @Override
    default boolean checkMachine_TM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        if (IStructureProviderImpl.super.checkMachine_TM(iGregTechTileEntity, itemStack)) {
            setSliceCount(0);
            Vec3Impl sliceStructureOffset = getSliceStructureOffset();
            for (int i = 0; i < getMaxSlices(); i++) {
                if (structureCheck_TM(TM_SLICE_MIDDLE,
                        sliceStructureOffset.get0(),
                        sliceStructureOffset.get1(),
                        sliceStructureOffset.get2())) {
                    setSliceCount(getSliceCount() + 1);
                    sliceStructureOffset = sliceStructureOffset.add(getPerSliceOffset());
                } else {
                    break;
                }
            }
        }
        return getSliceCount() >= getMinSlices();
    }

    @Override
    default void construct(ItemStack itemStack, boolean hintsOnly) {
        IStructureProviderImpl.super.construct(itemStack, hintsOnly);

        int sliceCount = Math.min(itemStack.stackSize, getMaxSlices());
        Vec3Impl sliceStructureOffset = getSliceStructureOffset();
        for (int i = 0; i < sliceCount; i++) {
            structureBuild_TM(TM_SLICE_MIDDLE,
                    sliceStructureOffset.get0(),
                    sliceStructureOffset.get1(),
                    sliceStructureOffset.get2(),
                    hintsOnly, itemStack);
            sliceStructureOffset = sliceStructureOffset.add(getPerSliceOffset());
        }
    }
}
