package com.gtnewhorizons.obama.main.tileentities.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.Obama_MetaTileEntity_Factory;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.structure.IConstructableStructureSliceableCapped;
import com.gtnewhorizons.obama.main.utils.ObamaTooltips;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;

import static com.github.bartimaeusnek.bartworks.system.material.BW_GT_MaterialReference.TungstenCarbide;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class Obama_MetaTileEntity_Large_Macerator extends Obama_MetaTileEntity_Factory<Obama_MetaTileEntity_Large_Macerator> implements IConstructableStructureSliceableCapped {
    public Obama_MetaTileEntity_Large_Macerator(int aID) {
        super(aID, "multimachine.obama.large_macerator", "Large Macerator");
    }

    public Obama_MetaTileEntity_Large_Macerator(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new Obama_MetaTileEntity_Large_Macerator(mName);
    }

    private static final ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Macerator>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Macerator>>() {
        @Override
        protected IStructureDefinition<Obama_MetaTileEntity_Large_Macerator> computeValue(Class<?> type) {
            return StructureDefinition.<Obama_MetaTileEntity_Large_Macerator>builder()
                .addShape(TM_STRUCTURE_START, transpose(new String[][] {
                    {" AAA "},
                    {"AAAAA"},
                    {"AA~AA"},
                    {"AAAAA"},
                    {" AAA "},
                }))
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] {
                    {" mBp "},
                    {"c---c"},
                    {"B---B"},
                    {"c---c"},
                    {" pBm "},
                }))
                .addShape(TM_STRUCTURE_CAP, transpose(new String[][] {
                    {" AAA "},
                    {"AAAAA"},
                    {"AAAAA"},
                    {"AAAAA"},
                    {" AAA "},
                }))
                .addElement('A', lazy(t -> ofChain(
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()),
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 0),
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 0))))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addInputToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addOutputToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('m', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addMotorCasingToMachineList, t.getTextureIndex(), 2)))
                .addElement('p', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addPistonCasingToMachineList, t.getTextureIndex(), 3)))
                .addElement('c', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 4)))
                .build();
        }
    };

    @Override
    public IStructureDefinition<Obama_MetaTileEntity_Large_Macerator> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Macerator")
            .addInfo("Controller block for the Large Macerator")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(5, 5, 5, 5, 2 + getMinSlices(), 2 + getMaxSlices(),  true)
            .addController("Front Center")
            .addCasingInfo("Large Macerator Casing", 10) // TODO (Count, and name)
            .addEnergyHatch("Front/Block Casings")
            .addMaintenanceHatch("Front/Block Casings")
            .addInputHatch("Middle of Top/Bottom/Left/Right", 1)
            .addInputBus("Middle of Top/Bottom/Left/Right", 1)
            .addOutputHatch("Middle of Top/Bottom/Left/Right", 1)
            .addOutputBus("Middle of Top/Bottom/Left/Right", 1)
            .addOtherStructurePart(ObamaTooltips.TT_motorCasing, "Bottom/Top", 2)
            .addOtherStructurePart(ObamaTooltips.TT_pistonCasing, "Bottom/Top", 3)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Left/Right", 4)
            .toolTipFinisher("Obama");
        return tt;
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(2, 2, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(2, 2, -1);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 0, -1);
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
        return 1;
    }

    @Override
    public int getParalellsPerSlice() {
        return 32;
    }

    @Override
    public int getMinParallel() {
        return 0;
    }

    @Override
    public short getCasingMeta() {
        return TungstenCarbide.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_MACERATOR";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sMaceratorRecipes;
    }
}
