package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure;

import com.github.technus.tectech.util.Vec3Impl;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.item.ItemStack;

public interface IConstructableStructureSliceableCapped extends IConstructableStructureSliceable {
    String TM_STRUCTURE_CAP = "CAP";

    Vec3Impl getCapStructureOffset();

    @Override
    default boolean checkMachine_TM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        if (!IConstructableStructureSliceable.super.checkMachine_TM(iGregTechTileEntity, itemStack))
            return false;

        return structureCheck_TM(TM_STRUCTURE_START, getCapStructureOffset());
    }

    @Override
    default void construct(ItemStack itemStack, boolean hintsOnly) {
        IConstructableStructureSliceable.super.construct(itemStack, hintsOnly);
        structureBuild_TM(TM_STRUCTURE_CAP, getCapStructureOffset(), hintsOnly, itemStack);
    }
}
