package com.gtnewhorizons.gtppnt.main.items;

import gregtech.common.blocks.GT_Item_Casings_Abstract;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

import static com.github.technus.tectech.util.CommonValues.COSMIC_MARK;

public class GT_Item_CasingDT0 extends GT_Item_Casings_Abstract {
    public GT_Item_CasingDT0(Block par1) {
        super(par1);
    }

    @Override
    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
        aList.add(COSMIC_MARK);
    }
}
