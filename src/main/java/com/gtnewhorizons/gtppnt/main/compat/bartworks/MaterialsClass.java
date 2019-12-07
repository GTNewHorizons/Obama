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
    /*public static final Werkstoff AlkalineFree = new Werkstoff(
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
    );*/
    // MOST of the Variables can be left out, since there are many constructors.
    // Needed are: color, defaultName, Werkstoff.Types, Werkstoff.GenerationFeatures, Meta ID, TextureSet texSet

    public static final Werkstoff Staballoy = new Werkstoff(
            new short[]{0x44, 0x4B, 0x42},
            "Staballoy",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3725).setBlastFurnace(true),
            Werkstoff.Types.MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30000,
            TextureSet.SET_DULL,
            new Pair<>(Materials.Uranium, 9),
            new Pair<>(Materials.Titanium, 1)
    );

    public static final Werkstoff Tantalloy60 = new Werkstoff(
            new short[]{0xD5, 0xE7, 0xED},
            "Tantalloy 60",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3300).setBlastFurnace(true),
            Werkstoff.Types.MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30001,
            TextureSet.SET_METALLIC,
            new Pair<>(Materials.Yttrium, 23),
            new Pair<>(Materials.Tungsten, 2)
    );

    public static final Werkstoff Tantalloy61 = new Werkstoff(
            new short[]{0xC1, 0xD3, 0xD9},
            "Tantalloy 61",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3305).setBlastFurnace(true),
            Werkstoff.Types.MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30002,
            TextureSet.SET_METALLIC,
            //new Pair<>(Materials.Tantalloy_60, 1), TODO recursive loading??
            new Pair<>(Materials.Titanium, 6),
            new Pair<>(Materials.Yttrium, 4)
    );

    public static final Werkstoff TumbagaMix = new Werkstoff(
            new short[]{0xEE, 0x8C, 0x4B},
            "Tumbaga Mix",
            new Werkstoff.Stats().setCentrifuge(true),
            Werkstoff.Types.MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().enforceUnification(),
            30003,
            TextureSet.SET_METALLIC,
            new Pair<>(Materials.Gold, 2),
            new Pair<>(Materials.Copper, 1)
    );

    public static final Werkstoff Tumbaga = new Werkstoff(
            new short[]{0xFF, 0xB2, 0x0F},
            "Tumbaga",
            new Werkstoff.Stats().setCentrifuge(true),
            Werkstoff.Types.MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30004,
            TextureSet.SET_METALLIC,
            new Pair<>(Materials.Gold, 7),//TODO add Tumbaga mix hand crafting, but keep centrifuging as 7 gold 3 copper
            new Pair<>(Materials.Copper, 3)
    );

    public static final Werkstoff Potin = new Werkstoff(
            new short[]{0xC9, 0x97, 0x81},
            "Potin",
            new Werkstoff.Stats().setCentrifuge(true),
            Werkstoff.Types.MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30005,
            TextureSet.SET_DULL,
            new Pair<>(Materials.Lead, 2),
            new Pair<>(Materials.Bronze, 2),
            new Pair<>(Materials.Tin, 1)
    );

    public static final Werkstoff Inconel625 = new Werkstoff(
            new short[]{0x80, 0xC8, 0x80},
            "Inconel 625",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(2700).setBlastFurnace(true),
            Werkstoff.Types.MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30006,
            TextureSet.SET_METALLIC,
            new Pair<>(Materials.Nichrome, 13),
            new Pair<>(Materials.Molybdenum, 10),
            new Pair<>(Materials.Invar, 10),
            new Pair<>(Materials.Chrome, 7),
            new Pair<>(Materials.Nickel, 3)
    );

    public static final Werkstoff Inconel690 = new Werkstoff(
            new short[]{0x76, 0xDC, 0x8A},
            "Inconel 690",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3700).setBlastFurnace(true),
            Werkstoff.Types.MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30007,
            TextureSet.SET_METALLIC,
            new Pair<>(Materials.Nichrome, 3),
            new Pair<>(Materials.Molybdenum, 2),
            new Pair<>(Materials.Niobium, 2),
            new Pair<>(Materials.Chrome, 1)
    );

    public static final Werkstoff Inconel792 = new Werkstoff(
            new short[]{0x6C, 0xF0, 0x76},
            "Inconel 792",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3700).setBlastFurnace(true),
            Werkstoff.Types.MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30008,
            TextureSet.SET_METALLIC,
            new Pair<>(Materials.Nickel, 2),
            new Pair<>(Materials.Titanium, 1),
            new Pair<>(Materials.Aluminium, 2),
            new Pair<>(Materials.Nichrome, 1)
    );

    public static final Werkstoff Nitinol60 = new Werkstoff(
            new short[]{0xCB, 0xAE, 0xEC},
            "Nitinol 60",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(5925).setBlastFurnace(true),
            Werkstoff.Types.MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30009,
            TextureSet.SET_METALLIC,
            new Pair<>(Materials.Nickel, 3),
            new Pair<>(Materials.Titanium, 2)
    );

    @Override
    public void run() {
        //I AM ALIVE!
        //No srsly when this gets called, the Variables init and will be automatically added to the Material Generation.
        //You can do stuff here like adding SubTags etc.
    }
}
