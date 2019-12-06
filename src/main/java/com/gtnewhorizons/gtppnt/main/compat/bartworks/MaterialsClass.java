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
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.github.bartimaeusnek.bartworks.util.Pair;
import gregtech.api.enums.Materials;
import gregtech.api.enums.TextureSet;

import java.util.Arrays;

@SuppressWarnings("ALL")
public class MaterialsClass implements Runnable {

    /*TODO: Some examples how to create Materials
      TODO: use material range from 30000-Short.MAX_VALUE-1
      TODO: use Hexadecimal shorts for the color. It looks nicer.
    */
    public static final Werkstoff AlkalineFree = new Werkstoff(
            new short[]{0xFF, 0x00, 0xFF},//color IN HEXADECIMAL!
            "Alkaline Free", //name
            "I am free of Alkalines!", //tooltip, if none is provided, it will be generated from the Contents of this Werkstoff
            new Werkstoff.Stats().setCentrifuge(true).setBlastFurnace(true).setMeltingPoint(250).setToxic(true), //chain of stuff that controls its behavior
            Werkstoff.Types.MIXTURE, // default stats, currently used in just a few things, basically,
            // use MIXTURE for ALLOYS and COMPOUND or BIOLOGICAL for materials that should be electrolysed.
            // this also controls a bit of the tiering in the Mixer/Centrifuge/Electrolyser/Chemreactor!
            new Werkstoff.GenerationFeatures().addMetalItems().addMixerRecipes(),//this controlls the generation features, set the flag .enforceUnification() to override existing materials!
            30000,             // Meta ID, we use 30000-Short.MAX_VALUE-1
            TextureSet.SET_EMERALD, // GT Texture Set
            Arrays.asList(Materials.Diamond, WerkstoffLoader.CubicZirconia), //Ore Byeproducts as a List, 3 at Max
            //Var-args-Array of Materials/Werkstoffe that are the contents of this Werkstoff, the stuff you get from processing, in i.e. a centrifuge/a
            new Pair<>(Materials.Sodium, 1),   //contains 1 Sodium
            new Pair<>(Materials.Cadmium, 1),
            new Pair<>(Materials.Nickel, 1),
            new Pair<>(Materials.Lithium, 1),
            new Pair<>(WerkstoffLoader.Neon, 1)
    );
    // MOST of the Variables can be left out, since there are many constructors.
    // Needed are: color, defaultName, Werkstoff.Types, Werkstoff.GenerationFeatures, Meta ID, TextureSet texSet

    public static final Werkstoff Potin = new Werkstoff(
            new short[]{0xC9, 0x97, 0x81},
            "Potin",
            new Werkstoff.Stats().setCentrifuge(true),
            Werkstoff.Types.MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30001,
            TextureSet.SET_METALLIC,
            new Pair<>(Materials.Lead, 2),
            new Pair<>(Materials.Bronze, 2),
            new Pair<>(Materials.Tin, 1)
    );

    @Override
    public void run() {
        //I AM ALIVE!
        //No srsly when this gets called, the Variables init and will be automatically added to the Material Generation.
        //You can do stuff here like adding SubTags etc.
    }
}
