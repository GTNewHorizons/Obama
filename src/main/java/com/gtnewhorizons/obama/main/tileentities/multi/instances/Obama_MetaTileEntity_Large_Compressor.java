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
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.MaragingSteel250;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class Obama_MetaTileEntity_Large_Compressor extends Obama_MetaTileEntity_Factory<Obama_MetaTileEntity_Large_Compressor> implements IConstructableStructureSliceableCapped {
    public Obama_MetaTileEntity_Large_Compressor(int aID) {
        super(aID, "multimachine.obama.large_compressor", "Large Compressor");
    }

    public Obama_MetaTileEntity_Large_Compressor(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new Obama_MetaTileEntity_Large_Compressor(mName);
    }


    private static final ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Compressor>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Compressor>>() {
        @Override
        protected IStructureDefinition<Obama_MetaTileEntity_Large_Compressor> computeValue(Class<?> type) {

            return StructureDefinition.<Obama_MetaTileEntity_Large_Compressor>builder()
                .addShape(TM_STRUCTURE_START, transpose(new String[][] {
                    {" AAAAA ", " AAAAA "},
                    {"AGGGGGA", "A-A-A-A"},
                    {"AGGGGGA", "A-A-A-A"},
                    {"AGGGGGA", "A-A-A-A"},
                    {"BBB~BBB", "BAAAAAB"},
                }))
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] {
                    {" AAAAA ", " AAAAA "},
                    {"cpA-Apc", "A-A-A-A"},
                    {"A-A-A-A", "cpA-Apc"},
                    {"A-A-A-A", "A-A-A-A"},
                    {" AAAAA ", " AAAAA "},
                }))
                .addShape(TM_STRUCTURE_CAP, transpose(new String[][] {
                    {"",        " AAAAA "},
                    {"",        "AGGGGGA"},
                    {"",        "AGGGGGA"},
                    {"",        "AGGGGGA"},
                    {"BAAAAAB", "BBBBBBB"},
                }))
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('B', lazy(t -> ofChain(
                        ofHatchAdder(Obama_MetaTileEntity_Factory::addToMachineList, t.getTextureIndex(), 1),
                        ofBlock(t.getCasingBlock(), t.getCasingMeta())
                )))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('p', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addPistonCasingToMachineList, t.getTextureIndex(), 2)))
                .addElement('c', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 3)))
                .build();
        } 
    };

    @Override
    public IStructureDefinition<Obama_MetaTileEntity_Large_Compressor> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Compressor")
            .addInfo("Controller block for the Large Compressor")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(7, 7, 5, 5, 3 + getMinSlices(), 3 + getMaxSlices(),  true)
            .addController("Front Bottom Middle")
            .addCasingInfo("Large Compressor Casing", 10) // TODO (Count, and name)
            .addEnergyHatch("Any casing", 1)
            .addInputHatch("Any casing", 1)
            .addOutputHatch("Any Casing", 1)
            .addInputBus("Any Casing", 1)
            .addOutputBus("Any Casing", 1)
            .addMaintenanceHatch("Any Casing", 1)
            .addDynamoHatch("Any Casing", 1)
            .addOtherStructurePart(ObamaTooltips.TT_pistonCasing, "Inside Middle", 2)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Outside Middle", 3)
            .addStructureInfo("Reinforced Glass")
            .toolTipFinisher("Obama");
        return tt;
    }
    
    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(3, 4, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(3, 4, -2);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 0, -2);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(0, 0, 1);
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
    public int getMinParallel() {
        return 0;
    }

    @Override
    public int getParalellsPerSlice() {
        return 48;
    }

    @Override
    public short getCasingMeta() {
        return MaragingSteel250.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_COMPRESSOR";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sCompressorRecipes;
    }
}
