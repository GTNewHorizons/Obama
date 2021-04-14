package com.gtnewhorizons.gtppnt.main.tileentites.multi.instances;

import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.util.Vec3Impl;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure.IConstructableStructureSliceable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;

import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;
import static com.github.technus.tectech.thing.casing.TT_Container_Casings.sHintCasingsTT;
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.MaragingSteel250;

public class GT_MetaTileEntity_TM_Large_Centrifuge extends GT_MetaTileEntity_TM_Factory implements IConstructableStructureSliceable {
    //region Constructors
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
    //endregion

    //region Structure
    @Override
    public short getCasingMeta() {
        return MaragingSteel250.getmID();
    }

    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Factory> getMachineStructure() {
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                        {"BBBB", "BB~B", "BBBB"},
                }) // 2 1 0
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                        {"AmmA", "c  A", "AAAA",},
                        {"AmmA", "c  A", "AAAA",},
                        {"AAAA", "AAAA", "AAAA",},
                }) // 2 1 -1
                .addElement('A', ofBlock(getCasingBlock(), getCasingMeta()))
                .addElement('B', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicToMachineList,
                                getTextureIndex(), sHintCasingsTT, 0),
                        ofBlock(getCasingBlock(), getCasingMeta())
                ))
                .addElement('c', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(), sHintCasingsTT, 1)
                )
                .addElement('m', ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMotorCasingToMachineList,
                        getTextureIndex(), sHintCasingsTT, 2)
                )
                .build();
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(2, 1, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(2, 1, -1);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 0, -3);
    }

    @Override
    public int getMaxSlices() {
        return 4;
    }

    @Override
    public int getParalellsPerSlice() {
        return 32;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{"CHANGE-ME"}; // TODO fix the description
    }
    //endregion

    //region Textures
    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_CENTRIFUGE";
    }
    //endregion

    //region Sounds
    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq"; // assets/tectech/sounds/fx_lo_freq.ogg
    }
    //endregion

    //region On Tick
    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sCentrifugeRecipes;
    }
    //endregion
}
