/*
 * Copyright 2021 The GTNH Team
 *
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */


package com.gtnewhorizons.obama.main.client.guicontainers;


import com.gtnewhorizons.obama.main.server.container.GT_Container_TieredBoiler;
import gregtech.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_TieredBoiler extends GT_GUIContainerMetaTile_Machine {
    public GT_GUIContainer_TieredBoiler(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, String aTextureName, int aSteamCapacity) {
        super(new GT_Container_TieredBoiler(aInventoryPlayer, aTileEntity, aSteamCapacity), "gregtech:textures/gui/" + aTextureName);
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString("Tiered Boiler", 8, 4, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        if (this.mContainer != null) {
            int tScale = ((GT_Container_TieredBoiler) this.mContainer).mSteamAmount;
            if (tScale > 0) {
                this.drawTexturedModalRect(x + 70, y + 25 + 54 - tScale, 194, 54 - tScale, 10, tScale);
            }

            tScale = ((GT_Container_TieredBoiler) this.mContainer).mWaterAmount;
            if (tScale > 0) {
                this.drawTexturedModalRect(x + 83, y + 25 + 54 - tScale, 204, 54 - tScale, 10, tScale);
            }

            tScale = ((GT_Container_TieredBoiler) this.mContainer).mTemperature;
            if (tScale > 0) {
                this.drawTexturedModalRect(x + 96, y + 25 + 54 - tScale, 214, 54 - tScale, 10, tScale);
            }

            tScale = ((GT_Container_TieredBoiler) this.mContainer).mProcessingEnergy;
            if (tScale > 0) {
                this.drawTexturedModalRect(x + 117, y + 44 + 14 - tScale, 177, 14 - tScale, 15, tScale + 1);
            }
        }

    }
}

