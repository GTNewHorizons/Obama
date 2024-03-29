/*
 * Copyright 2021 The GTNH Team
 * 
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */

package com.gtnewhorizons.obama.main.server.container;

import com.gtnewhorizons.obama.main.tileentities.single.generators.GT_MetaTileEntity_TieredBoiler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.gui.GT_ContainerMetaTile_Machine;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class GT_Container_TieredBoiler extends GT_ContainerMetaTile_Machine {
    private final int STEAM_CAPACITY;
    public int mWaterAmount = 0;
    public int mSteamAmount = 0;
    public int mProcessingEnergy = 0;
    public int mTemperature = 2;

    public GT_Container_TieredBoiler(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aSteamCapacity) {
        super(aInventoryPlayer, aTileEntity);
        this.STEAM_CAPACITY = aSteamCapacity;
    }

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot(this.mTileEntity, 2, 116, 62));
        this.addSlotToContainer(new Slot(this.mTileEntity, 0, 44, 26));
        this.addSlotToContainer(new Slot(this.mTileEntity, 1, 44, 62));
        this.addSlotToContainer(new Slot(this.mTileEntity, 3, 116, 26));
    }

    public int getSlotCount() {
        return 4;
    }

    public int getShiftClickSlotCount() {
        return 1;
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (!this.mTileEntity.isClientSide() && this.mTileEntity.getMetaTileEntity() != null) {
            this.mTemperature = ((GT_MetaTileEntity_TieredBoiler) this.mTileEntity.getMetaTileEntity()).mTemperature;
            this.mProcessingEnergy = ((GT_MetaTileEntity_TieredBoiler) this.mTileEntity.getMetaTileEntity()).mProcessingEnergy;
            this.mSteamAmount = ((GT_MetaTileEntity_TieredBoiler) this.mTileEntity.getMetaTileEntity()).mSteam == null ? 0 : ((GT_MetaTileEntity_TieredBoiler) this.mTileEntity.getMetaTileEntity()).mSteam.amount;
            this.mWaterAmount = ((GT_MetaTileEntity_TieredBoiler) this.mTileEntity.getMetaTileEntity()).mFluid == null ? 0 : ((GT_MetaTileEntity_TieredBoiler) this.mTileEntity.getMetaTileEntity()).mFluid.amount;
            this.mTemperature = Math.min(54, Math.max(0, this.mTemperature * 54 / (((GT_MetaTileEntity_TieredBoiler) this.mTileEntity.getMetaTileEntity()).maxProgresstime() - 10)));
            this.mSteamAmount = Math.min(54, Math.max(0, this.mSteamAmount * 54 / (this.STEAM_CAPACITY - 100)));
            this.mWaterAmount = Math.min(54, Math.max(0, this.mWaterAmount * 54 / 15900));
            this.mProcessingEnergy = Math.min(14, Math.max(this.mProcessingEnergy > 0 ? 1 : 0, this.mProcessingEnergy * 14 / 1000));

            for (Object crafter : this.crafters) {
                ICrafting var1 = (ICrafting) crafter;
                var1.sendProgressBarUpdate(this, 100, this.mTemperature);
                var1.sendProgressBarUpdate(this, 101, this.mProcessingEnergy);
                var1.sendProgressBarUpdate(this, 102, this.mSteamAmount);
                var1.sendProgressBarUpdate(this, 103, this.mWaterAmount);
            }

        }
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 100:
                this.mTemperature = par2;
                break;
            case 101:
                this.mProcessingEnergy = par2;
                break;
            case 102:
                this.mSteamAmount = par2;
                break;
            case 103:
                this.mWaterAmount = par2;
        }

    }
}
