package com.gtnewhorizons.obama.main.tileentites.multi.definition;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.loaders.CasingTextureLoader;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.sound.ISoundProviderImpl;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.structure.IConstructableStructure;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.texture.ITextureProviderImpl;
import com.gtnewhorizons.obama.main.tileentites.single.hatches.GT_MetaTileEntity_TM_HatchCasing;
import com.gtnewhorizons.obama.main.tileentites.single.hatches.defenition.IFunctionalCasingMachineList;
import com.gtnewhorizons.obama.main.tileentites.single.hatches.defenition.IHeatingCoilMachineList;
import com.gtnewhorizons.obama.main.utils.MultiBlockUtils;
import com.gtnewhorizons.obama.main.utils.RecipeIterable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.GT_Values;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_EnhancedMultiBlockBase;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Energy;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtech.api.util.GT_Recipe;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//TODO Slot recipe handling into its own interface
public abstract class GT_MetaTileEntity_TM_Factory<T extends GT_MetaTileEntity_TM_Factory<T>> extends GT_MetaTileEntity_EnhancedMultiBlockBase<T> implements
        IConstructableStructure, IFunctionalCasingMachineList, ITextureProviderImpl, ISoundProviderImpl,
        IHeatingCoilMachineList {
    private final Set<GT_MetaTileEntity_TM_HatchCasing> functionalCasings = new HashSet<>();
    private byte casingTier = 0;
    private int coilTier = 0;
    private Vec3Impl structureOffset;
    private GT_Recipe buffered_Recipe;
    private RecipeProgresion[] runningRecipes = new RecipeProgresion[0];
    private int parallelRunning = 0;
    private int newProgressTime = Integer.MAX_VALUE;

    private int structureCounter = 0;
    private int maxParallels = 0;

    //region Constructors
    public GT_MetaTileEntity_TM_Factory(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
        setRepairFlags();
        registerStructure();
        registerActivitySound_TM();
    }

    public GT_MetaTileEntity_TM_Factory(String aName) {
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
    @Override
    public int getTextureIndex() {
        return CasingTextureLoader.getBasicCasingTextureIndex(getCasingMeta());
    }

//    @Override
//    public IStructureDefinition<? extends GT_MetaTileEntity_EnhancedMultiBlockBase<?> > getStructure_EM() {
//        return getStructure_TM();
//    }

    @Override
    public Block getCasingBlock() {
        return WerkstoffLoader.BWBlockCasings;
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        functionalCasingsPreCheckMachine();
        heatingCoilPreCheckMachine();
        return checkMachine(iGregTechTileEntity, itemStack) &&
                functionalCasingsPostCheckMachine() &&
                heatingCoilPostCheckMachine(getCasingTier());
    }

//    @Override
//    public boolean structureCheck(String piece, int horizontalOffset, int verticalOffset, int depthOffset) {
//        return structureCheck_EM(piece, horizontalOffset, verticalOffset, depthOffset);
//    }
//
//    @Override
//    public boolean structureBuild(String piece, int horizontalOffset, int verticalOffset, int depthOffset, boolean hintsOnly, ItemStack trigger) {
//        return structureBuild_EM(piece, horizontalOffset, verticalOffset, depthOffset, hintsOnly, trigger);
//    }
    //endregion

    //region Shape Interface
    @Override
    public void setCurrentStructureOffset(Vec3Impl structureOffset) {
        this.structureOffset = structureOffset;
    }

    @Override
    public Vec3Impl getCurrentStructureOffset() {
        return this.structureOffset;
    }

    @Override
    public int getStructureCounter() {
        return structureCounter;
    }

    @Override
    public void setStructureCounter(int structureCounter) {
        this.structureCounter = structureCounter;
    }

    @Override
    public int getMaxParallels() {
        return maxParallels;
    }

    @Override
    public void setMaxParallels(int maxParallels) {
        this.maxParallels = maxParallels;
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

//    //region Sounds
//    @Override
//    @SideOnly(Side.CLIENT)
//    protected ResourceLocation getActivitySound() {
//        return getActivitySound_TM();
//    }
//    //endregion

    //region On Tick
    @Override
    public abstract GT_Recipe.GT_Recipe_Map getRecipeMap();

    protected boolean isPerfectOC() {
        return false;
    }

    private long getMaxVoltage() {
        long voltage = 0;
        if (getCasingTier() >= 0 && getCasingTier() <= 15) {
            voltage = GT_Values.V[getCasingTier()];
        }
        return voltage;
    }

    @Override
    public boolean onRunningTick(ItemStack aStack) {
        recipeControl();
        return super.onRunningTick(aStack);
    }

    //TODO test without null check
    void recipeControl() {
        int progressTime = this.mProgresstime + 1;
        if (this.mMaxProgresstime > 0 && progressTime >= this.mMaxProgresstime && runningRecipes != null) {
            // if all new or remaning recipes are more then 100 ticks long it will still recheck a recipe in 100 ticks
            newProgressTime = 100;
            ArrayList<RecipeProgresion> finishedRecipes = new ArrayList<>(runningRecipes.length);
            int totalItemStacks = 0;
            int totalFluidStacks = 0;
            // put all finished recipes in a array and remove then from running
            // get count of how many item and fluid stacks will be needed on output
            for (int i = 0; i < runningRecipes.length; i++) {
                RecipeProgresion runningRecipe = runningRecipes[i];
                if (runningRecipe != null) {
                    int timeLeft = runningRecipe.isRecipeDone(progressTime);
                    if (timeLeft <= 0) {
                        finishedRecipes.add(runningRecipe);
                        totalItemStacks += runningRecipe.getItems().length;
                        totalFluidStacks += runningRecipe.getFluids().length;
                        runningRecipes[i] = null;
                    } else {
                        newProgressTime = Math.min(newProgressTime, timeLeft);
                    }
                }
            }

            if (finishedRecipes.size() > 0) {
                ItemStack[] outputItems;
                FluidStack[] outputFluids;
                int freedParallel = 0;
                int freedPower = 0;

                //get output items, fluids, parallel and power from finished recipes
                if (finishedRecipes.size() == 1) {//if only 1 recipe is done avoid the extra processing
                    RecipeProgresion finishedRecipe = finishedRecipes.get(0);
                    outputItems = finishedRecipe.getItems();
                    outputFluids = finishedRecipe.getFluids();
                    freedParallel = finishedRecipe.getAmount();
                    freedPower = finishedRecipe.getEUUsage();
                } else {
                    outputItems = new ItemStack[totalItemStacks];
                    outputFluids = new FluidStack[totalFluidStacks];
                    int itemIndex = 0;
                    int fluidIndex = 0;
                    for (RecipeProgresion finishedRecipe : finishedRecipes) {
                        ItemStack[] items = finishedRecipe.getItems();
                        int itemLen = items.length;
                        for (int i = 0; i < itemLen; i++) {
                            outputItems[i + itemIndex] = items[i];
                        }
                        itemIndex += itemLen;
                        FluidStack[] fluids = finishedRecipe.getFluids();
                        int fluidLen = fluids.length;
                        for (int i = 0; i < fluidLen; i++) {
                            outputFluids[i + fluidIndex] = fluids[i];
                        }
                        freedParallel += finishedRecipe.getAmount();
                        freedPower += finishedRecipe.getEUUsage();
                    }
                }
                //remove all null from array
                RecipeProgresion[] newRunning = new RecipeProgresion[runningRecipes.length - finishedRecipes.size()];
                int newIndex = 0;
                for (RecipeProgresion runningRecipe : runningRecipes) {
                    if (runningRecipe != null)
                        newRunning[newIndex++] = runningRecipe;
                }
                //re set values so they are correct with recipes gone
                runningRecipes = newRunning;
                mOutputItems = outputItems;
                mOutputFluids = outputFluids;
                parallelRunning -= freedParallel;
                mEUt += freedPower;
                if (!getBaseMetaTileEntity().isAllowedToWork()) {
                    mMaxProgresstime = newProgressTime;
                    mProgresstime = 0;
                }
            }
        }
    }

    public final long getMaxInputEnergy() {
        long energy = 0;
        for (GT_MetaTileEntity_Hatch_Energy tHatch : mEnergyHatches) {
            if (GT_MetaTileEntity_MultiBlockBase.isValidMetaTileEntity(tHatch)) {
                energy += tHatch.maxEUInput()*tHatch.maxAmperesIn();
            }
        }
        return energy;
    }


    @Override
    public boolean checkRecipe(ItemStack itemStack) {
        boolean canRunRecipe = false;
        int totalEUUsage = this.mEUt;
        int maxTotalRecipes = getMaxUniqueRecipes() - runningRecipes.length;
        //check if we can actually add any new recipes
        if (this.getEUVar() > this.getMaxInputVoltage() && maxTotalRecipes > 0) {
            ItemStack[] inputItems = this.getStoredInputs().toArray(new ItemStack[0]);
            FluidStack[] inputFluids = this.getStoredFluids().toArray(new FluidStack[0]);
            if (inputItems.length > 0 || inputFluids.length > 0) {
                ItemStack[] combinedItems = MultiBlockUtils.combineStacks(inputItems);
                RecipeIterable recipes = new RecipeIterable(
                        getRecipeMap(),
                        this.buffered_Recipe,
                        false,
                        true,
                        this.getMaxInputVoltage(),
                        inputFluids,
                        inputItems);
                int parallel = getMaxParallels() - parallelRunning;
                int parallelDone = 0;
                int voltage = (int) getMaxVoltage();
                int amps = (int) getMaxInputEnergy() / voltage;
                if (amps < parallel) {
                    parallel = amps;
                }
                ArrayList<RecipeProgresion> newRecipes = new ArrayList<>();
                for (GT_Recipe recipe : recipes) {
                    if (recipe != null) {
                        if (recipe.mCanBeBuffered) {
                            this.buffered_Recipe = recipe;
                        }

                        parallelDone = checkAndConsumeRecipe(recipe, inputItems, combinedItems, inputFluids, parallel);

                        if (parallelDone > 0) {

                            RecipeProgresion processedRecipe = getRecipeProgresionWithOC(recipe, voltage, parallelDone);
                            newRecipes.add(processedRecipe);
                            totalEUUsage -= processedRecipe.getEUUsage();

                            newProgressTime = Math.min(newProgressTime, processedRecipe.getTimeLeft());
                            parallelRunning += parallelDone;
                            parallel -= parallelDone;
                            canRunRecipe = true;
                        }
                    }
                    if (parallel < 1 || maxTotalRecipes < newRecipes.size())
                        break;
                }
                if (canRunRecipe) {
                    addNewRunningRecipes(newRecipes.toArray(new RecipeProgresion[0]));
                    this.updateSlots();
                }
            }
        }
        canRunRecipe = setEnergy(newProgressTime, totalEUUsage) && this.runningRecipes.length > 0;
        if (!canRunRecipe)
            turnOff();
        else {
            turnOn();
        }
        return canRunRecipe;
    }

    public int getMaxUniqueRecipes() {
        return 8;
    }

    //allows multies to overide this incase more special recipe check is needid
    public int checkAndConsumeRecipe(GT_Recipe recipe, ItemStack[] inputItems, ItemStack[] combinedItems,
                                     FluidStack[] inputFluids, int parrallel) {
        return MultiBlockUtils.isRecipeEqualAndRemoveParallel(recipe,
                inputItems, combinedItems, inputFluids, parrallel, true);
    }

    //allows multies to overide this incase more special OC is needid
    public RecipeProgresion getRecipeProgresionWithOC(GT_Recipe recipe, int voltage, int parrallelDone) {
        return MultiBlockUtils.getRecipeProgressionWithOC(
                recipe,
                getRecipeVoltage(recipe),
                voltage,
                parrallelDone);
    }


    public int getRecipeVoltage(GT_Recipe recipe) {
        return recipe.mEUt / getRecipeMap().mAmperage;
    }

    public void turnOn() {
        if (!getBaseMetaTileEntity().isActive())
            setFunctionalCasingActivity(true);
    }

    public void turnOff() {
        this.mEUt = 0;
        this.mMaxProgresstime = 0;
        this.mProgresstime = 0;
        this.newProgressTime = Integer.MAX_VALUE;
        if (getBaseMetaTileEntity().isActive())
            setFunctionalCasingActivity(false);
    }

    public void addNewRunningRecipes(RecipeProgresion[] newRunningRecipes) {
        RecipeProgresion[] newList = new RecipeProgresion[this.runningRecipes.length + newRunningRecipes.length];
        int index = 0;
        for (RecipeProgresion runningRecipe : this.runningRecipes) {
            newList[index++] = runningRecipe;
        }
        for (RecipeProgresion runningRecipe : newRunningRecipes) {
            newList[index++] = runningRecipe;
        }
        this.runningRecipes = newList;
    }

    private boolean setEnergy(int time, int euUsage) {
        this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
        this.mEfficiencyIncrease = 10000;
        this.mEUt = euUsage;
        if (mMaxProgresstime != Integer.MAX_VALUE - 1 && mEUt != Integer.MAX_VALUE - 1) {
            if (this.mEUt > 0) {
                this.mEUt *= -1;
            }
            this.mProgresstime = 0;
            this.mMaxProgresstime = Math.max(1, time);
            return true;
        }
        return false;
    }

    @Override
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        super.onPostTick(aBaseMetaTileEntity, aTick);
    }

    @Override
    public void stopMachine() {
        super.stopMachine();
    }
    //endregion

    @Override
    public byte getCasingTier() {
        return casingTier;
    }

    @Override
    public void setCasingTier(byte casingTier) {
        this.casingTier = casingTier;
    }

    @Override
    public int getCoilTier() {
        return coilTier;
    }

    @Override
    public void setCoilTier(int coilTier) {
        this.coilTier = coilTier;
    }

    @Override
    public Set<GT_MetaTileEntity_TM_HatchCasing> getFunctionalCasings() {
        return functionalCasings;
    }

    @Override
    public String[] getInfoData() {
        return ArrayUtils.add(super.getInfoData(),
                "Max Parallel: " + EnumChatFormatting.GREEN + getMaxParallels() + EnumChatFormatting.RESET +
                        " / " + EnumChatFormatting.YELLOW + parallelRunning + EnumChatFormatting.RESET);
    }


    @Override
    public boolean isCorrectMachinePart(ItemStack aStack) {
        return true;
    }

    @Override
    public int getMaxEfficiency(ItemStack aStack) {
        return 10000;
    }

    @Override
    public int getPollutionPerTick(ItemStack aStack) {
        return 0;
    }

    @Override
    public int getDamageToComponent(ItemStack aStack) {
        return 0;
    }

    @Override
    public boolean explodesOnComponentBreak(ItemStack aStack) {
        return false;
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {

    }

    
}
