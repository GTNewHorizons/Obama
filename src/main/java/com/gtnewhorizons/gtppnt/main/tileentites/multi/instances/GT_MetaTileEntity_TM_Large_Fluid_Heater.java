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
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.MaragingSteel250;

public class GT_MetaTileEntity_TM_Large_Fluid_Heater extends GT_MetaTileEntity_TM_Factory implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Fluid_Heater(int aID) {
        super(aID, "multimachine.tm.large_fluid_heater", "Large Fluid Heater");
    }

    public GT_MetaTileEntity_TM_Large_Fluid_Heater(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Fluid_Heater(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"Large Fluid Heater desc"};
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        Block reinforcedGlass = Block.getBlockFromName("IC2:blockAlloyGlass");
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_START,new String[][]{
                        {"BBB","B~B"},
                })
                .addShape(TM_STRUCTURE_MIDDLE,new String[][]{
                        {"hGh","BcB"}
                })
                .addShape(TM_STRUCTURE_CAP,new String[][]{
                        {"BBB","BBB"}
                })
                .addElement('G', ofBlockAnyMeta(reinforcedGlass))
                .addElement('B',ofChain(
                        ofBlock(getCasingBlock(), getCasingMeta()),
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicToMachineList,
                                getTextureIndex(),1)))
                .addElement('h',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addHeatingToMachineList,
                        getTextureIndex(),1))
                .addElement('c',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(),2))
                .build();
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(1,1,0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(1,1,-1);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0,0,-1);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(0,0,0);
    }

    @Override
    public int getMaxSlices() {
        return 8;
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
                "Casing - Classic Hatch",
                "1 - Heating Hatch",
                "2 - Circuit Hatch"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_FLUID_HEATER";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sFluidHeaterRecipes;
    }
}