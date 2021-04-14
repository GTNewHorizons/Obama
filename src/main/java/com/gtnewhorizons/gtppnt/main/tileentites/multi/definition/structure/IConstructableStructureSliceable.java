package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure;

import com.github.technus.tectech.util.Vec3Impl;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.item.ItemStack;

public interface IConstructableStructureSliceable extends IConstructableStructureImpl {
    String TM_STRUCTURE_MIDDLE = "MIDDLE";
    String TM_STRUCTURE_END = "END";

    @Override
    default int getMaxParalells() {
        return getSliceCount() * getParalellsPerSlice();
    }

    Vec3Impl getSliceStructureOffset();

    Vec3Impl getPerSliceOffset();

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
        Vec3Impl sliceStructureOffset = getSliceStructureOffset();
        for (int i = 0; i < getMaxSlices(); i++) {
            if (structureCheck_TM(TM_STRUCTURE_MIDDLE, sliceStructureOffset)) {
                setSliceCount(getSliceCount() + 1);
                sliceStructureOffset = sliceStructureOffset.add(getPerSliceOffset());
            } else {
                break;
            }
        }

        return getSliceCount() >= getMinSlices();
    }

    @Override
    default void construct(ItemStack itemStack, boolean hintsOnly) {
        IConstructableStructureImpl.super.construct(itemStack, hintsOnly);

        int sliceCount = Math.min(itemStack.stackSize, getMaxSlices());
        Vec3Impl sliceStructureOffset = getSliceStructureOffset();

        for (int i = 0; i < sliceCount; i++) {
            structureBuild_TM(TM_STRUCTURE_MIDDLE, sliceStructureOffset, hintsOnly, itemStack);
            sliceStructureOffset = sliceStructureOffset.add(getPerSliceOffset());
        }
    }
}
