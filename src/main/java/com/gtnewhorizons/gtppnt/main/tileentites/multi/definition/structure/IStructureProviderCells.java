package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.item.ItemStack;

public interface IStructureProviderCells  extends IStructureProviderABCD {
    @Override
    default boolean checkMachine_TM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        if (!checkMachineA())
            return false;

        if (!checkMachineB())
            return false;

        if (!checkMachineC())
            return false;

        return checkMachineD();
    }

    @Override
    default void construct(ItemStack itemStack, boolean hintsOnly) {
        int progression = Math.min(itemStack.stackSize, 4);

        constructMachineA(hintsOnly, itemStack);
        if (progression == 1)
            return;
        constructMachineB(hintsOnly, itemStack);
        if (progression == 2)
            return;
        constructMachineC(hintsOnly, itemStack);
        if (progression == 3)
            return;
        constructMachineD(hintsOnly, itemStack);
    }
}
