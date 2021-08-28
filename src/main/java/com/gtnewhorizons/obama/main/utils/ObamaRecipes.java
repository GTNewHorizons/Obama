/*
 * Copyright 2021 The GTNH Team
 * 
 *  See LICENSE.md, COPYING, and COPYING.LESSER
 */

package com.gtnewhorizons.obama.main.utils;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.github.bartimaeusnek.bartworks.util.BW_Util;
import com.github.bartimaeusnek.bartworks.util.NonNullWrappedHashSet;
import com.github.bartimaeusnek.bartworks.util.Pair;
import com.gtnewhorizons.obama.main.ObamaMod;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.gtnewhorizons.obama.main.utils.IC2CellGetter.COOLANT;
import static com.gtnewhorizons.obama.main.utils.IC2CellGetter.DISTILLED_WATER;
import static com.gtnewhorizons.obama.main.utils.IC2CellGetter.HOT_COOLANT;
import static com.gtnewhorizons.obama.main.utils.IC2CellGetter.LAVA;
import static com.gtnewhorizons.obama.main.utils.IC2CellGetter.PAHOEHOELAVA;
import static com.gtnewhorizons.obama.main.utils.IC2CellGetter.STEAM;
import static com.gtnewhorizons.obama.main.utils.IC2CellGetter.SUPERHEATEDSTEAM;
import static com.gtnewhorizons.obama.main.utils.MaterialsUtils.applyToAllMaterialsAndWerkstoffe;
import static gregtech.api.enums.GT_Values.RES_PATH_GUI;

public class ObamaRecipes {

    private static final GT_Recipe.GT_Recipe_Map SIMPLE_WASHER_MAP =
            new GT_Recipe.GT_Recipe_Map(new NonNullWrappedHashSet<>(),
                    "Obama.map.simpleWasher", "Simple Washer Recipes",
                    "Simple Washer Recipes", RES_PATH_GUI + "basicmachines/Default",
                    1, 1, 1, 1, 1,
                    "", 1, "", true, true
            );

    private static final GT_Recipe.GT_Recipe_Map_Fuel THERMALGENERATOR_FUELS =
            new GT_Recipe.GT_Recipe_Map_Fuel(new NonNullWrappedHashSet<>(),
                    "Obama.map.thermal", "Thermal Generator Fuels",
                    "Thermal Generator Fuels", RES_PATH_GUI + "basicmachines/Default",
                    1, 1, 0, 0, 1,
                    "Fuel Value: ", 1000, " EU", true, true
            );

    private static final GT_Recipe.GT_Recipe_Map ENHANCED_MIXER_MAP =
            new GT_Recipe.GT_Recipe_Map(new NonNullWrappedHashSet<>(),
                    "Obama.map.enhancedMixer", "Enhanced Mixer Recipes",
                    "Enhanced Mixer Recipes", RES_PATH_GUI + "basicmachines/Default",
                    4, 4, 1, 1, 1,
                    "", 1, "", true, true
            );

    private static final Map<Materials, Materials> MATERIALS_MATERIALS_HASH_MAP = new HashMap<>();

    /*-------------------- GETTER METHODES --------------------*/

    public static synchronized Map<Materials, Materials> getSimpleWasherOverrideMap() {
        return MATERIALS_MATERIALS_HASH_MAP;
    }

    public static synchronized GT_Recipe.GT_Recipe_Map getSimpleWasherMap() {
        return SIMPLE_WASHER_MAP;
    }

    public static synchronized GT_Recipe.GT_Recipe_Map_Fuel getThermalgeneratorFuels() {
        return THERMALGENERATOR_FUELS;
    }

    public static synchronized GT_Recipe.GT_Recipe_Map getEnhancedMixerMap() {
        return ENHANCED_MIXER_MAP;
    }

    /*-------------------- ACTUAL CODE  --------------------*/

    public static void executeObamaRecipes() {
        ObamaMod.LOGGER.info("Starting ObamaRecipes");

        executeMultiThreadedRecipeTasks();
        executeSingleThreadedRecipeTasks();

        ObamaMod.LOGGER.info("ObamaRecipes registered!");
    }

    private static void executeMultiThreadedRecipeTasks() {
        Thread obamaRecipeRegistrationThread = new Thread(() -> {
            synchronized (SIMPLE_WASHER_MAP) {
                //lock whatever is inside here
                fillSimpleWasherMap();
                fillThermalGeneratorMap();
            }
            ObamaMod.LOGGER.info("Obama recipe registration threaded ended!");
        });

        obamaRecipeRegistrationThread.setName("Obama recipe registration thread"); //rename the thread
        obamaRecipeRegistrationThread.start(); //start it in the background
        ObamaMod.LOGGER.info("Obama recipe registration threaded started!");
    }

    private static void executeSingleThreadedRecipeTasks() {
        ObamaMod.LOGGER.info("Obama recipe registration single threaded target started!");
        fillEnhancedMixerMap();
        ObamaMod.LOGGER.info("Obama recipe registration single threaded target ended!");
    }

    private static void fillSimpleWasherMap() {
        fillSimpleWasherReplacementMap();
        addAllToSimpleWasherMap();
    }

    private static void fillThermalGeneratorMap() {
        THERMALGENERATOR_FUELS.addFuel(LAVA.getCell(), PAHOEHOELAVA.getCell(), 20);
        THERMALGENERATOR_FUELS.addFuel(HOT_COOLANT.getCell(), COOLANT.getCell(), 15);
        THERMALGENERATOR_FUELS.addFuel(SUPERHEATEDSTEAM.getCell(), STEAM.getCell(), 10);
        THERMALGENERATOR_FUELS.addFuel(STEAM.getCell(), DISTILLED_WATER.getCell(), 5);
    }

    private static void fillEnhancedMixerMap() {
        GT_Recipe.GT_Recipe_Map.sMixerRecipes.mRecipeList.forEach(ObamaRecipes::getEnhancedMixerLogic);
        ENHANCED_MIXER_MAP.reInit();
    }

    /*-------------------- HELPER METHODES --------------------*/

    private static void getEnhancedMixerLogic(GT_Recipe recipe) {
        ItemStack[] cleanedInputs = BW_Util.copyAndRemoveNulls(recipe.mInputs, ItemStack.class);
        if (cleanedInputs.length <= 4) {
            GT_Recipe recipeToAdd = recipe.copy();
            recipeToAdd.mInputs = cleanedInputs;
            recipeToAdd.mEUt <<= 2;
            recipeToAdd.mDuration >>= 1;
            ENHANCED_MIXER_MAP.mRecipeList.add(recipeToAdd);
        }
    }

    private static ItemStack getSimpleWasherInput(OrePrefixes orePrefixes, Materials materials) {
        return GT_OreDictUnificator.get(
                orePrefixes,
                materials,
                1);
    }

    private static ItemStack getSimpleWasherOutput(OrePrefixes orePrefixes, Materials materials, boolean isPureDust) {
        return GT_OreDictUnificator.get(
                orePrefixes,
                isPureDust ?
                        MATERIALS_MATERIALS_HASH_MAP.getOrDefault(materials, materials) :
                        materials,
                1);
    }

    private static Predicate<Materials> getNonNullFilter(boolean isPureDust, Pair<OrePrefixes, OrePrefixes> orePrefixesPair) {
        return materials ->
                Objects.nonNull(getSimpleWasherInput(orePrefixesPair.getKey(), materials)) &&
                        Objects.nonNull(getSimpleWasherOutput(orePrefixesPair.getValue(), materials, isPureDust));

    }

    private static Consumer<Materials> addToSimpleWasherMap(boolean isPureDust, Pair<OrePrefixes, OrePrefixes> orePrefixesPair) {
        return materials ->
                SIMPLE_WASHER_MAP.addRecipe(true,
                        new ItemStack[]{getSimpleWasherInput(orePrefixesPair.getKey(), materials)},
                        new ItemStack[]{getSimpleWasherOutput(orePrefixesPair.getValue(), materials, isPureDust)},
                        null,
                        new FluidStack[]{Materials.Water.getFluid(100)},
                        null,
                        10,
                        7,
                        0
                );
    }

    @SuppressWarnings("unchecked")
    private static void fillSimpleWasherReplacementMap() {
        Pair<Materials, Materials>[] pairArr = new Pair[]{
                new Pair<>(Materials.Platinum, WerkstoffLoader.PTMetallicPowder.getBridgeMaterial()),
                new Pair<>(Materials.Palladium, WerkstoffLoader.PDMetallicPowder.getBridgeMaterial()),
                new Pair<>(Materials.Osmium, WerkstoffLoader.IrOsLeachResidue.getBridgeMaterial()),
                new Pair<>(Materials.Iridium, WerkstoffLoader.IrLeachResidue.getBridgeMaterial()),
                new Pair<>(WerkstoffLoader.Rhodium.getBridgeMaterial(), WerkstoffLoader.CrudeRhMetall.getBridgeMaterial()),
                new Pair<>(WerkstoffLoader.Ruthenium.getBridgeMaterial(), WerkstoffLoader.LeachResidue.getBridgeMaterial()),
        };

        Arrays.stream(pairArr).forEach(p -> MATERIALS_MATERIALS_HASH_MAP.put(p.getKey(), p.getValue()));
    }

    @SuppressWarnings("unchecked")
    private static void addAllToSimpleWasherMap() {
        Pair<OrePrefixes, OrePrefixes>[] arr = new Pair[]{ //create an array of in -> output
                new Pair<>(OrePrefixes.crushed, OrePrefixes.crushedPurified),
                new Pair<>(OrePrefixes.dustImpure, OrePrefixes.dustPure),
                new Pair<>(OrePrefixes.dustPure, OrePrefixes.dust),
        };
        Arrays.stream(arr).forEach(orePrefixesPair ->
                {
                    boolean isPureDust = orePrefixesPair == arr[2];
                    applyToAllMaterialsAndWerkstoffe(
                            //make sure in- & output is not null
                            getNonNullFilter(isPureDust, orePrefixesPair),
                            //add the recipe to the map
                            addToSimpleWasherMap(isPureDust, orePrefixesPair)
                    );
                }
        );
    }
}