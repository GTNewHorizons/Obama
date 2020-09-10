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

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31021,
                "multimachine.tm.large_centrifuge",
                "Large Centrifuge",
                TT_Utils.MultiBlockDefinition.LARGE_CENTRIFUGE
        );

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
                "multimachine.tm.ore_washing_plant",
                "Ore Washing Plant",
                TT_Utils.MultiBlockDefinition.ORE_WASHING_PLANT
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31030,
                "multimachine.tm.industrial_extrusion_machine",
                "Industrial Extrusion Machine",
                TT_Utils.MultiBlockDefinition.INDUSTRIAL_EXTRUSION_MACHINE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31031,
                "multimachine.tm.high_current_industrial_arc_furnace",
                "High Current Industrial Arc Furnace",
                TT_Utils.MultiBlockDefinition.HIGH_CURRENT_INDUSTRIAL_ARC_FURNACE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31032,
                "multimachine.tm.thermal_boiler",
                "Thermal Boiler",
                TT_Utils.MultiBlockDefinition.THERMAL_BOILER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31033,
                "multimachine.tm.large_scale_auto_assembler",
                "Large Scale Auto Assembler",
                TT_Utils.MultiBlockDefinition.LARGE_SCALE_AUTO_ASSEMBLER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31034,
                "multimachine.tm.cryogenic_freezer",
                "Cryogenic Freezer",
                TT_Utils.MultiBlockDefinition.CRYOGENIC_FREEZER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31035,
                "multimachine.tm.packaging_r_us",
                "Packaging R US",
                TT_Utils.MultiBlockDefinition.PACKAGING_R_US
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31036,
                "multimachine.tm.cutting_factory",
                "Cutting Factory",
                TT_Utils.MultiBlockDefinition.CUTTING_FACTORY
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31037,
                "multimachine.tm.gregodyne_59f_engine",
                "Gregodyne 59F Engine",
                TT_Utils.MultiBlockDefinition.GREGODYNE_59F_ENGINE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31038,
                "multimachine.tm.kerotronics_chemical_plant",
                "Kerotronics Chemical Plant",
                TT_Utils.MultiBlockDefinition.KEROTRONICS_CHEMICAL_PLANT
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31039,
                "multimachine.tm.combinational_smelt_o_tron",
                "Combinational Smelt-O-Tron",
                TT_Utils.MultiBlockDefinition.COMBINATIONAL_SMELT_O_TRON
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31040,
                "multimachine.tm.lpr_advanced_compressor",
                "Advanced Compressor",
                TT_Utils.MultiBlockDefinition.LPR_ADVANCED_COMPRESSOR
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31041,
                "multimachine.tm.lpr_advanced_lathe",
                "Advanced Lathe",
                TT_Utils.MultiBlockDefinition.LPR_ADVANCED_LATHE
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31042,
                "multimachine.tm.lpr_advanced_precision_laser",
                "Advanced Precision Laser",
                TT_Utils.MultiBlockDefinition.LPR_ADVANCED_PRECISION_LASER
        );

        new GT_MetaTileEntity_MultiMachine_RecipeMap(
                31043,
                "multimachine.tm.lpr_advanced_autoclave",
                "Advanced Autoclave",
                TT_Utils.MultiBlockDefinition.LPR_ADVANCED_AUTOCLAVE
        );
    }
}
