package com.gtnewhorizons.obama.main.tileentities.single.hatches.definition;

import com.gtnewhorizons.obama.main.tileentities.single.hatches.GT_MetaTileEntity_TM_HatchCasing;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;

import java.util.Set;

public interface IFunctionalCasingMachineList {
    Set<GT_MetaTileEntity_TM_HatchCasing> getFunctionalCasings();

    default void clearFunctionalCasings() {
        getFunctionalCasings().clear();
    }

    default void functionalCasingsPreCheckMachine() {
        clearFunctionalCasings();
    }

    byte getCasingTier();

    void setCasingTier(byte casingTier);

    default boolean addFunctionalCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex, CasingFunction function) {
        if (aTileEntity == null)
            return false;

        IMetaTileEntity mte = aTileEntity.getMetaTileEntity();
        if (!(mte instanceof GT_MetaTileEntity_TM_HatchCasing))
            return false;

        GT_MetaTileEntity_TM_HatchCasing hatch = ((GT_MetaTileEntity_TM_HatchCasing) mte);
        if (hatch.function != function)
            return false;

        hatch.updateTexture(aBaseCasingIndex);
        getFunctionalCasings().add(hatch);
        return true;
    }

    default boolean addGrindingCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity, aBaseCasingIndex, CasingFunction.GRINDING);
    }

    default boolean addPistonCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity, aBaseCasingIndex, CasingFunction.PISTON);
    }

    default boolean addCircuitCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity, aBaseCasingIndex, CasingFunction.CIRCUIT);
    }

    default boolean addMotorCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity, aBaseCasingIndex, CasingFunction.MOTOR);
    }

    default boolean addConveyorCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity, aBaseCasingIndex, CasingFunction.CONVEYOR);
    }

    default boolean addPumpCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity, aBaseCasingIndex, CasingFunction.PUMP);
    }

    default boolean addEmitterCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity, aBaseCasingIndex, CasingFunction.EMITTER);
    }

    default boolean addRobotArmCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity, aBaseCasingIndex, CasingFunction.ROBOT_ARM);
    }

    default boolean addWireCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity, aBaseCasingIndex, CasingFunction.WIRE);
    }

    default boolean addFilterCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity, aBaseCasingIndex, CasingFunction.FILTER);
    }

    default boolean addRotorCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity, aBaseCasingIndex, CasingFunction.ROTOR);
    }

    default void setFunctionalCasingActivity(boolean state) {
        getFunctionalCasings().forEach(mte -> mte.getBaseMetaTileEntity().setActive(state));
    }

    default boolean functionalCasingsPostCheckMachine() {
        return checkCasingTiers();
    }

    //fixme dirty looking, clean.
    default boolean checkCasingTiers() {
        if (getFunctionalCasings().isEmpty())
            return false;

        byte tier = -1;
        for (GT_MetaTileEntity_TM_HatchCasing casing : getFunctionalCasings()) {
            if (tier == -1) {
                tier = casing.mTier;
            } else if (tier != casing.mTier) {
                tier = -1;
                break;
            }
        }

        setCasingTier(tier);
        return tier > -1;
    }
}
