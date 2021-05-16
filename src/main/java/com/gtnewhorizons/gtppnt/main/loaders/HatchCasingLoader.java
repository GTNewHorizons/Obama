package com.gtnewhorizons.gtppnt.main.loaders;

import com.gtnewhorizons.gtppnt.main.GTAFMod;
import com.gtnewhorizons.gtppnt.main.items.CustomItemList;
import com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.GT_MetaTileEntity_TM_HatchCasing;
import com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.GT_MetaTileEntity_TM_Hatch_Catalytic_Muffler;
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
    //TODO change IDs to use the ones from alk
    private static void loadCasings() {
        int aID = 31071;

        aID = setMultipleCasingTiered(CasingFunction.GRINDING,aID,
                GRINDING_CASING_LV,GRINDING_CASING_MV,GRINDING_CASING_HV);
        aID = setMultipleCasingTiered(CasingFunction.PISTON,aID,
                PISTON_CASING_LV,PISTON_CASING_MV,PISTON_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.MOTOR,aID,
                MOTOR_CASING_LV,MOTOR_CASING_MV,MOTOR_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.CIRCUIT,aID,
                CIRCUIT_CASING_LV,CIRCUIT_CASING_MV,CIRCUIT_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.CONVEYOR,aID,
                CONVEYOR_CASING_LV,CONVEYOR_CASING_MV,CONVEYOR_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.PUMP,aID,
                PUMP_CASING_LV,PISTON_CASING_MV,PUMP_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.EMITTER,aID,
                EMITTER_CASING_LV,EMITTER_CASING_MV,EMITTER_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.HEATING,aID,
                HEATING_CASING_LV,HEATING_CASING_MV,HEATING_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.ARM,aID,
                ARM_CASING_LV,ARM_CASING_MV,ARM_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.WIRE,aID,
                WIRE_LV,WIRE_MV,WIRE_HV);

        //TODO filter casing only needs 1 tier
        aID = setMultipleCasingTiered(CasingFunction.FILTER,aID,
                FILTER_LV,FILTER_MV,FILTER_HV);


        CATALYTIC_MUFFLER_EV.set(new GT_MetaTileEntity_TM_Hatch_Catalytic_Muffler(aID++, 4).getItem());
        CATALYTIC_MUFFLER_IV.set(new GT_MetaTileEntity_TM_Hatch_Catalytic_Muffler(aID++, 5).getItem());
        CATALYTIC_MUFFLER_LuV.set(new GT_MetaTileEntity_TM_Hatch_Catalytic_Muffler(aID++, 6).getItem());
        CATALYTIC_MUFFLER_ZPM.set(new GT_MetaTileEntity_TM_Hatch_Catalytic_Muffler(aID++, 7).getItem());
        CATALYTIC_MUFFLER_UV.set(new GT_MetaTileEntity_TM_Hatch_Catalytic_Muffler(aID++, 8).getItem());
    }

    private static int setMultipleCasingTiered(CasingFunction function,int aID,CustomItemList... itemList) {
        for (int i = 0;i<itemList.length;i++) {
            itemList[i].set(new GT_MetaTileEntity_TM_HatchCasing(aID++,function,(i+1)).getItem());
        }
        return aID;
    }
}
