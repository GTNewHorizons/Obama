package com.gtnewhorizons.obama.main.tileentities.single.hatches.definition;

import static com.gtnewhorizons.obama.main.CommonValues.MACHINE_PREFIXES;

public enum CasingFunction {
    GRINDING, PISTON, MOTOR, CIRCUIT, CONVEYOR, PUMP, EMITTER, ROTOR, ROBOT_ARM, WIRE, FILTER;

    public String getLocalizedName(int aTier) {
        String name = this.name();
        name = name.charAt(0) + name.substring(1).toLowerCase();
        String prefix = MACHINE_PREFIXES[aTier];
        return prefix + " " + name + " Casing";
    }

    public String getUnlocalizedName(int aTier) {
        String name = this.name();
        return "gt.blockmachines.multimachine.obama." + name.toLowerCase() + "_casing." + aTier + ".name";
    }
}