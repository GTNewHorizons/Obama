package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.item.ItemStack;

/**
 * <p>The cell constructable structure interface implementation used to construct and check machines.
 *
 * <p>It is meant to be used for extensible multiblocks, where different sizes will add another 'cell' structure.
 */
public interface IConstructableStructureCells extends IConstructableStructureABCD {
    /**
     * {@inheritDoc}
     */
    @Override
    default boolean checkMachine_TM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        if (!checkMachineA())
            return false;

        if (!checkMachineB())
            return true;

        if (!checkMachineC())
            return true;

        checkMachineD();
        return true;
    }

    /**
     * {@inheritDoc}
     */
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
