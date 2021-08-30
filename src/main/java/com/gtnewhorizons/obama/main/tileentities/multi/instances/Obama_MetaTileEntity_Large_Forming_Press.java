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

import static com.github.bartimaeusnek.bartworks.system.material.BW_GT_MaterialReference.Titanium;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class Obama_MetaTileEntity_Large_Forming_Press extends Obama_MetaTileEntity_Factory<Obama_MetaTileEntity_Large_Forming_Press> implements IConstructableStructureSliceableCapped {
    public Obama_MetaTileEntity_Large_Forming_Press(int aID) {
        super(aID, "multimachine.obama.large_forming_press", "Large Forming Press");
    }

    public Obama_MetaTileEntity_Large_Forming_Press(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new Obama_MetaTileEntity_Large_Forming_Press(mName);
    }


    private static final ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Forming_Press>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Forming_Press>>() {
        @Override
        protected IStructureDefinition<Obama_MetaTileEntity_Large_Forming_Press> computeValue(Class<?> type) {
            return StructureDefinition.<Obama_MetaTileEntity_Large_Forming_Press>builder()
                .addShape(TM_STRUCTURE_START, transpose(new String[][] {
                    {"cIIIc", "I-p-I", "IpApI", "I-p-I", "cIIIc"},
                    {"A---A", "-----", "-----", "-----", "A---A"},
                    {"BB~BB", "BBBBB", "BBBBB", "BBBBB", "BBBBB"},
                }))
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] {
                    {"c---c", "--p--", "-pAp-", "--p--", "c---c"},
                }))
                .addShape(TM_STRUCTURE_CAP, transpose(new String[][] {
                    {"A A", "AAA", " A ", "AAA", "A A"},
                }))
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addOutputToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('I', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addInputToMachineList, t.getTextureIndex(), 2),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('p', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addPistonCasingToMachineList, t.getTextureIndex(), 3)))
                .addElement('c', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 4)))
                .build();
        }
    };
    
    @Override
    public IStructureDefinition<Obama_MetaTileEntity_Large_Forming_Press> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Forming Press")
            .addInfo("Controller block for the Large Forming Press")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(5, 5, 4, 4 + getMaxSlices(), 5, 5,  false)
            .addController("Front Bottom")
            .addCasingInfo("Large Forming Press Casing", 10) // TODO (Count, and name)
            .addMaintenanceHatch("Bottom Layer Casings", 1)
            .addEnergyHatch("Bottom Layer Casings", 1)
            .addOutputHatch("Bottom Layer Casings", 1)
            .addInputHatch("Third Layer Casings", 2)
            .addOtherStructurePart(ObamaTooltips.TT_pistonCasing, "Middle Center Ring", 3)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Middle Layer Corners", 4)
            .addStructureInfo("Reinforced Glass")
            .toolTipFinisher("Obama");
        return tt;
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(2, 2, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(2, 3, 0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 1, 0);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(-1, 0, 0);
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
        return 32;
    }

    @Override
    public int getMinParallel() {
        return 32;
    }

    @Override
    public short getCasingMeta() {
        return Titanium.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_FORMING_PRESS";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sPressRecipes;
    }
}
