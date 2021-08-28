package com.gtnewhorizons.obama.main.tileentites.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.structure.IConstructableStructureSliceableCapped;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;

import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.MaragingSteel250;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Cutting_Machine extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Cutting_Machine> implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Cutting_Machine(int aID) {
        super(aID, "multimachine.tm.large_cutting_machine", "Large Cutting Machine");
    }

    public GT_MetaTileEntity_TM_Large_Cutting_Machine(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Cutting_Machine(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"large saw mill dec"};
    }

    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Cutting_Machine>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Cutting_Machine>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Cutting_Machine> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Cutting_Machine>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                    {"   ", "AAA", "A  ", "A  ", "I~B"},
                    {"PAc", "A  ", "AGG", "AGG", "IAA"},
                    {"PAc", "A  ", "A  ", "A  ", "IAv"},
                    {"   ", "AAA", "AAm", "AAA", "IAA"},
                    {"   ", "   ", "  m", "  A", "  A"},
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                    {" ", "A", " ", " ", "B"},
                    {"c", " ", "G", "G", "A"},
                    {"c", " ", " ", " ", "v"},
                    {" ", "A", "m", "A", "A"},
                    {" ", " ", "m", "A", "A"}
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                    {"  ", "AA", "  ", "  ", "BO"},
                    {"AA", " A", "G ", "G ", "AO"},
                    {"AA", " A", "G ", "G ", "AO"},
                    {"  ", "AA", "A ", "A ", "AO"},
                })
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('I', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addInputToMachineList, t.getTextureIndex(), 8),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('O', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addOutputToMachineList, t.getTextureIndex(), 3),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('P', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 4),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('m', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMotorCasingToMachineList, t.getTextureIndex(), 5)))
                .addElement('v', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addConveyorCasingToMachineList, t.getTextureIndex(), 6)))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 7)))
                .build();
        }
    
    };

    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Cutting_Machine> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        return null;
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(1, 4, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(-2, 4, 0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(-1, 0, 0);
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
    public String[] getStructureDescription(ItemStack itemStack) {
        //TODO Locelise this
        return new String[]{
                "1 - Maintenance hatch",
                "2 - Input Hatch",
                "3 - Output Hatch",
                "4 - Energy Hatch",
                "5 - Motor Casing",
                "6 - Conveyor Casing",
                "7 - Circuit Casing"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_CUTTING_MACHINE";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sCutterRecipes;
    }
}
