package com.gtnewhorizons.gtppnt.main.tileentites.multi.instances;

import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure.IConstructableStructureCells;
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

import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;
import static com.github.technus.tectech.mechanics.structure.StructureUtility.ofHatchAdder;
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.EglinSteel;

public class GT_MetaTileEntity_TM_Large_Sifter extends GT_MetaTileEntity_TM_Factory implements IConstructableStructureCells {
    int paralells = 0;

    public GT_MetaTileEntity_TM_Large_Sifter(int aID) {
        super(aID, "multimachine.tm.large_sifting_machine", "Large Sifting Machine");
    }

    public GT_MetaTileEntity_TM_Large_Sifter(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Sifter(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"large sifter desc"};
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_A,new String[][]{
                        {"AppA","AAAA","A  A","A  A","A  A","A  A","~AAA","A  A"},
                        {"cssc","A  A","    ","    ","    ","    ","A  A","    "},
                        {"cssc","A  A","    ","    ","    ","    ","A  A","    "},
                        {"AppA","AAAA","A  A","A  A","A  A","A  A","AAAA","A  A"},
                })
                .addShape(TM_STRUCTURE_B,new String[][]{
                        {" pp ","AAAA"},
                        {"cssc","A  A","    ","    ","A  A","    "},
                        {"cssc","A  A","    ","    ","A  A","    "},
                        {"AppA","AAAA","A  A","A  A","AAAA","A  A"},
                })
                .addShape(TM_STRUCTURE_C,new String[][]{
                        {" pp "},
                        {"cssc","    ","A  A","    "},
                        {"cssc","    ","A  A","    "},
                        {"AppA","A  A","A  A","A  A"},
                })
                .addShape(TM_STRUCTURE_D,new String[][]{
                        {"    "," pp "},
                        {"    ","cssc"},
                        {"    ","cssc"},
                        {"AAAA","AppA","A  A"}

                })
                .addElement('A',ofChain(
                        ofBlock(getCasingBlock(), getCasingMeta()),
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicToMachineList,
                                getTextureIndex(),1)))
                .addElement('s',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addFilterToMachineList,
                        getTextureIndex(),1))
                .addElement('p',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addPistonCasingToMachineList,
                        getTextureIndex(),2))
                .addElement('c',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(),3))
                .build();
    }

    @Override
    public Vec3Impl getStartOffsetA() {
        return new Vec3Impl(0,6,0);
    }

    @Override
    public Vec3Impl getStartOffsetB() {
        return new Vec3Impl(0,4,-3);
    }

    @Override
    public Vec3Impl getStartOffsetC() {
        return new Vec3Impl(0,2,-6);
    }

    @Override
    public Vec3Impl getStartOffsetD() {
        return new Vec3Impl(0,1,-9);
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
    public void setParalellsABCD(int paralells) {
        this.paralells = paralells;
    }

    @Override
    public int getParalellsABCD() {
        return paralells;
    }

    @Override
    public short getCasingMeta() {
        return EglinSteel.getmID();
    }
    @Override
    @SideOnly(Side.CLIENT)
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "Casing - Classic Hatches",
                "1 - Filter Casing",
                "2 - Piston Casing",
                "3 - Circuit Casing"
        };
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
