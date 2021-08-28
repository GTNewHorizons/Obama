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

    //NOTE: aNameRegional is overwritten by the *.lang files
    //Change both if you wish to make edits to the names!
    //Uses ID Range of 31021 to 31070
    private static void loadNewIDs() {
        //TODO Remove any ID system that uses increments and decide on solid ID ranges.
        int aID = 31021;

        //Replaces part of Industrial Material Press
        LARGE_FORMING_PRESS.set(new GT_MetaTileEntity_TM_Large_Forming_Press(aID++).getItem());

        //Replaces part of Large Washing Plant
        LARGE_CHEMICAL_BATH.set(new GT_MetaTileEntity_TM_Large_Chemical_Bath(aID++).getItem());

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
        LARGE_LATHE.set(new GT_MetaTileEntity_TM_Large_Lathe(aID++).getItem());

        //Replaces part of Large Processing Factory
        LARGE_AUTOCLAVE.set(new GT_MetaTileEntity_TM_Large_Autoclave(aID++).getItem());

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
        LARGE_BENDING_MACHINE.set(new GT_MetaTileEntity_TM_Large_Bender(792).getItem());

        //Overlaps with Industrial Electrolyzer
        LARGE_ELECTROLYZER.set(new GT_MetaTileEntity_TM_Large_Electrolyzer(796).getItem());

        //Overlaps with Maceration Stack Controller
        LARGE_MACERATOR.set(new GT_MetaTileEntity_TM_Large_Macerator(797).getItem());

        //Overlaps with Wire Factory Controller
        LARGE_WIREMILL.set(new GT_MetaTileEntity_TM_Large_Wiremill(798).getItem());

        //Overlaps with Industrial Mixing Machine
        LARGE_MIXER.set(new GT_MetaTileEntity_TM_Large_Mixer(811).getItem());

        //Overlaps with Large Sifter Control Block
        LARGE_SIFTING_MACHINE.set(new GT_MetaTileEntity_TM_Large_Sifter(840).getItem());

        //Overlaps with Large Thermal Refinery
        LARGE_THERMAL_CENTRIFUGE.set(new GT_MetaTileEntity_TM_Large_Thermal_Centrifuge(849).getItem());

        LARGE_ORE_WASHING_PLANT.set(new GT_MetaTileEntity_TM_Large_Ore_Washer(850).getItem());

        //Overlaps with Industrial Extrusion Machine
        LARGE_EXTRUDER.set(new GT_MetaTileEntity_TM_Large_Extruder(859).getItem());

        //Overlaps with High Current Industrial Arc Furnace
        LARGE_ARC_FURNACE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                862,
                "multimachine.tm.large_arc_furnace",
                "Large Arc Furnace",
                MultiBlockDefinition.LARGE_ARC_FURNACE
        ).getStackForm(1L));

        //Overlaps with Thermal Boiler
        LARGE_FLUID_HEATER.set(new GT_MetaTileEntity_TM_Large_Fluid_Heater(875).getItem());

        //Overlaps with Large Scale Auto-Assembler v1.01
        LARGE_ASSEMBLING_MACHINE.set(new GT_MetaTileEntity_TM_Large_Assembler(876).getItem());

        //Overlaps with Amazon Warehousing Depot.
        LARGE_PACKAGER.set(new GT_MetaTileEntity_TM_Large_Packager(942).getItem());

        //Overlaps with Cutting Factory Controller
        LARGE_CUTTING_MACHINE.set(new GT_MetaTileEntity_TM_Large_Cutting_Machine(992).getItem());

        //Overlaps with Large Processing Factory
        LARGE_PRECISION_LASER_ENGRAVER.set(new GT_MetaTileEntity_TM_Large_Laser_Engraver(860).getItem());
    }
}
