package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RecipeProgresion {
    private GT_Recipe recipe;
    private int euUsage;
    private int time;
    private ItemStack[] items;
    private FluidStack[] fluids;
    private int amount;

    public RecipeProgresion(GT_Recipe recipe, int amount,int time,int euUsage){
        this.recipe = recipe;
        this.time = time;
        this.euUsage = euUsage;
        this.amount = amount;
        processRecipe(amount);
    }

    public int isRecipeDone(int time){
        this.time -= time;
        return this.time;
    }

    public int getTimeLeft() {
        return this.time;
    }

    public int getEUUsage() {
        return euUsage;
    }

    public int getAmount() {
        return amount;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public FluidStack[] getFluids() {
        return fluids;
    }

    //TODO multi thread this??? if it consumes any significan amount of time
    private void processRecipe(int amount) {

        int totalStacks = getTotalStacks(amount);
        this.items = new ItemStack[totalStacks];
        populateItemStack(amount);
        fluids = new FluidStack[recipe.mFluidOutputs.length];
        populateFluidStack(amount);

    }

    private int getTotalStacks(int amount) {
        int totalStacks = 0;
        ItemStack[] items = recipe.mOutputs;
        for (ItemStack item : items) {
            if (item == null)
                continue;
            int maxStackSize = item.getMaxStackSize();
            int total = item.stackSize * amount;
            totalStacks += total/maxStackSize;
            if (total%maxStackSize>0)
                totalStacks++;
        }
        return totalStacks;
    }

    //asumes this.items has a valid array
    private void populateItemStack(int amount) {
        int itemIndex = 0;
        for (ItemStack item : recipe.mOutputs) {
            if (item == null)
                continue;
            int maxStackSize = item.getMaxStackSize();
            int total = item.stackSize * amount;
            int stackCount = total/maxStackSize;
            for (int i = 0; i < stackCount; i++) {
                ItemStack copy = item.copy();
                copy.stackSize = maxStackSize;
                this.items[itemIndex] = copy;
                itemIndex++;
            }
            int rest = total%maxStackSize;
            if (rest > 0){
                ItemStack copy = item.copy();
                copy.stackSize = rest;
                this.items[itemIndex] = copy;
                itemIndex++;
            }
        }
    }

    private void populateFluidStack(int amount) {
        for (int i = 0; i < recipe.mFluidOutputs.length; i++) {
            FluidStack fluid = recipe.mFluidOutputs[i].copy();
            fluid.amount *= amount;
            fluids[i] = fluid;
        }
    }
}
