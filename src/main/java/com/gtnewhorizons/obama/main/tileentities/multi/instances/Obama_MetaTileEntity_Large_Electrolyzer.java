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

import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockAnyMeta;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.Elwoodite;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class Obama_MetaTileEntity_Large_Electrolyzer extends Obama_MetaTileEntity_Factory<Obama_MetaTileEntity_Large_Electrolyzer> implements IConstructableStructureSliceableCapped {
    public Obama_MetaTileEntity_Large_Electrolyzer(int aID) {
        super(aID, "multimachine.obama.large_electrolyzer", "Large Electrolyzer");
    }

    public Obama_MetaTileEntity_Large_Electrolyzer(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new Obama_MetaTileEntity_Large_Electrolyzer(mName);
    }


    private static final ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Electrolyzer>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Electrolyzer>>() {
        @Override
        protected IStructureDefinition<Obama_MetaTileEntity_Large_Electrolyzer> computeValue(Class<?> type) {
            return StructureDefinition.<Obama_MetaTileEntity_Large_Electrolyzer>builder()
                .addShape(TM_STRUCTURE_START, transpose(new String[][] {
                    {"BBB~BBB", "BBBBBBB", "BBBBBBB", "BBBBBBB", "BBBBBBB"}, // Bottom
                }))
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] {
                    {"GGGGGGG", "c-w-w-c", "A-----A", "c-w-w-c", "GGGGGGG"}, // Middle
                }))
                .addShape(TM_STRUCTURE_CAP, transpose(new String[][] {
                    {"BBBBBBB", "BBBBBBB", "BBBBBBB", "BBBBBBB", "BBBBBBB"}, // Top
                }))
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('w', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addWireCasingToMachineList, t.getTextureIndex(), 2)))
                .addElement('c', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 3)))
                .build();
        }
    };

    @Override
    public IStructureDefinition<Obama_MetaTileEntity_Large_Electrolyzer> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Electrolyzer")
            .addInfo("Controller block for the Large Electrolyzer")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(5, 5*(getMaxSlices()+1), 5, 5, 5, 5, false)
            .addController("Bottom Center")
            .addCasingInfo("Large Electrolyzer Casing", 10) // TODO (Count, and name)
            .addEnergyHatch("Any top/bottom casing", 1)
            .addInputHatch("Any top/bottom casing", 1)
            .addOutputHatch("Any top/bottom casing", 1)
            .addInputBus("Any top/bottom casing", 1)
            .addOutputBus("Any top/bottom casing", 1)
            .addMaintenanceHatch("Any top/bottom casing", 1)
            .addDynamoHatch("Any top/bottom casing", 1)
            .addOtherStructurePart(ObamaTooltips.TT_wireCasing, "Somewhere", 2)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Somewhere", 3)
            .addStructureInfo("Reinforced Glass")
            .toolTipFinisher("Obama");
        return tt;
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(3, 0, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(3, 1, 0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 1, 0);
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
        return Elwoodite.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_ELECTROLYZER";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sElectrolyzerRecipes;
    }
}
