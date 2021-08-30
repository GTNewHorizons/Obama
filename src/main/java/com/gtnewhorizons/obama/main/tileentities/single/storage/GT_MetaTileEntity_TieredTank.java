/*
 * Copyright 2021 The GTNH Team
 * 
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */

package com.gtnewhorizons.obama.main.tileentities.single.storage;

import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtech.api.objects.GT_RenderedTexture;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

//TODO Cross verify with the original tiered tanks from GT5
public class GT_MetaTileEntity_TieredTank extends GT_MetaTileEntity_BasicTank {

    public GT_MetaTileEntity_TieredTank(int aID, String aName, String aNameRegional, int aTier) {
        super(aID, aName, aNameRegional, aTier, 3, "Stores " + CommonSizeCompute(aTier) + "L of fluid");
    }

    public GT_MetaTileEntity_TieredTank(String aName, int aTier, String aDescription, ITexture[][][] aTextures) {
        super(aName, aTier, 3, aDescription, aTextures);
    }

    private static int CommonSizeCompute(int tier) {
        return ((int) (Math.pow(2, tier) * 32000));
    }

    public ITexture[][][] getTextureSet(ITexture[] aTextures) {
        return new ITexture[0][0][0];
    }

    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
        return aSide == 1 ? new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[this.mTier][aColorIndex + 1], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_STANK)} : new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[this.mTier][aColorIndex + 1]};
    }

    @Override
    @SuppressWarnings("deprecation")
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new GT_MetaTileEntity_TieredTank(this.mName, this.mTier, this.mDescription, this.mTextures);
    }

    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
    }

    public boolean onRightclick(IGregTechTileEntity aBaseMetaTileEntity, EntityPlayer aPlayer) {
        if (!aBaseMetaTileEntity.isClientSide()) {
            aBaseMetaTileEntity.openGUI(aPlayer);
        }
        return true;
    }

    public boolean isSimpleMachine() {
        return true;
    }

    public boolean isFacingValid(byte aFacing) {
        return true;
    }

    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
    }

    public final byte getUpdateData() {
        return 0;
    }

    public boolean doesFillContainers() {
        return true;
    }

    public boolean doesEmptyContainers() {
        return true;
    }

    public boolean canTankBeFilled() {
        return true;
    }

    public boolean canTankBeEmptied() {
        return true;
    }

    public boolean displaysItemStack() {
        return true;
    }

    public boolean displaysStackSize() {
        return false;
    }

    public String[] getInfoData() {
        return this.mFluid == null ? new String[]{EnumChatFormatting.BLUE + "Basic Tank" + EnumChatFormatting.RESET, "Stored Fluid:", EnumChatFormatting.GOLD + "No Fluid" + EnumChatFormatting.RESET, EnumChatFormatting.GREEN + Integer.toString(0) + " L" + EnumChatFormatting.RESET + " " + EnumChatFormatting.YELLOW + this.getCapacity() + " L" + EnumChatFormatting.RESET} : new String[]{EnumChatFormatting.BLUE + "Basic Tank" + EnumChatFormatting.RESET, "Stored Fluid:", EnumChatFormatting.GOLD + this.mFluid.getLocalizedName() + EnumChatFormatting.RESET, EnumChatFormatting.GREEN + Integer.toString(this.mFluid.amount) + " L" + EnumChatFormatting.RESET + " " + EnumChatFormatting.YELLOW + this.getCapacity() + " L" + EnumChatFormatting.RESET};
    }

    public boolean isGivingInformation() {
        return true;
    }

    public int getCapacity() {
        return CommonSizeCompute(this.mTier);
    }

    public int getTankPressure() {
        return 100;
    }
}
