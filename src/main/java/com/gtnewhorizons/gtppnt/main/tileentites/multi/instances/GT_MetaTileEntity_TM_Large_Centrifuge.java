package com.gtnewhorizons.gtppnt.main.tileentites.multi.instances;

import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.util.Vec3Impl;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure.IConstructableStructureCells;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure.IConstructableStructureShapes;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure.IConstructableStructureSliceable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Recipe;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;
import static com.github.technus.tectech.thing.casing.TT_Container_Casings.sHintCasingsTT;
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.MaragingSteel250;

public class GT_MetaTileEntity_TM_Large_Centrifuge extends GT_MetaTileEntity_TM_Factory implements IConstructableStructureShapes {
    int paralells = 0;

    public GT_MetaTileEntity_TM_Large_Centrifuge(int aID) {
        super(aID, "multimachine.tm.large_centrifuge", "Large Centrifuge");//TODO Set cooler name + .lang
    }

    public GT_MetaTileEntity_TM_Large_Centrifuge(String aName) {
        super(aName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"description?"};//TODO Set proper description + .lang
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Centrifuge(mName);
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        Block reinforcedGlass = Block.getBlockFromName("IC2:blockAlloyGlass");
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_A,new String[][]{
                        {" cc "," A~ "," BB "},
                        {"cmmc","A--A","BBBB"},
                        {"cmmc","A--A","BBBB"},
                        {" cc "," AA "," BB "}
                })
                .addShape(TM_STRUCTURE_B,new String[][]{
                        {" cBc "," A~A "," cBc "},
                        {"cmmmc","A---A","cBBBc"},
                        {"BmmmB","A---A","BBBBB"},
                        {"cmmmc","A---A","cBBBc"},
                        {" cBc "," AAA "," cBc "}
                })
                .addShape(TM_STRUCTURE_C,new String[][]{
                        {"  ccc  ","  A~A  ","  ccc  "},
                        {" cmmmc "," A---A "," cBBBc "},
                        {"cmmBmmc","A-----A","cBBBBBc"},
                        {"cmBBBmc","A-----A","cBBBBBc"},
                        {"cmmBmmc","A-----A","cBBBBBc"},
                        {" cmmmc "," A---A "," cBBBc "},
                        {"  ccc  ","  AAA  ","  ccc  "}
                })
                .addShape(TM_STRUCTURE_D,new String[][]{
                        {"  ccccc  ","  AA~AA  ","  ccccc  "},
                        {" ccmmmcc "," A-----A "," ccmmmcc "},
                        {"ccmBBBmcc","A-------A","ccmBBBmcc"},
                        {"cmBBBBBmc","A-------A","cmBBBBBmc"},
                        {"cmBBBBBmc","A-------A","cmBBBBBmc"},
                        {"cmBBBBBmc","A-------A","cmBBBBBmc"},
                        {"ccmBBBmcc","A-------A","ccmBBBmcc"},
                        {" ccmmmcc "," A-----A "," ccmmmcc "},
                        {"  ccccc  ","  AAAAA  ","  ccccc  "}
                })
                .addElement('A', ofBlock(getCasingBlock(), getCasingMeta()))
                .addElement('G', ofBlockAnyMeta(reinforcedGlass))
                .addElement('B',ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicToMachineList,
                                getTextureIndex(),1),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('m',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMotorCasingToMachineList,
                        getTextureIndex(),2))
                .addElement('c',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(),3))
                .build();
    }

    @Override
    public Vec3Impl getStartOffsetA() {
        return new Vec3Impl(2,1,0);
    }

    @Override
    public Vec3Impl getStartOffsetB() {
        return new Vec3Impl(2,1,0);
    }

    @Override
    public Vec3Impl getStartOffsetC() {
        return new Vec3Impl(3,1,0);
    }

    @Override
    public Vec3Impl getStartOffsetD() {
        return new Vec3Impl(4,1,0);
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
        return 128;
    }

    @Override
    public int getParalellsD() {
        return 256;
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
        return MaragingSteel250.getmID();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Classic Hatch",
                "2 - Motor Casing",
                "3 - Circuit Casing"
        };
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getMachineTextureName() {
        return "TM_LARGE_CENTRIFUGE";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sCentrifugeRecipes;
    }
}
