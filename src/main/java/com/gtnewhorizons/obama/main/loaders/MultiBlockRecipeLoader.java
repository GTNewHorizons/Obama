package com.gtnewhorizons.obama.main.loaders;

import com.gtnewhorizons.obama.main.items.CustomItemList;

import static gregtech.api.enums.Materials.EnderPearl;
import static gregtech.api.enums.Materials.Glass;
import static gregtech.api.enums.Materials.IronMagnetic;
import static gregtech.api.enums.OrePrefixes.gem;
import static gregtech.api.enums.OrePrefixes.plate;
import static gregtech.api.util.GT_ModHandler.RecipeBits;
import static gregtech.api.util.GT_ModHandler.addCraftingRecipe;

public class MultiBlockRecipeLoader {

    public static void load() {
        addCraftingRecipe(CustomItemList.LARGE_CENTRIFUGE.get(1),
                RecipeBits.BUFFERED | RecipeBits.NOT_REMOVABLE,
                new Object[]{
                        "PPP",
                        "GEG",
                        "PPP",
                        'P', plate.get(IronMagnetic),
                        'G', plate.get(Glass),
                        'E', gem.get(EnderPearl)});
    }
}
