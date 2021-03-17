package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure;


import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.item.ItemStack;

public interface IStructureProviderShapes extends IStructureProviderABCD {
    @Override
    default boolean checkMachine_TM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        if (checkMachineD())
            return true;

        if (checkMachineC())
            return true;

        if (checkMachineB())
            return true;

        return checkMachineA();
    }

    @Override
    default void construct(ItemStack itemStack, boolean hintsOnly) {
        int shape = Math.min(itemStack.stackSize, 4);
        switch (shape) {
            case 1:
                constructMachineA(hintsOnly, itemStack);
            case 2:
                constructMachineB(hintsOnly, itemStack);
            case 3:
                constructMachineC(hintsOnly, itemStack);
            case 4:
                constructMachineD(hintsOnly, itemStack);
        }
    }
}
