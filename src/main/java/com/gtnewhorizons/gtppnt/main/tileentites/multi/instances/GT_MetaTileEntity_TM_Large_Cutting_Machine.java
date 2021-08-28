package com.gtnewhorizons.gtppnt.main.tileentites.multi.instances;

import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.util.Vec3Impl;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure.IConstructableStructureSliceableCapped;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Recipe;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.MaragingSteel250;

import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;

public class GT_MetaTileEntity_TM_Large_Cutting_Machine extends GT_MetaTileEntity_TM_Factory implements
        IConstructableStructureSliceableCapped {
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

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        Block reinforcedGlass = Block.getBlockFromName("IC2:blockAlloyGlass");
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
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
                .addElement('A', ofBlock(getCasingBlock(), getCasingMeta()))
                .addElement('B', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicMaintenanceToMachineList,
                                getTextureIndex(), 1),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(reinforcedGlass))
                .addElement('I', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicInputToMachineList,
                                getTextureIndex(), 8),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('O', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicOutputToMachineList,
                                getTextureIndex(), 3),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('P', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyIOToMachineList,
                                getTextureIndex(), 4),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('m', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMotorCasingToMachineList,
                        getTextureIndex(), 5))
                .addElement('v', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addConveyorCasingToMachineList,
                        getTextureIndex(), 6))
                .addElement('c', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(), 7))
                .build();
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
