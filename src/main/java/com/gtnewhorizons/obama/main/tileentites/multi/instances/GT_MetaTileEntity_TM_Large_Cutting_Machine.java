package com.gtnewhorizons.obama.main.tileentites.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.structure.IConstructableStructureSliceableCapped;
import com.gtnewhorizons.obama.main.utils.ObamaTooltips;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockAnyMeta;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.MaragingSteel250;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Cutting_Machine extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Cutting_Machine> implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Cutting_Machine(int aID) {
        super(aID, "multimachine.tm.large_cutting_machine", "Large Cutting Machine");
    }

    public GT_MetaTileEntity_TM_Large_Cutting_Machine(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Cutting_Machine(mName);
    }


    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Cutting_Machine>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Cutting_Machine>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Cutting_Machine> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Cutting_Machine>builder()
                .addShape(TM_STRUCTURE_START, transpose(new String[][] { // Left
                    {"   ", "PAc", "PAc", "   ", "   "},
                    {"AAA", "A  ", "A  ", "AAA", "   "},
                    {"A  ", "AGG", "A  ", "AAm", "  m"},
                    {"A  ", "AGG", "A  ", "AAA", "  A"},
                    {"I~B", "IAA", "IAv", "IAA", "  A"},
                }))
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] { // Middle
                    {" ",   "c",   "c",   " ",   " "},
                    {"A",   " ",   " ",   "A",   " "},
                    {" ",   "G",   " ",   "m",   "m"},
                    {" ",   "G",   " ",   "A",   "A"},
                    {"B",   "A",   "v",   "A",   "A"},
                }))
                .addShape(TM_STRUCTURE_CAP, transpose(new String[][] { // Right
                    {"  ",  "AA",  "AA",  "  "},
                    {"AA",  " A",  " A",  "AA"},
                    {"  ",  "G ",  "G ",  "A "},
                    {"  ",  "G ",  "G ",  "A "},
                    {"BO",  "AO",  "AO",  "AO"},
                }))
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('I', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addInputToMachineList, t.getTextureIndex(), 2),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('O', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addOutputToMachineList, t.getTextureIndex(), 3),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('P', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 4),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('m', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMotorCasingToMachineList, t.getTextureIndex(), 5)))
                .addElement('v', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addConveyorCasingToMachineList, t.getTextureIndex(), 6)))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 7)))
                .build();
        }
    
    };

    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Cutting_Machine> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Cutting Machine")
            .addInfo("Controller block for the Large Cutting Machine")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(5, 5 + getMaxSlices(), 5, 5, 5,5,  true)
            .addController("Bottom Front, Second Leftmost")
            .addCasingInfo("Large Cutting Machine Casing", 10) // TODO (Count, and name)
            .addMaintenanceHatch("", 1)
            .addInputBus("Left Bottom", 2)
            .addInputHatch("Left Bottom", 2)
            .addOutputBus("Right Bottom", 3)
            .addOutputHatch("Right Bottom", 3)
            .addEnergyHatch("Top Left", 4)
            .addOtherStructurePart(ObamaTooltips.TT_motorCasing, "Back Middle", 5)
            .addOtherStructurePart(ObamaTooltips.TT_conveyorCasing, "Bottom", 6)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Top", 7)
            .addStructureInfo("Reinforced Glass")
            .toolTipFinisher("Obama");
        return tt;
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(1, 4, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(-2, 4, 0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(-1, 0, 0);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(0, 0, 0);
    }

    @Override
    public int getMaxSlices() {
        return 6;
    }

    @Override
    public int getMinSlices() {
        return 0;
    }

    @Override
    public int getParalellsPerSlice() {
        return 16;
    }

    @Override
    public int getMinParallel() {
        return 16;
    }

    @Override
    public short getCasingMeta() {
        return MaragingSteel250.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_CUTTING_MACHINE";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sCutterRecipes;
    }
}
