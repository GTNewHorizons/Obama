package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.sound;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

/**
 * <p>The abstract sound provider interface used to register and get sounds.
 */
@SideOnly(Side.CLIENT)
public interface ISoundProvider {
    /**
     * <p>Register sound.
     */
    @SideOnly(Side.CLIENT)
    void registerActivitySound_TM();

    /**
     * <p>Gets the registered sound.
     *
     * @return the sound
     */
    @SideOnly(Side.CLIENT)
    ResourceLocation getActivitySound_TM();
}
