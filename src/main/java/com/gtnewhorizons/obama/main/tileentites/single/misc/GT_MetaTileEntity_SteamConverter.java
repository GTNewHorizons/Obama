/*
 * Copyright 2020 The GTNH Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT  OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.gtnewhorizons.obama.main.tileentites.single.misc;

import com.gtnewhorizons.obama.main.utils.IC2CellGetter;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

//TODO Verify function, tidy up.
public class GT_MetaTileEntity_SteamConverter extends GT_MetaTileEntity_BasicTank {

    private FluidStack mOutputStack = new FluidStack(FluidRegistry.WATER, 0);

    public GT_MetaTileEntity_SteamConverter(int aID, String aName, String aNameRegional, int aTier, int aInvSlotCount, String aDescription, ITexture... aTextures) {
        super(aID, aName, aNameRegional, aTier, aInvSlotCount, aDescription, aTextures);
        this.mFluid = IC2CellGetter.STEAM.getFluidStack(0);
    }

    public GT_MetaTileEntity_SteamConverter(String aName, int aTier, int aInvSlotCount, String aDescription, ITexture[][][] aTextures) {
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
        return new GT_MetaTileEntity_SteamConverter(this.mName, this.mTier, this.mInventory.length, this.mDescription, this.mTextures);
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
