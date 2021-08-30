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
import gregtech.api.enums.ItemList;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockAdder;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockAnyMeta;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.MaragingSteel250;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Thermal_Centrifuge extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Thermal_Centrifuge> implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Thermal_Centrifuge(int aID) {
        super(aID, "multimachine.tm.large_thermal_centrifuge", "Large Thermal Centrifuge");
    }

    public GT_MetaTileEntity_TM_Large_Thermal_Centrifuge(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Thermal_Centrifuge(mName);
    }


    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Thermal_Centrifuge>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Thermal_Centrifuge>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Thermal_Centrifuge> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Thermal_Centrifuge>builder()
                .addShape(TM_STRUCTURE_START, transpose(new String[][] {
                    {" BBB ", " ccc "},
                    {"B---B", "mAAAm"},
                    {"B-~-B", "mAAAm"},
                    {"B---B", "mAAAm"},
                    {" BBB ", " ccc "},
                    {"E   E", "     "},
                }))
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] {
                    {" ccc "},
                    {"m---m"},
                    {"m---m"},
                    {"m---m"},
                    {" ccc "},
                }))
                .addShape(TM_STRUCTURE_CAP, transpose(new String[][] {
                    {"  AAA  ", "       ", "       ", "  AAA  ", " AAAAA ", "AAAIAAA", "AAIIIAA", "AAAIAAA", " AAAAA ", "  AAA  "},
                    {" AAAAA ", "   A   ", "   A   ", "  AAA  ", " A   A ", "A     A", "A     A", "A     A", " A   A ", "  AAA  "},
                    {" AA-AA ", "  A-A  ", "  A-A  ", "  A-A  ", " A A A ", "A  A  A", "G  A  A", "G     A", " G   A ", "  GGA  "},
                    {" AAAAA ", "   A   ", "   A   ", "  AAA  ", " A   A ", "A     A", "A  A  A", "A     A", " A   A ", "  AAA  "},
                    {"  AAA  ", "       ", "       ", "  AAA  ", " AhhhA ", "AhhOhhA", "AhOOOhA", "AhhOhhA", " AhhhA ", "  AAA  "},
                    {" E   E ", "       ", "       ", "A     A", "       ", "       ", "       ", "       ", "A     A", "       "},
                }))
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('E', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 2),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('I', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addInputToMachineList, t.getTextureIndex(), 3),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('O', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addOutputToMachineList, t.getTextureIndex(), 4),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('m', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMotorCasingToMachineList, t.getTextureIndex(), 5)))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 6)))
                .addElement('h', lazy(t -> ofBlockAdder(GT_MetaTileEntity_TM_Factory::addCoilToMachineList, ItemList.Casing_Coil_Cupronickel.getBlock(), 0)))
                .build();
        }
    };
    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Thermal_Centrifuge> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Thermal Centrifuge")
            .addInfo("Controller block for the Large Thermal Centrifuge")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(7, 7, 6, 6, 12 + getMinSlices(), 12 + getMaxSlices(), true)
            .addController("Left Side")
            .addMaintenanceHatch("Front Layer", 1)
            .addEnergyHatch("Bottom Layer", 2)
            .addInputHatch("Top Middle", 3)
            .addInputBus("Top Middle", 3)
            .addOutputHatch("Bottom Middle", 4)
            .addOutputBus("Bottom Middle", 4)
            .addOtherStructurePart(ObamaTooltips.TT_motorCasing, "", 5)
            .addOtherStructurePart(ObamaTooltips.TT_motorCasing, "", 6)
            .addOtherStructurePart(ObamaTooltips.TT_motorCasing, "", 5)
            .addOtherStructurePart("Cupronickel Coil Block", "")
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
        return new Vec3Impl(2, 2, -2);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 0, -1);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(1, 0, 0);
    }

    @Override
    public int getMaxSlices() {
        return 4;
    }

    @Override
    public int getMinSlices() {
        return 0;
    }

    @Override
    public int getParalellsPerSlice() {
        return 48;
    }

    @Override
    public int getMinParallel() {
        return 48;
    }

    @Override
    public short getCasingMeta() {
        return MaragingSteel250.getmID();
    }


    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_THERMAL_CENTRIFUGE";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sThermalCentrifugeRecipes;
    }
}
