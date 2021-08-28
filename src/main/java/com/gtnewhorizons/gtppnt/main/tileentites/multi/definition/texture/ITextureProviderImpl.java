package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.texture;

import com.github.technus.tectech.thing.metaTileEntity.multi.base.render.TT_RenderedExtendedFacingTexture;
import com.gtnewhorizons.gtppnt.main.loaders.CasingTextureLoader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>The interface Texture provider.
 */
@SideOnly(Side.CLIENT)
public interface ITextureProviderImpl extends ITextureProvider {
    Map<String, Textures.BlockIcons.CustomIcon> screenTextures = new HashMap<>();

    String ICONSETS_PREFIX = "iconsets/";
    String INACTIVE_SUFFIX = "_INACTIVE";
    String ACTIVE_SUFFIX = "_ACTIVE";

    String SCREEN_FRAME = "TM_SCREEN_BASE";
    String SCREEN_INACTIVE = "TM_SCREEN_BASE_INACTIVE";
    String SCREEN_ACTIVE = "TM_SCREEN_BASE_ACTIVE";

    String REPAIR_OK = "TM_REPAIR_OK";
    String REPAIR_STRUCTURE = "TM_REPAIR_STRUCTURE";
    String REPAIR_WRENCH = "TM_REPAIR_WRENCH";
    String REPAIR_SCREWDRIVER = "TM_REPAIR_SCREWDRIVER";
    String REPAIR_SOFTHAMMER = "TM_REPAIR_SOFTHAMMER";
    String REPAIR_HARDHAMMER = "TM_REPAIR_HARDHAMMER";
    String REPAIR_SOLDERINGIRON = "TM_REPAIR_SOLDERINGIRON";
    String REPAIR_CROWBAR = "TM_REPAIR_CROWBAR";

    /**
     * <p>Add texture to the static map.
     *
     * @param texture the texture file name
     */
    default void addTextureToMap(String texture) {
        screenTextures.putIfAbsent(texture, new Textures.BlockIcons.CustomIcon(ICONSETS_PREFIX + texture));
    }

    /**
     * <p>Gets texture from the static map.
     *
     * @param texture the texture file name
     * @return the texture
     */
    default TT_RenderedExtendedFacingTexture getTextureFromMap(String texture) {
        return new TT_RenderedExtendedFacingTexture(screenTextures.get(texture));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SideOnly(Side.CLIENT)
    default void registerIcons_TM() {
        if (screenTextures.isEmpty())
            registerBaseIcons();
        registerMachineIcons();
    }

    /**
     * <p>Register base textures.
     */
    default void registerBaseIcons() {
        registerScreenIcons();
        registerRepairIcons();
    }

    /**
     * <p>Register screen textures.
     */
    default void registerScreenIcons() {
        addTextureToMap(SCREEN_FRAME);
        addTextureToMap(SCREEN_INACTIVE);
        addTextureToMap(SCREEN_ACTIVE);
    }

    /**
     * <p>Register repair textures.
     */
    default void registerRepairIcons() {
        addTextureToMap(REPAIR_OK);
        addTextureToMap(REPAIR_STRUCTURE);
        addTextureToMap(REPAIR_WRENCH);
        addTextureToMap(REPAIR_SCREWDRIVER);
        addTextureToMap(REPAIR_SOFTHAMMER);
        addTextureToMap(REPAIR_HARDHAMMER);
        addTextureToMap(REPAIR_SOLDERINGIRON);
        addTextureToMap(REPAIR_CROWBAR);
    }

    /**
     * <p>Register machine textures.
     */
    default void registerMachineIcons() {
        addTextureToMap(getMachineInactiveTexture());
        addTextureToMap(getMachineActiveTexture());
    }

    /**
     * <p>Gets machine texture name.
     *
     * <p>Intended to be defined by the final machine class implementation.
     *
     * <p>Expects to get the name of the *.png file stored in the GregTech iconsets directory.
     * It is further expected that there is an active and inactive variant.
     *
     * @return the machine texture file name
     */
    String getMachineTextureName();

    /**
     * <p>Gets machine inactive texture.
     *
     * @return the machine inactive file name
     */
    default String getMachineInactiveTexture() {
        return getMachineTextureName() + INACTIVE_SUFFIX;
    }

    /**
     * <p>Gets machine active texture.
     *
     * @return the machine active file name
     */
    default String getMachineActiveTexture() {
        return getMachineTextureName() + ACTIVE_SUFFIX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SideOnly(Side.CLIENT)
    default ITexture[] getTexture_TM(byte aSide, byte aFacing, boolean aActive) {
        if (aSide != aFacing)
            return new ITexture[]{getHullTexture()};

        return new ITexture[]{
                getHullTexture(),
                getScreenFrameTexture(),
                getScreenActivityTexture(aActive),
                getMachineActivityTexture(aActive),
                getRepairTexture()
        };
    }

    /**
     * <p>Gets hull texture.
     *
     * @return the hull texture
     */
    default ITexture getHullTexture() {
        return Textures.BlockIcons.casingTexturePages[CasingTextureLoader.texturePage][getTextureIndex()];
    }

    /**
     * <p>Gets texture index.
     *
     * @return the texture index
     */
    int getTextureIndex();

    /**
     * <p>Gets screen frame texture.
     *
     * @return the screen frame texture
     */
    default ITexture getScreenFrameTexture() {
        return getTextureFromMap(SCREEN_FRAME);
    }

    /**
     * <p>Gets screen activity texture.
     *
     * @param aActive the activity state
     * @return the screen activity texture
     */
    default ITexture getScreenActivityTexture(boolean aActive) {
        return getTextureFromMap(aActive ? SCREEN_ACTIVE : SCREEN_INACTIVE);
    }

    /**
     * <p>Gets machine activity texture.
     *
     * @param aActive the activity state
     * @return the machine activity texture
     */
    default ITexture getMachineActivityTexture(boolean aActive) {
        return getTextureFromMap(aActive ? getMachineActiveTexture() : getMachineInactiveTexture());
    }

    /**
     * <p>Gets repair texture.
     *
     * @return the repair texture
     */
    default ITexture getRepairTexture() {
        return getTextureFromMap(getRepairState());
    }

    /**
     * <p>Gets repair state.
     *
     * @return the repair state
     */
    default String getRepairState() {
        return REPAIR_OK;
    }
}
