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
import com.gtnewhorizons.gtppnt.main.utils.TT_Utils;

import static com.gtnewhorizons.gtppnt.main.items.CustomItemList.LARGE_CENTRIFUGE;

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

    private static void loadNewIDs() {
    }

    private static void loadOverrideIDs() {
        //new GT_MetaTileEntity_MultiMachine_RecipeMap(
        //        31121,
        //        "FreezerUnlocalised",
        //        "FreezerLocalised",
        //        TT_Utils.MultiBlockDefinition.FREEZER
        //);

        LARGE_CENTRIFUGE.set(new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31021,
                "multimachine.tm.large_centrifuge",
                "Large Centrifuge",
                TT_Utils.MultiBlockDefinition.LARGE_CENTRIFUGE
        ).getStackForm(1L));

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31022,
                "multimachine.tm.large_bending_machine",
                "Large Bending Machine",
                TT_Utils.MultiBlockDefinition.LARGE_BENDING_MACHINE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31023,
                "multimachine.tm.large_electrolyzer",
                "Large Electrolyzer",
                TT_Utils.MultiBlockDefinition.LARGE_ELECTROLYZER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31024,
                "multimachine.tm.large_macerator",
                "Large Macerator",
                TT_Utils.MultiBlockDefinition.LARGE_MACERATOR
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31025,
                "multimachine.tm.large_wiremill",
                "Large Wiremill",
                TT_Utils.MultiBlockDefinition.LARGE_WIREMILL
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31026,
                "multimachine.tm.large_mixer",
                "Large Mixer",
                TT_Utils.MultiBlockDefinition.LARGE_MIXER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31027,
                "multimachine.tm.large_sifting_machine",
                "Large Sifting Machine",
                TT_Utils.MultiBlockDefinition.LARGE_SIFTING_MACHINE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31028,
                "multimachine.tm.large_thermal_centrifuge",
                "Large Thermal Centrifuge",
                TT_Utils.MultiBlockDefinition.LARGE_THERMAL_CENTRIFUGE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31029,
                "multimachine.tm.large_ore_washing_plant",
                "Large Ore Washing Plant",
                TT_Utils.MultiBlockDefinition.LARGE_ORE_WASHING_PLANT
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31030,
                "multimachine.tm.large_extruder",
                "Large Extruder",
                TT_Utils.MultiBlockDefinition.LARGE_EXTRUDER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31031,
                "multimachine.tm.large_arc_furnace",
                "Large Arc Furnace",
                TT_Utils.MultiBlockDefinition.LARGE_ARC_FURNACE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31032,
                "multimachine.tm.large_fluid_heater",
                "Large Fluid Heater",
                TT_Utils.MultiBlockDefinition.LARGE_FLUID_HEATER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31033,
                "multimachine.tm.large_assembling_machine",
                "Large Assembling Machine",
                TT_Utils.MultiBlockDefinition.LARGE_ASSEMBLING_MACHINE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31034,
                "multimachine.tm.large_circuit_assembling_machine",
                "Large Circuit Assembling Machine",
                TT_Utils.MultiBlockDefinition.LARGE_CIRCUIT_ASSEMBLING_MACHINE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31035,
                "multimachine.tm.large_packager",
                "Large Packager",
                TT_Utils.MultiBlockDefinition.LARGE_PACKAGER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31036,
                "multimachine.tm.large_cutting_machine",
                "Large Cutting Machine",
                TT_Utils.MultiBlockDefinition.LARGE_CUTTING_MACHINE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31037,
                "multimachine.tm.large_alloy_smelter",
                "Large Alloy Smelter",
                TT_Utils.MultiBlockDefinition.LARGE_ALLOY_SMELTER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31038,
                "multimachine.tm.large_compressor",
                "Large Compressor",
                TT_Utils.MultiBlockDefinition.LARGE_COMPRESSOR
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31039,
                "multimachine.tm.large_lathe",
                "Large Lathe",
                TT_Utils.MultiBlockDefinition.LARGE_LATHE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31040,
                "multimachine.tm.large_precision_laser_engraver",
                "Large Precision Laser Engraver",
                TT_Utils.MultiBlockDefinition.LARGE_PRECISION_LASER_ENGRAVER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31041,
                "multimachine.tm.large_autoclave",
                "Large Autoclave",
                TT_Utils.MultiBlockDefinition.LARGE_AUTOCLAVE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31042,
                "multimachine.tm.large_fermenter",
                "Large Fermenter",
                TT_Utils.MultiBlockDefinition.LARGE_FERMENTER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31043,
                "multimachine.tm.large_extractor",
                "Large Extractor",
                TT_Utils.MultiBlockDefinition.LARGE_EXTRACTOR
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31044,
                "multimachine.tm.large_fluid_extractor",
                "Large Fluid Extractor",
                TT_Utils.MultiBlockDefinition.LARGE_FLUID_EXTRACTOR
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31045,
                "multimachine.tm.large_polarizer",
                "Large Polarizer",
                TT_Utils.MultiBlockDefinition.LARGE_POLARIZER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31046,
                "multimachine.tm.large_fluid_solifier",
                "Large Solifier",
                TT_Utils.MultiBlockDefinition.LARGE_FLUID_SOLIFIER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31047,
                "multimachine.tm.large_forming_press",
                "Large Forming Press",
                TT_Utils.MultiBlockDefinition.LARGE_FORMING_PRESS
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31048,
                "multimachine.tm.large_slicing_machine",
                "Large Slicing Machine",
                TT_Utils.MultiBlockDefinition.LARGE_SLICING_MACHINE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31049,
                "multimachine.tm.large_recycler",
                "Large Recycler",
                TT_Utils.MultiBlockDefinition.LARGE_RECYCLER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31050,
                "multimachine.tm.large_microwave",
                "Large Microwave",
                TT_Utils.MultiBlockDefinition.LARGE_MICROWAVE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31051,
                "multimachine.tm.large_plasma_arc_furnace",
                "Large Plasma Arc Furnace",
                TT_Utils.MultiBlockDefinition.LARGE_PLASMA_ARC_FURNACE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31052,
                "multimachine.tm.large_printer",
                "Large Printer",
                TT_Utils.MultiBlockDefinition.LARGE_PRINTER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31053,
                "multimachine.tm.large_electromagnetic_separator",
                "Large Electromagnetic Separator",
                TT_Utils.MultiBlockDefinition.LARGE_ELECTROMAGNETIC_SEPARATOR
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31054,
                "multimachine.tm.large_chemical_bath",
                "Large Chemical Bath",
                TT_Utils.MultiBlockDefinition.LARGE_CHEMICAL_BATH
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31055,
                "multimachine.tm.large_fluid_canner",
                "Large Fluid Canner",
                TT_Utils.MultiBlockDefinition.LARGE_FLUID_CANNER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31056,
                "multimachine.tm.large_brewery",
                "Large Brewery",
                TT_Utils.MultiBlockDefinition.LARGE_BREWERY
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31057,
                "multimachine.tm.large_canning_machine",
                "Large Canning Machine",
                TT_Utils.MultiBlockDefinition.LARGE_CANNING_MACHINE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31058,
                "multimachine.tm.large_forge_hammer",
                "Large Forge Hammer",
                TT_Utils.MultiBlockDefinition.LARGE_FORGE_HAMMER
        );
    }
}
