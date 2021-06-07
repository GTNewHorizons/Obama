package com.gtnewhorizons.gtppnt.main.tileentites.multi.instances;

import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.util.Vec3Impl;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure.IConstructableStructureSliceableCapped;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.ItemList;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Recipe;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;
import static com.github.technus.tectech.mechanics.structure.StructureUtility.ofHatchAdder;
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.MaragingSteel250;

public class GT_MetaTileEntity_TM_Large_Extruder extends GT_MetaTileEntity_TM_Factory implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Extruder(int aID) {
        super(aID, "multimachine.tm.large_extruder", "Large Extruder");
    }

    public GT_MetaTileEntity_TM_Large_Extruder(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Extruder(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"large extruder desc"};
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        Block reinforcedGlass = Block.getBlockFromName("IC2:blockAlloyGlass");
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_START,new String[][]{
                        {" BBB ","B   B","B   ~","B   B"},
                        {" AOA ","AOOOA","AO-OA","AOOOA"},
                        {" --- "," --- "," --- ","AAAAA"},
                        {" AAA ","A---A","A---A","AAAAA"},
                        {" --- "," --- "," --- ","AAAAA"},
                        {" AAA ","A---A","A---A","AAAAA"},
                        {" --- "," --- "," --- ","AAAAA"},
                        {" III ","IAAAI","IA-AI","IAAAI"}
                })
                .addShape(TM_STRUCTURE_MIDDLE,new String[][]{
                        {" hhh ","h---h","Gp-pG","ccAcc"}
                })
                .addShape(TM_STRUCTURE_CAP,new String[][]{
                        {" BBB ","BBBBB","BBBBB","BBBBB"}
                })
                .addElement('A', ofBlock(getCasingBlock(), getCasingMeta()))
                .addElement('G', ofBlockAnyMeta(reinforcedGlass))
                .addElement('B',ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicMaintenanceToMachineList,
                                getTextureIndex(),1),
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyIOToMachineList,
                                getTextureIndex(),1),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('I',ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicInputToMachineList,
                                getTextureIndex(),2),
                        ofBlock(getCasingBlock(),getCasingMeta())))
                .addElement('O',ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicOutputToMachineList,
                                getTextureIndex(),3),
                        ofBlock(getCasingBlock(),getCasingMeta())))
                .addElement('p',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(),4))
                .addElement('h',ofBlockAdder(GT_MetaTileEntity_TM_Factory::addCoilToMachineList,
                        ItemList.Casing_Coil_Cupronickel.getBlock(),0))

                .addElement('c',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(),5))
                .build();
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(4,2,0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(4,2,-8);
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
        return 6;
    }

    @Override
    public int getParalellsPerSlice() {
        return 32;
    }

    @Override
    public short getCasingMeta() {
        return MaragingSteel250.getmID();
    }
    @Override
    @SideOnly(Side.CLIENT)
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Energy/Maintenance Hatch",
                "2 - Input Hatach",
                "3 - Output Hatch",
                "4 - Piston Casing",
                "5 - circuit Casing",
                "Coil - Coil of tier"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_EXTRUDER";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sExtruderRecipes;
    }
}
