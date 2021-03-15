package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.ITexture;

@SideOnly(Side.CLIENT)
public interface ITextureProvider {
    @SideOnly(Side.CLIENT)
    void registerIcons_TM();

    @SideOnly(Side.CLIENT)
    ITexture[] getTexture_TM(byte aSide, byte aFacing, boolean aActive);
}
