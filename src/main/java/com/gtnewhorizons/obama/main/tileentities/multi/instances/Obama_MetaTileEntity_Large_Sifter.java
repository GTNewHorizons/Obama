package com.gtnewhorizons.obama.main.tileentities.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.Obama_MetaTileEntity_Factory;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.structure.IConstructableStructureCells;
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
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.EglinSteel;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class Obama_MetaTileEntity_Large_Sifter extends Obama_MetaTileEntity_Factory<Obama_MetaTileEntity_Large_Sifter> implements IConstructableStructureCells {
    public Obama_MetaTileEntity_Large_Sifter(int aID) {
        super(aID, "multimachine.obama.large_sifting_machine", "Large Sifting Machine");
    }

    public Obama_MetaTileEntity_Large_Sifter(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new Obama_MetaTileEntity_Large_Sifter(mName);
    }


    private static final ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Sifter>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Sifter>>() {
        @Override
        protected IStructureDefinition<Obama_MetaTileEntity_Large_Sifter> computeValue(Class<?> type) {
            return StructureDefinition.<Obama_MetaTileEntity_Large_Sifter>builder()
                .addShape(TM_STRUCTURE_A, transpose(new String[][] {
                    {"AppA", "cssc", "cssc", "AppA"},
                    {"AAAA", "A  A", "A  A", "AAAA"},
                    {"A  A", "    ", "    ", "A  A"},
                    {"A  A", "    ", "    ", "A  A"},
                    {"A  A", "    ", "    ", "A  A"},
                    {"A  A", "    ", "    ", "A  A"},
                    {"~AAA", "A  A", "A  A", "AAAA"},
                    {"A  A", "    ", "    ", "A  A"},
                }))
                .addShape(TM_STRUCTURE_B, transpose(new String[][] {
                    {" pp ", "cssc", "cssc", "AppA"},
                    {"AAAA", "A  A", "A  A", "AAAA"},
                    {"    ", "    ", "    ", "A  A"},
                    {"    ", "    ", "    ", "A  A"},
                    {"    ", "A  A", "A  A", "AAAA"},
                    {"    ", "    ", "    ", "A  A"},
                }))
                .addShape(TM_STRUCTURE_C, transpose(new String[][] {
                    {" pp ", "cssc", "cssc", "AppA"},
                    {"    ", "    ", "    ", "A  A"},
                    {"    ", "A  A", "A  A", "A  A"},
                    {"    ", "    ", "    ", "A  A"},
                }))
                .addShape(TM_STRUCTURE_D, transpose(new String[][] {
                    {"    ", "    ", "    ", "AAAA"},
                    {" pp ", "cssc", "cssc", "AppA"},
                    {"    ", "    ", "    ", "A  A"},
                }))
                .addElement('A', lazy(t -> ofChain(
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()),
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addToMachineList, t.getTextureIndex(), 0))))
                .addElement('s', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addFilterCasingToMachineList, t.getTextureIndex(), 1)))
                .addElement('p', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addPistonCasingToMachineList, t.getTextureIndex(), 2)))
                .addElement('c', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 3)))
                .build();
        }
    };

    @Override
    public IStructureDefinition<Obama_MetaTileEntity_Large_Sifter> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Sifter")
            .addInfo("Controller block for the Large Sifter")
            .addInfo("Extensible Multiblock")
            .addInfo(String.format("Parallels: %d, %d, %d, %d", getParalellsA(), getParalellsB(), getParalellsC(), getParalellsD()))
            .addSeparator()
            .beginVariableStructureBlock(4, 4, 8, 8, 4, 13,  true)
            .addController("Front Left, Second layer from bottom")
            .addEnergyHatch("Any casing")
            .addInputHatch("Any casing")
            .addOutputHatch("Any casing")
            .addInputBus("Any casing")
            .addOutputBus("Any casing")
            .addMaintenanceHatch("Any casing")
            .addDynamoHatch("Any casing")
            .addOtherStructurePart(ObamaTooltips.TT_filterCasing, "Top Layers", 1)
            .addOtherStructurePart(ObamaTooltips.TT_pistonCasing, "Top Layers", 2)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Top Layers", 3)
            .toolTipFinisher("Obama");
        return tt;
    }

    @Override
    public Vec3Impl getStartOffsetA() {
        return new Vec3Impl(0, 6, 0);
    }

    @Override
    public Vec3Impl getStartOffsetB() {
        return new Vec3Impl(0, 4, -3);
    }

    @Override
    public Vec3Impl getStartOffsetC() {
        return new Vec3Impl(0, 2, -6);
    }

    @Override
    public Vec3Impl getStartOffsetD() {
        return new Vec3Impl(0, 1, -9);
    }

    @Override
    public int getParalellsA() {
        return 32;
    }

    @Override
    public int getParalellsB() {
        return 64;
    }

    @Override
    public int getParalellsC() {
        return 92;
    }

    @Override
    public int getParalellsD() {
        return 128;
    }

    @Override
    public short getCasingMeta() {
        return EglinSteel.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_SIFTING_MACHINE";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sSifterRecipes;
    }
}
