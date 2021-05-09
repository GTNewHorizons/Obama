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
import com.gtnewhorizons.gtppnt.main.tileentites.multi.GT_MetaTileEntity_MultiMachine_RecipeMap;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.instances.*;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.MultiBlockDefinition;

import static com.gtnewhorizons.gtppnt.main.items.CustomItemList.*;

public class MultiBlockLoader {

    private MultiBlockLoader() {
    }

    public static void load() {
        try {
            loadOverrideIDs();
            loadNewIDs();
        } catch (Exception e) {
            GTAFMod.LOGGER.catching(e);
        }
    }

    //NOTE: aNameRegional is overwriten by the *.lang files
    //Change both if you wish to make edits to the names!
    //Uses ID Range of 31021 to 31070
    private static void loadNewIDs() {
        int aID = 31021;

        //Replaces part of Industrial Material Press
        LARGE_FORMING_PRESS.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                aID++,
                "multimachine.tm.large_forming_press",
                "Large Forming Press",
                MultiBlockDefinition.LARGE_FORMING_PRESS
        ).getStackForm(1L));

        //Replaces part of Large Washing Plant
        LARGE_CHEMICAL_BATH.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                aID++,
                "multimachine.tm.large_chemical_bath",
                "Large Chemical Bath",
                MultiBlockDefinition.LARGE_CHEMICAL_BATH
        ).getStackForm(1L));

        //Replaces part of High Current Industrial Arc Furnace
        LARGE_PLASMA_ARC_FURNACE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                aID++,
                "multimachine.tm.large_plasma_arc_furnace",
                "Large Plasma Arc Furnace",
                MultiBlockDefinition.LARGE_PLASMA_ARC_FURNACE
        ).getStackForm(1L));

        //Replaces part of Cutting Factory Controller
        LARGE_SLICING_MACHINE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                aID++,
                "multimachine.tm.large_slicing_machine",
                "Large Slicing Machine",
                MultiBlockDefinition.LARGE_SLICING_MACHINE
        ).getStackForm(1L));

        LARGE_COMPRESSOR.set(new GT_MetaTileEntity_TM_Large_Compressor(aID++).getItem());

        //Replaces part of Large Processing Factory
//        LARGE_COMPRESSOR.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
//                aID++,
//                "multimachine.tm.large_compressor",
//                "Large Compressor",
//                MultiBlockDefinition.LARGE_COMPRESSOR
//        ).getStackForm(1L));

        //Replaces part of Large Processing Factory
        LARGE_LATHE.set(new GT_MetaTileEntitiy_TM_Large_Lathe(aID++).getItem());
//
//        LARGE_LATHE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
//                aID++,
//                "multimachine.tm.large_lathe",
//                "Large Lathe",
//                MultiBlockDefinition.LARGE_LATHE
//        ).getStackForm(1L));

        //Replaces part of Large Processing Factory
        LARGE_AUTOCLAVE.set(new GT_MetaTileEntity_TM_Large_Autoclave(aID++).getItem());

//        LARGE_AUTOCLAVE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
//                aID++,
//                "multimachine.tm.large_autoclave",
//                "Large Autoclave",
//                MultiBlockDefinition.LARGE_AUTOCLAVE
//        ).getStackForm(1L));

        //LARGE_ALLOY_SMELTER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31037,
        //        "multimachine.tm.large_alloy_smelter",
        //        "Large Alloy Smelter",
        //        TT_Utils.MultiBlockDefinition.LARGE_ALLOY_SMELTER
        //).getStackForm(1L));

        //LARGE_FERMENTER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31042,
        //        "multimachine.tm.large_fermenter",
        //        "Large Fermenter",
        //        TT_Utils.MultiBlockDefinition.LARGE_FERMENTER
        //).getStackForm(1L));

        //LARGE_EXTRACTOR.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31043,
        //        "multimachine.tm.large_extractor",
        //        "Large Extractor",
        //        TT_Utils.MultiBlockDefinition.LARGE_EXTRACTOR
        //).getStackForm(1L));

        //LARGE_FLUID_EXTRACTOR.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31044,
        //        "multimachine.tm.large_fluid_extractor",
        //        "Large Fluid Extractor",
        //        TT_Utils.MultiBlockDefinition.LARGE_FLUID_EXTRACTOR
        //).getStackForm(1L));

        //LARGE_POLARIZER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31045,
        //        "multimachine.tm.large_polarizer",
        //        "Large Polarizer",
        //        TT_Utils.MultiBlockDefinition.LARGE_POLARIZER
        //).getStackForm(1L));

        //LARGE_FLUID_SOLIFIER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31046,
        //        "multimachine.tm.large_fluid_solifier",
        //        "Large Solifier",
        //        TT_Utils.MultiBlockDefinition.LARGE_FLUID_SOLIFIER
        //).getStackForm(1L));

        //LARGE_RECYCLER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31049,
        //        "multimachine.tm.large_recycler",
        //        "Large Recycler",
        //        TT_Utils.MultiBlockDefinition.LARGE_RECYCLER
        //).getStackForm(1L));

        //LARGE_MICROWAVE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31050,
        //        "multimachine.tm.large_microwave",
        //        "Large Microwave",
        //        TT_Utils.MultiBlockDefinition.LARGE_MICROWAVE
        //).getStackForm(1L));

        //LARGE_PRINTER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31052,
        //        "multimachine.tm.large_printer",
        //        "Large Printer",
        //        TT_Utils.MultiBlockDefinition.LARGE_PRINTER
        //).getStackForm(1L));

        //LARGE_ELECTROMAGNETIC_SEPARATOR.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31053,
        //        "multimachine.tm.large_electromagnetic_separator",
        //        "Large Electromagnetic Separator",
        //        TT_Utils.MultiBlockDefinition.LARGE_ELECTROMAGNETIC_SEPARATOR
        //).getStackForm(1L));

        //LARGE_FLUID_CANNER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31055,
        //        "multimachine.tm.large_fluid_canner",
        //        "Large Fluid Canner",
        //        TT_Utils.MultiBlockDefinition.LARGE_FLUID_CANNER
        //).getStackForm(1L));

        //LARGE_BREWERY.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31056,
        //        "multimachine.tm.large_brewery",
        //        "Large Brewery",
        //        TT_Utils.MultiBlockDefinition.LARGE_BREWERY
        //).getStackForm(1L));

        //LARGE_CANNING_MACHINE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31057,
        //        "multimachine.tm.large_canning_machine",
        //        "Large Canning Machine",
        //        TT_Utils.MultiBlockDefinition.LARGE_CANNING_MACHINE
        //).getStackForm(1L));

        //LARGE_FORGE_HAMMER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31058,
        //        "multimachine.tm.large_forge_hammer",
        //        "Large Forge Hammer",
        //        TT_Utils.MultiBlockDefinition.LARGE_FORGE_HAMMER
        //).getStackForm(1L));
    }

    //ID overlap with GT++ range since the mods are mutually exclusive
    private static void loadOverrideIDs() {
        //Overlaps with Industrial Centrifuge
        LARGE_CENTRIFUGE.set(new GT_MetaTileEntity_TM_Large_Centrifuge(790).getItem());

        //Overlaps with Industrial Material Press
        LARGE_BENDING_MACHINE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                792,
                "multimachine.tm.large_bending_machine",
                "Large Bending Machine",
                MultiBlockDefinition.LARGE_BENDING_MACHINE
        ).getStackForm(1L));

        //Overlaps with Industrial Electrolyzer
        LARGE_ELECTROLYZER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                796,
                "multimachine.tm.large_electrolyzer",
                "Large Electrolyzer",
                MultiBlockDefinition.LARGE_ELECTROLYZER
        ).getStackForm(1L));

        //Overlaps with Maceration Stack Controller
        //LARGE_MACERATOR.set(new GT_MetaTileEntity_TM_Large_Macerator(
        //        797,
        //        "multimachine.tm.large_macerator",
        //        "Large Macerator"
        //).getItem());

        //Overlaps with Wire Factory Controller
        LARGE_WIREMILL.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                798,
                "multimachine.tm.large_wiremill",
                "Large Wiremill",
                MultiBlockDefinition.LARGE_WIREMILL
        ).getStackForm(1L));

        //Overlaps with Industrial Mixing Machine
        LARGE_MIXER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                811,
                "multimachine.tm.large_mixer",
                "Large Mixer",
                MultiBlockDefinition.LARGE_MIXER
        ).getStackForm(1L));

        //Overlaps with Large Sifter Control Block
        LARGE_SIFTING_MACHINE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                840,
                "multimachine.tm.large_sifting_machine",
                "Large Sifting Machine",
                MultiBlockDefinition.LARGE_SIFTING_MACHINE
        ).getStackForm(1L));

        //Overlaps with Large Thermal Refinery
        LARGE_THERMAL_CENTRIFUGE.set(new GT_MetaTileEntitiy_TM_Large_Thermal_Centrifuge(849).getItem());

//        LARGE_THERMAL_CENTRIFUGE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
//                849,
//                "multimachine.tm.large_thermal_centrifuge",
//                "Large Thermal Centrifuge",
//                MultiBlockDefinition.LARGE_THERMAL_CENTRIFUGE
//        ).getStackForm(1L));

        //Overlaps with Large Washing Plant
        //LARGE_ORE_WASHING_PLANT.set(new GT_MetaTileEntity_TM_Large_Ore_Washing_Plant(
        //        850,
        //        "multimachine.tm.large_ore_washing_plant",
        //        "Large Ore Washing Plant"
        //).getItem());

        //Overlaps with Industrial Extrusion Machine
        LARGE_EXTRUDER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                859,
                "multimachine.tm.large_extruder",
                "Large Extruder",
                MultiBlockDefinition.LARGE_EXTRUDER
        ).getStackForm(1L));

        //Overlaps with High Current Industrial Arc Furnace
        LARGE_ARC_FURNACE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                862,
                "multimachine.tm.large_arc_furnace",
                "Large Arc Furnace",
                MultiBlockDefinition.LARGE_ARC_FURNACE
        ).getStackForm(1L));

        //Overlaps with Thermal Boiler
        LARGE_FLUID_HEATER.set(new GT_MetaTileEntity_TM_Large_Fluid_Heater(875).getItem());

//        LARGE_FLUID_HEATER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
//                875,
//                "multimachine.tm.large_fluid_heater",
//                "Large Fluid Heater",
//                MultiBlockDefinition.LARGE_FLUID_HEATER
//        ).getStackForm(1L));

        //Overlaps with Large Scale Auto-Assembler v1.01
        LARGE_ASSEMBLING_MACHINE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                876,
                "multimachine.tm.large_assembling_machine",
                "Large Assembling Machine",
                MultiBlockDefinition.LARGE_ASSEMBLING_MACHINE
        ).getStackForm(1L));

        //Overlaps with Amazon Warehousing Depot.
        LARGE_PACKAGER.set(new GT_MetaTileEntitiy_TM_Large_Packager(942).getItem());

//        LARGE_PACKAGER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
//                942,
//                "multimachine.tm.large_packager",
//                "Large Packager",
//                MultiBlockDefinition.LARGE_PACKAGER
//        ).getStackForm(1L));

        //Overlaps with Cutting Factory Controller
        LARGE_CUTTING_MACHINE.set(new GT_MetaTileEntity_TM_Large_Cuttin_Machine(992).getItem());

        //Overlaps with Large Processing Factory
        LARGE_PRECISION_LASER_ENGRAVER.set(new GT_MetaTileEntitiy_TM_Large_Laser_Engraver(860).getItem());

//        LARGE_PRECISION_LASER_ENGRAVER.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
//                860,
//                "multimachine.tm.large_precision_laser_engraver",
//                "Large Precision Laser Engraver",
//                MultiBlockDefinition.LARGE_PRECISION_LASER_ENGRAVER
//        ).getStackForm(1L));
    }
}
