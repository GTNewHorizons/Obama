/*
 * Copyright 2021 The GTNH Team
 *
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */


package com.gtnewhorizons.obama.main.tileentities.single.misc;

import com.gtnewhorizons.obama.main.utils.IC2CellGetter;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

//TODO Verify function, tidy up.
public class Obama_MetaTileEntity_SteamConverter extends GT_MetaTileEntity_BasicTank {

    private FluidStack mOutputStack = new FluidStack(FluidRegistry.WATER, 0);

    public Obama_MetaTileEntity_SteamConverter(int aID, String aName, String aNameRegional, int aTier, int aInvSlotCount, String aDescription, ITexture... aTextures) {
        super(aID, aName, aNameRegional, aTier, aInvSlotCount, aDescription, aTextures);
        this.mFluid = IC2CellGetter.STEAM.getFluidStack(0);
    }

    public Obama_MetaTileEntity_SteamConverter(String aName, int aTier, int aInvSlotCount, String aDescription, ITexture[][][] aTextures) {
        super(aName, aTier, aInvSlotCount, aDescription, aTextures);
        this.mFluid = IC2CellGetter.STEAM.getFluidStack(0);
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        if (this.mOutputStack != null) {
            aNBT.setTag("mOutputStack", this.mOutputStack.writeToNBT(new NBTTagCompound()));
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        this.mOutputStack = FluidStack.loadFluidStackFromNBT(aNBT.getCompoundTag("mOutputStack"));
    }

    @Override
    public boolean isFluidChangingAllowed() {
        return false;
    }

    @Override
    public FluidStack getFillableStack() {
        return this.mFluid;
    }

    @Override
    public FluidStack getDrainableStack() {
        return this.mOutputStack;
    }

    @Override
    public boolean doesFillContainers() {
        return true;
    }

    @Override
    public boolean doesEmptyContainers() {
        return true;
    }

    @Override
    public boolean canTankBeFilled() {
        return true;
    }

    @Override
    public boolean canTankBeEmptied() {
        return true;
    }

    @Override
    public boolean displaysItemStack() {
        return false;
    }

    @Override
    public boolean displaysStackSize() {
        return false;
    }

    @Override
    public int getCapacity() {
        return 150000;
    }

    @Override
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        if (aTick % 20 == 0 && this.mFluid.amount % 150 == 0) {
            this.mOutputStack.amount = (this.mFluid.amount / 150);
            this.mFluid.amount = 0;
        }
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new Obama_MetaTileEntity_SteamConverter(this.mName, this.mTier, this.mInventory.length, this.mDescription, this.mTextures);
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity iGregTechTileEntity, byte b, byte b1, byte b2, boolean b3, boolean b4) {
        return new ITexture[0];
    }

    @Override
    public ITexture[][][] getTextureSet(ITexture[] iTextures) {
        return new ITexture[0][][];
    }

}
