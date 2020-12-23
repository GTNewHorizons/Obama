package com.gtnewhorizons.gtppnt.main.loaders;

import com.github.bartimaeusnek.bartworks.system.material.BW_MetaGeneratedBlocks_CasingAdvanced_TE;
import com.github.bartimaeusnek.bartworks.system.material.BW_MetaGeneratedBlocks_Casing_TE;

import gregtech.api.enums.Textures;
import net.minecraft.block.Block;

//TODO Delete this whole class! Bart there HAS to be a better solution but I have NO FUCKING CLUE what it is!
public class CasingTextureLoader {
    public CasingTextureLoader() {
    }

    public static final byte texturePage = 0; //Using the [0][64-127] - GT++ page since the mods are mutually exclusive

    public static final byte BASIC_START_INDEX = 64; // 64 offset for the base GT textures
    private static final short[] BASIC_CASING_IDS = new short[]{30011};//No more than 16
    public static final byte ADVANCED_START_INDEX = 80; // BASIC_START_INDEX + 16
    private static final short[] ADVANCED_CASING_IDS = new short[]{30011};//No more than 16

    public static void patchTexturePage() {
        BW_MetaGeneratedBlocks_Casing_TE casing_basic_te = new BW_MetaGeneratedBlocks_Casing_TE();
        for (byte b = 0; b < BASIC_CASING_IDS.length; b = (byte) (b + 1)) {
            casing_basic_te.mMetaData = BASIC_CASING_IDS[b];
            Textures.BlockIcons.casingTexturePages[texturePage][b + BASIC_START_INDEX] =
                    (casing_basic_te.getTexture(Block.getBlockById(0), (byte) 0))[1];
        }

        BW_MetaGeneratedBlocks_CasingAdvanced_TE casingAdvancedTe = new BW_MetaGeneratedBlocks_CasingAdvanced_TE();
        for (byte b = 0; b < ADVANCED_CASING_IDS.length; b = (byte) (b + 1)) {
            casingAdvancedTe.mMetaData = ADVANCED_CASING_IDS[b];
            Textures.BlockIcons.casingTexturePages[texturePage][b + ADVANCED_START_INDEX] =
                    (casingAdvancedTe.getTexture(Block.getBlockById(0), (byte) 0))[1];
        }
    }
}
