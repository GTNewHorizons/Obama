/*
 * Copyright 2021 The GTNH Team
 * 
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */

package com.gtnewhorizons.obama.main.loaders;

import com.gtnewhorizons.obama.main.ObamaMod;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Assembler;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Autoclave;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Bender;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Centrifuge;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Chemical_Bath;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Compressor;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Cutting_Machine;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Electrolyzer;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Extruder;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Fluid_Heater;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Forming_Press;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Laser_Engraver;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Lathe;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Macerator;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Mixer;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Ore_Washer;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Packager;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Sifter;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Thermal_Centrifuge;
import com.gtnewhorizons.obama.main.tileentities.multi.instances.Obama_MetaTileEntity_Large_Wiremill;

import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_ASSEMBLING_MACHINE;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_AUTOCLAVE;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_BENDING_MACHINE;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_CENTRIFUGE;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_CHEMICAL_BATH;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_COMPRESSOR;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_CUTTING_MACHINE;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_ELECTROLYZER;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_EXTRUDER;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_FLUID_HEATER;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_FORMING_PRESS;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_LATHE;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_MACERATOR;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_MIXER;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_ORE_WASHING_PLANT;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_PACKAGER;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_PRECISION_LASER_ENGRAVER;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_SIFTING_MACHINE;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_THERMAL_CENTRIFUGE;
import static com.gtnewhorizons.obama.main.items.CustomItemList.LARGE_WIREMILL;

public class MultiBlockLoader {
    private MultiBlockLoader() {
    }

    public static void load() {
        try {
            loadOverrideIDs();
            loadNewIDs();
        } catch (Exception e) {
            ObamaMod.LOGGER.catching(e);
        }
    }

    // NOTE: aNameRegional is overwritten by the *.lang files
    // Change both if you wish to make edits to the names!
    // Uses ID Range of 31021 to 31070
    private static void loadNewIDs() {
        //TODO Remove any ID system that uses increments and decide on solid ID ranges.
        int aID = 31021;

        //Replaces part of Industrial Material Press
        LARGE_FORMING_PRESS.set(new Obama_MetaTileEntity_Large_Forming_Press(aID++).getItem());

        //Replaces part of Large Washing Plant
        LARGE_CHEMICAL_BATH.set(new Obama_MetaTileEntity_Large_Chemical_Bath(aID++).getItem());

//        //Replaces part of High Current Industrial Arc Furnace
//        LARGE_PLASMA_ARC_FURNACE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
//                aID++,
//                "multimachine.obama.large_plasma_arc_furnace",
//                "Large Plasma Arc Furnace",
//                MultiBlockDefinition.LARGE_PLASMA_ARC_FURNACE
//        ).getStackForm(1L));
//
//        //Replaces part of Cutting Factory Controller
//        LARGE_SLICING_MACHINE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
//                aID++,
//                "multimachine.obama.large_slicing_machine",
//                "Large Slicing Machine",
//                MultiBlockDefinition.LARGE_SLICING_MACHINE
//        ).getStackForm(1L));

        LARGE_COMPRESSOR.set(new Obama_MetaTileEntity_Large_Compressor(aID++).getItem());

        //Replaces part of Large Processing Factory
        LARGE_LATHE.set(new Obama_MetaTileEntity_Large_Lathe(aID++).getItem());

        //Replaces part of Large Processing Factory
        LARGE_AUTOCLAVE.set(new Obama_MetaTileEntity_Large_Autoclave(aID++).getItem());

        //LARGE_ALLOY_SMELTER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31037,
        //        "multimachine.obama.large_alloy_smelter",
        //        "Large Alloy Smelter",
        //        TT_Utils.MultiBlockDefinition.LARGE_ALLOY_SMELTER
        //).getStackForm(1L));

        //LARGE_FERMENTER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31042,
        //        "multimachine.obama.large_fermenter",
        //        "Large Fermenter",
        //        TT_Utils.MultiBlockDefinition.LARGE_FERMENTER
        //).getStackForm(1L));

        //LARGE_EXTRACTOR.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31043,
        //        "multimachine.obama.large_extractor",
        //        "Large Extractor",
        //        TT_Utils.MultiBlockDefinition.LARGE_EXTRACTOR
        //).getStackForm(1L));

        //LARGE_FLUID_EXTRACTOR.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31044,
        //        "multimachine.obama.large_fluid_extractor",
        //        "Large Fluid Extractor",
        //        TT_Utils.MultiBlockDefinition.LARGE_FLUID_EXTRACTOR
        //).getStackForm(1L));

        //LARGE_POLARIZER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31045,
        //        "multimachine.obama.large_polarizer",
        //        "Large Polarizer",
        //        TT_Utils.MultiBlockDefinition.LARGE_POLARIZER
        //).getStackForm(1L));

        //LARGE_FLUID_SOLIFIER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31046,
        //        "multimachine.obama.large_fluid_solifier",
        //        "Large Solifier",
        //        TT_Utils.MultiBlockDefinition.LARGE_FLUID_SOLIFIER
        //).getStackForm(1L));

        //LARGE_RECYCLER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31049,
        //        "multimachine.obama.large_recycler",
        //        "Large Recycler",
        //        TT_Utils.MultiBlockDefinition.LARGE_RECYCLER
        //).getStackForm(1L));

        //LARGE_MICROWAVE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31050,
        //        "multimachine.obama.large_microwave",
        //        "Large Microwave",
        //        TT_Utils.MultiBlockDefinition.LARGE_MICROWAVE
        //).getStackForm(1L));

        //LARGE_PRINTER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31052,
        //        "multimachine.obama.large_printer",
        //        "Large Printer",
        //        TT_Utils.MultiBlockDefinition.LARGE_PRINTER
        //).getStackForm(1L));

        //LARGE_ELECTROMAGNETIC_SEPARATOR.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31053,
        //        "multimachine.obama.large_electromagnetic_separator",
        //        "Large Electromagnetic Separator",
        //        TT_Utils.MultiBlockDefinition.LARGE_ELECTROMAGNETIC_SEPARATOR
        //).getStackForm(1L));

        //LARGE_FLUID_CANNER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31055,
        //        "multimachine.obama.large_fluid_canner",
        //        "Large Fluid Canner",
        //        TT_Utils.MultiBlockDefinition.LARGE_FLUID_CANNER
        //).getStackForm(1L));

        //LARGE_BREWERY.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31056,
        //        "multimachine.obama.large_brewery",
        //        "Large Brewery",
        //        TT_Utils.MultiBlockDefinition.LARGE_BREWERY
        //).getStackForm(1L));

        //LARGE_CANNING_MACHINE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31057,
        //        "multimachine.obama.large_canning_machine",
        //        "Large Canning Machine",
        //        TT_Utils.MultiBlockDefinition.LARGE_CANNING_MACHINE
        //).getStackForm(1L));

        //LARGE_FORGE_HAMMER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31058,
        //        "multimachine.obama.large_forge_hammer",
        //        "Large Forge Hammer",
        //        TT_Utils.MultiBlockDefinition.LARGE_FORGE_HAMMER
        //).getStackForm(1L));
    }

    //ID overlap with GT++ range since the mods are mutually exclusive
    private static void loadOverrideIDs() {
        // Overlaps with Industrial Centrifuge
        LARGE_CENTRIFUGE.set(new Obama_MetaTileEntity_Large_Centrifuge(790).getItem());

        // Overlaps with Industrial Material Press
        LARGE_BENDING_MACHINE.set(new Obama_MetaTileEntity_Large_Bender(792).getItem());

        // Overlaps with Industrial Electrolyzer
        LARGE_ELECTROLYZER.set(new Obama_MetaTileEntity_Large_Electrolyzer(796).getItem());

        // Overlaps with Maceration Stack Controller
        LARGE_MACERATOR.set(new Obama_MetaTileEntity_Large_Macerator(797).getItem());

        // Overlaps with Wire Factory Controller
        LARGE_WIREMILL.set(new Obama_MetaTileEntity_Large_Wiremill(798).getItem());

        // Overlaps with Industrial Mixing Machine
        LARGE_MIXER.set(new Obama_MetaTileEntity_Large_Mixer(811).getItem());

        // Overlaps with Large Sifter Control Block
        LARGE_SIFTING_MACHINE.set(new Obama_MetaTileEntity_Large_Sifter(840).getItem());

        // Overlaps with Large Thermal Refinery
        LARGE_THERMAL_CENTRIFUGE.set(new Obama_MetaTileEntity_Large_Thermal_Centrifuge(849).getItem());

        LARGE_ORE_WASHING_PLANT.set(new Obama_MetaTileEntity_Large_Ore_Washer(850).getItem());

        // Overlaps with Industrial Extrusion Machine
        LARGE_EXTRUDER.set(new Obama_MetaTileEntity_Large_Extruder(859).getItem());

//        // Overlaps with High Current Industrial Arc Furnace
//        LARGE_ARC_FURNACE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
//                862,
//                "multimachine.obama.large_arc_furnace",
//                "Large Arc Furnace",
//                MultiBlockDefinition.LARGE_ARC_FURNACE
//        ).getStackForm(1L));

        // Overlaps with Thermal Boiler
        LARGE_FLUID_HEATER.set(new Obama_MetaTileEntity_Large_Fluid_Heater(875).getItem());

        // Overlaps with Large Scale Auto-Assembler v1.01
        LARGE_ASSEMBLING_MACHINE.set(new Obama_MetaTileEntity_Large_Assembler(876).getItem());

        // Overlaps with Amazon Warehousing Depot.
        LARGE_PACKAGER.set(new Obama_MetaTileEntity_Large_Packager(942).getItem());

        // Overlaps with Cutting Factory Controller
        LARGE_CUTTING_MACHINE.set(new Obama_MetaTileEntity_Large_Cutting_Machine(992).getItem());

        // Overlaps with Large Processing Factory
        LARGE_PRECISION_LASER_ENGRAVER.set(new Obama_MetaTileEntity_Large_Laser_Engraver(860).getItem());
    }
}
