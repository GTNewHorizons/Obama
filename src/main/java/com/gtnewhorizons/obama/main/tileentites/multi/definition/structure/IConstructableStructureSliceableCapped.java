package com.gtnewhorizons.obama.main.tileentites.multi.definition.structure;

import com.gtnewhorizon.structurelib.util.Vec3Impl;

/**
 * <p>The sliceable capped constructable structure interface implementation used to construct and check machines.
 *
 * <p>It is meant to be used for extensible multiblocks, where a machine is extended with identical 'slices'.
 * Compared with the regular sliceable structure, this expects an end 'cap' after the final slice.
 */
public interface IConstructableStructureSliceableCapped extends IConstructableStructureSliceable {
    String TM_STRUCTURE_CAP = "CAP";

    /**
     * <p>Gets cap structure offset.
     *
     * <p>Defines the vector by which the cap will be offset from the final checked slice.
     *
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the cap structure offset
     */
    Vec3Impl getCapStructureOffset();

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    default boolean checkMachine(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
//        if (!IConstructableStructureSliceable.super.checkMachine(iGregTechTileEntity, itemStack))
//            return false;
//        setCurrentStructureOffset(getCurrentStructureOffset().add(getCapStructureOffset()));
//        return structureCheck(TM_STRUCTURE_CAP, getCurrentStructureOffset());
//    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    default void construct(ItemStack itemStack, boolean hintsOnly) {
//        IConstructableStructureSliceable.super.construct(itemStack, hintsOnly);
//        setCurrentStructureOffset(getCurrentStructureOffset().add(getCapStructureOffset()));
//        structureBuild(TM_STRUCTURE_CAP, getCurrentStructureOffset(), hintsOnly, itemStack);
//    }
}
