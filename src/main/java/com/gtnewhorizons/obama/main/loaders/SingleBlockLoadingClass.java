/*
 * Copyright 2021 The GTNH Team
 * 
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */

package com.gtnewhorizons.obama.main.loaders;

import com.gtnewhorizons.obama.main.ObamaMod;
import com.gtnewhorizons.obama.main.config.ConfigHandler;
import com.gtnewhorizons.obama.main.tileentities.single.generators.GT_MetaTileEntity_SemiFluidGenerator;
import com.gtnewhorizons.obama.main.tileentities.single.generators.GT_MetaTileEntity_ThermalGenerator;
import com.gtnewhorizons.obama.main.tileentities.single.generators.GT_MetaTileEntity_TieredBoiler;
import com.gtnewhorizons.obama.main.tileentities.single.misc.GT_MetaTileEntity_SteamConverter;
import com.gtnewhorizons.obama.main.tileentities.single.storage.GT_MetaTileEntity_TieredTank;
import com.gtnewhorizons.obama.main.tileentities.single.storage.GT_MetaTileEntity_TiredChest;
import com.gtnewhorizons.obama.main.utils.ObamaRecipes;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_GT_Recipe;
import net.minecraft.item.ItemStack;

import static com.gtnewhorizons.obama.main.CommonValues.MACHINE_PREFIXES;
import static com.gtnewhorizons.obama.main.CommonValues.ROMAN_LETTERS;
import static gregtech.api.enums.GT_Values.VN;
import static gregtech.api.enums.GT_Values.VOLTAGE_NAMES;

@SuppressWarnings("ALL")
public class SingleBlockLoadingClass {

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
    //Helper Name Arrays
    public static ItemStack[] GTTieredBoilers = new ItemStack[VN.length];
    public static ItemStack[] GTBasicChests = new ItemStack[VN.length];
    public static ItemStack[] SimpleWashing = new ItemStack[VN.length];
    public static ItemStack[] SemiFluidGenerator = new ItemStack[3];
    public static ItemStack[] ThermalGenerator = new ItemStack[3];
    //Machines
    public static ItemStack[] GTBasicTanks = new ItemStack[VN.length];
    public static ItemStack[] EnhancedMixer = new ItemStack[VN.length];
    private SingleBlockLoadingClass() {
    }

    public static void load() {
        try {
            loadOverrideIDs();
            loadNewIDs();
        } catch (Exception e) {
            ObamaMod.LOGGER.catching(e);
        }
    }

    //TODO: Textures for the Simple Washer & own GUI Texture
    public static void loadOverrideIDs() throws Exception {
        for (int i = 0; i < 4; i++) {
            GTBasicTanks[i] = new GT_MetaTileEntity_TieredTank(
                    817 + i,
                    "Obama.basic.tank." + i,
                    VOLTAGE_NAMES[i] + " Fluid Tank",
                    i
            ).getStackForm(1L);

            SimpleWashing[1 + i] = new GT_MetaTileEntity_BasicMachine_GT_Recipe(
                31017 + i,
                "Obama.simple.washer." + (1 + i),
                "Simple Washer " + ROMAN_LETTERS[1 + i],
                1 + i,
                "",
                ObamaRecipes.getSimpleWasherMap(),
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
                        "Obama.tiered.boiler." + i,
                        VOLTAGE_NAMES[i] + " Boiler",
                        i
                ).getStackForm(1L);
        }

        SimpleWashing[0] = new GT_MetaTileEntity_BasicMachine_GT_Recipe(
            767,
            "Obama.simple.washer.0",
            "Simple Washer " + ROMAN_LETTERS[0],
            0,
            "",
            ObamaRecipes.getSimpleWasherMap(),
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
                    "Obama.generator.semifluid." + i + 1,
                    MACHINE_PREFIXES[i + 1] + " Semi-Fluid Generator",
                    i + 1,
                    "Burns all the impure Fuels! Why? Cause f*** the enviroment!"
            ).getStackForm(1L);
            ThermalGenerator[i] = new GT_MetaTileEntity_ThermalGenerator(
                    830 + i,
                    "Obama.generator.thermal." + i + 1,
                    MACHINE_PREFIXES[i + 1] + " Thermal Generator",
                    i + 4,
                    "Cools down hot liquids and makes power."
            ).getStackForm(1L);
        }

        for (int i = 0; i < 5; i++) {
            GTBasicChests[i] = new GT_MetaTileEntity_TiredChest(946 + i,
                    "Obama.basic.chest." + i,
                    VOLTAGE_NAMES[i] + " Chest",
                    i
            ).getStackForm(1L);
        }

        for (int i = 0; i < 8; i++) {
            EnhancedMixer[i] = new GT_MetaTileEntity_BasicMachine_GT_Recipe(
                851 + i,
                "Obama.enhanced.mixer." + i,
                "Enhanced Mixer " + ROMAN_LETTERS[i], i + 1,
                "Mixing stuff at a whole new Level",
                ObamaRecipes.getEnhancedMixerMap(),
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
        new GT_MetaTileEntity_SteamConverter(769, "SteamConderserShitWhatever", "SteamConderserShitWhatever", 0, 3, "");
    }

    public static void loadNewIDs() throws Exception {
        for (int i = 5; i < VN.length; i++) {
            SimpleWashing[i] = new GT_MetaTileEntity_BasicMachine_GT_Recipe(
                ConfigHandler.IDOFFSET + 20 + i,
                "Obama.simple.washer." + (i),
                "Simple Washer " + ROMAN_LETTERS[i],
                i, "",
                ObamaRecipes.getSimpleWasherMap(),
                1, 1,
                8000 * i,
                0, 0,
                "OreWasher.png", "",
                false, false,
                0, "ORE_WASHER",
                SIMPLEWASHERRECIPE.clone()
            ).getStackForm(1);

            GTBasicChests[i] = new GT_MetaTileEntity_TiredChest(
                    ConfigHandler.IDOFFSET + 31 + i,
                    "Obama.basic.chest." + i,
                    VOLTAGE_NAMES[i] + " Chest",
                    i
            ).getStackForm(1L);
        }

        for (int i = 4; i < VN.length; i++) {
            GTBasicTanks[i] = new GT_MetaTileEntity_TieredTank(
                    ConfigHandler.IDOFFSET - 4 + i,
                    "Obama.basic.tank." + i,
                    VOLTAGE_NAMES[i] + " Fluid Tank",
                    i
            ).getStackForm(1L);
            GTTieredBoilers[i] = new GT_MetaTileEntity_TieredBoiler(
                    ConfigHandler.IDOFFSET + 9 + i,
                    "Obama.tiered.boiler." + i,
                    VOLTAGE_NAMES[i] + " Boiler",
                    i
            ).getStackForm(1L);
        }

        GTTieredBoilers[0] = new GT_MetaTileEntity_TieredBoiler(
                ConfigHandler.IDOFFSET + 12,
                "Obama.tiered.boiler.0",
                VOLTAGE_NAMES[0] + " Boiler",
                0
        ).getStackForm(1L);
    }
}