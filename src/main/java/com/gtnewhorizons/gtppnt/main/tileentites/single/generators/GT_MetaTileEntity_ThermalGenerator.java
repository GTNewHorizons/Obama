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

package com.gtnewhorizons.gtppnt.main.tileentites.single.generators;

import com.github.bartimaeusnek.bartworks.util.MathUtils;
import com.gtnewhorizons.gtppnt.main.utils.GTAFGeneratorUtils;
import com.gtnewhorizons.gtppnt.main.utils.GTAFRecipes;
import gregtech.api.GregTech_API;
import gregtech.api.enums.ConfigCategories;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class GT_MetaTileEntity_ThermalGenerator extends GT_MetaTileEntity_BasicGenerator {

    private int mEfficiency;

    public GT_MetaTileEntity_ThermalGenerator(int aID, String aName, String aNameRegional, int aTier, String aDescription, ITexture... aTextures) {
        super(aID, aName, aNameRegional, aTier, aDescription, aTextures);
        onConfigLoad();
    }

    public GT_MetaTileEntity_ThermalGenerator(String aName, int aTier, String[] aDescription, ITexture[][][] aTextures) {
        super(aName, aTier, aDescription, aTextures);
        onConfigLoad();
    }

    public void onConfigLoad() {
        if (this.mEfficiency == 0)
            this.mEfficiency = GregTech_API.sMachineFile.get(ConfigCategories.machineconfig, "ThermalGenerator.efficiency.tier." + this.mTier, (int) MathUtils.ceil(-10F * mTier + 123F));
    }

    @Override
    @SuppressWarnings("deprecation")
    public String[] getDescription() {
        return new String[]{this.mDescription, "Produces " + (this.getPollution() * 20) + " pollution/sec", "Fuel Efficiency: " + this.getEfficiency() + "%"};
    }

    public int consumedFluidPerOperation(FluidStack aLiquid) {
        return 1;
    }

    @Override
    public int getPollution() {
        return 3 * mTier;
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipes() {
        return GTAFRecipes.getThermalgeneratorFuels();
    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public long maxEUStore() {
        return Math.max(this.getEUVar(), GT_Values.V[this.mTier] * 20L + this.getMinimumStoredEU());
    }

    @Override
    public boolean isOutputFacing(byte aSide) {
        return aSide == getBaseMetaTileEntity().getFrontFacing();
    }

    @Override
    public int getFuelValue(ItemStack aStack) {
        return GTAFGeneratorUtils.getFuelValueGenerator(aStack, this, () -> {
            double rValue = super.getFuelValue(aStack);
            if (GT_Utility.areStacksEqual(new ItemStack(Items.lava_bucket), aStack)) {
                rValue = super.getFuelValue(Materials.Lava.getFluid(1000));
                this.getRealInventory()[this.getInputSlot()] = GT_Values.NI;
                this.getRealInventory()[this.getOutputSlot()] = new ItemStack(Items.bucket);
            }
            return rValue;
        });
    }

    @Override
    public boolean doesEmptyContainers() {
        return false;
    }

    @Override
    public int getEfficiency() {
        return mEfficiency;
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new GT_MetaTileEntity_ThermalGenerator(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    @Override
    public ITexture[] getFront(byte aColor) {
        return new ITexture[]{super.getFront(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.GAS_TURBINE_FRONT), Textures.BlockIcons.OVERLAYS_ENERGY_OUT[this.mTier]};
    }

    @Override
    public ITexture[] getBack(byte aColor) {
        return new ITexture[]{super.getBack(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.GAS_TURBINE_BACK)};
    }

    @Override
    public ITexture[] getBottom(byte aColor) {
        return new ITexture[]{super.getBottom(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.GAS_TURBINE_BOTTOM)};
    }

    @Override
    public ITexture[] getTop(byte aColor) {
        return new ITexture[]{super.getTop(aColor)[0], new GT_RenderedTexture(new Textures.BlockIcons.CustomIcon("basicmachines/pulverizer/OVERLAY_TOP"))};
    }

    @Override
    public ITexture[] getSides(byte aColor) {
        return new ITexture[]{super.getSides(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.GAS_TURBINE_SIDE)};
    }

    @Override
    public ITexture[] getFrontActive(byte aColor) {
        return new ITexture[]{super.getFrontActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.GAS_TURBINE_FRONT_ACTIVE), Textures.BlockIcons.OVERLAYS_ENERGY_OUT[this.mTier]};
    }

    @Override
    public ITexture[] getBackActive(byte aColor) {
        return new ITexture[]{super.getBackActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.GAS_TURBINE_BACK_ACTIVE)};
    }

    @Override
    public ITexture[] getBottomActive(byte aColor) {
        return new ITexture[]{super.getBottomActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.GAS_TURBINE_BOTTOM_ACTIVE)};
    }

    @Override
    public ITexture[] getTopActive(byte aColor) {
        return new ITexture[]{super.getTopActive(aColor)[0], new GT_RenderedTexture(new Textures.BlockIcons.CustomIcon("basicmachines/pulverizer/OVERLAY_TOP_ACTIVE"))};
    }

    @Override
    public ITexture[] getSidesActive(byte aColor) {
        return new ITexture[]{super.getSidesActive(aColor)[0], new GT_RenderedTexture(Textures.BlockIcons.GAS_TURBINE_SIDE_ACTIVE)};
    }
}
