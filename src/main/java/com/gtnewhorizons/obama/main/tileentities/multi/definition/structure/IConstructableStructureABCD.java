package com.gtnewhorizons.obama.main.tileentities.multi.definition.structure;

import com.gtnewhorizon.structurelib.util.Vec3Impl;
import net.minecraft.item.ItemStack;

/**
 * <p>The abstract ABCD constructable structure interface used to construct and check machines.
 */
public interface IConstructableStructureABCD extends IConstructableStructure {
    String TM_STRUCTURE_A = "A";
    String TM_STRUCTURE_B = "B";
    String TM_STRUCTURE_C = "C";
    String TM_STRUCTURE_D = "D";

    /**
     * <p>Gets start offset for shape A.
     * 
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the start offset
     */
    Vec3Impl getStartOffsetA();

    /**
     * <p>Gets start offset for shape B.
     * 
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the start offset
     */
    default Vec3Impl getStartOffsetB() {
        return null;
    }

    /**
     * <p>Gets start offset for shape C.
     * 
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the start offset
     */
    default Vec3Impl getStartOffsetC() {
        return null;
    }

    /**
     * <p>Gets start offset for shape D.
     * 
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the start offset
     */
    default Vec3Impl getStartOffsetD() {
        return null;
    }

    /**
     * <p>Gets paralells for shape A.
     * 
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the paralells
     */
    int getParalellsA();

    /**
     * <p>Gets paralells for shape B.
     * 
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the paralells
     */
    default int getParalellsB() {
        return 0;
    }

    /**
     * <p>Gets paralells for shape C.
     * 
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the paralells
     */
    default int getParalellsC() {
        return 0;
    }

    /**
     * <p>Gets paralells for shape D.
     * 
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the paralells
     */
    default int getParalellsD() {
        return 0;
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    default boolean structureCheck(String piece, Vec3Impl offset) {
//        if (offset == null)
//            return false;
//        return IConstructableStructure.super.structureCheck(piece, offset);
//    }

    /**
     * <p>Check machine shape A and sets appropriate parallel count.
     *
     * @return true if shape valid
     */
    default boolean checkMachineA() {
        if (!structureCheck(TM_STRUCTURE_A, getStartOffsetA()))
            return false;
        setMaxParallels(getParalellsA());
        return true;
    }

    /**
     * <p>Check machine shape B and sets appropriate parallel count.
     *
     * @return true if shape valid
     */
    default boolean checkMachineB() {
        if (!structureCheck(TM_STRUCTURE_B, getStartOffsetB()))
            return false;
        setMaxParallels(getParalellsB());
        return true;
    }

    /**
     * <p>Check machine shape C and sets appropriate parallel count.
     *
     * @return true if shape valid
     */
    default boolean checkMachineC() {
        if (!structureCheck(TM_STRUCTURE_C, getStartOffsetC()))
            return false;
        setMaxParallels(getParalellsC());
        return true;
    }

    /**
     * <p>Check machine shape D and sets appropriate parallel count.
     *
     * @return true if shape valid
     */
    default boolean checkMachineD() {
        if (!structureCheck(TM_STRUCTURE_D, getStartOffsetD()))
            return false;
        setMaxParallels(getParalellsD());
        return true;
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    default boolean structureBuild(String piece, Vec3Impl offset, boolean hintsOnly, ItemStack trigger) {
//        if (offset == null)
//            return false;
//        return IConstructableStructure.super.structureBuild(piece, offset, hintsOnly, trigger);
//    }

    /**
     * <p>Construct machine for shape A.
     *
     * @param hintsOnly the hints only switch
     * @param trigger   the trigger item
     */
    default void constructMachineA(boolean hintsOnly, ItemStack trigger) {
        buildPiece(TM_STRUCTURE_A, getStartOffsetA(), hintsOnly, trigger);
    }

    /**
     * <p>Construct machine for shape B.
     *
     * @param hintsOnly the hints only switch
     * @param trigger   the trigger item
     */
    default void constructMachineB(boolean hintsOnly, ItemStack trigger) {
        buildPiece(TM_STRUCTURE_B, getStartOffsetB(), hintsOnly, trigger);
    }

    /**
     * <p>Construct machine for shape C.
     *
     * @param hintsOnly the hints only switch
     * @param trigger   the trigger item
     */
    default void constructMachineC(boolean hintsOnly, ItemStack trigger) {
        buildPiece(TM_STRUCTURE_C, getStartOffsetC(), hintsOnly, trigger);
    }

    /**
     * <p>Construct machine for shape D.
     *
     * @param hintsOnly the hints only switch
     * @param trigger   the trigger item
     */
    default void constructMachineD(boolean hintsOnly, ItemStack trigger) {
        buildPiece(TM_STRUCTURE_D, getStartOffsetD(), hintsOnly, trigger);
    }
}
