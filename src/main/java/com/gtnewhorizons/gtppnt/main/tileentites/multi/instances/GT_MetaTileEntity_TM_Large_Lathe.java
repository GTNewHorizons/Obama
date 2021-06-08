package com.gtnewhorizons.gtppnt.main.tileentites.multi.instances;

import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.util.Vec3Impl;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure.IConstructableStructureSliceable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;

import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;
import static com.github.technus.tectech.mechanics.structure.StructureUtility.ofBlock;
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.MaragingSteel250;

public class GT_MetaTileEntity_TM_Large_Lathe extends GT_MetaTileEntity_TM_Factory implements
        IConstructableStructureSliceable {
    public GT_MetaTileEntity_TM_Large_Lathe(int aID) {
        super(aID, "multimachine.tm.large_lathe", "Large Lathe");
    }

    public GT_MetaTileEntity_TM_Large_Lathe(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Lathe(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"large lathe desc"};
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                        {"         ", "~--------", "BBBBBBBBA"},
                        {"A        ", "mAAAAAAAA", "AA------A"},
                        {"         ", "p--------", "cBBBBBBBA"},
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                        {" ", "A", "B"},
                        {"A", "m", "A"},
                        {" ", "p", "c"},
                })
                .addElement('A', ofBlock(getCasingBlock(), getCasingMeta()))
                .addElement('B', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicToMachineList,
                                getTextureIndex(), 1),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('m', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMotorCasingToMachineList,
                        getTextureIndex(), 2))
                .addElement('p', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addPistonCasingToMachineList,
                        getTextureIndex(), 3))
                .addElement('c', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(), 4))
                .build();
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(0, 1, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(1, 1, 0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(1, 0, 0);
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
    public int getMinParallel() {
        return 16;
    }

    @Override
    public int getParalellsPerSlice() {
        return 16;
    }

    @Override
    public short getCasingMeta() {
        return MaragingSteel250.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Classic Hatches",
                "2 - Motor Casing",
                "3 - Piston Casing",
                "4 - Circuit Casing"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_LATHE";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sLatheRecipes;
    }
}
