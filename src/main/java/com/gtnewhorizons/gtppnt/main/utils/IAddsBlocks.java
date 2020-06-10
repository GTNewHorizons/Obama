/*
 * Copyright 2020 The GTNH Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT  OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.gtnewhorizons.gtppnt.main.utils;

import com.github.bartimaeusnek.bartworks.util.Pair;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.block.Block;

import java.util.List;

public interface IAddsBlocks {

    static boolean addBlockToMachine(IGregTechTileEntity iGregTechTileEntity, Block var2, Integer var3) {
        IAddsBlocks metaMachine = ((IAddsBlocks) iGregTechTileEntity.getMetaTileEntity());
        Pair<Block, Integer> compareAgainst = metaMachine.getRequiredSpecialBlock();
        if (var2.equals(compareAgainst.getKey()) && var3.equals(compareAgainst.getValue())) {
            metaMachine.getSpecialBlocks().add(new Pair<>(var2, var3));
            return true;
        }
        return false;
    }

    List<Pair<Block, Integer>> getSpecialBlocks();

    Pair<Block, Integer> getRequiredSpecialBlock();
}
