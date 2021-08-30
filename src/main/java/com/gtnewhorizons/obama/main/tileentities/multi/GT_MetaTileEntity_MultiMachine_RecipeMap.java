///*
// * Copyright 2021 The GTNH Team
// *
// *  See LICENSE.md, COPYING, and COPYING.LESSER
// */
//
//
//package com.gtnewhorizons.obama.main.tileentites.multi;
//
//import com.github.bartimaeusnek.bartworks.util.Pair;
//import com.gtnewhorizon.structurelib.alignment.constructable.IConstructable;
//import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
//import com.gtnewhorizons.obama.main.tileentites.multi.definition.IStructureExpander;
//import com.gtnewhorizons.obama.main.tileentites.multi.definition.MultiBlockDefinition;
//import com.gtnewhorizons.obama.main.tileentites.single.hatches.GT_MetaTileEntity_TM_HatchCasing;
//import com.gtnewhorizons.obama.main.utils.IAddsBlocks;
//import com.gtnewhorizons.obama.main.utils.MultiBlockUtils;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//import gregtech.api.enums.Textures;
//import gregtech.api.interfaces.ITexture;
//import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
//import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
//import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_EnhancedMultiBlockBase;
//import gregtech.api.util.GT_Recipe;
//import gregtech.api.util.GT_Utility;
//import net.minecraft.block.Block;
//import net.minecraft.client.renderer.texture.IIconRegister;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraftforge.fluids.FluidStack;
//
//import java.util.*;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;
//import static gregtech.api.util.GT_StructureUtility.ofHatchAdderOptional;
//import static com.gtnewhorizons.obama.main.loaders.CasingTextureLoader.texturePage;
//
//
////TODO Delete this Bart code
//@Deprecated
//public class GT_MetaTileEntity_MultiMachine_RecipeMap extends GT_MetaTileEntity_EnhancedMultiBlockBase<GT_MetaTileEntity_MultiMachine_RecipeMap> implements
//        IAddsBlocks, IConstructable/* this interface adds blueprinting option*/ {
//
//    public static final IStructureExpander<GT_MetaTileEntity_MultiMachine_RecipeMap> FUNCTIONAL_CASING_STRUCTURE_EXPANDER = (def, builder) -> builder
//            .addElement('f', ofHatchAdder(
//                    GT_MetaTileEntity_MultiMachine_RecipeMap::addFunctionalCasingToMachineList,
//                    def.getTextureIndex(), 8))
//            .addElement('F', ofHatchAdderOptional(
//                    GT_MetaTileEntity_MultiMachine_RecipeMap::addFunctionalCasingToMachineList,
//                    def.getTextureIndex(), 8, def.getSpecialBlock(), def.getMetaSpecialBlock()));
//    private static final Map<String, Textures.BlockIcons.CustomIcon> ScreensOFF = new HashMap<>();
//    private static final Map<String, Textures.BlockIcons.CustomIcon> ScreensON = new HashMap<>();
//    private final ArrayList<GT_MetaTileEntity_TM_HatchCasing> mFunctionalCasings = new ArrayList<>();
//    private final List<Pair<Block, Integer>> mSpecialBlocks = new ArrayList<>();
//    private GT_Recipe buffered_Recipe;
//    private MultiBlockDefinition multiBlockDefinition;
//
//    public GT_MetaTileEntity_MultiMachine_RecipeMap(int aID,
//                                                    String aName,
//                                                    String aNameRegional,
//                                                    MultiBlockDefinition multiBlockDefinition) {
//        super(aID, aName, aNameRegional);
//        this.multiBlockDefinition = multiBlockDefinition;
//    }
//
//    public GT_MetaTileEntity_MultiMachine_RecipeMap(String aName, MultiBlockDefinition multiBlockDefinition) {
//        super(aName);
//        this.multiBlockDefinition = multiBlockDefinition;
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void registerIcons(IIconRegister aBlockIconRegister) {
//        super.registerIcons(aBlockIconRegister);
//        String name = multiBlockDefinition.name();
//        ScreensOFF.put(name, new Textures.BlockIcons.CustomIcon("iconsets/TM_" + name));
//        ScreensON.put(name, new Textures.BlockIcons.CustomIcon("iconsets/TM_" + name + "_ACTIVE"));
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
//        int textureID = multiBlockDefinition.getStructure().getTextureIndex();
//        ITexture[] textures;
//
//        if (aSide == aFacing) {
//            String name = multiBlockDefinition.name();
//            Textures.BlockIcons.CustomIcon aScreenOFF = ScreensOFF.get(name);
//            Textures.BlockIcons.CustomIcon aScreenON = ScreensON.get(name);
////            textures = new ITexture[]{Textures.BlockIcons.casingTexturePages[texturePage][textureID], new TT_RenderedExtendedFacingTexture(aActive ? aScreenON : aScreenOFF)};
//            textures = new ITexture[]{Textures.BlockIcons.casingTexturePages[texturePage][textureID]};
//        } else {
//            textures = new ITexture[]{Textures.BlockIcons.casingTexturePages[texturePage][textureID]};
//        }
//        return textures;
//    }
//
//    public String[] getDescription() {
//        return multiBlockDefinition.getTooltip();
//    }
//
//    @Override
//    public void saveNBTData(NBTTagCompound aNBT) {
//        super.saveNBTData(aNBT);
//        aNBT.setString("definition", multiBlockDefinition.name());
//    }
//
//    @Override
//    public void loadNBTData(NBTTagCompound aNBT) {
//        super.loadNBTData(aNBT);
//        multiBlockDefinition = MultiBlockDefinition.valueOf(aNBT.getString("definition"));
//    }
//
//    @Override
//    public boolean checkMachine(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
//        this.mSpecialBlocks.clear();
//        return this.structureCheck("main",
//                multiBlockDefinition.getStructure().getHorizontalOffset(),
//                multiBlockDefinition.getStructure().getVerticalOffset(),
//                multiBlockDefinition.getStructure().getDepthOffset()
//        );
//    }
//
//    @Override
//    public IMetaTileEntity newMetaEntity(IGregTechTileEntity iGregTechTileEntity) {
//        return new GT_MetaTileEntity_MultiMachine_RecipeMap(mName, multiBlockDefinition);
//    }
//
//    public byte getTier() {
//        return GT_Utility.getTier(this.getMaxInputVoltage());
//    }
//
//    @Override
//    public boolean checkRecipe(ItemStack itemStack) {
//        AtomicBoolean ret = new AtomicBoolean(false);
//        FluidStack[] flinputs = MultiBlockUtils.sortFluidStacks(this.getStoredFluids());
//        ItemStack[] itinputs = MultiBlockUtils.sortItemStacks(this.getStoredInputs());
//        if (flinputs.length > 0 || itinputs.length > 0) {
//            GT_Recipe.GT_Recipe_Map map = getRecipeMap();
//
//            Optional<GT_Recipe> gt_recipe = Optional.ofNullable(
//                    map.findRecipe(
//                            this.getBaseMetaTileEntity(),
//                            buffered_Recipe,
//                            false,
//                            true,
//                            this.getMaxInputVoltage(),
//                            flinputs,
//                            this.getStackInSlot(0),
//                            itinputs
//                    )
//            );
//
//            if (!gt_recipe.isPresent())
//                return false;
//
//            gt_recipe
//                    .filter(recipe -> recipe.mCanBeBuffered)
//                    .ifPresent(recipe -> buffered_Recipe = recipe);
//
//            gt_recipe
//                    .ifPresent(recipe -> {
//                        ArrayList<ItemStack> outputItems = new ArrayList<>();
//                        ArrayList<FluidStack> outputFluids = new ArrayList<>();
//                        boolean rzz;
//                        int i = 0;
//                        while (true) {
//                            rzz = recipe.isRecipeInputEqual(
//                                    true,
//                                    false,
//                                    flinputs,
//                                    itinputs
//                            );
//                            i++;
//                            if (rzz && multiBlockDefinition.getMaxParalellsPerTier() * this.getTier() < i) {
//                                for (int j = 0; j < recipe.mOutputs.length; j++) {
//                                    outputItems.add(recipe.getOutput(i));
//                                }
//
//                                for (int j = 0; j < recipe.mFluidOutputs.length; j++) {
//                                    outputFluids.add(recipe.getFluidOutput(i));
//                                }
//                            } else
//                                break;
//                        }
//
//                        this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
//                        this.mEfficiencyIncrease = 10000;
//
//
//                        this.mOutputItems = MultiBlockUtils.sortItemStacks(outputItems);
//                        this.mOutputFluids = MultiBlockUtils.sortFluidStacks(outputFluids);
//
//                        this.calculateOverclockedNessMultiInternal(recipe.mEUt * i,
//                                recipe.mDuration,
//                                map.mAmperage * i,
//                                this.getMaxInputVoltage(),
//                                multiBlockDefinition.isPerfectOC()
//                        );
//
//                        if (mMaxProgresstime == Integer.MAX_VALUE - 1 && mEUt == Integer.MAX_VALUE - 1)
//                            return;
//
//                        if (this.mEUt > 0) {
//                            this.mEUt = (-this.mEUt);
//                        }
//
//                        this.mMaxProgresstime = Math.max(1, this.mMaxProgresstime);
//                        updateSlots();
//                        ret.set(true);
//                        mFunctionalCasings.forEach(mte -> mte.getBaseMetaTileEntity().setActive(true));
//                    });
//        }
//        return ret.get();
//    }
//
//    @Override
//    public IStructureDefinition<GT_MetaTileEntity_MultiMachine_RecipeMap> getStructure_EM() {
//        return multiBlockDefinition.getStructureDefinition();
//    }
//
//    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
//        return multiBlockDefinition.getRecipe_map();
//    }
//
//    @Override
//    public void stopMachine() {
//        super.stopMachine();
//        mFunctionalCasings.forEach(mte -> mte.getBaseMetaTileEntity().setActive(false));
//    }
//
//    @Override
//    public List<Pair<Block, Integer>> getSpecialBlocks() {
//        return this.mSpecialBlocks;
//    }
//
//    @Override
//    public Pair<Block, Integer> getRequiredSpecialBlock() {
//        return multiBlockDefinition.getStructure().getSpecialBlockPair();
//    }
//
//    @Override
//    public void construct(ItemStack itemStack, boolean hintsOnly) {
//        structureBuild_EM("main",
//                multiBlockDefinition.getStructure().getHorizontalOffset(),
//                multiBlockDefinition.getStructure().getVerticalOffset(),
//                multiBlockDefinition.getStructure().getDepthOffset(),
//                hintsOnly, itemStack);
//    }
//
//    @Override
//    public String[] getStructureDescription(ItemStack itemStack) {
//        return new String[0];//todo description on blueprint?
//    }
//
//    public final boolean addFunctionalCasingToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
//        boolean flag = false;
//        if (aTileEntity != null) {
//            IMetaTileEntity mte = aTileEntity.getMetaTileEntity();
//            if (mte instanceof GT_MetaTileEntity_TM_HatchCasing) {
//                GT_MetaTileEntity_TM_HatchCasing hatch = ((GT_MetaTileEntity_TM_HatchCasing) mte);
//                hatch.updateTexture(aBaseCasingIndex);
//                flag = this.mFunctionalCasings.add(hatch);
//            }
//        }
//        return flag;
//    }
//}
