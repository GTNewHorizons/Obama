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
import com.github.bartimaeusnek.bartworks.util.Pair;
import gregtech.api.enums.Materials;

import static com.github.bartimaeusnek.bartworks.system.material.Werkstoff.Types.*;
import static com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader.*;
import static gregtech.api.enums.Materials.*;
import static gregtech.api.enums.TextureSet.*;


@SuppressWarnings("ALL")
public class MaterialsClass implements Runnable {
    private static final Werkstoff.GenerationFeatures ADD_CASINGS_ONLY = new Werkstoff.GenerationFeatures().disable().addCasings();

    /*TODO: Some examples how to create Materials (Done)
      TODO: use material range from 30000-Short.MAX_VALUE-1 (Done)
      TODO: use Hexadecimal shorts for the color. It looks nicer. (Done)
    */
    /*public static final Werkstoff AlkalineFree = new Werkstoff(
            new short[]{0xFF, 0x00, 0xFF},//color IN HEXADECIMAL!
            "Alkaline Free", //name
            "I am free of Alkalines!", //tooltip, if none is provided, it will be generated from the Contents of this Werkstoff
            new Werkstoff.Stats().setCentrifuge(true).setBlastFurnace(true).setMeltingPoint(250).setToxic(true), //chain of stuff that controls its behavior
            MIXTURE, // default stats, currently used in just a few things, basically,
            // use MIXTURE for ALLOYS and COMPOUND or BIOLOGICAL for materials that should be electrolysed.
            // this also controls a bit of the tiering in the Mixer/Centrifuge/Electrolyser/Chemreactor!
            new Werkstoff.GenerationFeatures().addMetalItems().addCraftingMetalWorkingItems().addMixerRecipes(),//this controlls the generation features, set the flag .enforceUnification() to override existing materials!
            30000,             // Meta ID, we use 30000-Short.MAX_VALUE-1
            SET_EMERALD, // GT Texture Set
            Arrays.asList(Diamond, CubicZirconia), //Ore Byeproducts as a List, 3 at Max
            //Var-args-Array of Materials/Werkstoffe that are the contents of this Werkstoff, the stuff you get from processing, in i.e. a centrifuge/a
            new Pair<>(Sodium, 1),   //contains 1 Sodium
            new Pair<>(Cadmium, 1),
            new Pair<>(Nickel, 1),
            new Pair<>(Lithium, 1),
            new Pair<>(Neon, 1)
    );*/
    // MOST of the Variables can be left out, since there are many constructors.
    // Needed are: color, defaultName, Werkstoff.Types, Werkstoff.GenerationFeatures, Meta ID, TextureSet texSet

    //https://www.globalsecurity.org/military/systems/munitions/du.htm
    //TODO (low prio) Add Stakalloy (8 Uranium, 1 Niobium, 1 Vanadium)
    public static final Werkstoff Staballoy = new Werkstoff(
            new short[]{0x56, 0x76, 0x61},
            "Staballoy",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3725).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addCasings().addMixerRecipes().enforceUnification(),
            30000,
            SET_METALLIC,
            new Pair<>(Uranium, 9),
            new Pair<>(Titanium, 1)
    );

    //http://www.matweb.com/search/DataSheet.aspx?MatGUID=95126296d0444b3f9d187b9daa828970
    //Tantalum was named after the Greek god Tantalus which was sometimes also called Atys
    //61 and 63 would be boring gameplay wise, since the change would be differing
    public static final Werkstoff Atysal = new Werkstoff(
            new short[]{0x95, 0x9B, 0xAC},
            "Atysal 60",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3300).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30001,
            SET_METALLIC,
            new Pair<>(Tantalum, 23),
            new Pair<>(Tungsten, 2)
    ).addAdditionalOreDict("Tantalloy60");

    //Ficticious alloy
    public static final Werkstoff AtysalYT = new Werkstoff(
            new short[]{0x75, 0xAA, 0x9C},
            "Atysal 61",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3305).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30002,
            SET_METALLIC,
            new Pair<>(Atysal, 1),
            new Pair<>(Titanium, 6),
            new Pair<>(Yttrium, 4)
    ).addAdditionalOreDict("Tantalloy61");

    //http://www.chemistrylearner.com/tumbaga.html
    //TumbagoMix exists to allow hand crafting
    public static final Werkstoff TumbagaMix = new Werkstoff(
            new short[]{0xEE, 0x8D, 0x09},
            "Tumbago Mix",
            new Werkstoff.Stats().setCentrifuge(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMixerRecipes(),
            30003,
            SET_SHINY,
            new Pair<>(Gold, 3),
            new Pair<>(Copper, 2)
    );

    //Same ref as above
    public static final Werkstoff Tumbaga = new Werkstoff(
            new short[]{0xEF, 0xB1, 0x10},
            "Tumbago",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(2700).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30004,
            SET_SHINY,
            new Pair<>(Gold, 7),
            new Pair<>(Copper, 3)
    ).addAdditionalOreDict("Tumbaga");

    //https://mrbcoins.com/introtopotinsofgaul.html
    public static final Werkstoff Potin = new Werkstoff(
            new short[]{0x72, 0x3F, 0x37},
            "Potin",
            new Werkstoff.Stats().setCentrifuge(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30005,
            SET_DULL,
            new Pair<>(Bronze, 2),
            new Pair<>(Lead, 2),
            new Pair<>(Tin, 1)
    );

    //http://www.matweb.com/search/datasheet.aspx?matguid=4a194f59f35a427dbc5009f043349cb5
    //Inconel alloys were first made in Hereford, thus the nickname
    //TODO add SX 300, special alloy for spaceships
    public static final Werkstoff Hereford625 = new Werkstoff(
            new short[]{0xB6, 0xCC, 0xE4},
            "Hereford 625",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(2700).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30006,
            SET_METALLIC,
            new Pair<>(Nichrome, 13),
            new Pair<>(Molybdenum, 10),
            new Pair<>(Invar, 10),
            new Pair<>(Chrome, 7),
            new Pair<>(Nickel, 3)
    ).addAdditionalOreDict("Inconel625");

    //http://www.matweb.com/search/DataSheet.aspx?MatGUID=3542755925f74f549bec41d8b3162d0c
    public static final Werkstoff Hereford690 = new Werkstoff(
            new short[]{0x98, 0xAA, 0xBE},
            "Hereford 690",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3700).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addCasings().addMixerRecipes().enforceUnification(),
            30007,
            SET_METALLIC,
            new Pair<>(Nichrome, 3),
            new Pair<>(Molybdenum, 2),
            new Pair<>(Niobium, 2),
            new Pair<>(Chrome, 1)
    ).addAdditionalOreDict("Inconel690");

    //Ficticious alloy
    public static final Werkstoff Hereford792 = new Werkstoff(
            new short[]{0x72, 0x91, 0xB4},
            "Hereford 792",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3700).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30008,
            SET_METALLIC,
            new Pair<>(Nickel, 2),
            new Pair<>(Titanium, 1),
            new Pair<>(Aluminium, 2),
            new Pair<>(Nichrome, 1)
    ).addAdditionalOreDict("Inconel792");

    //https://ntrs.nasa.gov/archive/nasa/casi.ntrs.nasa.gov/20130001731.pdf
    public static final Werkstoff Nitinol60 = new Werkstoff(
            new short[]{0xAA, 0xA1, 0xCF},
            "Nitinol 60",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(5925).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30009,
            SET_METALLIC,
            new Pair<>(Titanium, 3),
            new Pair<>(Nickel, 2)
    );

    //https://www.neonickel.com/alloys/duplex-stainless-steels/zeron-100-super-duplex/
    //Simple parody name
    public static final Werkstoff Rezron100 = new Werkstoff(
            new short[]{0xE2, 0xE2, 0xBD},
            "Rezron 100",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(6375).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addCasings().addMixerRecipes().enforceUnification(),
            30010,
            SET_METALLIC,
            new Pair<>(Steel, 20),
            new Pair<>(Chrome, 13),
            new Pair<>(Copper, 10),
            new Pair<>(Nickel, 3),
            new Pair<>(Molybdenum, 2),
            new Pair<>(Tungsten, 2)
    ).addAdditionalOreDict("Zeron100");

    //https://www.aircraftmaterials.com/data/nickel/C250.html
    public static final Werkstoff MaragingSteel250 = new Werkstoff(
            new short[]{0x5C, 0x64, 0xBD},
            "Maraging Steel 250",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(2685).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addCasings().addMixerRecipes().enforceUnification(),
            30011,
            SET_METALLIC,
            new Pair<>(Steel, 16),
            new Pair<>(Nickel, 4),
            new Pair<>(Cobalt, 8),
            new Pair<>(Molybdenum, 1),
            new Pair<>(Titanium, 1)
    );

    //https://www.aircraftmaterials.com/data/nickel/C300.html
    public static final Werkstoff MaragingSteel300 = new Werkstoff(
            new short[]{0x58, 0x64, 0xA9},
            "Maraging Steel 300",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(2685).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addCasings().addMixerRecipes().enforceUnification(),
            30012,
            SET_METALLIC,
            new Pair<>(Steel, 16),
            new Pair<>(Nickel, 4),
            new Pair<>(Cobalt, 8),
            new Pair<>(Aluminium, 1),
            new Pair<>(Titanium, 1)
    );

    //https://www.aircraftmaterials.com/data/nickel/C350.html
    public static final Werkstoff MaragingSteel350 = new Werkstoff(
            new short[]{0x55, 0x6F, 0xA1},
            "Maraging Steel 350",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(2685).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30013,
            SET_METALLIC,
            new Pair<>(Steel, 16),
            new Pair<>(Nickel, 4),
            new Pair<>(Cobalt, 8),
            new Pair<>(Aluminium, 1),
            new Pair<>(Molybdenum, 1)
    );

    //http://www.worldstainless.org/Files/issf/non-image-files/PDF/Euro_Inox/Tables_TechnicalProperties_EN.pdf
    //Not a particular type of steel, rather a type of stainless meant for use in water
    public static final Werkstoff AquaticSteel = new Werkstoff(
            new short[]{0xA4, 0xD3, 0xAE},
            "Aquatic Steel",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(2945).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30014,
            SET_METALLIC,
            new Pair<>(Steel, 12),
            new Pair<>(Carbon, 2),
            new Pair<>(Silicon, 2),
            new Pair<>(Aluminium, 1),
            new Pair<>(Manganese, 1),
            new Pair<>(Phosphorus, 1),
            new Pair<>(Sulfur, 1)

    ).addAdditionalOreDict("WatertightSteel");

    //https://www.azom.com/article.aspx?ArticleID=9857
    //Named after it's inventor, Elwood Haynes
    public static final Werkstoff Elwoodite = new Werkstoff(
            new short[]{0x7D, 0x6E, 0xD6},
            "Elwoodite",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(4585).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addCasings().addMixerRecipes().enforceUnification(),
            30015,
            SET_METALLIC,
            new Pair<>(Cobalt, 7),
            new Pair<>(Chrome, 7),
            new Pair<>(Manganese, 4),
            new Pair<>(Titanium, 2)
    ).addAdditionalOreDict("Stellite");

    //https://www.azom.com/article.aspx?ArticleID=1723
    //Claws and Talons, get it?
    //TODO add Cavorite (anti-grav metal and kinda a Hawken throwback)
    public static final Werkstoff Claworite = new Werkstoff(
            new short[]{0x60, 0x53, 0xDA},
            "Claworite",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3725).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30016,
            SET_METALLIC,
            new Pair<>(Cobalt, 4),
            new Pair<>(Chrome, 3),
            new Pair<>(Phosphorus, 2),
            new Pair<>(Molybdenum, 1)
    ).addAdditionalOreDict("Talonite");

    //https://www.haynesintl.com/alloys/alloy-portfolio_/High-temperature-Alloys/hastelloy-w-alloy/nominal-composition
    //Nickomol because they are Nickel/Molybdenum based
    public static final Werkstoff NickmolW = new Werkstoff(
            new short[]{0x9F, 0x9F, 0xC4},
            "Nickmol W",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3625).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30017,
            SET_METALLIC,
            new Pair<>(Nickel, 31),
            new Pair<>(Molybdenum, 12),
            new Pair<>(Chrome, 3),
            new Pair<>(Iron, 3),
            new Pair<>(Cobalt, 1)
    ).addAdditionalOreDict("HastelloyW");

    //https://www.haynesintl.com/alloys/alloy-portfolio_/High-temperature-Alloys/hastelloy-X-alloy/nominal-composition
    public static final Werkstoff NickmolX = new Werkstoff(
            new short[]{0x99, 0x92, 0xC8},
            "Nickmol X",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3625).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30018,
            SET_METALLIC,
            new Pair<>(Nickel, 24),
            new Pair<>(Chrome, 11),
            new Pair<>(Iron, 9),
            new Pair<>(Molybdenum, 4),
            new Pair<>(Manganese, 1),
            new Pair<>(Silicon, 1)
    ).addAdditionalOreDict("HastelloyX");

    //Ficticious alloy
    public static final Werkstoff NickmolY = new Werkstoff(
            new short[]{0xA0, 0xA2, 0xB7},
            "Nickmol Y",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(4625).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30019,
            SET_METALLIC,
            new Pair<>(Nickel, 15),
            new Pair<>(Molybdenum, 4),
            new Pair<>(Chrome, 2),
            new Pair<>(Titanium, 2),
            new Pair<>(Yttrium, 2)
    ).addAdditionalOreDict("HastelloyN");

    //http://haynesintl.com/docs/default-source/pdfs/new-alloy-brochures/corrosion-resistant-alloys/brochures/c-276.pdf
    public static final Werkstoff NickmolC276 = new Werkstoff(
            new short[]{0xB5, 0xA8, 0xAF},
            "Nickmol C276",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(4625).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30020,
            SET_METALLIC,
            new Pair<>(Nickel, 32),
            new Pair<>(Molybdenum, 8),
            new Pair<>(Chrome, 7),
            new Pair<>(Cobalt, 1),
            new Pair<>(Copper, 1),
            new Pair<>(Tungsten, 1)
    ).addAdditionalOreDict("HastelloyC276");

    //https://www.specialmetals.com/assets/smc/documents/alloys/incoloy/incoloy-alloy-020.pdf
    //Deroloy is a random name
    public static final Werkstoff Deroloy020 = new Werkstoff(
            new short[]{0xE3, 0xB6, 0xAA},
            "Deroloy 020",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3700).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30021,
            SET_METALLIC,
            new Pair<>(Iron, 10),
            new Pair<>(Nickel, 9),
            new Pair<>(Chrome, 5),
            new Pair<>(Copper, 1)
    ).addAdditionalOreDict("Incoloy020");

    //https://www.specialmetals.com/assets/smc/documents/alloys/incoloy/incoloy-alloy-ds.pdf
    public static final Werkstoff DeroloyDS = new Werkstoff(
            new short[]{0xAB, 0xAB, 0xCF},
            "Deroloy DS",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(3700).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30022,
            SET_METALLIC,
            new Pair<>(Iron, 23),
            new Pair<>(Chrome, 9),
            new Pair<>(Cobalt, 9),
            new Pair<>(Nickel, 9)
    ).addAdditionalOreDict("IncoloyDS");

    //http://www.matweb.com/search/datasheet.aspx?matguid=e1894961e3724a889af2b211d3c6de90&ckck=1
    //Technically Yttrium should be Yttrium Oxide
    public static final Werkstoff DeroloyMA956 = new Werkstoff(
            new short[]{0xA3, 0xAD, 0xB6},
            "Deroloy MA956",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(4700).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30023,
            SET_METALLIC,
            new Pair<>(Iron, 16),
            new Pair<>(Chrome, 5),
            new Pair<>(Aluminium, 3),
            new Pair<>(Yttrium, 1)
    ).addAdditionalOreDict("IncoloyMA956");

    //https://www.americanelements.com/tungsten-titanium-carbide-39377-63-4
    //In reality the recipe should be involving more carbon
    public static final Werkstoff TungstenTitaniumCarbide = new Werkstoff(
            new short[]{0x3E, 0x12, 0x66},
            "Tungsten Titanium Carbide",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(2700).setBlastFurnace(true),
            COMPOUND,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30025,
            SET_METALLIC,
            new Pair<>(Materials.TungstenCarbide, 7),
            new Pair<>(Titanium, 3)
    );

    //https://www.americanelements.com/silicon-carbide-409-21-2
    public static final Werkstoff SiliconCarbide = new Werkstoff(
            new short[]{0x17, 0x17, 0x2D},
            "Silicon Carbide",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(2700).setBlastFurnace(true),
            COMPOUND,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30026,
            SET_METALLIC,
            new Pair<>(Silicon, 1),
            new Pair<>(Carbon, 1)
    );

    //https://www.americanelements.com/tantalum-carbide-ta2c-12070-07-4
    //Realistically missing a Tantalum
    public static final Werkstoff TantalumCarbide = new Werkstoff(
            new short[]{0xAF, 0xAF, 0xAF},
            "Tantalum Carbide",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(2700).setBlastFurnace(true),
            COMPOUND,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30027,
            SET_METALLIC,
            new Pair<>(Tantalum, 1),
            new Pair<>(Carbon, 1)
    );

    //https://www.americanelements.com/zirconium-carbide-powder-12070-14-3
    public static final Werkstoff ZirconiumCarbide = new Werkstoff(
            new short[]{0x99, 0x99, 0x99},
            "Zirconium Carbide",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(2700).setBlastFurnace(true),
            COMPOUND,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addCasings().addMixerRecipes().enforceUnification(),
            30028,
            SET_METALLIC,
            new Pair<>(Zirconium, 1),
            new Pair<>(Carbon, 1)
    );

    //https://www.americanelements.com/niobium-carbide-powder-12069-94-2
    public static final Werkstoff NiobiumCarbide = new Werkstoff(
            new short[]{0xA7, 0xA1, 0xAD},
            "Niobium Carbide",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(2700).setBlastFurnace(true),
            COMPOUND,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30029,
            SET_METALLIC,
            new Pair<>(Niobium, 1),
            new Pair<>(Carbon, 1)
    );

    //Fictional alloy
    //Named after the github complaints text channel (may it rest in android hell)
    public static final Werkstoff Complainium = new Werkstoff(
            new short[]{0xE7, 0x9D, 0x73},
            "Complainium",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(4125).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addCasings().addMixerRecipes().enforceUnification(),
            30030,
            SET_DULL,
            new Pair<>(Carbon, 9),
            new Pair<>(Lithium, 9),
            new Pair<>(Potassium, 9),
            new Pair<>(Sulfur, 9),
            new Pair<>(Titanium, 9),
            new Pair<>(Hydrogen, 5)
    ).addAdditionalOreDict("Grisium");

    //https://patents.google.com/patent/US7537727B2/en
    public static final Werkstoff EglinSteelBaseMixture = new Werkstoff(
            new short[]{0x9B, 0x9F, 0x7D},
            "Eglin Steel Base Mixture",
            new Werkstoff.Stats().setCentrifuge(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMixerRecipes().enforceUnification(),
            30031,
            SET_METALLIC,
            new Pair<>(Invar, 5),
            new Pair<>(Iron, 4),
            new Pair<>(Kanthal, 1)
    ).addAdditionalOreDict("EglinSteelBaseCompound");

    //Same ref as above
    public static final Werkstoff EglinSteel = new Werkstoff(
            new short[]{0x60, 0x2E, 0x0D},
            "Eglin Steel",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(2700).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addCasings().addMixerRecipes().enforceUnification(),
            30032,
            SET_METALLIC,
            new Pair<>(EglinSteelBaseMixture, 10),
            new Pair<>(Silicon, 4),
            new Pair<>(Carbon, 9),
            new Pair<>(Sulfur, 9)
    );

    //Master of teallc
    public static final Werkstoff TriniumTitaniumAlloy = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Trinium Titanium Alloy",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(4025).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30033,
            SET_METALLIC,
            new Pair<>(Trinium, 3),
            new Pair<>(Titanium, 7)
    );

    //Carterium
    public static final Werkstoff TriniumNaquadahAlloy = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Trinium Naquadah Alloy",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(6775).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30034,
            SET_METALLIC,
            new Pair<>(Trinium, 5),
            new Pair<>(Naquadah, 9)
    );

    //onnelium
    public static final Werkstoff TriniumNaquadahCarbonite = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Trinium Naquadah Carbonite",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(5200).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30035,
            SET_METALLIC,
            new Pair<>(TriniumNaquadahAlloy, 9),
            new Pair<>(Carbon, 1)
    );

    //
    public static final Werkstoff TriniumReinforcedSteel = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Trinium Reinforced Steel",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(7830).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30036,
            SET_METALLIC,
            new Pair<>(MaragingSteel350, 4),
            new Pair<>(Trinium, 3),
            new Pair<>(Materials.TungstenSteel, 2),
            new Pair<>(Osmiridium, 1),
            new Pair<>(Strontium, 1)
    );

    //0lafe Compound
    public static final Werkstoff Lafium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Lafium",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(7025).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30037,
            SET_METALLIC,
            new Pair<>(NickmolY, 4),
            new Pair<>(Nickel, 4),
            new Pair<>(Aluminium, 3),
            new Pair<>(Naquadah, 2),
            new Pair<>(Tungsten, 2),
            new Pair<>(Argon, 1),
            new Pair<>(Carbon, 1),
            new Pair<>(Samarium, 1)
    );

    //Cinobi Alloy
    public static final Werkstoff CinobiteA243 = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "CinobiteA243",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(7625).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30038,
            SET_METALLIC,
            new Pair<>(Rezron100, 16),
            new Pair<>(Titanium, 12),
            new Pair<>(Osmiridium, 6),
            new Pair<>(Naquadria, 7),
            new Pair<>(Gadolinium, 5),
            new Pair<>(Aluminium, 3),
            new Pair<>(Mercury, 2),
            new Pair<>(Tin, 2)
    );

    //Piky Alloy
    public static final Werkstoff Pikyonium64B = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Pikyonium 64B",
            new Werkstoff.Stats().setCentrifuge(true).setMeltingPoint(8125).setBlastFurnace(true),
            MIXTURE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().addMixerRecipes().enforceUnification(),
            30039,
            SET_METALLIC,
            new Pair<>(Hereford792, 8),
            new Pair<>(EglinSteel, 5),
            new Pair<>(NaquadahEnriched, 4),
            new Pair<>(Materials.TungstenSteel, 4),
            new Pair<>(Cerium, 3),
            new Pair<>(Antimony, 2),
            new Pair<>(Platinum, 2),
            new Pair<>(Ytterbium, 1)
    );

    //Germanium
    public static final Werkstoff Germanium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Germanium",
            "Ge",
            new Werkstoff.Stats().setMass(73).setProtons(32),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().enforceUnification(),
            30040,
            SET_METALLIC
    );

    //Iodine
    public static final Werkstoff Iodine = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Iodine",
            "I",
            new Werkstoff.Stats().setMass(127).setProtons(53),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().enforceUnification(),
            30041,
            SET_METALLIC
    );

    //Selenium
    public static final Werkstoff Selenium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Selenium",
            "Se",
            new Werkstoff.Stats().setMass(79).setProtons(34),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().enforceUnification(),
            30042,
            SET_METALLIC
    );

    //Bromine
    public static final Werkstoff Bromine = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Bromine",
            "Br",
            new Werkstoff.Stats().setMass(80).setProtons(35),
            ELEMENT,
            new Werkstoff.GenerationFeatures().addMolten().enforceUnification(),
            30043,
            SET_METALLIC
    );

    //Hafnium
    public static final Werkstoff Hafnium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Hafnium",
            "Hf",
            new Werkstoff.Stats().setMass(178).setProtons(72).setMeltingPoint(2506).setBlastFurnace(true),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().enforceUnification(),
            30046,
            SET_METALLIC
    );

    //Thallium
    public static final Werkstoff Thallium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Thallium",
            "Tl",
            new Werkstoff.Stats().setMass(204).setProtons(81),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().enforceUnification(),
            30047,
            SET_METALLIC
    );

    //Polonium
    public static final Werkstoff Polonium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Polonium",
            "Po",
            new Werkstoff.Stats().setMass(209).setProtons(84),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30048,
            SET_METALLIC
    );

    //Astatine
    public static final Werkstoff Astatine = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Astatine",
            "At",
            new Werkstoff.Stats().setMass(210).setProtons(85),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30049,
            SET_METALLIC
    );

    //Francium
    public static final Werkstoff Francium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Francium",
            "Fr",
            new Werkstoff.Stats().setMass(223).setProtons(87),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30050,
            SET_METALLIC
    );

    //Actinium
    public static final Werkstoff Actinium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Actinium",
            "Ac",
            new Werkstoff.Stats().setMass(227).setProtons(89),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30051,
            SET_METALLIC
    );

    //Protactinium
    public static final Werkstoff Protactinium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Protactinium",
            "Pa",
            new Werkstoff.Stats().setMass(231).setProtons(91).setMeltingPoint(1841).setBlastFurnace(true),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30052,
            SET_METALLIC
    );

    //Neptunium
    public static final Werkstoff Neptunium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Neptunium",
            "Np",
            new Werkstoff.Stats().setMass(237).setProtons(93),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().addCraftingMetalWorkingItems().addSimpleMetalWorkingItems().enforceUnification(),
            30053,
            SET_METALLIC
    );

    //Curium
    public static final Werkstoff Curium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Curium",
            "Cm",
            new Werkstoff.Stats().setMass(247).setProtons(96),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30054,
            SET_METALLIC
    );

    //Berkelium
    public static final Werkstoff Berkelium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Berkelium",
            "Bk",
            new Werkstoff.Stats().setMass(247).setProtons(97),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30055,
            SET_METALLIC
    );

    //Einsteinium
    public static final Werkstoff Einsteinium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Einsteinium",
            "Es",
            new Werkstoff.Stats().setMass(252).setProtons(99),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30056,
            SET_METALLIC
    );

    //Fermium
    public static final Werkstoff Fermium = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Fermium",
            "Fm",
            new Werkstoff.Stats().setMass(257).setProtons(100),
            ELEMENT,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30057,
            SET_METALLIC
    );

    //Lithium 7
    public static final Werkstoff Lithium7 = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Lithium 7",
            "Li-7",
            new Werkstoff.Stats().setMass(7).setProtons(3),
            ISOTOPE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30058,
            SET_METALLIC
    );

    //Uranium 232
    public static final Werkstoff Uranium232 = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Uranium 232",
            "U-232",
            new Werkstoff.Stats().setMass(232).setProtons(92),
            ISOTOPE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30059,
            SET_METALLIC
    );

    //Uranium 233
    public static final Werkstoff Uranium233 = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Uranium 233",
            "U-233",
            new Werkstoff.Stats().setMass(233).setProtons(92),
            ISOTOPE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30060,
            SET_METALLIC
    );

    //Plutonium 238
    public static final Werkstoff Plutonium238 = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Plutonium 238",
            "Pu-238",
            new Werkstoff.Stats().setMass(238).setProtons(94),
            ISOTOPE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30061,
            SET_METALLIC
    );

    //Plutonium 239
    public static final Werkstoff Plutonium239 = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Plutonium 239",
            "Pu-239",
            new Werkstoff.Stats().setMass(239).setProtons(94),
            ISOTOPE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30062,
            SET_METALLIC
    );

    //Strontium 90
    public static final Werkstoff Strontium90 = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Strontium 90",
            "Sr-90",
            new Werkstoff.Stats().setMass(90).setProtons(38),
            ISOTOPE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30063,
            SET_METALLIC
    );

    //Polonium 210
    public static final Werkstoff Polonium210 = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Polonium 210",
            "Po-210",
            new Werkstoff.Stats().setMass(210).setProtons(84),
            ISOTOPE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30064,
            SET_METALLIC
    );

    //Americium241
    public static final Werkstoff Americium241 = new Werkstoff(
            new short[]{0x00, 0x00, 0x00},
            "Americium 241",
            "Am-241",
            new Werkstoff.Stats().setMass(241).setProtons(95),
            ISOTOPE,
            new Werkstoff.GenerationFeatures().onlyDust().addMolten().addMetalItems().enforceUnification(),
            30065,
            SET_METALLIC
    );

    //TODO Add colors to new materials (33~ ones with no color)
    //TODO Check Mixing recipes
    //TODO Check generated metal parts to match GTpp

    @Override
    public void run() {
        //I AM ALIVE!
        //No srsly when this gets called, the Variables init and will be automatically added to the Material Generation.
        //You can do stuff here like adding SubTags etc.
    }
}
