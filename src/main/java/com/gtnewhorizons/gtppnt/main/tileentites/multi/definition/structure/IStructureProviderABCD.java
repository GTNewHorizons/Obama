package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure;

import com.github.technus.tectech.util.Vec3Impl;
import net.minecraft.item.ItemStack;

public interface IStructureProviderABCD extends IStructureProviderBase {
    String TM_STRUCTURE_A = "A";
    String TM_STRUCTURE_B = "B";
    String TM_STRUCTURE_C = "C";
    String TM_STRUCTURE_D = "D";

    Vec3Impl getStartOffsetA();

    default Vec3Impl getStartOffsetB() {
        return null;
    }

    default Vec3Impl getStartOffsetC() {
        return null;
    }

    default Vec3Impl getStartOffsetD() {
        return null;
    }

    @Override
    default int getMaxParalells() {
        return getParalellsABCD();
    }

    void setParalellsABCD(int paralells);

    int getParalellsABCD();

    int getParalellsA();

    default int getParalellsB() {
        return getParalellsA();
    }

    default int getParalellsC() {
        return getParalellsA();
    }

    default int getParalellsD() {
        return getParalellsA();
    }

    default boolean checkMachineABCD(String piece, Vec3Impl offset) {
        if (offset == null)
            return false;
        return structureCheck_TM(piece, offset);
    }

    default boolean checkMachineA() {
        if (!checkMachineABCD(TM_STRUCTURE_A, getStartOffsetA()))
            return false;
        setParalellsABCD(getParalellsA());
        return true;
    }

    default boolean checkMachineB() {
        if (!checkMachineABCD(TM_STRUCTURE_B, getStartOffsetB()))
            return false;
        setParalellsABCD(getParalellsB());
        return true;
    }

    default boolean checkMachineC() {
        if (!checkMachineABCD(TM_STRUCTURE_C, getStartOffsetC()))
            return false;
        setParalellsABCD(getParalellsC());
        return true;
    }

    default boolean checkMachineD() {
        if (!checkMachineABCD(TM_STRUCTURE_D, getStartOffsetD()))
            return false;
        setParalellsABCD(getParalellsD());
        return true;
    }

    default void constructMachineLetterABCD(String piece, Vec3Impl offset, boolean hintsOnly, ItemStack trigger) {
        if (offset == null)
            return;
        structureBuild_TM(piece, offset, hintsOnly, trigger);
    }

    default void constructMachineA(boolean hintsOnly, ItemStack trigger) {
        constructMachineLetterABCD(TM_STRUCTURE_A, getStartOffsetA(), hintsOnly, trigger);
    }

    default void constructMachineB(boolean hintsOnly, ItemStack trigger) {
        constructMachineLetterABCD(TM_STRUCTURE_B, getStartOffsetB(), hintsOnly, trigger);
    }

    default void constructMachineC(boolean hintsOnly, ItemStack trigger) {
        constructMachineLetterABCD(TM_STRUCTURE_C, getStartOffsetC(), hintsOnly, trigger);
    }

    default void constructMachineD(boolean hintsOnly, ItemStack trigger) {
        constructMachineLetterABCD(TM_STRUCTURE_D, getStartOffsetD(), hintsOnly, trigger);
    }
}
