package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import com.github.technus.tectech.thing.metaTileEntity.multi.base.render.TT_RenderedExtendedFacingTexture;
import com.gtnewhorizons.gtppnt.main.loaders.CasingTextureLoader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;

import java.util.HashMap;
import java.util.Map;

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

    default void addTextureToMap(String texture) {
        screenTextures.putIfAbsent(texture, new Textures.BlockIcons.CustomIcon(ICONSETS_PREFIX + texture));
    }

    default TT_RenderedExtendedFacingTexture getTextureFromMap(String texture) {
        return new TT_RenderedExtendedFacingTexture(screenTextures.get(texture));
    }

    @Override
    @SideOnly(Side.CLIENT)
    default void registerIcons_TM() {
        if (screenTextures.isEmpty())
            registerBaseIcons();
        registerMachineIcons();
    }

    default void registerBaseIcons() {
        registerScreenIcons();
        registerRepairIcons();
    }

    default void registerScreenIcons() {
        addTextureToMap(SCREEN_FRAME);
        addTextureToMap(SCREEN_INACTIVE);
        addTextureToMap(SCREEN_ACTIVE);
    }

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

    default void registerMachineIcons() {
        addTextureToMap(getMachineInactiveTexture());
        addTextureToMap(getMachineActiveTexture());
    }

    String getMachineTextureName();

    default String getMachineInactiveTexture() {
        return getMachineTextureName() + INACTIVE_SUFFIX;
    }

    default String getMachineActiveTexture() {
        return getMachineTextureName() + ACTIVE_SUFFIX;
    }

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

    default ITexture getHullTexture() {
        return Textures.BlockIcons.casingTexturePages[CasingTextureLoader.texturePage][getTextureIndex()];
    }

    int getTextureIndex();

    default ITexture getScreenFrameTexture() {
        return getTextureFromMap(SCREEN_FRAME);
    }

    default ITexture getScreenActivityTexture(boolean aActive) {
        return getTextureFromMap(aActive ? SCREEN_ACTIVE : SCREEN_INACTIVE);
    }

    default ITexture getMachineActivityTexture(boolean aActive) {
        return getTextureFromMap(aActive ? getMachineActiveTexture() : getMachineInactiveTexture());
    }

    default ITexture getRepairTexture() {
        return getTextureFromMap(getRepairState());
    }

    default String getRepairState() {
        return REPAIR_OK;
    }
}
