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

import static com.github.bartimaeusnek.bartworks.system.material.BW_GT_MaterialReference.TungstenCarbide;
import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;
import static com.github.technus.tectech.mechanics.structure.StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Macerator extends GT_MetaTileEntity_TM_Factory implements
        IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Macerator(int aID) {
        super(aID, "multimachine.tm.large_macerator", "Large Macerator");
    }

    public GT_MetaTileEntity_TM_Large_Macerator(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Macerator(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"large macerator desc"};
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                        {" AAA ", "AAAAA", "AA~AA", "AAAAA", " AAA "}
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                        {" mBp ", "c---c", "B---B", "c---c", " pBm "}
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                        {" AAA ", "AAAAA", "AAAAA", "AAAAA", " AAA "}
                })
                .addElement('A', ofChain(
                        ofBlock(getCasingBlock(), getCasingMeta()),
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMaintenanceToMachineList,
                                getTextureIndex(), 0),
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyIOToMachineList,
                                getTextureIndex(), 0)))
                .addElement('B', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addInputToMachineList,
                                getTextureIndex(), 1),
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addOutputToMachineList,
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
    public short getCasingMeta() {
        return TungstenCarbide.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "Casing - Energy/Maintenance Hatch",
                "1 - Input/Output Hatch",
                "2 - Motor Casing",
                "3 - Piston Casing",
                "4 - Circuit Casing"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_MACERATOR";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sMaceratorRecipes;
    }
}
