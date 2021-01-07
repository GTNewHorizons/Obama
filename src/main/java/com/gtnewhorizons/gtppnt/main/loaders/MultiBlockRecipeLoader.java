package com.gtnewhorizons.gtppnt.main.loaders;

import com.gtnewhorizons.gtppnt.main.items.CustomItemList;
import static gregtech.api.enums.Materials.*;
import static gregtech.api.enums.OrePrefixes.*;
import static gregtech.api.util.GT_ModHandler.*;

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
