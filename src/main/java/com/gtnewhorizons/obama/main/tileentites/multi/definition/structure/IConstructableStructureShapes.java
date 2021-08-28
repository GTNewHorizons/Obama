package com.gtnewhorizons.obama.main.tileentites.multi.definition.structure;


/**
 * <p>The shape constructable structure interface implementation used to construct and check machines.
 *
 * <p>It is meant to be used for extensible multiblocks, where different sizes require a different 'shape' be built.
 */
public interface IConstructableStructureShapes extends IConstructableStructureABCD {
    /**
     * {@inheritDoc}
     */
//    @Override
//    default boolean checkMachine(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
//        if (checkMachineD())
//            return true;
//
//        if (checkMachineC())
//            return true;
//
//        if (checkMachineB())
//            return true;
//
//        return checkMachineA();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    default void construct(ItemStack itemStack, boolean hintsOnly) {
//        int shape = Math.min(itemStack.stackSize, 4);
//        switch (shape) {
//            case 1:
//                constructMachineA(hintsOnly, itemStack);
//                break;
//            case 2:
//                constructMachineB(hintsOnly, itemStack);
//                break;
//            case 3:
//                constructMachineC(hintsOnly, itemStack);
//                break;
//            case 4:
//                constructMachineD(hintsOnly, itemStack);
//                break;
//        }
//    }
}
