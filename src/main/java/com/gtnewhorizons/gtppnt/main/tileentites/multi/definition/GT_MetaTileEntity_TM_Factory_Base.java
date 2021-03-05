package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.github.technus.tectech.mechanics.constructable.IConstructable;
import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.render.TT_RenderedExtendedFacingTexture;
import com.github.technus.tectech.util.CommonValues;
import com.github.technus.tectech.util.Vec3Impl;
import com.gtnewhorizons.gtppnt.main.loaders.CasingTextureLoader;
import com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.GT_MetaTileEntity_TM_HatchCasing;
import com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.defenition.CasingFunction;
import com.gtnewhorizons.gtppnt.main.utils.MultiBlockUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Recipe;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.*;

public abstract class GT_MetaTileEntity_TM_Factory_Base extends GT_MetaTileEntity_MultiblockBase_EM implements
        IConstructable {
    //region Fields
    protected static final String START_STRUCTURE = "start";
    protected static final String SLICE_STRUCTURE = "slice";
    protected static final String END_STRUCTURE = "end";

    private static final HashMap<Class<? extends GT_MetaTileEntity_TM_Factory_Base>, IStructureDefinition<GT_MetaTileEntity_TM_Factory_Base>> structures = new HashMap<>();

    @SideOnly(Side.CLIENT)
    private static Textures.BlockIcons.CustomIcon[] repairTextures;
    @SideOnly(Side.CLIENT)
    private static Textures.BlockIcons.CustomIcon screenBaseInactive;
    @SideOnly(Side.CLIENT)
    private static Textures.BlockIcons.CustomIcon screenBaseActive;
    @SideOnly(Side.CLIENT)
    private static final HashMap<Class<? extends GT_MetaTileEntity_TM_Factory_Base>, Textures.BlockIcons.CustomIcon> screensMachineInactive = new HashMap<>();
    @SideOnly(Side.CLIENT)
    private static final HashMap<Class<? extends GT_MetaTileEntity_TM_Factory_Base>, Textures.BlockIcons.CustomIcon> screensMachineActive = new HashMap<>();
    @SideOnly(Side.CLIENT)
    private static final HashMap<Class<? extends GT_MetaTileEntity_TM_Factory_Base>, ResourceLocation> sounds = new HashMap<>();

    private final HashSet<GT_MetaTileEntity_TM_HatchCasing> mFunctionalCasings = new HashSet<>();
    private GT_Recipe buffered_Recipe;
    private int sliceCount = 0;
    private byte casingTier = 0;
    //endregion

    //region Constructors
    public GT_MetaTileEntity_TM_Factory_Base(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
        setRepairFlags();
        structures.put(this.getClass(), getStructure());
        sounds.put(this.getClass(), getSound());
    }

    public GT_MetaTileEntity_TM_Factory_Base(String aName) {
        super(aName);
        // FIXME: 25/02/2021 remove this later
        setRepairFlags();
    }

    public ItemStack getItem() {
        return this.getStackForm(1L);
    }

    protected void setRepairFlags() {
        this.mWrench = true;
        this.mScrewdriver = true;
        this.mSoftHammer = true;
        this.mHardHammer = true;
        this.mSolderingTool = true;
        this.mCrowbar = true;
    }

    @Override
    public abstract String[] getDescription();
    //endregion

    //region Functional Casings
    private boolean addFunctionalCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex, CasingFunction function) {
        boolean flag = false;
        if (aTileEntity != null) {
            IMetaTileEntity mte = aTileEntity.getMetaTileEntity();
            if (mte instanceof GT_MetaTileEntity_TM_HatchCasing) {
                GT_MetaTileEntity_TM_HatchCasing hatch = ((GT_MetaTileEntity_TM_HatchCasing) mte);
                if (hatch.function == function) {
                    hatch.updateTexture(aBaseCasingIndex);
                    flag = this.mFunctionalCasings.add(hatch);
                }
            }
        }
        return flag;
    }

    public final boolean addGrindingCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity,aBaseCasingIndex, CasingFunction.GRINDING);
    }

    public final boolean addPistonCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity,aBaseCasingIndex, CasingFunction.PISTON);
    }

    public final boolean addCircuitCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity,aBaseCasingIndex, CasingFunction.CIRCUIT);
    }

    public final boolean addMotorCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return addFunctionalCasingToMachineList(aTileEntity,aBaseCasingIndex, CasingFunction.MOTOR);
    }

    protected void setFunctionalCasingActivity(boolean state) {
        mFunctionalCasings.forEach(mte -> mte.getBaseMetaTileEntity().setActive(state));
    }
    //endregion

    //region Structure

    protected Block getCasingBlock() {
        return WerkstoffLoader.BWBlockCasings;
    }

    protected abstract short getCasingMeta();

    protected int getTextureIndex() {
        return CasingTextureLoader.getBasicCasingTextureIndex(getCasingMeta());
    }

    protected abstract IStructureDefinition<GT_MetaTileEntity_TM_Factory_Base> getStructure();

    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Factory_Base> getStructure_EM() {
        return structures.get(this.getClass());
    }

    protected abstract Vec3Impl getStartStructureOffset();

    protected abstract Vec3Impl getSliceStructureOffset();

    protected abstract Vec3Impl getPerSliceOffset();

    protected abstract int getMaxSlices();

    protected int getMinSlices() {
        return 1;
    }

    protected abstract int getParalellsPerSlice();

    protected boolean checkCasingTiers() {
        boolean tierMatchingCasings = true;
        casingTier = -1;
        for (GT_MetaTileEntity_TM_HatchCasing casing : mFunctionalCasings) {
            if (casingTier == -1) {
                casingTier = casing.mTier;
            } else if (casingTier != casing.mTier) {
                tierMatchingCasings = false;
                break;
            }
        }
        return tierMatchingCasings;
    }

    @Override
    protected boolean checkMachine_EM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        this.sliceCount = 0;
        this.mFunctionalCasings.clear();

        if (this.structureCheck_EM(START_STRUCTURE,
                getStartStructureOffset().get0(),
                getStartStructureOffset().get1(),
                getStartStructureOffset().get2())) {

            Vec3Impl sliceStructureOffset = getSliceStructureOffset();
            for (int i = 0; i < getMaxSlices(); i++) {
                if (structureCheck_EM(SLICE_STRUCTURE,
                        sliceStructureOffset.get0(),
                        sliceStructureOffset.get1(),
                        sliceStructureOffset.get2())) {
                    this.sliceCount++;
                    sliceStructureOffset = sliceStructureOffset.add(getPerSliceOffset());
                } else {
                    break;
                }
            }
        }

        return this.sliceCount >= getMinSlices() && checkCasingTiers();
    }

    @Override
    public void construct(ItemStack itemStack, boolean hintsOnly) {
        int sliceCount = Math.min(itemStack.stackSize, getMaxSlices());
        structureBuild_EM(START_STRUCTURE,
                getStartStructureOffset().get0(),
                getStartStructureOffset().get1(),
                getStartStructureOffset().get2(),
                hintsOnly, itemStack);

        Vec3Impl sliceStructureOffset = getSliceStructureOffset();
        for (int i = 0; i < sliceCount; i++) {
            structureBuild_EM(SLICE_STRUCTURE,
                    sliceStructureOffset.get0(),
                    sliceStructureOffset.get1(),
                    sliceStructureOffset.get2(),
                    hintsOnly, itemStack);
            sliceStructureOffset = sliceStructureOffset.add(getPerSliceOffset());
        }
    }
    //endregion

    //region Textures
    @SideOnly(Side.CLIENT)
    public void registerBaseIcons() {
        if (repairTextures == null || screenBaseInactive == null || screenBaseActive == null) {
            repairTextures = new Textures.BlockIcons.CustomIcon[7];
            repairTextures[0] = new Textures.BlockIcons.CustomIcon("iconsets/repair/none");
            repairTextures[1] = new Textures.BlockIcons.CustomIcon("iconsets/repair/wrench");
            repairTextures[2] = new Textures.BlockIcons.CustomIcon("iconsets/repair/screwdriver");
            repairTextures[3] = new Textures.BlockIcons.CustomIcon("iconsets/repair/softhammer");
            repairTextures[4] = new Textures.BlockIcons.CustomIcon("iconsets/repair/hardhammer");
            repairTextures[5] = new Textures.BlockIcons.CustomIcon("iconsets/repair/solderingtool");
            repairTextures[6] = new Textures.BlockIcons.CustomIcon("iconsets/repair/crowbar");

            screenBaseInactive = new Textures.BlockIcons.CustomIcon("iconsets/repair/inactive");
            screenBaseActive = new Textures.BlockIcons.CustomIcon("iconsets/repair/active");
        }
    }

    @SideOnly(Side.CLIENT)
    public abstract Textures.BlockIcons.CustomIcon getScreenMachineInactive();

    @SideOnly(Side.CLIENT)
    public abstract Textures.BlockIcons.CustomIcon getScreenMachineActive();

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aBlockIconRegister) {
        super.registerIcons(aBlockIconRegister);
        registerBaseIcons();
        screensMachineInactive.put(this.getClass(), getScreenMachineInactive());
        screensMachineActive.put(this.getClass(), getScreenMachineActive());
    }

    @SideOnly(Side.CLIENT)
    public ITexture getHullTexture() {
        return Textures.BlockIcons.casingTexturePages[CasingTextureLoader.texturePage][getTextureIndex()];
    }

    @SideOnly(Side.CLIENT)
    public ITexture getScreenBaseTexture(boolean aActive) {
        return new TT_RenderedExtendedFacingTexture(aActive ? screenBaseActive : screenBaseInactive);
    }

    @SideOnly(Side.CLIENT)
    public ITexture getScreenMachineTexture(boolean aActive) {
        Textures.BlockIcons.CustomIcon texture;
        if (aActive) {
            texture = screensMachineInactive.get(this.getClass());
        } else {
            texture = screensMachineActive.get(this.getClass());
        }
        return new TT_RenderedExtendedFacingTexture(texture);
    }


    @SideOnly(Side.CLIENT)
    public ITexture getRepairTexture() {
        ITexture texture = new TT_RenderedExtendedFacingTexture(repairTextures[0]);
        if (!this.mWrench) {
            texture = new TT_RenderedExtendedFacingTexture(repairTextures[1]);
        } else if (!this.mScrewdriver) {
            texture = new TT_RenderedExtendedFacingTexture(repairTextures[2]);
        } else if (!this.mSoftHammer) {
            texture = new TT_RenderedExtendedFacingTexture(repairTextures[3]);
        } else if (!this.mHardHammer) {
            texture = new TT_RenderedExtendedFacingTexture(repairTextures[4]);
        } else if (!this.mSolderingTool) {
            texture = new TT_RenderedExtendedFacingTexture(repairTextures[5]);
        } else if (!this.mCrowbar) {
            texture = new TT_RenderedExtendedFacingTexture(repairTextures[6]);
        }
        return texture;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
        ITexture[] textures;
        if (aSide != aFacing) {
            textures = new ITexture[]{getHullTexture()};
        } else {
            textures = new ITexture[]{
                    getHullTexture(),
                    getScreenBaseTexture(aActive),
                    getScreenMachineTexture(aActive),
                    getRepairTexture()};
        }
        return textures;
    }
    //endregion

    //region Sounds
    @SideOnly(Side.CLIENT)
    protected abstract ResourceLocation getSound();

    @Override
    @SideOnly(Side.CLIENT)
    protected ResourceLocation getActivitySound() {
        return sounds.get(this.getClass());
    }
    //endregion

    //region On Tick
    @Override
    public abstract GT_Recipe.GT_Recipe_Map getRecipeMap();

    protected int getMaxParalells() {
        return this.sliceCount * getParalellsPerSlice();
    }

    protected boolean isPerfectOC() {
        return false;
    }

    private long getMaxVoltage() {
        long voltage = 0;
        if (this.casingTier >= 0 && this.casingTier <= 15) {
            voltage = CommonValues.V[this.casingTier];
        }
        return voltage;
    }

    @Override
    public boolean checkRecipe_EM(ItemStack itemStack) {
        boolean canRunRecipe = false;
        if (this.getEUVar() > this.getMaxInputVoltage()) {
            ItemStack[] inputItems = MultiBlockUtils.sortInputItemStacks(this.getStoredInputs());
            FluidStack[] inputFluids = MultiBlockUtils.sortInputFluidStacks(this.getStoredFluids());
            if (inputItems.length > 0 || inputFluids.length > 0) {
                GT_Recipe recipe = getRecipeMap().findRecipe(
                        this.getBaseMetaTileEntity(),
                        this.buffered_Recipe,
                        false,
                        true,
                        this.getMaxInputVoltage(),
                        inputFluids,
                        this.getStackInSlot(0),
                        inputItems);

                if (recipe != null) {
                    if (recipe.mCanBeBuffered) {
                        this.buffered_Recipe = recipe;
                    }

                    ArrayList<ItemStack> outputItems = new ArrayList<>();
                    ArrayList<FluidStack> outputFluids = new ArrayList<>();
                    int recipeRepeats = 0;
                    for (boolean canProcess = true; canProcess && this.getMaxParalells() > recipeRepeats; ) {
                        if (recipe.isRecipeInputEqual(
                                true,
                                false,
                                inputFluids,
                                inputItems)) {

                            recipeRepeats++;
                            for (int i = 0; i < recipe.mOutputs.length; i++) {
                                outputItems.add(recipe.getOutput(i));
                            }
                            for (int i = 0; i < recipe.mFluidOutputs.length; i++) {
                                outputFluids.add(recipe.getFluidOutput(i));
                            }
                        } else {
                            canProcess = false;
                        }
                    }

                    if (recipeRepeats > 0) {
                        this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
                        this.mEfficiencyIncrease = 10000;
                        this.mOutputItems = MultiBlockUtils.sortOutputItemStacks(outputItems);
                        this.mOutputFluids = MultiBlockUtils.sortOutputFluidStacks(outputFluids);

                        this.calculateOverclockedNessMultiInternal(recipe.mEUt * recipeRepeats, recipe.mDuration / 50, getRecipeMap().mAmperage * recipeRepeats, getMaxVoltage(), isPerfectOC());
                        // FIXME: 26/02/2021 Undo duration debug boost
                        if (mMaxProgresstime != Integer.MAX_VALUE - 1 && mEUt != Integer.MAX_VALUE - 1) {
                            if (this.mEUt > 0) {
                                this.mEUt *= -1;
                            }
                            this.mMaxProgresstime = Math.max(1, this.mMaxProgresstime);
                            this.updateSlots();
                            canRunRecipe = true;
                        }
                    }
                }
            }
        }
        this.setFunctionalCasingActivity(canRunRecipe);
        return canRunRecipe;
    }

    @Override
    public void stopMachine() {
        super.stopMachine();
        this.setFunctionalCasingActivity(false);
    }
    //endregion
}
