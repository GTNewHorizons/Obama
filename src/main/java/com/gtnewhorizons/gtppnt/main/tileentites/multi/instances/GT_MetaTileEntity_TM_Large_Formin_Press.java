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
import net.minecraft.item.ItemStack;

import static com.github.bartimaeusnek.bartworks.system.material.BW_GT_MaterialReference.Titanium;
import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;
import static com.github.technus.tectech.mechanics.structure.StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Formin_Press extends GT_MetaTileEntity_TM_Factory implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Formin_Press(int aID) {
        super(aID, "multimachine.tm.large_forming_press", "Large Forming Press");
    }

    public GT_MetaTileEntity_TM_Large_Formin_Press(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Formin_Press(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{""};
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_START,new String[][]{
                        {"cIIIc","A---A","B~BBB"},
                        {"I-p-I","-----","BBBBB"},
                        {"IpApI","-----","BBBBB"},
                        {"I-p-I","-----","BBBBB"},
                        {"cIIIc","A---A","BBBBB"}

                })
                .addShape(TM_STRUCTURE_MIDDLE,new String[][]{
                        {"c---c"},
                        {"--p--"},
                        {"-pAp-"},
                        {"--p--"},
                        {"c---c"}
                })
                .addShape(TM_STRUCTURE_CAP,new String[][]{
                        {"A A"},
                        {"AAA"},
                        {" A "},
                        {"AAA"},
                        {"A A"}
                })
                .addElement('A', ofBlock(getCasingBlock(), getCasingMeta()))
                .addElement('B',ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyIOToMachineList,
                                getTextureIndex(),1),
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMaintenanceToMachineList,
                                getTextureIndex(),1),
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addOutputToMachineList,
                                getTextureIndex(),1),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('I',ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addInputToMachineList,
                                getTextureIndex(),2),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('p',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addPistonCasingToMachineList,
                        getTextureIndex(),3))
                .addElement('c',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(),4))
                .build();
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(1,2,0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(1,3,0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0,1,0);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(-1,0,0);
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
    public int getParalellsPerSlice() {
        return 32;
    }

    @Override
    public int getMinParrallel() {
        return 32;
    }

    @Override
    public short getCasingMeta() {
        return Titanium.getmID();
    }
    @Override
    @SideOnly(Side.CLIENT)
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Enrgy/Input/Maintenance Hatch",
                "2 - Input Hatch",
                "3 - Piston Casing",
                "4 - Circuit Casing"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_FORMING_PRESS";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sPressRecipes;
    }
}
