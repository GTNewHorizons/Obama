/*
 * Copyright 2019 The GTNH Team
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

package com.gtnewhorizons.gtppnt.main.tileentites.single;

import com.gtnewhorizons.gtppnt.main.client.guicontainers.GT_GUIContainer_TieredBoiler;
import com.gtnewhorizons.gtppnt.main.server.container.GT_Container_TieredBoiler;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtech.api.objects.GT_ItemStack;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.objects.XSTR;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import gregtech.common.GT_Pollution;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class GT_MetaTileEntity_TieredBoiler extends GT_MetaTileEntity_BasicTank {

    //TODO: MAKE PROPPER GUI OR REUSE EXISTING BOILER GUI

    public int mTemperature = 20;
    public int mProcessingEnergy = 0;
    public int mLossTimer = 0;
    public FluidStack mSteam = null;
    public boolean mHadNoWater = false;
    private long steamPerTier = steamPerTier(this.mTier);

    public GT_MetaTileEntity_TieredBoiler(int aID, String aName, String aNameRegional, int aTier) {
        super(aID, aName, aNameRegional, aTier, 4, new String[]{"A Tiered Boiler", "Produces " + (int) (steamPerTier((byte) aTier) * (20f / (float) calculateSteamRateAndPollution(aTier)[0])) + "L of Steam per second", "Causes " + calculateSteamRateAndPollution(aTier)[1] + " Pollution per second"});
    }

    public GT_MetaTileEntity_TieredBoiler(String aName, int aTier, String[] aDescription, ITexture[][][] aTextures) {
        super(aName, aTier, 4, aDescription, aTextures);
    }

    private static int[] calculateSteamRateAndPollution(int aTier) {
        return new int[]{20 - aTier, 15 + (aTier * aTier * 5)};
    }

    private static long steamPerTier(byte atier) {
        return (long) Math.ceil((150f * (float) (atier + 1)) / 4f * 3f);
    }

    @Override
    public Object getServerGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_Container_TieredBoiler(aPlayerInventory, aBaseMetaTileEntity, this.getCapacity());
    }

    @Override
    public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_GUIContainer_TieredBoiler(aPlayerInventory, aBaseMetaTileEntity, this.getLocalName(), this.getCapacity());
    }

    @Override
    public ITexture[][][] getTextureSet(ITexture[] aTextures) {
        ITexture[][][] rTextures = new ITexture[6][17][];

        for (byte i = -1; i < 16; ++i) {
            rTextures[0][i + 1] = new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[this.mTier][i + 1]};
            rTextures[1][i + 1] = new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[this.mTier][i + 1], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PIPE)};
            rTextures[2][i + 1] = new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[this.mTier][i + 1], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PIPE)};
            rTextures[3][i + 1] = new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[this.mTier][i + 1], new GT_RenderedTexture(Textures.BlockIcons.BOILER_FRONT)};
            rTextures[4][i + 1] = new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[this.mTier][i + 1], new GT_RenderedTexture(Textures.BlockIcons.BOILER_FRONT_ACTIVE)};
            rTextures[5][i + 1] = new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[this.mTier][i + 1]};
        }

        return rTextures;
    }

    @Override
    public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TieredBoiler(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    public boolean isElectric() {
        return false;
    }

    public boolean isPneumatic() {
        return false;
    }

    public boolean isSteampowered() {
        return false;
    }

    public boolean isSimpleMachine() {
        return false;
    }

    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
        ITexture[] tmp = this.mTextures[aSide >= 2 ? (aSide != aFacing ? 2 : (byte) (aActive ? 4 : 3)) : aSide][aColorIndex + 1];
        if (aSide != aFacing && tmp.length == 2) {
            tmp = new ITexture[]{tmp[0]};
        }

        return tmp;
    }

    public boolean isFacingValid(byte aFacing) {
        return aFacing > 1;
    }

    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    public boolean isValidSlot(int aIndex) {
        return true;
    }

    public int getProgresstime() {
        return this.mTemperature;
    }

    public int maxProgresstime() {
        return 500;
    }

    public boolean onRightclick(IGregTechTileEntity aBaseMetaTileEntity, EntityPlayer aPlayer) {
        if (!aBaseMetaTileEntity.isClientSide()) {
            if (aPlayer != null) {
                if (GT_Utility.areStacksEqual(aPlayer.getCurrentEquippedItem(), new ItemStack(Items.water_bucket, 1))) {
                    this.fill(Materials.Water.getFluid(1000 * aPlayer.getCurrentEquippedItem().stackSize), true);
                    aPlayer.getCurrentEquippedItem().func_150996_a(Items.bucket);
                } else {
                    aBaseMetaTileEntity.openGUI(aPlayer);
                }
            }
        }
        return true;
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
        return false;
    }

    public boolean displaysStackSize() {
        return false;
    }

    public boolean isFluidInputAllowed(FluidStack aFluid) {
        return GT_ModHandler.isWater(aFluid);
    }

    public FluidStack getDrainableStack() {
        return this.mSteam;
    }

    public FluidStack setDrainableStack(FluidStack aFluid) {
        this.mSteam = aFluid;
        return this.mSteam;
    }

    public boolean allowCoverOnSide(byte aSide, GT_ItemStack aCover) {
        return GregTech_API.getCoverBehavior(aCover.toStack()).isSimpleCover();
    }

    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setInteger("mLossTimer", this.mLossTimer);
        aNBT.setInteger("mTemperature", this.mTemperature);
        aNBT.setInteger("mProcessingEnergy", this.mProcessingEnergy);
        if (this.mSteam != null) {
            try {
                aNBT.setTag("mSteam", this.mSteam.writeToNBT(new NBTTagCompound()));
            } catch (Throwable ignored) {
            }
        }

    }

    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        this.mLossTimer = aNBT.getInteger("mLossTimer");
        this.mTemperature = aNBT.getInteger("mTemperature");
        this.mProcessingEnergy = aNBT.getInteger("mProcessingEnergy");
        this.mSteam = FluidStack.loadFluidStackFromNBT(aNBT.getCompoundTag("mSteam"));
    }

    public boolean allowPullStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, byte aSide, ItemStack aStack) {
        return GT_Mod.gregtechproxy.mAllowSmallBoilerAutomation;
    }

    public boolean allowPutStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, byte aSide, ItemStack aStack) {
        return GT_Mod.gregtechproxy.mAllowSmallBoilerAutomation;
    }

    public void doSound(byte aIndex, double aX, double aY, double aZ) {
        if (aIndex == 1) {
            GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(4), 2, 1.0F, aX, aY, aZ);

            for (int l = 0; l < 8; ++l) {
                this.getBaseMetaTileEntity().getWorld().spawnParticle("largesmoke", aX - 0.5D + (double) XSTR.XSTR_INSTANCE.nextFloat(), aY, aZ - 0.5D + (double) XSTR.XSTR_INSTANCE.nextFloat(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    public int getCapacity() {
        return 8000 * (mTier + 1) * 2;
    }

    public int getTankPressure() {
        return 100;
    }

    @Override
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        int[] logicvalues = calculateSteamRateAndPollution(mTier);
        this.singleBlockBoilerLogic(aBaseMetaTileEntity, aTick, 2, 40, logicvalues[0], logicvalues[1]);
    }

    protected void singleBlockBoilerLogic(IGregTechTileEntity aBaseMetaTileEntity, long aTick, int aMultiplier, int aTimer, long aTickDivider, int aPollution) {
        if (aBaseMetaTileEntity.isServerSide() && aTick > 20L) {
            if (this.mTemperature <= 20) {
                this.mTemperature = 20;
                this.mLossTimer = 0;
            }

            if (++this.mLossTimer > aTimer) {
                --this.mTemperature;
                this.mLossTimer = 0;
            }

            for (byte i = 1; this.mSteam != null && i < 6; ++i) {
                if (i != aBaseMetaTileEntity.getFrontFacing()) {
                    IFluidHandler tTileEntity = aBaseMetaTileEntity.getITankContainerAtSide(i);
                    if (tTileEntity != null) {
                        FluidStack tDrained = aBaseMetaTileEntity.drain(ForgeDirection.getOrientation(i), Math.max(1, this.mSteam.amount / 2), false);
                        if (tDrained != null) {
                            int tFilledAmount = tTileEntity.fill(ForgeDirection.getOrientation(i).getOpposite(), tDrained, false);
                            if (tFilledAmount > 0) {
                                tTileEntity.fill(ForgeDirection.getOrientation(i).getOpposite(), aBaseMetaTileEntity.drain(ForgeDirection.getOrientation(i), tFilledAmount, true), true);
                            }
                        }
                    }
                }
            }

            if (aTick % aTickDivider == 0L) {
                if (this.mTemperature > 100) {
                    if (GT_ModHandler.isWater(this.mFluid) && this.mFluid.amount > 0) {
                        if (this.mHadNoWater) {
                            GT_Log.exp.println("Boiler " + this.mName + " had no Water!");
                            aBaseMetaTileEntity.doExplosion(2048L);
                            return;
                        }


                        this.mFluid.amount -= this.mTier;
                        if (this.mSteam == null) {
                            this.mSteam = GT_ModHandler.getSteam(steamPerTier);
                        } else if (GT_ModHandler.isSteam(this.mSteam)) {
                            FluidStack var10000 = this.mSteam;
                            var10000.amount += steamPerTier;
                        } else {
                            this.mSteam = GT_ModHandler.getSteam(steamPerTier);
                        }
                    } else {
                        this.mHadNoWater = true;
                    }
                } else {
                    this.mHadNoWater = false;
                }
            }

            if (this.mSteam != null && this.mSteam.amount > this.getCapacity() * aMultiplier) {
                this.sendSound((byte) 1);
                this.mSteam.amount = (int) (((float) this.getCapacity() / 4f * 3f) * (float) aMultiplier);
            }

            if (this.mProcessingEnergy <= 0 && aBaseMetaTileEntity.isAllowedToWork() && this.mInventory[2] != null) {
                if ((!GT_Utility.isPartOfMaterials(this.mInventory[2], Materials.Coal) || GT_Utility.isPartOfOrePrefix(this.mInventory[2], OrePrefixes.block)) && (!GT_Utility.isPartOfMaterials(this.mInventory[2], Materials.Charcoal) || GT_Utility.isPartOfOrePrefix(this.mInventory[2], OrePrefixes.block)) && (!GT_Utility.isPartOfMaterials(this.mInventory[2], Materials.Lignite) || GT_Utility.isPartOfOrePrefix(this.mInventory[2], OrePrefixes.block)) && (!GT_Utility.isPartOfMaterials(this.mInventory[2], Materials.Diamond) || GT_Utility.isPartOfOrePrefix(this.mInventory[2], OrePrefixes.block)) && !GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[2], "fuelCoke")) {
                    if (!GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[2], OrePrefixes.block.get(Materials.Coal)) && !GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[2], OrePrefixes.block.get(Materials.Lignite)) && !GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[2], OrePrefixes.block.get(Materials.Charcoal)) && !GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[2], OrePrefixes.block.get(Materials.Diamond)) && (Block.getBlockFromItem(this.mInventory[2].getItem()) == null || !Block.getBlockFromItem(this.mInventory[2].getItem()).getUnlocalizedName().toLowerCase().contains("tile") || !Block.getBlockFromItem(this.mInventory[2].getItem()).getUnlocalizedName().toLowerCase().contains("charcoal") && !Block.getBlockFromItem(this.mInventory[2].getItem()).getUnlocalizedName().toLowerCase().contains("coal") && !Block.getBlockFromItem(this.mInventory[2].getItem()).getUnlocalizedName().toLowerCase().contains("diamond") && !Block.getBlockFromItem(this.mInventory[2].getItem()).getUnlocalizedName().toLowerCase().contains("coke") && !Block.getBlockFromItem(this.mInventory[2].getItem()).getUnlocalizedName().toLowerCase().contains("railcraft.cube") && !Block.getBlockFromItem(this.mInventory[2].getItem()).getUnlocalizedName().toLowerCase().contains("lignite"))) {
                        if (TileEntityFurnace.getItemBurnTime(this.mInventory[2]) >= 2000 && !this.mInventory[2].getUnlocalizedName().toLowerCase().contains("bucket") && !this.mInventory[2].getUnlocalizedName().toLowerCase().contains("cell")) {
                            this.mProcessingEnergy += TileEntityFurnace.getItemBurnTime(this.mInventory[2]) / 10;
                            aBaseMetaTileEntity.decrStackSize(2, 1);
                            if (XSTR.XSTR_INSTANCE.nextInt(2) == 0) {
                                aBaseMetaTileEntity.addStackToSlot(3, GT_OreDictUnificator.get(TileEntityFurnace.getItemBurnTime(this.mInventory[2]) >= 10000 ? (TileEntityFurnace.getItemBurnTime(this.mInventory[2]) >= 100000 ? OrePrefixes.dust : OrePrefixes.dustSmall) : OrePrefixes.dustTiny, Materials.Ash, 1L));
                            }
                        }
                    } else if (TileEntityFurnace.getItemBurnTime(this.mInventory[2]) / 10 > 0) {
                        this.mProcessingEnergy += TileEntityFurnace.getItemBurnTime(this.mInventory[2]) / 10;
                        aBaseMetaTileEntity.decrStackSize(2, 1);
                        aBaseMetaTileEntity.addStackToSlot(3, GT_OreDictUnificator.get(OrePrefixes.dust, !GT_Utility.isPartOfMaterials(this.mInventory[2], Materials.Lignite) && !GT_Utility.isPartOfMaterials(this.mInventory[2], Materials.Coal) && !Block.getBlockFromItem(this.mInventory[2].getItem()).getUnlocalizedName().toLowerCase().contains("coal") && !Block.getBlockFromItem(this.mInventory[2].getItem()).getUnlocalizedName().toLowerCase().contains("lignite") ? Materials.Ash : Materials.DarkAsh, 1L));
                    }
                } else if (TileEntityFurnace.getItemBurnTime(this.mInventory[2]) / 10 > 0) {
                    this.mProcessingEnergy += TileEntityFurnace.getItemBurnTime(this.mInventory[2]) / 10;
                    aBaseMetaTileEntity.decrStackSize(2, 1);
                    if (XSTR.XSTR_INSTANCE.nextInt(!GT_Utility.isPartOfMaterials(this.mInventory[2], Materials.Coal) && !GT_Utility.isPartOfMaterials(this.mInventory[2], Materials.Charcoal) ? (GT_Utility.isPartOfMaterials(this.mInventory[2], Materials.Lignite) ? 8 : 2) : 3) == 0) {
                        aBaseMetaTileEntity.addStackToSlot(3, GT_OreDictUnificator.get(OrePrefixes.dustTiny, !GT_Utility.isPartOfMaterials(this.mInventory[2], Materials.Lignite) && !GT_Utility.isPartOfMaterials(this.mInventory[2], Materials.Coal) ? Materials.Ash : Materials.DarkAsh, 1L));
                    }
                }
            }

            if (this.mTemperature < 500 * aMultiplier && this.mProcessingEnergy > 0 && aTick % 12L == 0L) {
                this.mProcessingEnergy -= aMultiplier;
                ++this.mTemperature;
            }

            if (this.mProcessingEnergy > 0 && aTick % 20L == 0L) {
                GT_Pollution.addPollution(this.getBaseMetaTileEntity(), aPollution);
            }

            aBaseMetaTileEntity.setActive(this.mProcessingEnergy > 0);
        }

    }

}
