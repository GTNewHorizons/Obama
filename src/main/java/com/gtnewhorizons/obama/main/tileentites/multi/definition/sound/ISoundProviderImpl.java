package com.gtnewhorizons.obama.main.tileentites.multi.definition.sound;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>The sound provider interface implementation used to register and get sounds.
 */
@SideOnly(Side.CLIENT)
public interface ISoundProviderImpl extends ISoundProvider {
//    String RESOURCE_PREFIX = Reference.MODID + ":";
    String RESOURCE_PREFIX = "gregtech:";
    Map<String, ResourceLocation> activitySounds = new HashMap<>();

    /**
     * <p>Register sound to the static map.
     *
     * @param sound the sound file name
     */
    default void addActivitySoundToMap(String sound) {
        activitySounds.putIfAbsent(sound, new ResourceLocation(RESOURCE_PREFIX + sound));
    }

    /**
     * <p>Gets registered sound from the static map.
     *
     * @param sound the sound file name
     * @return the registered sound
     */
    default ResourceLocation getActivitySoundFromMap(String sound) {
        return activitySounds.get(sound);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SideOnly(Side.CLIENT)
    default void registerActivitySound_TM() {
        addActivitySoundToMap(getMachineSoundName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SideOnly(Side.CLIENT)
    default ResourceLocation getActivitySound_TM() {
        return getActivitySoundFromMap(getMachineSoundName());
    }

    /**
     * <p>Gets the machine sound name.
     *
     * <p>Intended to be defined by the final machine class implementation.
     *
     * <p>Expects to get the name of the *.ogg file stored in the sounds assets directory.
     *
     * @return the machine sound file name
     */
    String getMachineSoundName();
}
