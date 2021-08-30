package com.gtnewhorizons.obama.main.tileentities.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.Obama_MetaTileEntity_Factory;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.structure.IConstructableStructureCells;
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

public class Obama_MetaTileEntity_Large_Ore_Washer extends Obama_MetaTileEntity_Factory<Obama_MetaTileEntity_Large_Ore_Washer> implements IConstructableStructureCells {
    public Obama_MetaTileEntity_Large_Ore_Washer(int aID) {
        super(aID, "multimachine.obama.large_ore_washing_plant", "Large Ore Washing Plant");
    }

    public Obama_MetaTileEntity_Large_Ore_Washer(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new Obama_MetaTileEntity_Large_Ore_Washer(mName);
    }


    private static final ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Ore_Washer>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Ore_Washer>>() {
        @Override
        protected IStructureDefinition<Obama_MetaTileEntity_Large_Ore_Washer> computeValue(Class<?> type) {
            return StructureDefinition.<Obama_MetaTileEntity_Large_Ore_Washer>builder()
                .addShape(TM_STRUCTURE_A, transpose(new String[][] {
                    {"   Acc ", "   A--c", "   A--c", "   AFF "},
                    {"   AGG ", "   f--G", "   f--G", "   AAI "},
                    {"AGG~cc ", "G--f--c", "G--f--c", "AGGAAI "},
                    {"BBBAAA ", "BBBAmmA", "BBBAmmA", "BBBAAA "},
                }))
                .addShape(TM_STRUCTURE_B, transpose(new String[][] {
                    {"AAA ", "F--c", "F--c", " cc "},
                    {"Aff ", "A--G", "I--G", " GG "},
                    {"Aff ", "A--c", "I--c", " cc "},
                    {"    ", "AmmA", "AmmA", " AA "},
                    {"    ", "    ", "    ", "A  A"},
                    {"    ", "    ", "    ", "A  A"},
                }))
                .addShape(TM_STRUCTURE_C, transpose(new String[][] {
                    {" FFA", "c--A", "c--A", " cc "},
                    {" IA ", "G--f", "G--f", " GG "},
                    {" IA ", "c--f", "c--f", " cc "},
                    {" AA ", "Amm ", "Amm ", " AA "},
                    {"A   ", "    ", "    ", "A   "},
                    {"A   ", "    ", "    ", "A   "},
                    {"A   ", "    ", "    ", "A   "},
                    {"A   ", "    ", "    ", "A   "},
                }))
                .addShape(TM_STRUCTURE_D, transpose(new String[][] {
                    {" cc ", "c--A", "c--A", " AA "},
                    {" GG ", "G--I", "G--A", " ff "},
                    {" cc ", "c--I", "c--A", " ff "},
                    {" AA ", "AmmA", "AmmA", "    "},
                    {"A  A", "    ", "    ", "    "},
                    {"A  A", "    ", "    ", "    "},
                    {"A   ", "    ", "    ", "    "},
                    {"A   ", "    ", "    ", "    "},
                }))
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addOutputToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('I', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addInputToMachineList, t.getTextureIndex(), 2),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('m', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addMotorCasingToMachineList, t.getTextureIndex(), 3)))
                .addElement('f', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addPumpCasingToMachineList, t.getTextureIndex(), 4)))
                .addElement('c', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 5)))
                .addElement('F', lazy(t -> ofChain(
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()),
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addPumpCasingToMachineList, t.getTextureIndex(), 6))))
                .build();
        }
    };

    @Override
    public IStructureDefinition<Obama_MetaTileEntity_Large_Ore_Washer> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Ore Washer")
            .addInfo("Controller block for the Large Ore Washer")
            .addInfo("Extensible Multiblock")
            .addInfo(String.format("Parallels: %d, %d, %d, %d", getParalellsA(), getParalellsB(), getParalellsC(), getParalellsD()))
            .addSeparator()
            .beginVariableStructureBlock(7, 7, 4, 10, 4, 7,  true)
            .addController("Front Center Second from Bottom")
            .addCasingInfo("Ore Washer Casing", 10) // TODO (Count, and name)
            .addEnergyHatch("Bottom Layer", 1)
            .addMaintenanceHatch("Bottom Layer", 1)
            .addOutputBus("Bottom Layer", 1)
            .addOutputHatch("Bottom Layer", 1)
            .addInputBus("Front Right", 2)
            .addInputHatch("Front Right", 2)
            .addOtherStructurePart(ObamaTooltips.TT_motorCasing, "", 3)
            .addOtherStructurePart(ObamaTooltips.TT_pumpCasing, "", 4)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "", 5)
            .addStructureInfo("Reinforced Glass")
            .toolTipFinisher("Obama");
        return tt;
    }

    @Override
    public Vec3Impl getStartOffsetA() {
        return new Vec3Impl(3, 2, 0);
    }

    @Override
    public Vec3Impl getStartOffsetB() {
        return new Vec3Impl(0, 4, -3);
    }

    @Override
    public Vec3Impl getStartOffsetC() {
        return new Vec3Impl(3, 6, -3);
    }

    @Override
    public Vec3Impl getStartOffsetD() {
        return new Vec3Impl(3, 8, 0);
    }

    @Override
    public int getParalellsA() {
        return 64;
    }

    @Override
    public int getParalellsB() {
        return 128;
    }

    @Override
    public int getParalellsC() {
        return 192;
    }

    @Override
    public int getParalellsD() {
        return 256;
    }

    @Override
    public short getCasingMeta() {
        return MaragingSteel250.getmID();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getMachineTextureName() {
        return "TM_LARGE_ORE_WASHING_PLANT";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sOreWasherRecipes;
    }
}
