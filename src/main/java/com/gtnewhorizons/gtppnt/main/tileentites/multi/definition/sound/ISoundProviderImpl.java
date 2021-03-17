package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.sound;

import com.github.technus.tectech.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public interface ISoundProviderImpl extends ISoundProvider {
    Map<String, ResourceLocation> activitySounds = new HashMap<>();

    //TODO basically using TT resources for now, but then switch it over to our own
    //String RESOURCE_PREFIX = MODID + ":";
    String RESOURCE_PREFIX = Reference.MODID + ":";

    default void addActivitySoundToMap(String sound) {
        activitySounds.putIfAbsent(sound, new ResourceLocation(RESOURCE_PREFIX + sound));
    }

    default ResourceLocation getActivitySoundFromMap(String sound) {
        return activitySounds.get(sound);
    }

    @Override
    @SideOnly(Side.CLIENT)
    default void registerActivitySound_TM() {
        addActivitySoundToMap(getMachineSoundName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    default ResourceLocation getActivitySound_TM() {
        return getActivitySoundFromMap(getMachineSoundName());
    }

    String getMachineSoundName();
}
