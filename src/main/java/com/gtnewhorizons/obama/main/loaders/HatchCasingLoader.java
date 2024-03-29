package com.gtnewhorizons.obama.main.loaders;

import com.gtnewhorizons.obama.main.ObamaMod;
import com.gtnewhorizons.obama.main.items.CustomItemList;
import com.gtnewhorizons.obama.main.tileentities.single.hatches.Obama_MetaTileEntity_HatchCasing;
import com.gtnewhorizons.obama.main.tileentities.single.hatches.Obama_MetaTileEntity_Hatch_Catalytic_Muffler;
import com.gtnewhorizons.obama.main.tileentities.single.hatches.definition.CasingFunction;

import static com.gtnewhorizons.obama.main.items.CustomItemList.ARM_CASING_HV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.ARM_CASING_LV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.ARM_CASING_MV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.CATALYTIC_MUFFLER_EV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.CATALYTIC_MUFFLER_IV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.CATALYTIC_MUFFLER_LuV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.CATALYTIC_MUFFLER_UV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.CATALYTIC_MUFFLER_ZPM;
import static com.gtnewhorizons.obama.main.items.CustomItemList.CIRCUIT_CASING_HV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.CIRCUIT_CASING_LV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.CIRCUIT_CASING_MV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.CONVEYOR_CASING_HV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.CONVEYOR_CASING_LV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.CONVEYOR_CASING_MV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.EMITTER_CASING_HV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.EMITTER_CASING_LV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.EMITTER_CASING_MV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.FILTER_HV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.FILTER_LV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.FILTER_MV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.GRINDING_CASING_HV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.GRINDING_CASING_LV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.GRINDING_CASING_MV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.MOTOR_CASING_HV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.MOTOR_CASING_LV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.MOTOR_CASING_MV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.PISTON_CASING_HV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.PISTON_CASING_LV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.PISTON_CASING_MV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.PUMP_CASING_HV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.PUMP_CASING_LV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.PUMP_CASING_MV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.ROTOR_CASING_HV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.ROTOR_CASING_LV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.ROTOR_CASING_MV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.WIRE_HV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.WIRE_LV;
import static com.gtnewhorizons.obama.main.items.CustomItemList.WIRE_MV;

public class HatchCasingLoader {
    private HatchCasingLoader() {
    }

    public static void load() {
        try {
            loadCasings();
        } catch (Exception e) {
            ObamaMod.LOGGER.catching(e);
        }
    }

    //Uses ID Range of 31071 to 31100
    //TODO change IDs to use the ones from alk
    private static void loadCasings() {
        int aID = 31071;

        aID = setMultipleCasingTiered(CasingFunction.GRINDING, aID, GRINDING_CASING_LV, GRINDING_CASING_MV, GRINDING_CASING_HV);
        aID = setMultipleCasingTiered(CasingFunction.PISTON, aID, PISTON_CASING_LV, PISTON_CASING_MV, PISTON_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.MOTOR, aID, MOTOR_CASING_LV, MOTOR_CASING_MV, MOTOR_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.CIRCUIT, aID, CIRCUIT_CASING_LV, CIRCUIT_CASING_MV, CIRCUIT_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.CONVEYOR, aID, CONVEYOR_CASING_LV, CONVEYOR_CASING_MV, CONVEYOR_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.PUMP, aID, PUMP_CASING_LV, PUMP_CASING_MV, PUMP_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.EMITTER, aID, EMITTER_CASING_LV, EMITTER_CASING_MV, EMITTER_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.ROTOR, aID, ROTOR_CASING_LV, ROTOR_CASING_MV, ROTOR_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.ROBOT_ARM, aID, ARM_CASING_LV, ARM_CASING_MV, ARM_CASING_HV);

        aID = setMultipleCasingTiered(CasingFunction.WIRE, aID, WIRE_LV, WIRE_MV, WIRE_HV);

        //TODO filter casing only needs 1 tier
        aID = setMultipleCasingTiered(CasingFunction.FILTER, aID, FILTER_LV, FILTER_MV, FILTER_HV);


        CATALYTIC_MUFFLER_EV.set(new Obama_MetaTileEntity_Hatch_Catalytic_Muffler(aID++, 4).getItem());
        CATALYTIC_MUFFLER_IV.set(new Obama_MetaTileEntity_Hatch_Catalytic_Muffler(aID++, 5).getItem());
        CATALYTIC_MUFFLER_LuV.set(new Obama_MetaTileEntity_Hatch_Catalytic_Muffler(aID++, 6).getItem());
        CATALYTIC_MUFFLER_ZPM.set(new Obama_MetaTileEntity_Hatch_Catalytic_Muffler(aID++, 7).getItem());
        CATALYTIC_MUFFLER_UV.set(new Obama_MetaTileEntity_Hatch_Catalytic_Muffler(aID++, 8).getItem());
    }

    private static int setMultipleCasingTiered(CasingFunction function, int aID, CustomItemList... itemList) {
        for (int i = 0; i < itemList.length; i++) {
            itemList[i].set(new Obama_MetaTileEntity_HatchCasing(aID++, function, (i + 1)).getItem());
        }
        return aID;
    }
}
