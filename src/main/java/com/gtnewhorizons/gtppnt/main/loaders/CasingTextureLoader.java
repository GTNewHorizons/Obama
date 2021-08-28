package com.gtnewhorizons.gtppnt.main.loaders;

import com.github.bartimaeusnek.bartworks.system.material.BW_MetaGeneratedBlocks_CasingAdvanced_TE;
import com.github.bartimaeusnek.bartworks.system.material.BW_MetaGeneratedBlocks_Casing_TE;

import com.gtnewhorizons.gtppnt.main.GTAFMod;
import gregtech.api.enums.Textures;

import java.util.HashMap;
import java.util.Map;

import static com.github.bartimaeusnek.bartworks.system.material.BW_GT_MaterialReference.*;
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.*;

public class CasingTextureLoader {
    public static final byte texturePage = 0; //Using the [0][64-127] - GT++ page since the mods are mutually exclusive
    private static final byte START_INDEX = 64; // 64 offset for the base GT textures
    private static final Map<Short, Byte> basicWerkstoffCasingToTexurePageID = new HashMap<>();
    private static final Map<Short, Byte> advancedWerkstoffCasingToTexurePageID = new HashMap<>();

    static {
        basicWerkstoffCasingToTexurePageID.put(MaragingSteel250.getmID(), (byte) (START_INDEX + 0));
        basicWerkstoffCasingToTexurePageID.put(Titanium.getmID(), (byte) (START_INDEX + 1));
        basicWerkstoffCasingToTexurePageID.put(Elwoodite.getmID(), (byte) (START_INDEX + 2));
        basicWerkstoffCasingToTexurePageID.put(TungstenCarbide.getmID(), (byte) (START_INDEX + 3));
        basicWerkstoffCasingToTexurePageID.put(BlueSteel.getmID(), (byte) (START_INDEX + 4));
        basicWerkstoffCasingToTexurePageID.put(ZirconiumCarbide.getmID(), (byte) (START_INDEX + 5));
        basicWerkstoffCasingToTexurePageID.put(EglinSteel.getmID(), (byte) (START_INDEX + 6));
        basicWerkstoffCasingToTexurePageID.put(RedSteel.getmID(), (byte) (START_INDEX + 7));
        basicWerkstoffCasingToTexurePageID.put(Complainium.getmID(), (byte) (START_INDEX + 8));
        advancedWerkstoffCasingToTexurePageID.put(Complainium.getmID(), (byte) (START_INDEX + 9));
        basicWerkstoffCasingToTexurePageID.put(Hereford690.getmID(), (byte) (START_INDEX + 10));
        basicWerkstoffCasingToTexurePageID.put(Rezron100.getmID(), (byte) (START_INDEX + 11));
        advancedWerkstoffCasingToTexurePageID.put(Rezron100.getmID(), (byte) (START_INDEX + 12));
        basicWerkstoffCasingToTexurePageID.put(TungstenSteel.getmID(), (byte) (START_INDEX + 13));
        basicWerkstoffCasingToTexurePageID.put(NickmolX.getmID(), (byte) (START_INDEX + 14));
        basicWerkstoffCasingToTexurePageID.put(MaragingSteel300.getmID(), (byte) (START_INDEX + 15));
        basicWerkstoffCasingToTexurePageID.put(MaragingSteel350.getmID(), (byte) (START_INDEX + 16));
        advancedWerkstoffCasingToTexurePageID.put(Staballoy.getmID(), (byte) (START_INDEX + 17));
    }

    public CasingTextureLoader() {
    }

    public static void load() {
        try {
            patchTexturePage();
        } catch (Exception e) {
            GTAFMod.LOGGER.catching(e);
        }
    }

    private static void patchTexturePage() {
        BW_MetaGeneratedBlocks_Casing_TE casing_basic_te = new BW_MetaGeneratedBlocks_Casing_TE();
        for (Map.Entry<Short, Byte> Rx : basicWerkstoffCasingToTexurePageID.entrySet()) {
            casing_basic_te.mMetaData = Rx.getKey();
            Textures.BlockIcons.casingTexturePages[texturePage][Rx.getValue()] =
                    casing_basic_te.getTexture(null, (byte) 0)[1];
        }

        BW_MetaGeneratedBlocks_CasingAdvanced_TE casingAdvancedTe = new BW_MetaGeneratedBlocks_CasingAdvanced_TE();
        for (Map.Entry<Short, Byte> Rx : advancedWerkstoffCasingToTexurePageID.entrySet()) {
            casingAdvancedTe.mMetaData = Rx.getKey();
            Textures.BlockIcons.casingTexturePages[texturePage][Rx.getValue()] =
                    casingAdvancedTe.getTexture(null, (byte) 0)[1];
        }
    }

    public static byte getBasicCasingTextureIndex(short werkstoffID) {
        return basicWerkstoffCasingToTexurePageID.get(werkstoffID);
    }

    public static byte getAdvancedCasingTextureIndex(short werkstoffID) {
        return advancedWerkstoffCasingToTexurePageID.get(werkstoffID);
    }
}
