package com.gtnewhorizons.obama.main.tileentites.multi.definition.structure;

import com.gtnewhorizon.structurelib.util.Vec3Impl;
import net.minecraft.item.ItemStack;

/**
 * <p>The sliceable constructable structure interface implementation used to construct and check machines.
 *
 * <p>It is meant to be used for extensible multiblocks, where a machine is extended with identical 'slices'.
 */
public interface IConstructableStructureSliceable extends IConstructableStructureSimple {
    String TM_STRUCTURE_MIDDLE = "MIDDLE";

    /**
     * <p>Gets min parallel.
     *
     * <p>Defines the number of parallels at 0 slices.
     *
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the min parallel
     */
    int getMinParallel();

    /**
     * <p>Gets slice structure offset.
     *
     * <p>Defines the vector by which the original slice is offset from the multiblock.
     *
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the slice structure offset
     */
    Vec3Impl getSliceStructureOffset();

    /**
     * <p>Gets per slice offset.
     *
     * <p>Defines the vector by which every new slice is offset from the first slice.
     *
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the per slice offset
     */
    Vec3Impl getPerSliceOffset();

    /**
     * <p>Gets max slices.
     *
     * <p>Limit on how many slices will be checked.
     *
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the max slices
     */
    int getMaxSlices();

    /**
     * <p>Gets min slices.
     *
     * <p>Limit on the lowest number of slices for a valid structure.
     *
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the min slices
     */
    int getMinSlices();

    /**
     * <p>Gets paralells per slice.
     *
     * <p>Number of additional parallels per slice.
     *
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the paralells per slice
     */
    int getParalellsPerSlice();

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    default boolean checkMachine(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
//        if (!IConstructableStructureSimple.super.checkMachine(iGregTechTileEntity, itemStack))
//            return false;
//
//        setStructureCounter(0);
//        setCurrentStructureOffset(getSliceStructureOffset());
//        for (int i = 0; i < getMaxSlices(); i++) {
//            if (structureCheck(TM_STRUCTURE_MIDDLE, getCurrentStructureOffset())) {
//                setStructureCounter(getStructureCounter() + 1);
//                setCurrentStructureOffset(getCurrentStructureOffset().add(getPerSliceOffset()));
//            } else {
//                break;
//            }
//        }
//
//        if (getStructureCounter() < getMinSlices())
//            return false;
//
//        setMaxParallels(getStructureCounter() * getParalellsPerSlice() + getMinParallel());
//        return true;
//    }
//
    /**
     * {@inheritDoc}
     */
    @Override
    default void construct(ItemStack itemStack, boolean hintsOnly) {
        IConstructableStructureSimple.super.construct(itemStack, hintsOnly);
        int blueprintCount = (itemStack.stackSize - 1) + getMinSlices();
        int sliceCount = Math.min(blueprintCount, getMaxSlices());
        setCurrentStructureOffset(getSliceStructureOffset());

        for (int i = 0; i < sliceCount; i++) {
            buildPiece(TM_STRUCTURE_MIDDLE, getCurrentStructureOffset(), hintsOnly, itemStack);
            setCurrentStructureOffset(getCurrentStructureOffset().add(getPerSliceOffset()));
        }
    }
}
