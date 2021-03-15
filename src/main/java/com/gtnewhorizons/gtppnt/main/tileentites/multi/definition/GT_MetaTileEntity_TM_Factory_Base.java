package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.github.technus.tectech.mechanics.constructable.IConstructable;
import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.util.CommonValues;
import com.github.technus.tectech.util.Vec3Impl;
import com.gtnewhorizons.gtppnt.main.loaders.CasingTextureLoader;
import com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.GT_MetaTileEntity_TM_HatchCasing;
import com.gtnewhorizons.gtppnt.main.tileentites.single.hatches.defenition.IFunctionalCasingMachineList;
import com.gtnewhorizons.gtppnt.main.utils.MultiBlockUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Recipe;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.*;


//TODO Structures and constructors go into their own interface that extends IConstructable
//TODO This then requires two other abstract classes, one for Cell, Slice and newShape
//TODO Sounds go into their own interface that provides the sound storage
//TODO Slot recipe handling into its own interface
public abstract class GT_MetaTileEntity_TM_Factory_Base extends GT_MetaTileEntity_MultiblockBase_EM implements
        IConstructable, IFunctionalCasingMachineList, ITextureProviderImpl, ISoundProviderImpl {
    //region Fields
    protected static final String START_STRUCTURE = "start";
    protected static final String SLICE_STRUCTURE = "slice";
    protected static final String END_STRUCTURE = "end";

    private static final HashMap<Class<? extends GT_MetaTileEntity_TM_Factory_Base>, IStructureDefinition<GT_MetaTileEntity_TM_Factory_Base>> structures = new HashMap<>();

    private final Set<GT_MetaTileEntity_TM_HatchCasing> functionalCasings = new HashSet<>();
    private GT_Recipe buffered_Recipe;
    private int sliceCount = 0;
    private byte casingTier = 0;
    //endregion

    //region Constructors
    public GT_MetaTileEntity_TM_Factory_Base(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
        setRepairFlags();
        structures.put(this.getClass(), getStructure());
        registerActivitySound_TM();
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

    //region Structure

    protected Block getCasingBlock() {
        return WerkstoffLoader.BWBlockCasings;
    }

    protected abstract short getCasingMeta();

    @Override
    public int getTextureIndex() {
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

    @Override
    protected boolean checkMachine_EM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        this.setSliceCount(0);
        this.getFunctionalCasings().clear();

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
                    setSliceCount(getSliceCount() + 1);
                    sliceStructureOffset = sliceStructureOffset.add(getPerSliceOffset());
                } else {
                    break;
                }
            }
        }
        return getSliceCount() >= getMinSlices() && checkCasingTiers();
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
    @Override
    public void registerIcons(IIconRegister aBlockIconRegister) {
        super.registerIcons(aBlockIconRegister);
        registerIcons_TM();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex,
                                 boolean aActive, boolean aRedstone) {
        return getTexture_TM(aSide, aFacing, aActive);
    }
    //endregion

    //region Sounds
    @Override
    @SideOnly(Side.CLIENT)
    protected ResourceLocation getActivitySound() {
        return getActivitySound_TM();
    }
    //endregion

    //region On Tick
    @Override
    public abstract GT_Recipe.GT_Recipe_Map getRecipeMap();

    protected int getMaxParalells() {
        return getSliceCount() * getParalellsPerSlice();
    }

    protected boolean isPerfectOC() {
        return false;
    }

    private long getMaxVoltage() {
        long voltage = 0;
        if (getCasingTier() >= 0 && getCasingTier() <= 15) {
            voltage = CommonValues.V[getCasingTier()];
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
        return canRunRecipe;
    }

    @Override
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        super.onPostTick(aBaseMetaTileEntity, aTick);
        this.setFunctionalCasingActivity(aBaseMetaTileEntity.isActive());
    }

    @Override
    public void stopMachine() {
        super.stopMachine();
        this.setFunctionalCasingActivity(false);
    }
    //endregion

    public int getSliceCount() {
        return sliceCount;
    }

    public void setSliceCount(int sliceCount) {
        this.sliceCount = sliceCount;
    }

    @Override
    public byte getCasingTier() {
        return casingTier;
    }

    @Override
    public void setCasingTier(byte casingTier) {
        this.casingTier = casingTier;
    }

    @Override
    public Set<GT_MetaTileEntity_TM_HatchCasing> getFunctionalCasings() {
        return functionalCasings;
    }
}
