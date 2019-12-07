/*
 * Copyright 2019 The GTNH Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT  OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.gtnewhorizons.gtppnt.main.compat.bartworks;


import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;

import static com.github.technus.tectech.recipe.TT_recipeAdder.*;
import static com.github.bartimaeusnek.bartworks.API.BioRecipeAdder.*;
import static com.github.bartimaeusnek.bartworks.util.BWRecipes.*;
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.*;
import static gregtech.api.enums.GT_Values.*;
import static gregtech.api.enums.Materials.*;
import static gregtech.api.enums.OrePrefixes.*;

//DO NOT REMOVE THESE IMPORTS!

public class MaterialsExtraRecipeLoader {

    private static int getTimeFromWerkStoffForCentrifuge(Werkstoff werkstoff) {
        return (int) Math.max(1L, Math.abs(werkstoff.getStats().getMass() * werkstoff.getContents().getValue().size()));
    }

    private static int getEUTFromWerkStoffForCentrifuge(Werkstoff werkstoff) {
        return Math.min(4, werkstoff.getContents().getValue().size()) * 5;
    }

    public static void executeExtraRecipes() {
        RA.addCentrifugeRecipe(Tumbaga.get(dust, 10), 0, Gold.getDust(7), Copper.getDust(3), NI, NI, NI, NI, getTimeFromWerkStoffForCentrifuge(Tumbaga), getEUTFromWerkStoffForCentrifuge(Tumbaga));
        //RA.addCannerRecipe()                          adds a GT recipe
        //addResearchableAssemblylineRecipe()           adds a TT Research station recipe
        //instance.addBioLabRecipeIncubation()          adds a BW Bio Recipe
        //addBioLabRecipe()                             adds a BW Bio Recipe (API call, easier, but less options,
        //                                                                    prefered way if you dont know,
        //                                                                    what you are doing!)

    }
}
