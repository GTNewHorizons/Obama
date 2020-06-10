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

package com.gtnewhorizons.gtppnt.main.client.guicontainers;


import com.gtnewhorizons.gtppnt.main.server.container.GT_Container_TieredBoiler;
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

