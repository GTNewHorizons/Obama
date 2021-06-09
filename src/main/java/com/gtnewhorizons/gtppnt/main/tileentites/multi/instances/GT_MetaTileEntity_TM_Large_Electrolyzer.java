package com.gtnewhorizons.gtppnt.main.tileentites.multi.instances;

import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure.IConstructableStructureSliceableCapped;
import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.util.Vec3Impl;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Recipe;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;
import static com.github.technus.tectech.mechanics.structure.StructureUtility.ofHatchAdder;
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.Elwoodite;

public class GT_MetaTileEntity_TM_Large_Electrolyzer extends GT_MetaTileEntity_TM_Factory implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Electrolyzer(int aID) {
        super(aID, "multimachine.tm.large_electrolyzer", "Large Electrolyzer");
    }

    public GT_MetaTileEntity_TM_Large_Electrolyzer(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Electrolyzer(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"large electrolyzer desc"};
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        Block reinforcedGlass = Block.getBlockFromName("IC2:blockAlloyGlass");
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                        {"BBB~BBB"},
                        {"BBBBBBB"},
                        {"BBBBBBB"},
                        {"BBBBBBB"},
                        {"BBBBBBB"},
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{

                        {"GGGGGGG"},
                        {"c-w-w-c"},
                        {"A-----A"},
                        {"c-w-w-c"},
                        {"GGGGGGG"},
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                        {"BBBBBBB"},
                        {"BBBBBBB"},
                        {"BBBBBBB"},
                        {"BBBBBBB"},
                        {"BBBBBBB"}
                })
                .addElement('A', ofBlock(getCasingBlock(), getCasingMeta()))
                .addElement('G', ofBlockAnyMeta(reinforcedGlass))
                .addElement('B', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicToMachineList,
                                getTextureIndex(), 1),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('w', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addWireCasingToMachineList,
                        getTextureIndex(), 2))
                .addElement('c', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(), 3))
                .build();
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
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Classic Hatches",
                "2 - Wire Casing",
                "3 - Circuit Casing"
        };
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
