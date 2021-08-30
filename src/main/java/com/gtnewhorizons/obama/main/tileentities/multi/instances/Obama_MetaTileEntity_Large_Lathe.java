package com.gtnewhorizons.obama.main.tileentities.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.Obama_MetaTileEntity_Factory;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.structure.IConstructableStructureSliceable;
import com.gtnewhorizons.obama.main.utils.ObamaTooltips;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.MaragingSteel250;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class Obama_MetaTileEntity_Large_Lathe extends Obama_MetaTileEntity_Factory<Obama_MetaTileEntity_Large_Lathe> implements IConstructableStructureSliceable {
    public Obama_MetaTileEntity_Large_Lathe(int aID) {
        super(aID, "multimachine.obama.large_lathe", "Large Lathe");
    }

    public Obama_MetaTileEntity_Large_Lathe(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new Obama_MetaTileEntity_Large_Lathe(mName);
    }


    private static final ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Lathe>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Lathe>>() {
        @Override
        protected IStructureDefinition<Obama_MetaTileEntity_Large_Lathe> computeValue(Class<?> type) {
            return StructureDefinition.<Obama_MetaTileEntity_Large_Lathe>builder()
                .addShape(TM_STRUCTURE_START, transpose(new String[][] {  // Right
                    {"         ", "A        ", "         "},
                    {"~--------", "mAAAAAAAA", "p--------"},
                    {"BBBBBBBBA", "AA------A", "cBBBBBBBA"},
                }))
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] {  // Left
                    {" ", "A", " "},
                    {"A", "m", "p"},
                    {"B", "A", "c"},
                }))
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('m', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addMotorCasingToMachineList, t.getTextureIndex(), 2)))
                .addElement('p', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addPistonCasingToMachineList, t.getTextureIndex(), 3)))
                .addElement('c', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 4)))
                .build();
        }
    };

    @Override
    public IStructureDefinition<Obama_MetaTileEntity_Large_Lathe> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Lathe")
            .addInfo("Controller block for the Large Lathe")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(9 + getMinSlices(), 9 + getMaxSlices(), 3, 3, 3, 3,  true)
            .addController("Middle Layer")
            .addCasingInfo("Large Lathe Casing", 10) // TODO (Count, and name)
            .addEnergyHatch("Bottom Front/Rear casings", 1)
            .addInputHatch("Bottom Front/Rear casings", 1)
            .addOutputHatch("Bottom Front/Rear casings", 1)
            .addInputBus("Bottom Front/Rear casings", 1)
            .addOutputBus("Bottom Front/Rear casings", 1)
            .addMaintenanceHatch("Bottom Front/Rear casings", 1)
            .addDynamoHatch("Bottom Front/Rear casings", 1)
            .addOtherStructurePart(ObamaTooltips.TT_motorCasing, "Center Middle", 2)
            .addOtherStructurePart(ObamaTooltips.TT_pistonCasing, "Back Middle", 3)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Back Bottom", 4)
            
            .toolTipFinisher("Obama");
        return tt;
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(0, 1, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(1, 1, 0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(1, 0, 0);
    }

    @Override
    public int getMaxSlices() {
        return 3;
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
        return "TM_LARGE_LATHE";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sLatheRecipes;
    }
}
