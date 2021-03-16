package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public interface ISoundProvider {
    @SideOnly(Side.CLIENT)
    void registerActivitySound_TM();

    @SideOnly(Side.CLIENT)
    ResourceLocation getActivitySound_TM();
}
