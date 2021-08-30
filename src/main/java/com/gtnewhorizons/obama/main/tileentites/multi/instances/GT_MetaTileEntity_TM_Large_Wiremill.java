package com.gtnewhorizons.obama.main.tileentites.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.structure.IConstructableStructureSliceableCapped;
import com.gtnewhorizons.obama.main.utils.ObamaTooltips;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;

import static com.github.bartimaeusnek.bartworks.system.material.BW_GT_MaterialReference.BlueSteel;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Wiremill extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Wiremill> implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Wiremill(int aID) {
        super(aID, "multimachine.tm.large_wiremill", "Large Wiremill");
    }

    public GT_MetaTileEntity_TM_Large_Wiremill(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Wiremill(mName);
    }

    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Wiremill>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Wiremill>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Wiremill> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Wiremill>builder()
                .addShape(TM_STRUCTURE_START, transpose(new String[][] {
                    {" AA "},
                    {"A~AA"},
                    {"A  A"},
                }))
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] {
                    {"cmmc", " AA "},
                    {"AmmA", "AAAA"},
                }))
                .addShape(TM_STRUCTURE_CAP, transpose(new String[][] {
                    {"A  A"},
                }))
                .addElement('A', lazy(t -> ofChain(
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()),
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addToMachineList, t.getTextureIndex(), 0))))
                .addElement('m', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMotorCasingToMachineList, t.getTextureIndex(), 1)))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 2)))
                .build();
        }
    };


    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Wiremill> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Wiremill")
            .addInfo("Controller block for the Large Wiremill")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(4, 4, 3, 3, 1 + (2 *getMinSlices()), 1 + (2 *getMaxSlices()), true)
            .addCasingInfo("Wiremill Casing", 10) // TODO (Count, and name)
            .addEnergyHatch("Any casing")
            .addInputHatch("Any casing")
            .addOutputHatch("Any casing")
            .addInputBus("Any casing")
            .addOutputBus("Any casing")
            .addMaintenanceHatch("Any casing")
            .addDynamoHatch("Any casing")
            .addOtherStructurePart(ObamaTooltips.TT_motorCasing, "Center of Middle Slices", 1)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Top Corners of Middle Slices", 2)
            .toolTipFinisher("Obama");
        return tt;
    }


    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(1, 1, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(1, 1, -1);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 0, -2);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(0, -2, 1);
    }

    @Override
    public int getMaxSlices() {
        return 6;
    }

    @Override
    public int getMinSlices() {
        return 1;
    }

    @Override
    public int getParalellsPerSlice() {
        return 16;
    }

    @Override
    public int getMinParallel() {
        return 0;
    }

    @Override
    public short getCasingMeta() {
        return BlueSteel.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_WIREMILL";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sWiremillRecipes;
    }
}