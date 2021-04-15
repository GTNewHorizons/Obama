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
        setCurrentStructureOffset(getCurrentStructureOffset().add(getCapStructureOffset()));
        return structureCheck_TM(TM_STRUCTURE_CAP, getCurrentStructureOffset());
    }

    @Override
    default void construct(ItemStack itemStack, boolean hintsOnly) {
        IConstructableStructureSliceable.super.construct(itemStack, hintsOnly);
        setCurrentStructureOffset(getCurrentStructureOffset().add(getCapStructureOffset()));
        structureBuild_TM(TM_STRUCTURE_CAP, getCurrentStructureOffset(), hintsOnly, itemStack);
    }
}
