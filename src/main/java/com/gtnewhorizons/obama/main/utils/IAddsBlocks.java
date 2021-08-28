/*
 * Copyright 2021 The GTNH Team
 * 
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */

package com.gtnewhorizons.obama.main.utils;

import com.github.bartimaeusnek.bartworks.util.Pair;
import net.minecraft.block.Block;

import java.util.List;

public interface IAddsBlocks {
    static boolean addBlockToMachine(IAddsBlocks iAddsBlocks, Block block, Integer meta) {
        Pair<Block, Integer> compareAgainst = iAddsBlocks.getRequiredSpecialBlock();
        if (meta.equals(compareAgainst.getValue()) && block.equals(compareAgainst.getKey())) {
            iAddsBlocks.getSpecialBlocks().add(new Pair<>(block, meta));
            return true;
        }
        return false;
    }

    List<Pair<Block, Integer>> getSpecialBlocks();

    Pair<Block, Integer> getRequiredSpecialBlock();
}
