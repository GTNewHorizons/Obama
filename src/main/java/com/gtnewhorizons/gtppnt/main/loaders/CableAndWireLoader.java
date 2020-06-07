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

import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;
import com.gtnewhorizons.gtppnt.main.GTAFMod;
import com.gtnewhorizons.gtppnt.main.config.ConfigHandler;
import com.gtnewhorizons.gtppnt.preloader.postGT.loaders.EnumExtender;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.ISubTagContainer;
import gregtech.api.metatileentity.implementations.GT_MetaPipeEntity_Cable;
import gregtech.api.metatileentity.implementations.GT_MetaPipeEntity_Fluid;
import gregtech.api.metatileentity.implementations.GT_MetaPipeEntity_Item;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_OreDictUnificator;

import java.util.Optional;

import static gregtech.api.enums.Materials.*;
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.*;

@SuppressWarnings("ALL")
public class CableAndWireLoader {

    private final static String aTextWire1 = "wire.";

    public static void load() {
        try {
            loadOverrideIDs();
            loadNewIDs();
        } catch (Exception e) {
            GTAFMod.LOGGER.catching(e);
        }
    }

    private static final String aTextCable1 = "cable.";
    private static final String aTextWire2 = " Wire";
    private static final String aTextCable2 = " Cable";

    private CableAndWireLoader() {
    }

    public static void loadOverrideIDs() throws Exception {
        makeWires(RedstoneAlloy, 30645, 0, 1, 1, 32, true, false);

        //TODO: Redo Capacity Values
        generateFluidPipes(TriniumNaquadahCarbonite, 30500, 40);

        int startID = 30695;
        generateFluidPipes(Staballoy, startID += 5, 12500);
        generateFluidPipes(Atysal, startID += 5, 10000);
        generateFluidPipes(AtysalYT, startID += 5, 12000);
        generateFluidPipes(Void, startID += 5, 3200, 2500);
        generateFluidPipes(Europium, startID += 5, 12000);
        generateFluidPipes(Potin, startID += 5, 1920);
        generateFluidPipes(MaragingSteel300, startID += 5, 14000);
        generateFluidPipes(MaragingSteel350, startID += 5, 16000);
        generateFluidPipes(Hereford690, startID += 5, 15000);
        generateFluidPipes(Hereford792, startID += 5, 16000);
        generateFluidPipes(NickmolX, startID += 5, 20000);
        generateFluidPipes(Tungsten, startID += 5, 8640);
        generateFluidPipes(DarkSteel, startID += 5, 4640);
        generateFluidPipes(Clay, startID += 5, 200);
        generateFluidPipes(Lead, startID += 5, 1440);

        //16x Pipes
        Materials[] hexapipes = {
                Copper, Bronze, Steel, StainlessSteel, Titanium, TungstenSteel, Plastic, Polytetrafluoroethylene
        };
        int[][] hexapipesValues = {
                {1200, 2400, 4800, 7200, 9600, 12000, 7200, 9600},
                {1000, 2000, 2500, 3000, 5000, 7500, 350, 600}
        };
        for (int i = 0; i < hexapipes.length; i++) {
            generateHexadecoupleFluidMultiPipes(
                    hexapipes[i], hexapipes[i].mName, 30100 + i, hexapipesValues[0][i], hexapipesValues[1][i], true);
        }

    }

    public static void loadNewIDs() throws Exception {
        makeWires(EglinSteel, ConfigHandler.IDOFFSET + 47, 1, 2, 3, 8192, true, false); //IDK why i want this, but I WANT THIS!

        //12x Pipes
        Materials[] dodecapipes = {
                Copper, Bronze, Steel, StainlessSteel, Titanium, TungstenSteel, Plastic, Polytetrafluoroethylene
        };
        int[][] dodecapipeValues = {
                {1200, 2400, 4800, 7200, 9600, 12000, 7200, 9600},
                {1000, 2000, 2500, 3000, 5000, 7500, 350, 600}
        };
        for (int i = 0; i < dodecapipes.length; i++) {
            generateDodecoupleFluidMultiPipes(
                    dodecapipes[i], dodecapipes[i].mName, ConfigHandler.IDOFFSET + 59 + i, dodecapipeValues[0][i], dodecapipeValues[1][i], true);
        }

        //PikyPipe! HEYAO
        generateFluidPipes(Pikyonium64B, ConfigHandler.IDOFFSET + 67, 30000);
    }

    private static void generateItemPipes(ISubTagContainer stuff, String name, int startID, int baseInvSlots) {
        boolean isMaterials = stuff instanceof Materials;
        Materials aMaterial = isMaterials ? ((Materials) stuff) : !isMaterials && stuff instanceof Werkstoff ? ((Werkstoff) stuff).getBridgeMaterial() : null;

        if (aMaterial == null)
            throw new IllegalArgumentException("Stuff must either be a Werkstoff or a Materials");

        String displayName = isMaterials ? GT_LanguageManager.i18nPlaceholder ? "%material" : aMaterial.mDefaultLocalName : ((Werkstoff) stuff).getDefaultName();

        generateItemPipes(aMaterial, name, displayName, startID, baseInvSlots);
    }

    /**
     * Adjusted and Stripped GT Method.
     *
     * @param stuff        A Material or a Werkstoff
     * @param startID      start ID
     * @param baseCapacity capacity
     *                     meltingPoint is defaulted to 10% below actual Materials Melting point
     */
    private static void generateFluidPipes(ISubTagContainer stuff, int startID, int baseCapacity) {
        Materials materials = Optional.of(stuff)
                .map(s -> s instanceof Materials ? ((Materials) s) : s instanceof Werkstoff ? ((Werkstoff) s).getBridgeMaterial() : null)
                .orElseThrow(() -> new IllegalArgumentException("Stuff must either be a Werkstoff or a Materials"));

        generateFluidPipes(stuff, startID, baseCapacity, materials.mMeltingPoint - (materials.mMeltingPoint / 100)); //10% Below Melting Point
    }

    /**
     * Adjusted and Stripped GT Method.
     *
     * @param stuff        A Material or a Werkstoff
     * @param startID      start ID
     * @param baseCapacity capacity
     * @param meltingPoint meltingPoint
     */
    private static void generateFluidPipes(ISubTagContainer stuff, int startID, int baseCapacity, int meltingPoint) {
        boolean isMaterials = stuff instanceof Materials;
        Materials aMaterial = Optional.of(stuff)
                .map(s -> isMaterials ? ((Materials) s) : s instanceof Werkstoff ? ((Werkstoff) s).getBridgeMaterial() : null)
                .orElseThrow(() -> new IllegalArgumentException("Stuff must either be a Werkstoff or a Materials"));

        String displayName = isMaterials ? GT_LanguageManager.i18nPlaceholder ? "%material" : aMaterial.mDefaultLocalName : ((Werkstoff) stuff).getDefaultName();
        String internalName = isMaterials ? aMaterial.mName : ((Werkstoff) stuff).getVarName();

        generateFluidPipes(aMaterial, internalName, displayName, startID, baseCapacity, meltingPoint, true);
    }

    /**
     * Adjusted and Stripped GT Method.
     *
     * @param stuff        A Material or a Werkstoff
     * @param ID           ID
     * @param baseCapacity capacity
     * @param heatCapacity capacity
     * @param gasProof     gasProof
     */
    private static void generateHexadecoupleFluidMultiPipes(ISubTagContainer stuff, String name, int ID, int baseCapacity, int heatCapacity, boolean gasProof) {
        boolean isMaterials = stuff instanceof Materials;
        Materials aMaterial = Optional.of(stuff)
                .map(s -> isMaterials ? ((Materials) s) : s instanceof Werkstoff ? ((Werkstoff) s).getBridgeMaterial() : null)
                .orElseThrow(() -> new IllegalArgumentException("Stuff must either be a Werkstoff or a Materials"));

        String displayName = isMaterials ? GT_LanguageManager.i18nPlaceholder ? "%material" : aMaterial.mDefaultLocalName : ((Werkstoff) stuff).getDefaultName();

        GT_OreDictUnificator.registerOre(EnumExtender.pipeHexadecuple.get(aMaterial), new GT_MetaPipeEntity_Fluid(ID, "GT_Pipe_" + name + "_Hexadecouple", "Hexadecouple " + displayName + " Fluid Pipe", 1.0F, aMaterial, baseCapacity / 4 / 5, heatCapacity, gasProof, 16).getStackForm(1L));
    }

    /**
     * Adjusted and Stripped GT Method.
     *
     * @param stuff        A Material or a Werkstoff
     * @param ID           ID
     * @param baseCapacity capacity
     * @param heatCapacity capacity
     * @param gasProof     gasProof
     */
    private static void generateDodecoupleFluidMultiPipes(ISubTagContainer stuff, String name, int ID, int baseCapacity, int heatCapacity, boolean gasProof) {
        boolean isMaterials = stuff instanceof Materials;
        Materials aMaterial = Optional.of(stuff)
                .map(s -> isMaterials ? ((Materials) s) : s instanceof Werkstoff ? ((Werkstoff) s).getBridgeMaterial() : null)
                .orElseThrow(() -> new IllegalArgumentException("Stuff must either be a Werkstoff or a Materials"));

        String displayName = isMaterials ? GT_LanguageManager.i18nPlaceholder ? "%material" : aMaterial.mDefaultLocalName : ((Werkstoff) stuff).getDefaultName();

        GT_OreDictUnificator.registerOre(EnumExtender.pipeDodecuple.get(aMaterial), new GT_MetaPipeEntity_Fluid(ID, "GT_Pipe_" + name + "_Dodecouple", "Dodecouple " + displayName + " Fluid Pipe", 1.0F, aMaterial, baseCapacity / 4 / 5, heatCapacity, gasProof, 12).getStackForm(1L));
    }

    /**
     * Adjusted and Stripped GT Method.
     *
     * @param stuff        A Material or a Werkstoff
     * @param startID      start ID
     * @param baseCapacity capacity
     */
    private static void generateFluidMultiPipes(ISubTagContainer stuff, String name, int startID, int baseCapacity, int heatCapacity, boolean gasProof) {
        boolean isMaterials = stuff instanceof Materials;
        Materials aMaterial = Optional.of(stuff)
                .map(s -> isMaterials ? ((Materials) s) : s instanceof Werkstoff ? ((Werkstoff) s).getBridgeMaterial() : null)
                .orElseThrow(() -> new IllegalArgumentException("Stuff must either be a Werkstoff or a Materials"));

        String displayName = isMaterials ? GT_LanguageManager.i18nPlaceholder ? "%material" : aMaterial.mDefaultLocalName : ((Werkstoff) stuff).getDefaultName();

        generateFluidMultiPipes(aMaterial, name, displayName, startID, baseCapacity, heatCapacity, gasProof);
    }

    private static void makeWires(ISubTagContainer stuff, int aStartID, long aLossInsulated, long aLoss, long aAmperage, long aVoltage, boolean aInsulatable, boolean aAutoInsulated) {
        boolean isMaterials = stuff instanceof Materials;
        Materials aMaterial = Optional.of(stuff)
                .map(s -> isMaterials ? ((Materials) s) : s instanceof Werkstoff ? ((Werkstoff) s).getBridgeMaterial() : null)
                .orElseThrow(() -> new IllegalArgumentException("Stuff must either be a Werkstoff or a Materials"));

        String name = isMaterials ? GT_LanguageManager.i18nPlaceholder ? "%material" : aMaterial.mDefaultLocalName : ((Werkstoff) stuff).getDefaultName();

        GT_OreDictUnificator.registerOre(OrePrefixes.wireGt01, aMaterial, new GT_MetaPipeEntity_Cable(aStartID, aTextWire1 + aMaterial.mName.toLowerCase() + ".01", "1x " + name + aTextWire2, 0.125F, aMaterial, aLoss, 1L * aAmperage, aVoltage, false, !aAutoInsulated).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.wireGt02, aMaterial, new GT_MetaPipeEntity_Cable(aStartID + 1, aTextWire1 + aMaterial.mName.toLowerCase() + ".02", "2x " + name + aTextWire2, 0.25F, aMaterial, aLoss, 2L * aAmperage, aVoltage, false, !aAutoInsulated).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.wireGt04, aMaterial, new GT_MetaPipeEntity_Cable(aStartID + 2, aTextWire1 + aMaterial.mName.toLowerCase() + ".04", "4x " + name + aTextWire2, 0.375F, aMaterial, aLoss, 4L * aAmperage, aVoltage, false, !aAutoInsulated).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.wireGt08, aMaterial, new GT_MetaPipeEntity_Cable(aStartID + 3, aTextWire1 + aMaterial.mName.toLowerCase() + ".08", "8x " + name + aTextWire2, 0.5F, aMaterial, aLoss, 8L * aAmperage, aVoltage, false, !aAutoInsulated).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.wireGt12, aMaterial, new GT_MetaPipeEntity_Cable(aStartID + 4, aTextWire1 + aMaterial.mName.toLowerCase() + ".12", "12x " + name + aTextWire2, 0.625F, aMaterial, aLoss, 12L * aAmperage, aVoltage, false, !aAutoInsulated).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.wireGt16, aMaterial, new GT_MetaPipeEntity_Cable(aStartID + 5, aTextWire1 + aMaterial.mName.toLowerCase() + ".16", "16x " + name + aTextWire2, 0.75F, aMaterial, aLoss, 16L * aAmperage, aVoltage, false, !aAutoInsulated).getStackForm(1L));
        if (aInsulatable) {
            GT_OreDictUnificator.registerOre(OrePrefixes.cableGt01, aMaterial, new GT_MetaPipeEntity_Cable(aStartID + 6, aTextCable1 + aMaterial.mName.toLowerCase() + ".01", "1x " + name + aTextCable2, 0.25F, aMaterial, aLossInsulated, 1L * aAmperage, aVoltage, true, false).getStackForm(1L));
            GT_OreDictUnificator.registerOre(OrePrefixes.cableGt02, aMaterial, new GT_MetaPipeEntity_Cable(aStartID + 7, aTextCable1 + aMaterial.mName.toLowerCase() + ".02", "2x " + name + aTextCable2, 0.375F, aMaterial, aLossInsulated, 2L * aAmperage, aVoltage, true, false).getStackForm(1L));
            GT_OreDictUnificator.registerOre(OrePrefixes.cableGt04, aMaterial, new GT_MetaPipeEntity_Cable(aStartID + 8, aTextCable1 + aMaterial.mName.toLowerCase() + ".04", "4x " + name + aTextCable2, 0.5F, aMaterial, aLossInsulated, 4L * aAmperage, aVoltage, true, false).getStackForm(1L));
            GT_OreDictUnificator.registerOre(OrePrefixes.cableGt08, aMaterial, new GT_MetaPipeEntity_Cable(aStartID + 9, aTextCable1 + aMaterial.mName.toLowerCase() + ".08", "8x " + name + aTextCable2, 0.625F, aMaterial, aLossInsulated, 8L * aAmperage, aVoltage, true, false).getStackForm(1L));
            GT_OreDictUnificator.registerOre(OrePrefixes.cableGt12, aMaterial, new GT_MetaPipeEntity_Cable(aStartID + 10, aTextCable1 + aMaterial.mName.toLowerCase() + ".12", "12x " + name + aTextCable2, 0.75F, aMaterial, aLossInsulated, 12L * aAmperage, aVoltage, true, false).getStackForm(1L));
            GT_OreDictUnificator.registerOre(OrePrefixes.cableGt16, aMaterial, new GT_MetaPipeEntity_Cable(aStartID + 11, aTextCable1 + aMaterial.mName.toLowerCase() + ".16", "16x " + name + aTextCable2, 0.875F, aMaterial, aLossInsulated, 16L * aAmperage, aVoltage, true, false).getStackForm(1L));
        }

    }

    private static void generateItemPipes(Materials aMaterial, String name, String displayName, int startID, int baseInvSlots) {
        GT_OreDictUnificator.registerOre(OrePrefixes.pipeMedium.get(aMaterial), new GT_MetaPipeEntity_Item(startID, "GT_Pipe_" + name, displayName + " Item Pipe", 0.50F, aMaterial, baseInvSlots, 32768 / baseInvSlots, false).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.pipeLarge.get(aMaterial), new GT_MetaPipeEntity_Item(startID + 1, "GT_Pipe_" + name + "_Large", "Large " + displayName + " Item Pipe", 0.75F, aMaterial, baseInvSlots * 2, 16384 / baseInvSlots, false).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.pipeHuge.get(aMaterial), new GT_MetaPipeEntity_Item(startID + 2, "GT_Pipe_" + name + "_Huge", "Huge " + displayName + " Item Pipe", 1.00F, aMaterial, baseInvSlots * 4, 8192 / baseInvSlots, false).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.pipeRestrictiveMedium.get(aMaterial), new GT_MetaPipeEntity_Item(startID + 3, "GT_Pipe_Restrictive_" + name, "Restrictive " + displayName + " Item Pipe", 0.50F, aMaterial, baseInvSlots, 3276800 / baseInvSlots, true).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.pipeRestrictiveLarge.get(aMaterial), new GT_MetaPipeEntity_Item(startID + 4, "GT_Pipe_Restrictive_" + name + "_Large", "Large Restrictive " + displayName + " Item Pipe", 0.75F, aMaterial, baseInvSlots * 2, 1638400 / baseInvSlots, true).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.pipeRestrictiveHuge.get(aMaterial), new GT_MetaPipeEntity_Item(startID + 5, "GT_Pipe_Restrictive_" + name + "_Huge", "Huge Restrictive " + displayName + " Item Pipe", 0.875F, aMaterial, baseInvSlots * 4, 819200 / baseInvSlots, true).getStackForm(1L));
    }

    private static void generateFluidMultiPipes(Materials aMaterial, String name, String displayName, int startID, int baseCapacity, int heatCapacity, boolean gasProof) {
        GT_OreDictUnificator.registerOre(OrePrefixes.pipeQuadruple.get(aMaterial), new GT_MetaPipeEntity_Fluid(startID, "GT_Pipe_" + name + "_Quadruple", "Quadruple " + displayName + " Fluid Pipe", 1.0F, aMaterial, baseCapacity, heatCapacity, gasProof, 4).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.pipeNonuple.get(aMaterial), new GT_MetaPipeEntity_Fluid(startID + 1, "GT_Pipe_" + name + "_Nonuple", "Nonuple " + displayName + " Fluid Pipe", 1.0F, aMaterial, baseCapacity / 3, heatCapacity, gasProof, 9).getStackForm(1L));
    }

    private static void generateFluidPipes(Materials aMaterial, String name, String displayName, int startID, int baseCapacity, int heatCapacity, boolean gasProof) {
        GT_OreDictUnificator.registerOre(OrePrefixes.pipeTiny.get(aMaterial), new GT_MetaPipeEntity_Fluid(startID, "GT_Pipe_" + name + "_Tiny", "Tiny " + displayName + " Fluid Pipe", 0.25F, aMaterial, baseCapacity / 6, heatCapacity, gasProof).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.pipeSmall.get(aMaterial), new GT_MetaPipeEntity_Fluid(startID + 1, "GT_Pipe_" + name + "_Small", "Small " + displayName + " Fluid Pipe", 0.375F, aMaterial, baseCapacity / 3, heatCapacity, gasProof).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.pipeMedium.get(aMaterial), new GT_MetaPipeEntity_Fluid(startID + 2, "GT_Pipe_" + name, displayName + " Fluid Pipe", 0.5F, aMaterial, baseCapacity, heatCapacity, gasProof).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.pipeLarge.get(aMaterial), new GT_MetaPipeEntity_Fluid(startID + 3, "GT_Pipe_" + name + "_Large", "Large " + displayName + " Fluid Pipe", 0.75F, aMaterial, baseCapacity * 2, heatCapacity, gasProof).getStackForm(1L));
        GT_OreDictUnificator.registerOre(OrePrefixes.pipeHuge.get(aMaterial), new GT_MetaPipeEntity_Fluid(startID + 4, "GT_Pipe_" + name + "_Huge", "Huge " + displayName + " Fluid Pipe", 0.875F, aMaterial, baseCapacity * 4, heatCapacity, gasProof).getStackForm(1L));
    }
}