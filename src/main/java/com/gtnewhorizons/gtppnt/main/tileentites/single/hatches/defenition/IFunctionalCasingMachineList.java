package com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.defenition;

import com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.GT_MetaTileEntity_TM_HatchCasing;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;

import java.util.Set;

public interface IFunctionalCasingMachineList {
    Set<GT_MetaTileEntity_TM_HatchCasing> getFunctionalCasings();

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
        return getFunctionalCasings().add(hatch);
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

    default void setFunctionalCasingActivity(boolean state) {
        getFunctionalCasings().forEach(mte -> mte.getBaseMetaTileEntity().setActive(state));
    }

    default boolean checkCasingTiers() {
        boolean tierMatchingCasings = true;
        byte tier = -1;
        for (GT_MetaTileEntity_TM_HatchCasing casing : getFunctionalCasings()) {
            if (tier == -1) {
                tier = casing.mTier;
            } else if (tier != casing.mTier) {
                tierMatchingCasings = false;
                break;
            }
        }
        setCasingTier(tier);
        return tierMatchingCasings;
    }
}
