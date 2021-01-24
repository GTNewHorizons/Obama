package com.gtnewhorizons.gtppnt.main.loaders;

import com.gtnewhorizons.gtppnt.main.GTAFMod;
import com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.GT_MetaTileEntity_TM_HatchCasing;
import com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.defenition.CasingFunction;

import static com.gtnewhorizons.gtppnt.main.items.CustomItemList.*;

public class HatchCasingLoader {
    private HatchCasingLoader() {
    }

    public static void load() {
        try {
            loadCasings();
        } catch (Exception e) {
            GTAFMod.LOGGER.catching(e);
        }
    }

    //Uses ID Range of 31071 to 31100
    private static void loadCasings() {
        int aID = 31071;

        GRINDING_CASING_LV.set(new GT_MetaTileEntity_TM_HatchCasing(aID++, CasingFunction.GRINDING, 1).getItem());
        GRINDING_CASING_MV.set(new GT_MetaTileEntity_TM_HatchCasing(aID++, CasingFunction.GRINDING, 2).getItem());
        GRINDING_CASING_HV.set(new GT_MetaTileEntity_TM_HatchCasing(aID++, CasingFunction.GRINDING, 3).getItem());

        PISTON_CASING_LV.set(new GT_MetaTileEntity_TM_HatchCasing(aID++, CasingFunction.PISTON, 1).getItem());
        PISTON_CASING_MV.set(new GT_MetaTileEntity_TM_HatchCasing(aID++, CasingFunction.PISTON, 2).getItem());
        PISTON_CASING_HV.set(new GT_MetaTileEntity_TM_HatchCasing(aID++, CasingFunction.PISTON, 3).getItem());

        MOTOR_CASING_LV.set(new GT_MetaTileEntity_TM_HatchCasing(aID++, CasingFunction.MOTOR, 1).getItem());
        MOTOR_CASING_MV.set(new GT_MetaTileEntity_TM_HatchCasing(aID++, CasingFunction.MOTOR, 2).getItem());
        MOTOR_CASING_HV.set(new GT_MetaTileEntity_TM_HatchCasing(aID++, CasingFunction.MOTOR, 3).getItem());

        CIRCUIT_CASING_LV.set(new GT_MetaTileEntity_TM_HatchCasing(aID++, CasingFunction.CIRCUIT, 1).getItem());
        CIRCUIT_CASING_MV.set(new GT_MetaTileEntity_TM_HatchCasing(aID++, CasingFunction.CIRCUIT, 2).getItem());
        CIRCUIT_CASING_HV.set(new GT_MetaTileEntity_TM_HatchCasing(aID++, CasingFunction.CIRCUIT, 3).getItem());
    }
}