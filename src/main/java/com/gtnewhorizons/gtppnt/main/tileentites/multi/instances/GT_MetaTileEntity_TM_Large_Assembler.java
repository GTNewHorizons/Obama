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

import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;
import static com.github.technus.tectech.mechanics.structure.StructureUtility.ofHatchAdder;
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.MaragingSteel250;

public class GT_MetaTileEntity_TM_Large_Assembler extends GT_MetaTileEntity_TM_Factory implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Assembler(int aID) {
        super(aID, "multimachine.tm.large_assembling_machine", "Large Assembling Machine");
    }

    public GT_MetaTileEntity_TM_Large_Assembler(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Assembler(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"large assembler desc"};
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        Block reinforcedGlass = Block.getBlockFromName("IC2:blockAlloyGlass");
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                        {"    ", "GGGA", "GGGA", "GGGA", "cc~B"},
                        {"GGGA", "---A", "---A", "---A", "ccBB"},
                        {"GGGA", "-a-A", "-a-A", "---A", "vvBB"},
                        {"AAAA", "a--A", "a--A", "---A", "vvBB"},
                        {"AAAA", "AAAA", "AAAA", "IIAA", "OOOO"}
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                        {"    ", "GGGA", "GGGA", "GGGA", "cccB"},
                        {"GGGA", "----", "----", "----", "cccB"},
                        {"GGGA", "-a--", "-a--", "----", "vvvB"},
                        {"AAAA", "a-a-", "a-a-", "----", "vvvB"},
                        {"AAAA", "AAAA", "AAAA", "IIII", "OOOO"}
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                        {" ", "A", "A", "A", "A"},
                        {"A", "A", "A", "A", "A"},
                        {"A", "A", "A", "A", "A"},
                        {"A", "A", "A", "A", "A"},
                        {"A", "A", "A", "A", "A"}
                })
                .addElement('A', ofBlock(getCasingBlock(), getCasingMeta()))
                .addElement('G', ofBlockAnyMeta(reinforcedGlass))
                .addElement('B', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicMaintenanceToMachineList,
                                getTextureIndex(), 1),
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyIOToMachineList,
                                getTextureIndex(), 1),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('I', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addInputToMachineList,
                                getTextureIndex(), 2),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('O', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicOutputToMachineList,
                                getTextureIndex(), 3),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('a', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addArmCasingToMachineList,
                        getTextureIndex(), 4))
                .addElement('v', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addConveyorCasingToMachineList,
                        getTextureIndex(), 5))
                .addElement('c', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(), 6))
                .build();
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(2, 4, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(6, 4, 0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(4, 0, 0);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(-3, 0, 0);
    }

    @Override
    public int getMaxSlices() {
        return 2;
    }

    @Override
    public int getMinSlices() {
        return 0;
    }

    @Override
    public int getMinParrallel() {
        return 32;
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
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Maintenance/Energy Hatch",
                "2 - Input Hatch",
                "3 - Output Hatch",
                "4 - Robot Arm Casing",
                "5 - Conveyor Casing",
                "6 - Circuit Casing"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_ASSEMBLING_MACHINE";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sAssemblerRecipes;
    }
}
