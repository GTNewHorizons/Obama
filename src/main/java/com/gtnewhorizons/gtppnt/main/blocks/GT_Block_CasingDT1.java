package com.gtnewhorizons.gtppnt.main.blocks;

import com.gtnewhorizons.gtppnt.main.items.CustomItemList;
import com.gtnewhorizons.gtppnt.main.items.GT_Item_CasingDT0;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Textures;
import gregtech.api.objects.GT_CopiedBlockTexture;
import gregtech.api.util.GT_LanguageManager;
import gregtech.common.blocks.GT_Block_Casings_Abstract;
import gregtech.common.blocks.GT_Material_Casings;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

import static com.github.technus.tectech.TecTech.tectechTexturePage1;

public class GT_Block_CasingDT1 extends GT_Block_Casings_Abstract {
    public static final byte texturePage = tectechTexturePage1;
    public static final short textureOffset = (texturePage << 7) + 48;//Start of PAGE 8 (which is the 9th page)  (8*128)+32
    private static IIcon[] tM = new IIcon[16];

    public GT_Block_CasingDT1() {
        super(GT_Item_CasingDT0.class, "gt.blockcasingsDT1", GT_Material_Casings.INSTANCE);
        for (byte b = 0; b < 16; b = (byte) (b + 1)) {
            Textures.BlockIcons.casingTexturePages[texturePage][b + 48] = new GT_CopiedBlockTexture(this, 6, b);
            /*IMPORTANT for block recoloring**/
        }

        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".0.name", "Casing 16");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".1.name", "Casing 17");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".2.name", "Casing 18");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".3.name", "Casing 19");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".4.name", "Casing 20");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".5.name", "Casing 21");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".6.name", "Casing 22");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".7.name", "Casing 23");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".8.name", "Casing 24");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".9.name", "Casing 25");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".10.name", "Casing 26");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".11.name", "Casing 27");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".12.name", "Casing 28");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".13.name", "Casing 29");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".14.name", "Casing 30");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".15.name", "Casing 31");

        CustomItemList.tM_TestCasing_0.set(new ItemStack(this, 1, 0));
        CustomItemList.tM_TestCasing_1.set(new ItemStack(this, 1, 1));
        CustomItemList.tM_TestCasing_2.set(new ItemStack(this, 1, 2));
        CustomItemList.tM_TestCasing_3.set(new ItemStack(this, 1, 3));
        CustomItemList.tM_TestCasing_4.set(new ItemStack(this, 1, 4));
        CustomItemList.tM_TestCasing_5.set(new ItemStack(this, 1, 5));
        CustomItemList.tM_TestCasing_6.set(new ItemStack(this, 1, 6));
        CustomItemList.tM_TestCasing_7.set(new ItemStack(this, 1, 7));
        CustomItemList.tM_TestCasing_8.set(new ItemStack(this, 1, 8));
        CustomItemList.tM_TestCasing_9.set(new ItemStack(this, 1, 9));
        CustomItemList.tM_TestCasing_10.set(new ItemStack(this, 1, 10));
        CustomItemList.tM_TestCasing_11.set(new ItemStack(this, 1, 11));
        CustomItemList.tM_TestCasing_12.set(new ItemStack(this, 1, 12));
        CustomItemList.tM_TestCasing_13.set(new ItemStack(this, 1, 13));
        CustomItemList.tM_TestCasing_14.set(new ItemStack(this, 1, 14));
        CustomItemList.tM_TestCasing_15.set(new ItemStack(this, 1, 15));
    }

    @Override
    public void registerBlockIcons(IIconRegister aIconRegister) {
        tM[0] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[1] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[2] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[3] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[4] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[5] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[6] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[7] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[8] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[9] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[10] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[11] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[12] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[13] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[14] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
        tM[15] = aIconRegister.registerIcon("gregtech:iconsets/TM_TESLA_TOROID");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int aSide, int aMeta) {
        return tM[aMeta];
    }

    @Override
    public void getSubBlocks(Item aItem, CreativeTabs par2CreativeTabs, List aList) {
        for (int i = 0; i <= 15; i++) {
            aList.add(new ItemStack(aItem, 1, i));
        }
    }
}
