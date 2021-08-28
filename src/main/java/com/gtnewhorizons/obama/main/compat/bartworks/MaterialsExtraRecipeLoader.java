/*
 * Copyright 2021 The GTNH Team
 *
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */


package com.gtnewhorizons.obama.main.compat.bartworks;


import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;
import com.gtnewhorizons.obama.main.ObamaMod;

import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.Tumbaga;
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.TumbagaMix;
import static gregtech.api.enums.Materials.Copper;
import static gregtech.api.enums.Materials.Gold;
import static gregtech.api.enums.Materials.RoseGold;
import static gregtech.api.enums.OrePrefixes.dust;
import static gregtech.api.util.GT_ModHandler.RecipeBits;
import static gregtech.api.util.GT_ModHandler.addShapelessCraftingRecipe;

//DO NOT REMOVE THESE IMPORTS!

public class MaterialsExtraRecipeLoader {
    public static void load() {
        try {
            executeExtraRecipes();
        } catch (Exception e) {
            ObamaMod.LOGGER.catching(e);
        }
    }

    private static int getTimeFromWerkStoffForCentrifuge(Werkstoff werkstoff) {
        return (int) Math.max(1L, Math.abs(werkstoff.getStats().getMass() * werkstoff.getContents().getValue().size()));
    }

    private static int getEUTFromWerkStoffForCentrifuge(Werkstoff werkstoff) {
        return Math.min(4, werkstoff.getContents().getValue().size()) * 5;
    }

    private static void executeExtraRecipes() {
        //RA.addCentrifugeRecipe(Tumbaga.get(dust, 10), 0, Gold.getDust(7), Copper.getDust(3), NI, NI, NI, NI, getTimeFromWerkStoffForCentrifuge(Tumbaga), getEUTFromWerkStoffForCentrifuge(Tumbaga));
        //RA.addCannerRecipe()                          adds a GT recipe
        //addResearchableAssemblylineRecipe()           adds a TT Research station recipe
        //instance.addBioLabRecipeIncubation()          adds a BW Bio Recipe
        //addBioLabRecipe()                             adds a BW Bio Recipe (API call, easier, but less options,
        //                                                                    prefered way if you dont know,
        //                                                                    what you are doing!)

        //TU
        addShapelessCraftingRecipe(TumbagaMix.get(dust, 5),
                RecipeBits.BUFFERED | RecipeBits.NOT_REMOVABLE,
                new Object[]{Gold.getDust(1), Gold.getDust(1), Gold.getDust(1), Copper.getDust(1), Copper.getDust(1)});// 3 Gold, 2 Copper

        addShapelessCraftingRecipe(Tumbaga.get(dust, 2),
                RecipeBits.BUFFERED | RecipeBits.NOT_REMOVABLE,
                new Object[]{TumbagaMix.get(dust, 1), RoseGold.getDust(1)});

        addShapelessCraftingRecipe(Tumbaga.get(dust, 8),
                RecipeBits.BUFFERED | RecipeBits.NOT_REMOVABLE,
                new Object[]{TumbagaMix.get(dust, 4), RoseGold.getDust(4)});
    }
}
