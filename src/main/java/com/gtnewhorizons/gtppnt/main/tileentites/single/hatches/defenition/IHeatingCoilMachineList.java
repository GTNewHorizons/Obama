package com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.defenition;

import com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.GT_MetaTileEntity_TM_HatchCasing;
import com.gtnewhorizons.gtppnt.main.utils.IAddsBlocks;
import gregtech.api.enums.HeatingCoilLevel;
import gregtech.api.interfaces.IHeatingCoil;
import net.minecraft.block.Block;

import java.util.Set;

public interface IHeatingCoilMachineList {

    int getCoilTier();
    void setCoilTier(int tier);

    default void heatingCoilPreCheck() {
        setCoilTier(-1);
    }

    default boolean heatingCoilPostCheck(int wantedTier) {
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
            if (tier != coilTier) {
                return false;
            }
        }
        return true;
    }
}
