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
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.ZirconiumCarbide;

public class GT_MetaTileEntity_TM_Large_Mixer extends GT_MetaTileEntity_TM_Factory implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Mixer(int aID) {
        super(aID, "multimachine.tm.large_mixer", "Large Mixer");
    }

    public GT_MetaTileEntity_TM_Large_Mixer(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Mixer(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"large mixed desc"};
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        Block reinforcedGlass = Block.getBlockFromName("IC2:blockAlloyGlass");
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                        {" BBB ", "B   B", "B ~ B", "B   B", " BBB ", "E   E", "E   E"},
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                        {" cmc ", "cAAAc", "mAAAm", "cAAAc", " cmc "}
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                        {"         ", "         ", "   AAA   ", "  A   A  ", "  A A A  ", "  A   A  ", "   AAA   ", "         ", "         "},
                        {"         ", "         ", "  A   A  ", "         ", "    A    ", "         ", "  A   A  ", "         ", "         "},
                        {"   AAA   ", " AAAAAAA ", " AA   AA ", "AA     AA", "AA  A  AA", "AA     AA", " AA   AA ", "AAAAAAAAA", "A  AAA  A"},
                        {"   AAA   ", " AAAAAAA ", " AAAAAAA ", "IAAAAAAAI", "IAAAAAAAI", "IAAAAAAAI", " AAAAAAA ", " AAAAAAA ", "   OOO   "},
                        {"   AAA   ", " AAAAAAA ", " AA   AA ", "IA  r  AI", "IA rrr AI", "IA  r  AI", " AA   AA ", " AAAAAAA ", "   OOO   "},
                        {"   AAA   ", " AAAAAAA ", " AAGGGAA ", "AAGGGGGAA", "AAGGGGGAA", "AAGGGGGAA", " AAGGGAA ", " AAAAAAA ", "   AAA   "},
                        {"   AAA   ", " AAAAAAA ", " AA   AA ", "AA     AA", "AA     AA", "AA     AA", " AA   AA ", "AAAAAAAAA", "A  AAA  A"},
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
                //TODO add rotor casing
                .addElement('r', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addRotoToMachineList,
                        getTextureIndex(), 6))
                .addElement('c', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(), 7))
                .build();
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(2, 2, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(2, 2, -1);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 0, -1);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(2, 2, 0);
    }

    @Override
    public int getMaxSlices() {
        return 4;
    }

    @Override
    public int getParalellsPerSlice() {
        return 64;
    }

    @Override
    public short getCasingMeta() {
        return ZirconiumCarbide.getmID();
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
                "6 - Rotor Casing(heating atm)",
                "7 - Circuit Casing"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_MIXER";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sMixerRecipes;
    }
}
