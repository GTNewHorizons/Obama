package com.gtnewhorizons.obama.main.tileentities.single.hatches.definition;

import gregtech.api.interfaces.IHeatingCoil;
import net.minecraft.block.Block;

public interface IHeatingCoilMachineList {
    int getCoilTier();

    void setCoilTier(int tier);

    default void heatingCoilPreCheckMachine() {
        setCoilTier(-1);
    }

    default boolean heatingCoilPostCheckMachine(int wantedTier) {
        if (getCoilTier() == -1)
            return true;
        return wantedTier == getCoilTier();
    }

    default boolean addCoilToMachineList(Block block, Integer meta) {
        if (!(block instanceof IHeatingCoil))
            return false;

        IHeatingCoil coil = (IHeatingCoil) block;
        int tier = getCoilTier();
        int coilTier = coil.getCoilHeat(meta).getTier() + 1; // for soem reason the tier given is tier - 1 so for HV it gives 2 back

        if (tier < 0) {
            tier = coilTier;
            setCoilTier(tier);
        } else {
            return tier == coilTier;
        }
        return true;
    }
}
