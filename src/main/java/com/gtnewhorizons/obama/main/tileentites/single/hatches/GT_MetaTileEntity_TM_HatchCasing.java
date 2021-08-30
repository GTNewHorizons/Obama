package com.gtnewhorizons.obama.main.tileentites.single.hatches;

import com.gtnewhorizons.obama.main.tileentites.single.hatches.definition.CasingFunction;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.objects.GT_RenderedTexture;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

import java.util.HashMap;
import java.util.Map;

import static com.gtnewhorizons.obama.main.CommonValues.COMPONENT_COLORS;

public class GT_MetaTileEntity_TM_HatchCasing extends GT_MetaTileEntity_Hatch {
    private static final Map<String, Textures.BlockIcons.CustomIcon> FacingTiered = new HashMap<>();
    private static final Map<String, Textures.BlockIcons.CustomIcon> FacingInactive = new HashMap<>();
    private static final Map<String, Textures.BlockIcons.CustomIcon> FacingActive = new HashMap<>();
    public CasingFunction function;

    public GT_MetaTileEntity_TM_HatchCasing(int aID, CasingFunction function, int aTier) {
        super(aID, function.getUnlocalizedName(aTier), function.getLocalizedName(aTier), aTier, 0, "");
        this.function = function;
    }

    public GT_MetaTileEntity_TM_HatchCasing(String aName, int aTier, String aDescription, ITexture[][][] aTextures, CasingFunction function) {
        super(aName, aTier, 0, aDescription, aTextures);
        this.function = function;
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new GT_MetaTileEntity_TM_HatchCasing(mName, mTier, mDescription, mTextures, function);
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "You should basically",
                EnumChatFormatting.AQUA + "Never see this?.."
        };
    }

    //region Texture Handling
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aBlockIconRegister) {
        super.registerIcons(aBlockIconRegister);
        String name = function.name();
        FacingTiered.put(name, new Textures.BlockIcons.CustomIcon("iconsets/TM_" + name + "_CASING_TIER"));
        FacingInactive.put(name, new Textures.BlockIcons.CustomIcon("iconsets/TM_" + name + "_CASING_IDLE"));
        FacingActive.put(name, new Textures.BlockIcons.CustomIcon("iconsets/TM_" + name + "_CASING_ACTIVE"));
    }

    @Override
    public ITexture[] getTexturesActive(ITexture aBaseTexture) {
        return new ITexture[]{
                aBaseTexture,
                new GT_RenderedTexture(FacingTiered.get(function.name()), COMPONENT_COLORS[this.mTier]),
                new GT_RenderedTexture(FacingActive.get(this.function.name()))};
    }

    @Override
    public ITexture[] getTexturesInactive(ITexture aBaseTexture) {
        return new ITexture[]{
                aBaseTexture,
                new GT_RenderedTexture(FacingTiered.get(function.name()), COMPONENT_COLORS[this.mTier]),
                new GT_RenderedTexture(FacingInactive.get(this.function.name()))};
    }

    //@Override
    //public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
    //    //Passing the same side and facing each tine ensures the side is the facing side every time
    //    //We could do this in a cleaner way, but since mTexturePage and actualTexture are private
    //    //This is the best we can do here, might go into the GT5u source and make them protected some point
    //    return super.getTexture(aBaseMetaTileEntity, (byte) 0, (byte) 0, aColorIndex, aActive, aRedstone);
    //}
    //endregion

    //region Disabled Inherited Methods

    @Override
    public boolean isFacingValid(byte aFacing) {
        return true;
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return false;
    }

    @Override
    public boolean allowPullStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, byte aSide, ItemStack aStack) {
        return false;
    }

    @Override
    public boolean allowPutStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, byte aSide, ItemStack aStack) {
        return false;
    }

    @Override
    public Object getServerGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return null;
    }

    @Override
    public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return null;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }
    //endregion

    //region NBT Data
    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setString("function", function.name());
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        this.function = CasingFunction.valueOf(aNBT.getString("function"));
    }
    //endregion

    public ItemStack getItem() {
        return this.getStackForm(1L);
    }
}
