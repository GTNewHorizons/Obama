package com.gtnewhorizons.gtppnt.main.tileentites.multi;

import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.GT_MetaTileEntity_EM_transformer;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory_Base;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.item.ItemStack;

import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;
import static com.github.technus.tectech.thing.casing.GT_Block_CasingsTT.textureOffset;
import static com.github.technus.tectech.thing.casing.TT_Container_Casings.sBlockCasingsTT;
import static com.github.technus.tectech.thing.casing.TT_Container_Casings.sHintCasingsTT;
import static gregtech.api.GregTech_API.sBlockCasings1;

public class GT_MetaTileEntity_TM_Large_Centrifuge extends GT_MetaTileEntity_TM_Factory_Base {
    public GT_MetaTileEntity_TM_Large_Centrifuge(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public GT_MetaTileEntity_TM_Large_Centrifuge(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Centrifuge(mName);
    }

    private static final IStructureDefinition<GT_MetaTileEntity_TM_Large_Centrifuge> STRUCTURE_DEFINITION =
            StructureDefinition.<GT_MetaTileEntity_TM_Large_Centrifuge>builder()
                    .addShape("main",new String[][]{
                            {"111", "1~1", "111",},
                            {"111", "101", "111",},
                            {"111", "111", "111",},
                    })
                    .addElement('0', ofBlock(sBlockCasings1,15))
                    .addElement('1', ofChain(
                            ofHatchAdder(GT_MetaTileEntity_TM_Large_Centrifuge::addEnergyIOToMachineList,textureOffset,sHintCasingsTT,0),
                            onElementPass(t->t.casingCount++,ofBlock(sBlockCasingsTT,0))
                    ))
                    .build();

    private int casingCount = 0;
    private int sliceCount = 0;

    //@Override
    //public IStructureDefinition<GT_MetaTileEntity_TM_Large_Centrifuge> getStructure_EM() {
    //    return STRUCTURE_DEFINITION;
    //}

    //TODO undo, this is like this for hotswap only
    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Centrifuge> getStructure_EM() {
        return StructureDefinition.<GT_MetaTileEntity_TM_Large_Centrifuge>builder()
                .addShape("front",new String[][]{
                        {"111", "1~1", "111",},
                })
                .addShape("slice",new String[][]{
                        {"111", "101", "111",},
                })
                .addShape("back",new String[][]{
                        {"111", "111", "111",},
                })
                .addElement('0', ofBlock(sBlockCasings1,15))
                .addElement('1', ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Large_Centrifuge::addEnergyIOToMachineList,textureOffset,sHintCasingsTT,0),
                        onElementPass(t->t.casingCount++,ofBlock(sBlockCasingsTT,0))
                ))
                .build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected Textures.BlockIcons.CustomIcon getScreenOFF() {
        return new Textures.BlockIcons.CustomIcon("iconsets/TM_LARGE_FACTORY_IDLE");
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected Textures.BlockIcons.CustomIcon getScreenON() {
        return new Textures.BlockIcons.CustomIcon("iconsets/TM_LARGE_FACTORY_ACTIVE");
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected int getTextureID() {
        return 0;
    }

    @Override
    protected boolean checkMachine_EM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        boolean isValid;
        this.sliceCount = 0;

        isValid = this.structureCheck_EM("front", 1, 1, 0);

        if (isValid) {
            for (int i = 0; i < 8; i++) {
                if (structureCheck_EM("slice", 1, 1, -1 - i)) {
                    this.sliceCount++;
                } else {
                    break;
                }
            }
        }

        if (this.sliceCount > 0) {
            isValid = this.structureCheck_EM("back", 1, 1, -1 - this.sliceCount);
        } else {
            isValid = false;
        }

        return isValid;
    }

    @Override
    public void construct(ItemStack itemStack, boolean hintsOnly) {
        int sliceCount = Math.min(itemStack.stackSize, 8);

        structureBuild_EM("front", 1, 1, 0, hintsOnly, itemStack);

        for (int i = 0; i < sliceCount; i++) {
            structureBuild_EM("slice", 1, 1, -1 - i, hintsOnly, itemStack);
        }

        structureBuild_EM("back", 1, 1, -1 - sliceCount, hintsOnly, itemStack);
    }
}
