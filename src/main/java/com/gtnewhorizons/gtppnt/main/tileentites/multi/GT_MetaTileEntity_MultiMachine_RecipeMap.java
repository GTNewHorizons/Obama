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

package com.gtnewhorizons.gtppnt.main.tileentites.multi;

import com.github.bartimaeusnek.bartworks.util.Pair;
import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.gtnewhorizons.gtppnt.main.utils.IAddsBlocks;
import com.gtnewhorizons.gtppnt.main.utils.MultiBlockUtils;
import com.gtnewhorizons.gtppnt.main.utils.TT_Utils;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

//TODO: Test this SHIT
public class GT_MetaTileEntity_MultiMachine_RecipeMap extends GT_MetaTileEntity_MultiblockBase_EM
        implements IAddsBlocks {

    private GT_Recipe buffered_Recipe;
    private final List<Pair<Block, Integer>> mSpecialBlocks = new ArrayList<>();

    private TT_Utils.MultiBlockDefinition multiBlockDefinition;

    public GT_MetaTileEntity_MultiMachine_RecipeMap(int aID,
                                                    String aName,
                                                    String aNameRegional,
                                                    TT_Utils.MultiBlockDefinition multiBlockDefinition) {
        super(aID, aName, aNameRegional);
        this.multiBlockDefinition = multiBlockDefinition;
    }

    public GT_MetaTileEntity_MultiMachine_RecipeMap(String aName, TT_Utils.MultiBlockDefinition multiBlockDefinition) {
        super(aName);
        this.multiBlockDefinition = multiBlockDefinition;
    }

    public String[] getDescription() {
        return multiBlockDefinition.getTooltip();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setString("definition", multiBlockDefinition.name());
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        multiBlockDefinition = TT_Utils.MultiBlockDefinition.valueOf(aNBT.getString("definition"));
    }

    @Override
    protected boolean checkMachine_EM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        this.mSpecialBlocks.clear();
        return this.structureCheck_EM("main",
                multiBlockDefinition.getStructure().getHorizontalOffset(),
                multiBlockDefinition.getStructure().getVerticalOffset(),
                0
        );
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new GT_MetaTileEntity_MultiMachine_RecipeMap(mName, multiBlockDefinition);
    }

    public byte getTier() {
        return GT_Utility.getTier(this.getMaxInputVoltage());
    }

    @Override
    public boolean checkRecipe_EM(ItemStack itemStack) {
        AtomicBoolean ret = new AtomicBoolean(false);
        FluidStack[] flinputs = MultiBlockUtils.sortFluidStacks(this.getStoredFluids());
        ItemStack[] itinputs = MultiBlockUtils.sortItemStacks(this.getStoredInputs());
        if (flinputs.length > 0 || itinputs.length > 0) {
            GT_Recipe.GT_Recipe_Map map = getRecipeMap();

            Optional<GT_Recipe> gt_recipe = Optional.ofNullable(
                    map.findRecipe(
                            this.getBaseMetaTileEntity(),
                            buffered_Recipe,
                            false,
                            true,
                            this.getMaxInputVoltage(),
                            flinputs,
                            this.getStackInSlot(0),
                            itinputs
                    )
            );

            if (!gt_recipe.isPresent())
                return ret.get();

            gt_recipe
                    .filter(recipe -> recipe.mCanBeBuffered)
                    .ifPresent(recipe -> buffered_Recipe = recipe);

            gt_recipe
                    .ifPresent(recipe -> {
                        ArrayList<ItemStack> outputItems = new ArrayList<>();
                        ArrayList<FluidStack> outputFluids = new ArrayList<>();
                        boolean rzz;
                        int i = 0;
                        while (true) {
                            rzz = recipe.isRecipeInputEqual(
                                    true,
                                    false,
                                    flinputs,
                                    itinputs
                            );
                            i++;
                            if (rzz && multiBlockDefinition.getMaxParalellsPerTier() * this.getTier() < i) {
                                for (int j = 0; j < recipe.mOutputs.length; j++) {
                                    outputItems.add(recipe.getOutput(i));
                                }

                                for (int j = 0; j < recipe.mFluidOutputs.length; j++) {
                                    outputFluids.add(recipe.getFluidOutput(i));
                                }
                            } else
                                break;
                        }

                        this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
                        this.mEfficiencyIncrease = 10000;


                        this.mOutputItems = MultiBlockUtils.sortItemStacks(outputItems);
                        this.mOutputFluids = MultiBlockUtils.sortFluidStacks(outputFluids);

                        this.calculateOverclockedNessMultiInternal(recipe.mEUt * i,
                                recipe.mDuration,
                                map.mAmperage * i,
                                this.getMaxInputVoltage(),
                                multiBlockDefinition.isPerfectOC()
                        );

                        if (mMaxProgresstime == Integer.MAX_VALUE - 1 && mEUt == Integer.MAX_VALUE - 1)
                            return;

                        if (this.mEUt > 0) {
                            this.mEUt = (-this.mEUt);
                        }

                        this.mMaxProgresstime = Math.max(1, this.mMaxProgresstime);
                        updateSlots();
                        ret.set(true);
                    });
        }
        return ret.get();
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getStructure_EM() {
        return multiBlockDefinition.getStructure().getStructureDefinition();
    }

    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return multiBlockDefinition.getRecipe_map();
    }

    @Override
    public List<Pair<Block, Integer>> getSpecialBlocks() {
        return this.mSpecialBlocks;
    }

    @Override
    public Pair<Block, Integer> getRequiredSpecialBlock() {
        return multiBlockDefinition.getStructure().getSpecialBlock();
    }
}
