package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import com.github.technus.tectech.mechanics.constructable.IConstructable;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.render.TT_RenderedExtendedFacingTexture;
import com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.GT_MetaTileEntity_TM_HatchCasing;
import com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.defenition.CasingFunction;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

import static com.gtnewhorizons.gtppnt.main.loaders.CasingTextureLoader.texturePage;

public abstract class GT_MetaTileEntity_TM_Factory_Base extends GT_MetaTileEntity_MultiblockBase_EM implements
        IConstructable {
    protected final Set<GT_MetaTileEntity_TM_HatchCasing> mFunctionalCasings = new HashSet<>();

    //private static final String[] description;
    protected static Textures.BlockIcons.CustomIcon ScreenOFF;
    protected static Textures.BlockIcons.CustomIcon ScreenON;
    protected static int textureID;

    //region Constructors
    public GT_MetaTileEntity_TM_Factory_Base(int aID) {
        this(aID, "unlocal_name", "local_name");
    }

    public GT_MetaTileEntity_TM_Factory_Base(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
        setRepairFlags();
    }

    public GT_MetaTileEntity_TM_Factory_Base(String aName) {
        super(aName);
        setRepairFlags();
    }

    protected void setRepairFlags() {
        mWrench = true;
        mScrewdriver = true;
        mSoftHammer = true;
        mHardHammer = true;
        mSolderingTool = true;
        mCrowbar = true;
    }
    //endregion

    //region Texture Handling
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aBlockIconRegister) {
        super.registerIcons(aBlockIconRegister);
        if (ScreenOFF == null || ScreenON == null) {
            ScreenOFF = getScreenOFF();
            ScreenON = getScreenON();
        }
        textureID = getTextureID();
    }

    @SideOnly(Side.CLIENT)
    protected abstract Textures.BlockIcons.CustomIcon getScreenOFF();

    @SideOnly(Side.CLIENT)
    protected abstract Textures.BlockIcons.CustomIcon getScreenON();

    @SideOnly(Side.CLIENT)
    protected abstract int getTextureID();

    @Override
    @SideOnly(Side.CLIENT)
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
        ITexture[] textures;
        if (aSide == aFacing) {
            textures = new ITexture[]{Textures.BlockIcons.casingTexturePages[texturePage][textureID],
                    new TT_RenderedExtendedFacingTexture(aActive ? ScreenON : ScreenOFF)};
        } else {
            textures = new ITexture[]{Textures.BlockIcons.casingTexturePages[texturePage][textureID]};
        }
        return textures;
    }
    //endregion

    //TODO Define this a constant
    @Override
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{"No desc."};
    }

    @Override
    public void construct(ItemStack itemStack, boolean b) {
    }

    @Override
    public void stopMachine() {
        super.stopMachine();
        setFunctionalCasingActivity(false);
    }

    protected void setFunctionalCasingActivity(boolean state) {
        mFunctionalCasings.forEach(mte -> mte.getBaseMetaTileEntity().setActive(state));
    }

    public final boolean addFunctionalCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        boolean flag = false;
        if (aTileEntity != null) {
            IMetaTileEntity mte = aTileEntity.getMetaTileEntity();
            if (mte instanceof GT_MetaTileEntity_TM_HatchCasing) {
                GT_MetaTileEntity_TM_HatchCasing hatch = ((GT_MetaTileEntity_TM_HatchCasing) mte);
                hatch.updateTexture(aBaseCasingIndex);
                flag = this.mFunctionalCasings.add(hatch);
            }
        }
        return flag;
    }

    public final boolean addCircuitCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        boolean flag = false;
        if (aTileEntity != null) {
            IMetaTileEntity mte = aTileEntity.getMetaTileEntity();
            if (mte instanceof GT_MetaTileEntity_TM_HatchCasing) {
                GT_MetaTileEntity_TM_HatchCasing hatch = ((GT_MetaTileEntity_TM_HatchCasing) mte);
                if (hatch.function == CasingFunction.CIRCUIT) {
                    hatch.updateTexture(aBaseCasingIndex);
                    flag = this.mFunctionalCasings.add(hatch);
                }
            }
        }
        return flag;
    }

    public final boolean addMotorCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        boolean flag = false;
        if (aTileEntity != null) {
            IMetaTileEntity mte = aTileEntity.getMetaTileEntity();
            if (mte instanceof GT_MetaTileEntity_TM_HatchCasing) {
                GT_MetaTileEntity_TM_HatchCasing hatch = ((GT_MetaTileEntity_TM_HatchCasing) mte);
                if (hatch.function == CasingFunction.MOTOR) {
                    hatch.updateTexture(aBaseCasingIndex);
                    flag = this.mFunctionalCasings.add(hatch);
                }
            }
        }
        return flag;
    }
}
