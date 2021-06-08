package com.gtnewhorizons.gtppnt.main.tileentites.multi.instances;

import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.util.Vec3Impl;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure.IConstructableStructureCells;
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

public class GT_MetaTileEntity_TM_Large_Ore_Washer extends GT_MetaTileEntity_TM_Factory implements IConstructableStructureCells {
    int paralells = 0;

    public GT_MetaTileEntity_TM_Large_Ore_Washer(int aID) {
        super(aID, "multimachine.tm.large_ore_washing_plant", "Large Ore Washing Plant");
    }

    public GT_MetaTileEntity_TM_Large_Ore_Washer(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Ore_Washer(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"large washer desc"};
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        Block reinforcedGlass = Block.getBlockFromName("IC2:blockAlloyGlass");
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_A, new String[][]{
                        {"   Acc ", "   AGG ", "AGG~cc ", "BBBAAA "},
                        {"   A--c", "   f--G", "G--f--c", "BBBAmmA"},
                        {"   A--c", "   f--G", "G--f--c", "BBBAmmA"},
                        {"   AFF ", "   AAI ", "AGGAAI ", "BBBAAA "}
                })
                .addShape(TM_STRUCTURE_B, new String[][]{
                        {"AAA ", "Aff ", "Aff ", "    ", "    ", "    "},
                        {"F--c", "A--G", "A--c", "AmmA", "    ", "    "},
                        {"F--c", "I--G", "I--c", "AmmA", "    ", "    "},
                        {" cc ", " GG ", " cc ", " AA ", "A  A", "A  A"}
                })
                .addShape(TM_STRUCTURE_C, new String[][]{
                        {" FFA", " IA ", " IA ", " AA ", "A   ", "A   ", "A   ", "A   "},
                        {"c--A", "G--f", "c--f", "Amm ", "    ", "    ", "    ", "    "},
                        {"c--A", "G--f", "c--f", "Amm ", "    ", "    ", "    ", "    "},
                        {" cc ", " GG ", " cc ", " AA ", "A   ", "A   ", "A   ", "A   "}

                })
                .addShape(TM_STRUCTURE_D, new String[][]{
                        {" cc ", " GG ", " cc ", " AA ", "A  A", "A  A", "A   ", "A   "},
                        {"c--A", "G--I", "c--I", "AmmA"},
                        {"c--A", "G--A", "c--A", "AmmA"},
                        {" AA ", " ff ", " ff ", "    "}
                })
                .addElement('A', ofBlock(getCasingBlock(), getCasingMeta()))
                .addElement('G', ofBlockAnyMeta(reinforcedGlass))
                .addElement('B', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicMaintenanceToMachineList,
                                getTextureIndex(), 1),
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyIOToMachineList,
                                getTextureIndex(), 1),
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addOutputToMachineList,
                                getTextureIndex(), 1),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('I', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addInputToMachineList,
                                getTextureIndex(), 2),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('m', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMotorCasingToMachineList,
                        getTextureIndex(), 3))
                .addElement('f', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addPumpCasingToMachineList,
                        getTextureIndex(), 4))
                .addElement('F', ofChain(
                        ofBlock(getCasingBlock(), getCasingMeta()),
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addPumpCasingToMachineList,
                                getTextureIndex(), 10)))
                .addElement('c', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(), 5))
                .build();
    }

    @Override
    public Vec3Impl getStartOffsetA() {
        return new Vec3Impl(3, 2, 0);
    }

    @Override
    public Vec3Impl getStartOffsetB() {
        return new Vec3Impl(0, 4, -3);
    }

    @Override
    public Vec3Impl getStartOffsetC() {
        return new Vec3Impl(3, 6, -3);
    }

    @Override
    public Vec3Impl getStartOffsetD() {
        return new Vec3Impl(3, 8, 0);
    }

    @Override
    public int getParalellsA() {
        return 64;
    }

    @Override
    public int getParalellsB() {
        return 128;
    }

    @Override
    public int getParalellsC() {
        return 192;
    }

    @Override
    public int getParalellsD() {
        return 256;
    }

    @Override
    public int getParalellsABCD() {
        return paralells;
    }

    @Override
    public void setParalellsABCD(int paralells) {
        this.paralells = paralells;
    }

    @Override
    public short getCasingMeta() {
        return MaragingSteel250.getmID();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Output/Maintenance/Energy Hatch",
                "2 - Input Hatch",
                "3 - Motor Casing",
                "4 - Pump Casing",
                "5 - Circuit Casing"
        };
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getMachineTextureName() {
        return "TM_LARGE_ORE_WASHING_PLANT";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sOreWasherRecipes;
    }
}
