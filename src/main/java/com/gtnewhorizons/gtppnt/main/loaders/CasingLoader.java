package com.gtnewhorizons.gtppnt.main.loaders;

import com.gtnewhorizons.gtppnt.main.GTAFMod;
import com.gtnewhorizons.gtppnt.main.blocks.GT_Block_CasingDT0;
import com.gtnewhorizons.gtppnt.main.blocks.GT_Block_CasingDT1;
import com.gtnewhorizons.gtppnt.main.items.GTppnt_Container_Casings;

public class CasingLoader {

    public static void load() {
        try {
            loadNewCasings();
        } catch (Exception e) {
            GTAFMod.LOGGER.catching(e);
        }
    }

    private CasingLoader() {
    }

    public static void loadNewCasings() {
        GTppnt_Container_Casings.sBlockCasingsDT0 = new GT_Block_CasingDT0();
        GTppnt_Container_Casings.sBlockCasingsDT1 = new GT_Block_CasingDT1();
    }
}
