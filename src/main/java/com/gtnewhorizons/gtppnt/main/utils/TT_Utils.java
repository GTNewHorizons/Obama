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

package com.gtnewhorizons.gtppnt.main.utils;

import com.github.bartimaeusnek.bartworks.util.Pair;
import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.mechanics.structure.IStructureElement;
import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureUtility;
import com.github.technus.tectech.thing.casing.TT_Container_Casings;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import gregtech.api.GregTech_API;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Recipe;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TODO: Test this SHIT
public class TT_Utils {

    public static final char Input = 'i';
    public static final char Output = 'o';
    public static final char Energy = 'e';
    public static final char Dynamo = 'd';
    public static final char Muffler = 'p';
    public static final char Maintenance = 'm';
    public static final char Block = 'b';
    public static final char SpecialBlock = 's';
    public static final char defaultInputOutputEnergy = '1';

    /**
     * Creates a Multiblock Defintion
     * <p></p>
     * <p>Hints are: </p>
     * <p> 0 Input Hatch/Bus</p>
     * <p> 1 Output Hatch/Bus</p>
     * <p> 2 Energy Input Hatch</p>
     * <p> 3 Maintenance Hatch</p>
     * <p> 4 Muffler Hatch</p>
     * <p> 5 Dynamo Hatch</p>
     * <p> 6 Special Block</p>
     *
     * @param structure    make a new String[][]
     *
     *                     <p>each internal String[] defines a layer</p>
     *                     <p>each string the blocks of the layer</p>
     *                     <p>each char is a block definition</p>
     *                     <p></p>
     *                     <p>Allowed Characters, Uppercase is OPTIONAL:</p>
     *                     <p>i = Input Hatch/Bus</p>
     *                     <p>o = Output Hatch/Bus</p>
     *                     <p>e = Energy Input Hatch</p>
     *                     <p>d = Dynamo Hatch</p>
     *                     <p>p = Muffler Hatch</p>
     *                     <p>m = Maintenance Hatch</p>
     *                     <p>s = Special Block (i.e. Coils)</p>
     *                     <p>b = Block</p>
     *                     <p></p>
     *                     <p>- = air</p>
     *                     <p>+ = non-air</p>
     *                     <p>~ = controller</p>
     *                     <p></p>
     *                     <p>1 = any Input/Output Hatch/Bus or Energy Input Hatch</p>
     *                     <p></p>
     * @param textureIndex the tecture for the hatches
     * @param setCasing    Casing Block which is used to build the Multiblock
     * @param setMeta      Casing Block Meta which is used to build the Multiblock
     * @return IStructureDefinition for your Multiblock
     */
    public static IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getDefaultStructureDefinition(String[][] structure, int textureIndex, Block setCasing, int setMeta) {
        return StructureDefinition.builder().addShape("main", structure)
                .addElement('1', StructureUtility.ofChain(
                        new IStructureElement[]{
                                //(final IHatchAdder<T> iHatchAdder, final int textureIndex, final Block hintBlock, final int hintMeta, final Block placeCasing, final int placeCasingMeta)
                                StructureUtility.ofHatchAdderOptional(
                                        GT_MetaTileEntity_MultiblockBase_EM::addEnergyInputToMachineList,
                                        textureIndex,
                                        TT_Container_Casings.sHintCasingsTT,
                                        2,
                                        setCasing,
                                        setMeta
                                ),
                                StructureUtility.ofHatchAdderOptional(
                                        GT_MetaTileEntity_MultiblockBase_EM::addClassicInputToMachineList,
                                        textureIndex,
                                        TT_Container_Casings.sHintCasingsTT,
                                        0,
                                        setCasing,
                                        setMeta
                                ),
                                StructureUtility.ofHatchAdderOptional(
                                        GT_MetaTileEntity_MultiblockBase_EM::addClassicOutputToMachineList,
                                        textureIndex,
                                        TT_Container_Casings.sHintCasingsTT,
                                        1,
                                        setCasing,
                                        setMeta
                                ),
                                StructureUtility.ofHatchAdderOptional(
                                        GT_MetaTileEntity_MultiblockBase_EM::addClassicMaintenanceToMachineList,
                                        textureIndex,
                                        TT_Container_Casings.sHintCasingsTT,
                                        3,
                                        setCasing,
                                        setMeta
                                )
                        }
                        )
                )
                .addElement('b', StructureUtility.ofBlock(setCasing, setMeta))
                .addElement('B', StructureUtility.ofBlock(setCasing, setMeta, Blocks.air, 0))
                .addElement('s', StructureUtility.ofBlockAdder(IAddsBlocks::addBlockToMachine, 6))
                .addElement('S', StructureUtility.ofBlockAdder(IAddsBlocks::addBlockToMachine, Blocks.air, 0))
                .addElement(
                        'i', StructureUtility.ofHatchAdder(
                                GT_MetaTileEntity_MultiblockBase_EM::addClassicInputToMachineList,
                                textureIndex,
                                TT_Container_Casings.sHintCasingsTT,
                                0
                        )
                )
                .addElement(
                        'o', StructureUtility.ofHatchAdder(
                                GT_MetaTileEntity_MultiblockBase_EM::addClassicOutputToMachineList,
                                textureIndex,
                                TT_Container_Casings.sHintCasingsTT,
                                1
                        )
                )
                .addElement(
                        'e', StructureUtility.ofHatchAdder(
                                GT_MetaTileEntity_MultiblockBase_EM::addEnergyInputToMachineList,
                                textureIndex,
                                TT_Container_Casings.sHintCasingsTT,
                                2
                        )
                )
                .addElement(
                        'm', StructureUtility.ofHatchAdder(
                                GT_MetaTileEntity_MultiblockBase_EM::addClassicMaintenanceToMachineList,
                                textureIndex,
                                TT_Container_Casings.sHintCasingsTT,
                                3
                        )
                )
                .addElement(
                        'p', StructureUtility.ofHatchAdder(
                                GT_MetaTileEntity_MultiblockBase_EM::addClassicMufflerToMachineList,
                                textureIndex,
                                TT_Container_Casings.sHintCasingsTT,
                                4
                        )
                )
                .addElement(
                        'd', StructureUtility.ofHatchAdder(
                                GT_MetaTileEntity_MultiblockBase_EM::addDynamoToMachineList,
                                textureIndex,
                                TT_Container_Casings.sHintCasingsTT,
                                5
                        )
                )
                .addElement(
                        'I', StructureUtility.ofHatchAdderOptional(
                                GT_MetaTileEntity_MultiblockBase_EM::addClassicInputToMachineList,
                                textureIndex,
                                TT_Container_Casings.sHintCasingsTT,
                                0,
                                setCasing,
                                setMeta
                        )
                )
                .addElement(
                        'O', StructureUtility.ofHatchAdderOptional(
                                GT_MetaTileEntity_MultiblockBase_EM::addClassicOutputToMachineList,
                                textureIndex,
                                TT_Container_Casings.sHintCasingsTT,
                                1,
                                setCasing,
                                setMeta
                        )
                )
                .addElement(
                        'E', StructureUtility.ofHatchAdderOptional(
                                GT_MetaTileEntity_MultiblockBase_EM::addEnergyInputToMachineList,
                                textureIndex,
                                TT_Container_Casings.sHintCasingsTT,
                                2,
                                setCasing,
                                setMeta
                        )
                )
                .addElement(
                        'M', StructureUtility.ofHatchAdderOptional(
                                GT_MetaTileEntity_MultiblockBase_EM::addClassicMaintenanceToMachineList,
                                textureIndex,
                                TT_Container_Casings.sHintCasingsTT,
                                3,
                                setCasing,
                                setMeta
                        )
                )
                .addElement(
                        'P', StructureUtility.ofHatchAdderOptional(
                                GT_MetaTileEntity_MultiblockBase_EM::addClassicMufflerToMachineList,
                                textureIndex,
                                TT_Container_Casings.sHintCasingsTT,
                                4,
                                setCasing,
                                setMeta
                        )
                )
                .addElement(
                        'D', StructureUtility.ofHatchAdderOptional(
                                GT_MetaTileEntity_MultiblockBase_EM::addDynamoToMachineList,
                                textureIndex,
                                TT_Container_Casings.sHintCasingsTT,
                                5,
                                setCasing,
                                setMeta
                        )
                )
                .build();
    }

    public enum DefaultStructureDefinitions {

        FREEZER_ALIKE(GeometricInstances.CUBE_3x3x3, GregTech_API.sBlockCasings2, 1, null, 0, 17);

        private final GeometricInstances geometrics;
        private final Block toBuildWith;
        private final int metaToBuildWith;
        private final Block specialBlock;
        private final int metaSpecialBlock;
        private final int textureIndex;
        private final IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> iStructureDefinition;

        DefaultStructureDefinitions(GeometricInstances geometrics, Block toBuildWith, int metaToBuildWith, Block specialBlock, int metaSpecialBlock, int textureIndex) {
            this.geometrics = geometrics;
            this.toBuildWith = toBuildWith;
            this.metaToBuildWith = metaToBuildWith;
            this.textureIndex = textureIndex;
            this.iStructureDefinition = this.geometrics.getDefinition(this.textureIndex, this.toBuildWith, this.metaToBuildWith);
            this.specialBlock = specialBlock;
            this.metaSpecialBlock = metaSpecialBlock;
        }

        public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getStructureDefinition() {
            return iStructureDefinition;
        }

        public int getRequiredMufflers() {
            return geometrics.getAmount(Muffler);
        }

        public int getRequiredEnergyHatches() {
            return geometrics.getAmount(Energy);
        }

        public int getRequiredDynamos() {
            return geometrics.getAmount(Dynamo);
        }

        public int getRequiredMaintenanceHatches() {
            return geometrics.getAmount(Maintenance);
        }

        public int getRequiredInputs() {
            return geometrics.getAmount(Input);
        }

        public int getRequiredOutputs() {
            return geometrics.getAmount(Output);
        }

        public int getRequiredBlocks() {
            return geometrics.getAmount(Block);
        }

        public int getRequiredSpecialBlocks() {
            return geometrics.getAmount(SpecialBlock);
        }

        public int getHorizontalOffset() {
            return geometrics.horizontalOffset;
        }

        public int getVerticalOffset() {
            return geometrics.verticalOffset;
        }

        public int getDefaultHatches() {
            return geometrics.getAmount(defaultInputOutputEnergy);
        }

        public List<String> generateTooltip() {
            return Stream.of("<--Filled out by MultiBlockClass-->",
                    "<--Filled out by MultiBlockClass-->",
                    "Size(WxHxD): " + geometrics.getDimensions() + " (Hollow), Controller (Front centered)",
                    getDefaultHatches() + getRequiredInputs() > 0 ? getRequiredInputs() + getDefaultHatches() + "x Inputs" : null,
                    getDefaultHatches() + getRequiredOutputs() > 0 ? getRequiredOutputs() + getDefaultHatches() + "x Outputs" : null,
                    getRequiredMaintenanceHatches() > 0 ? getRequiredMaintenanceHatches() + "x Maintenance Hatch" : null,
                    getDefaultHatches() + getRequiredEnergyHatches() > 0 ? getRequiredEnergyHatches() + getDefaultHatches() + "x Energy Hatch" : null,
                    getRequiredDynamos() > 0 ? getRequiredDynamos() + "x Dynamo Hatch" : null,
                    getRequiredMufflers() > 0 ? getRequiredMufflers() + "x Muffler Hatch" : null,
                    getRequiredSpecialBlocks() > 0 ? getRequiredSpecialBlocks() + "x " + GT_LanguageManager.getTranslation(GT_LanguageManager.getTranslateableItemStackName(new ItemStack(specialBlock, 1, metaSpecialBlock))) : null,
                    getRequiredBlocks() > 0 ? getRequiredBlocks() + "x " + GT_LanguageManager.getTranslation(GT_LanguageManager.getTranslateableItemStackName(new ItemStack(toBuildWith, 1, metaToBuildWith))) : null,
                    "For more information use TecTechs Blueprint!"
            ).filter(Objects::nonNull).collect(Collectors.toList());
        }

        public Pair<Block, Integer> getSpecialBlock() {
            return new Pair<>(specialBlock, metaSpecialBlock);
        }
    }

    private enum GeometricInstances {

        CUBE_3x3x3(new String[][]{
                {"111", "111", "111"},
                {"1~1", "1-1", "111"},
                {"111", "111", "111"}
        }),

        CUBE_3x3x3_WithMuffler(new String[][]{
                {"111", "1P1", "111"},
                {"1~1", "1-1", "111"},
                {"111", "111", "111"}
        }),

        CUBE_5x5x5_WithMuffler(new String[][]{
                {"11111", "11111", "11P11", "11111", "11111"},
                {"11111", "-----", "-----", "-----", "11111"},
                {"11~11", "-----", "-----", "-----", "11111"},
                {"11111", "-----", "-----", "-----", "11111"},
                {"11111", "11111", "11111", "11111", "11111"},
        });

        private final String[][] structure;
        private final int horizontalOffset;
        private final int verticalOffset;

        GeometricInstances(String[][] structure) {
            this(structure, 1, 1);

        }

        GeometricInstances(String[][] structure, int horizontalOffset, int verticalOffset) {
            this.structure = structure;
            this.horizontalOffset = horizontalOffset;
            this.verticalOffset = verticalOffset;
        }

        private int getAmount(int toCompareAgainst) {
            return Arrays.stream(structure)
                    .flatMap(Arrays::stream)
                    .flatMapToInt(CharSequence::chars)
                    .filter(c -> c == toCompareAgainst).sum();
        }

        public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getDefinition(int textureIndex, Block setCasing, int setMeta) {
            return TT_Utils.getDefaultStructureDefinition(structure, textureIndex, setCasing, setMeta);
        }

        public String getDimensions() {
            return structure.length + "x" + structure[0].length + "x" + structure.length;
        }
    }

    public enum MultiBlockDefinition {
        FREEZER(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sVacuumRecipes,
                false,
                new Pair<>("Freezer!", "Cools down ingots!"),
                1);

        final DefaultStructureDefinitions structure;
        final GT_Recipe.GT_Recipe_Map recipe_map;
        final boolean isPerfectOC;
        final String[] tooltip;
        final int maxParalellsPerTier;

        MultiBlockDefinition(DefaultStructureDefinitions structure, GT_Recipe.GT_Recipe_Map recipe_map, boolean isPerfectOC, Pair<String, String> tooltip, int maxParalellsPerTier) {
            this.structure = structure;
            this.recipe_map = recipe_map;
            this.isPerfectOC = isPerfectOC;
            this.tooltip = getDescription(tooltip);
            this.maxParalellsPerTier = maxParalellsPerTier;
        }

        private String[] getDescription(Pair<String, String> tooltip) {
            List<String> stringList = structure.generateTooltip();
            stringList.set(0, tooltip.getKey());
            stringList.set(1, tooltip.getValue());
            return stringList.toArray(new String[0]);
        }

        public DefaultStructureDefinitions getStructure() {
            return structure;
        }

        public GT_Recipe.GT_Recipe_Map getRecipe_map() {
            return recipe_map;
        }

        public boolean isPerfectOC() {
            return isPerfectOC;
        }

        public String[] getTooltip() {
            return tooltip;
        }

        public int getMaxParalellsPerTier() {
            return maxParalellsPerTier;
        }
    }

}