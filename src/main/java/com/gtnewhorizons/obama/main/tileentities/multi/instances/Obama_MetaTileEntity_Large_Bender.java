package com.gtnewhorizons.obama.main.tileentities.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.Obama_MetaTileEntity_Factory;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.structure.IConstructableStructureSliceableCapped;
import com.gtnewhorizons.obama.main.utils.ObamaTooltips;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;

import static com.github.bartimaeusnek.bartworks.system.material.BW_GT_MaterialReference.Titanium;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockAnyMeta;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class Obama_MetaTileEntity_Large_Bender extends Obama_MetaTileEntity_Factory<Obama_MetaTileEntity_Large_Bender> implements IConstructableStructureSliceableCapped {
    public Obama_MetaTileEntity_Large_Bender(int aID) {
        super(aID, "multimachine.obama.large_bending_machine", "Large Bending Machine");
    }

    public Obama_MetaTileEntity_Large_Bender(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new Obama_MetaTileEntity_Large_Bender(mName);
    }

    private static final ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Bender>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Bender>>() {
        @Override
        protected IStructureDefinition<Obama_MetaTileEntity_Large_Bender> computeValue(Class<?> type) {

            return StructureDefinition.<Obama_MetaTileEntity_Large_Bender>builder()
                .addShape(TM_STRUCTURE_START, transpose(new String[][] {
                    {"AAA"},
                    {"AAA"},
                    {"A~A"},
                    {"AAA"},
                }))
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] {
                    {"cAA", "AAc"},
                    {"mpG", "Gpm"},
                    {"mpG", "Gpm"},
                    {"cAA", "AAc"},
                }))
                .addShape(TM_STRUCTURE_CAP, transpose(new String[][] {
                    {"AAA"},
                    {"AAA"},
                    {"AAA"},
                    {"AAA"},
                }))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('A', lazy(t -> ofChain(
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()),
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addToMachineList, t.getTextureIndex(), 0))))
                .addElement('m', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addMotorCasingToMachineList, t.getTextureIndex(), 1)))
                .addElement('p', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addPistonCasingToMachineList, t.getTextureIndex(), 2)))
                .addElement('c', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 3)))
                .build();
        }
    };

    @Override
    public IStructureDefinition<Obama_MetaTileEntity_Large_Bender> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Bender")
            .addInfo("Controller block for the Large Bender")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(3, 3, 4, 4, 4, 4+(2*getMaxSlices()), false)
            .addController("Front Middle, one up from the bottom")
            .addCasingInfo("Large Bender Casing", 10) // TODO (Count, and name)
            .addEnergyHatch("Any casing", 1)
            .addInputHatch("Any casing", 1)
            .addOutputHatch("Any Casing", 1)
            .addInputBus("Any Casing", 1)
            .addOutputBus("Any Casing", 1)
            .addMaintenanceHatch("Any Casing", 1)
            .addDynamoHatch("Any Casing", 1)
            .addOtherStructurePart(ObamaTooltips.TT_motorCasing, "Side of the slices", 1)
            .addOtherStructurePart(ObamaTooltips.TT_pistonCasing, "Middle of the slice", 2)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Slice Corners", 3)
            .addStructureInfo("Reinforced Glass")
            .toolTipFinisher("Obama");
        return tt;
    }
    
    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(1, 2, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(1, 2, -1);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 0, -2);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(0, 0, 0);
    }

    @Override
    public int getMaxSlices() {
        return 4;
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
        return Titanium.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_BENDING_MACHINE";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sBenderRecipes;
    }
}
