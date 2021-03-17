package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure;

import com.github.technus.tectech.util.Vec3Impl;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.item.ItemStack;

public interface IStructureProviderImpl extends IStructureProviderBase {
    String TM_STRUCTURE_START = "START";

    Vec3Impl getStartStructureOffset();

    @Override
    default boolean checkMachine_TM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        return structureCheck_TM(TM_STRUCTURE_START, getStartStructureOffset());
    }

    @Override
    default void construct(ItemStack itemStack, boolean hintsOnly) {
        structureBuild_TM(TM_STRUCTURE_START, getStartStructureOffset(), hintsOnly, itemStack);
    }
}
