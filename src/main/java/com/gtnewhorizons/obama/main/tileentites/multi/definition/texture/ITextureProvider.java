package com.gtnewhorizons.obama.main.tileentites.multi.definition.texture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.ITexture;

/**
 * <p>The abstract texture provider interface used to register and get textures.
 */
@SideOnly(Side.CLIENT)
public interface ITextureProvider {
    /**
     * <p>Register texture.
     */
    @SideOnly(Side.CLIENT)
    void registerIcons_TM();

    /**
     * <p>Get texture layers for a side from registered textures.
     *
     * @param aSide   the side being rendered
     * @param aFacing the facing side
     * @param aActive the activity state
     * @return the texture layers
     */
    @SideOnly(Side.CLIENT)
    ITexture[] getTexture_TM(byte aSide, byte aFacing, boolean aActive);
}
