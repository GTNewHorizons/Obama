/*
 * Copyright 2020 The GTNH Team
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

package com.gtnewhorizons.gtppnt.main.loaders;

import com.gtnewhorizons.gtppnt.main.GTAFMod;
import com.gtnewhorizons.gtppnt.main.config.ConfigHandler;
import com.gtnewhorizons.gtppnt.main.tileentites.single.generators.GT_MetaTileEntity_SemiFluidGenerator;
import com.gtnewhorizons.gtppnt.main.tileentites.single.generators.GT_MetaTileEntity_ThermalGenerator;
import com.gtnewhorizons.gtppnt.main.tileentites.single.generators.GT_MetaTileEntity_TieredBoiler;
import com.gtnewhorizons.gtppnt.main.tileentites.single.storage.GT_MetaTileEntity_TieredTank;
import com.gtnewhorizons.gtppnt.main.tileentites.single.storage.GT_MetaTileEntity_TiredChest;
import com.gtnewhorizons.gtppnt.main.utils.GTAFRecipes;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_GT_Recipe;
import net.minecraft.item.ItemStack;

import static gregtech.api.enums.GT_Values.VN;
import static gregtech.api.enums.GT_Values.VOLTAGE_NAMES;

@SuppressWarnings("ALL")
public class SingleBlockLoadingClass {

    //Helper Name Arrays
    private static final String[] MACHINE_PREFIXES = {"Primitive", "Basic", "Advanced", "Turbo", "Special", "Insane", "Uber", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
    public static ItemStack[] GTTieredBoilers = new ItemStack[VN.length];
    public static ItemStack[] GTBasicChests = new ItemStack[VN.length];
    public static ItemStack[] SimpleWashing = new ItemStack[VN.length];
    private static final String[] ROMAN_LETTERS = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
    public static ItemStack[] SemiFluidGenerator = new ItemStack[3];
    public static ItemStack[] ThermalGenerator = new ItemStack[3];
    //Recipe Object Arrays
    private static final Object[] SIMPLEWASHERRECIPE = new Object[]{
            "LPL",
            "CHC",
            "LXL",
            'H', GT_MetaTileEntity_BasicMachine_GT_Recipe.X.HULL,
            'P', GT_MetaTileEntity_BasicMachine_GT_Recipe.X.PUMP,
            'L', GT_MetaTileEntity_BasicMachine_GT_Recipe.X.PLATE,
            'X', GT_MetaTileEntity_BasicMachine_GT_Recipe.X.PIPE,
            'C', GT_MetaTileEntity_BasicMachine_GT_Recipe.X.CIRCUIT};
    //Machines
    public static ItemStack[] GTBasicTanks = new ItemStack[VN.length];
    public static ItemStack[] EnhancedMixer = new ItemStack[VN.length];

    public static void load() {
        try {
            loadOverrideIDs();
            loadNewIDs();
        } catch (Exception e) {
            GTAFMod.LOGGER.catching(e);
        }
    }

    //TODO: Textures for the Simple Washer & own GUI Texture
    public static void loadOverrideIDs() throws Exception {
        for (int i = 0; i < 4; i++) {
            GTBasicTanks[i] = new GT_MetaTileEntity_TieredTank(
                    817 + i,
                    "GTAF.basic.tank." + i,
                    VOLTAGE_NAMES[i] + " Fluid Tank",
                    i
            ).getStackForm(1L);

            SimpleWashing[1 + i] = new GT_MetaTileEntity_BasicMachine_GT_Recipe(
                    31017 + i,
                    "GTAF.simple.washer." + (1 + i),
                    "Simple Washer " + ROMAN_LETTERS[1 + i],
                    1 + i,
                    "",
                    GTAFRecipes.SIMPLE_WASHER_MAP,
                    1, 1,
                    8000 * (1 + i),
                    0, 0,
                    "OreWasher.png",
                    "",
                    false, false,
                    0, "ORE_WASHER",
                    SIMPLEWASHERRECIPE.clone()
            ).getStackForm(1);

            if (i != 0)
                GTTieredBoilers[i] = new GT_MetaTileEntity_TieredBoiler(
                        752 + i,
                        "GTAF.tiered.boiler." + i,
                        VOLTAGE_NAMES[i] + " Boiler",
                        i
                ).getStackForm(1L);
        }

        SimpleWashing[0] = new GT_MetaTileEntity_BasicMachine_GT_Recipe(
                767,
                "GTAF.simple.washer.0",
                "Simple Washer " + ROMAN_LETTERS[0],
                0,
                "",
                GTAFRecipes.SIMPLE_WASHER_MAP,
                1, 1,
                8000,
                0, 1,
                "OreWasher.png", "",
                false, false,
                0, "ORE_WASHER", SIMPLEWASHERRECIPE.clone()
        ).getStackForm(1);

        for (int i = 0; i < 3; i++) {
            SemiFluidGenerator[i] = new GT_MetaTileEntity_SemiFluidGenerator(
                    837 + i,
                    "GTAF.generator.semifluid." + i + 1,
                    MACHINE_PREFIXES[i + 1] + " Semi-Fluid Generator",
                    i + 1,
                    "Burns all the impure Fuels! Why? Cause f*** the enviroment!"
            ).getStackForm(1L);
            ThermalGenerator[i] = new GT_MetaTileEntity_ThermalGenerator(
                    830 + i,
                    "GTAF.generator.thermal." + i + 1,
                    MACHINE_PREFIXES[i + 1] + " Thermal Generator",
                    i + 4,
                    "Cools down hot liquids and makes power."
            ).getStackForm(1L);
        }

        for (int i = 0; i < 5; i++) {
            GTBasicChests[i] = new GT_MetaTileEntity_TiredChest(946 + i,
                    "GTAF.basic.chest." + i,
                    VOLTAGE_NAMES[i] + " Chest",
                    i
            ).getStackForm(1L);
        }

        for (int i = 0; i < 8; i++) {
            EnhancedMixer[i] = new GT_MetaTileEntity_BasicMachine_GT_Recipe(
                    851 + i,
                    "GTAF.enhanced.mixer." + i,
                    "Enhanced Mixer " + ROMAN_LETTERS[i], i + 1,
                    "Mixing stuff at a whole new Level",
                    GTAFRecipes.ENHANCED_MIXER_MAP,
                    4, 4,
                    8000 * i,
                    0, 1,
                    "Mixer.png",
                    "",
                    false, false,
                    0, "MIXER",
                    new Object[]{
                            "GRG",
                            "CEC",
                            "CMC",
                            'M', GT_MetaTileEntity_BasicMachine_GT_Recipe.X.HULL,
                            'E', GT_MetaTileEntity_BasicMachine_GT_Recipe.X.MOTOR,
                            'R', GT_MetaTileEntity_BasicMachine_GT_Recipe.X.ROTOR,
                            'C', GT_MetaTileEntity_BasicMachine_GT_Recipe.X.CIRCUIT,
                            'G', GT_MetaTileEntity_BasicMachine_GT_Recipe.X.GLASS}
            ).getStackForm(1L);
        }
    }

    public static void loadNewIDs() throws Exception {
        for (int i = 5; i < VN.length; i++) {
            SimpleWashing[i] = new GT_MetaTileEntity_BasicMachine_GT_Recipe(
                    ConfigHandler.IDOFFSET + 20 + i,
                    "GTAF.simple.washer." + (i),
                    "Simple Washer " + ROMAN_LETTERS[i],
                    i, "",
                    GTAFRecipes.SIMPLE_WASHER_MAP,
                    1, 1,
                    8000 * i,
                    0, 0,
                    "OreWasher.png", "",
                    false, false,
                    0, "ORE_WASHER",
                    SIMPLEWASHERRECIPE.clone()
            ).getStackForm(1);

            GTBasicChests[i] = new GT_MetaTileEntity_TiredChest(
                    ConfigHandler.IDOFFSET + 32 + i,
                    "GTAF.basic.chest." + i,
                    VOLTAGE_NAMES[i] + " Chest",
                    i
            ).getStackForm(1L);
        }

        for (int i = 4; i < VN.length; i++) {
            GTBasicTanks[i] = new GT_MetaTileEntity_TieredTank(
                    ConfigHandler.IDOFFSET - 4 + i,
                    "GTAF.basic.tank." + i,
                    VOLTAGE_NAMES[i] + " Fluid Tank",
                    i
            ).getStackForm(1L);
            GTTieredBoilers[i] = new GT_MetaTileEntity_TieredBoiler(
                    ConfigHandler.IDOFFSET + 9 + i,
                    "GTAF.tiered.boiler." + i,
                    VOLTAGE_NAMES[i] + " Boiler",
                    i
            ).getStackForm(1L);
        }

        GTTieredBoilers[0] = new GT_MetaTileEntity_TieredBoiler(
                ConfigHandler.IDOFFSET + 12,
                "GTAF.tiered.boiler.0",
                VOLTAGE_NAMES[0] + " Boiler",
                0
        ).getStackForm(1L);
    }
}