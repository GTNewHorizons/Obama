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
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.MaragingSteel250;

public class GT_MetaTileEntity_TM_Large_Thermal_Centrifuge extends GT_MetaTileEntity_TM_Factory implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Thermal_Centrifuge(int aID) {
        super(aID, "multimachine.tm.large_thermal_centrifuge", "Large Thermal Centrifuge");
    }

    public GT_MetaTileEntity_TM_Large_Thermal_Centrifuge(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Thermal_Centrifuge(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"thermal centrifuge desc"};
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        Block reinforcedGlass = Block.getBlockFromName("IC2:blockAlloyGlass");
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                        {" BBB ", "B---B", "B-~-B", "B---B", " BBB ", "E   E"},
                        {" ccc ", "mAAAm", "mAAAm", "mAAAm", " ccc ", "     "}
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                        {" ccc ", "m---m", "m---m", "m---m", " ccc "}
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                        {"  AAA  ", " AAAAA ", " AA-AA ", " AAAAA ", "  AAA  ", " E   E "},
                        {"       ", "   A   ", "  A-A  ", "   A   ", "       ", "       "},
                        {"       ", "   A   ", "  A-A  ", "   A   ", "       ", "       "},
                        {"  AAA  ", "  AAA  ", "  A-A  ", "  AAA  ", "  AAA  ", "A     A"},
                        {" AAAAA ", " A   A ", " A A A ", " A   A ", " AhhhA ", "       "},
                        {"AAAIAAA", "A     A", "A  A  A", "A     A", "AhhOhhA", "       "},
                        {"AAIIIAA", "A     A", "G  A  A", "A  A  A", "AhOOOhA", "       "},
                        {"AAAIAAA", "A     A", "G     A", "A     A", "AhhOhhA", "       "},
                        {" AAAAA ", " A   A ", " G   A ", " A   A ", " AhhhA ", "A     A"},
                        {"  AAA  ", "  AAA  ", "  GGA  ", "  AAA  ", "  AAA  ", "       "},
                })
                .addElement('A', ofBlock(getCasingBlock(), getCasingMeta()))
                .addElement('G', ofBlockAnyMeta(reinforcedGlass))
                .addElement('B', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicMaintenanceToMachineList,
                                getTextureIndex(), 1),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('E', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyIOToMachineList,
                                getTextureIndex(), 2),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('I', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicInputToMachineList,
                                getTextureIndex(), 3),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('O', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicOutputToMachineList,
                                getTextureIndex(), 4),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('m', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMotorCasingToMachineList,
                        getTextureIndex(), 5))
                .addElement('h', ofBlockAdder(GT_MetaTileEntity_TM_Factory::addCoilToMachineList,
                        ItemList.Casing_Coil_Cupronickel.getBlock(), 0))
                .addElement('c', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(), 6))
                .build();
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(2, 2, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(2, 2, -2);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 0, -1);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(1, 0, 0);
    }

    @Override
    public int getMaxSlices() {
        return 4;
    }

    @Override
    public int getMinSlices() {
        return 0;
    }

    @Override
    public int getMinParallel() {
        return 48;
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
                "1 - Maintenance Hatch",
                "2 - Energy Hatch",
                "3 - Input Hatch",
                "4 - Output Hatch",
                "5 - Motor Casing",
                "6 - Circuit Casing",
                "Coil - Coil of tier"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_THERMAL_CENTRIFUGE";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sThermalCentrifugeRecipes;
    }

}
