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
import com.github.technus.tectech.mechanics.structure.StructureDefinition;
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

import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;

//TODO: Test this SHIT
public class TT_Utils {
    public static final char DEFAULT_INPUT_OUTPUT_ENERGY = 'c';
    public static final char INPUT = 'i';
    public static final char OUTPUT = 'o';
    public static final char ENERGY = 'e';
    public static final char DYNAMO = 'd';
    public static final char MUFFLER = 'p';
    public static final char MAINTENANCE = 'm';
    public static final char BLOCK = 'b';
    public static final char SPECIAL_BLOCK = 's';

    /**
     * Creates a Multiblock Definition
     * <p></p>
     * <p>Hints are: </p>
     * <p> 1 Any hatch?/Bus</p>
     * <p> 2 Input Hatch/Bus</p>
     * <p> 3 Output Hatch/Bus</p>
     * <p> 4 Energy Hatch</p>
     * <p> 5 Dynamo Hatch</p>
     * <p> 6 Muffler Hatch</p>
     * <p> 7 Maintenance Hatch</p>
     * <p> 8 Special Block</p>
     *
     * @param structure    make a new String[][]
     *
     *                     <p>each internal String[] defines a layer</p>
     *                     <p>each string the blocks of the layer</p>
     *                     <p>each char is a block definition</p>
     *                     <p></p>
     *                     <p>Allowed Characters, Uppercase means OPTIONAL:</p>
     *                     <p>c = any Input/Output Hatch/Bus or Energy Input Hatch</p>
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
     * @param textureIndex the texture for the hatches
     * @param setCasing    Casing Block which is used to build the Multiblock
     * @param setMeta      Casing Block Meta which is used to build the Multiblock
     * @return IStructureDefinition for your Multiblock
     */
    public static <T extends GT_MetaTileEntity_MultiblockBase_EM & IAddsBlocks> IStructureDefinition<T> getDefaultStructureDefinition(
            String[][] structure,
            int textureIndex,
            Block setCasing,
            int setMeta) {
        return StructureDefinition.<T>builder()
                .addShape("main", structure)
                .addElement('b', ofBlock(setCasing, setMeta))
                .addElement('B', ofBlock(setCasing, setMeta, Blocks.air, 0))
                .addElement('s', ofBlockAdder(IAddsBlocks::addBlockToMachine, 8))
                .addElement('S', ofBlockAdder(IAddsBlocks::addBlockToMachine, Blocks.air, 0))
                .addElement('c', ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicToMachineList,
                        textureIndex,
                        1))
                .addElement('i', ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicInputToMachineList,
                        textureIndex,
                        2))
                .addElement('o', ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicOutputToMachineList,
                        textureIndex,
                        3))
                .addElement('e', ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addEnergyInputToMachineList,
                        textureIndex,
                        4))
                .addElement('d', ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addDynamoToMachineList,
                        textureIndex,
                        5))
                .addElement('p', ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicMufflerToMachineList,
                        textureIndex,
                        6))
                .addElement('m', ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicMaintenanceToMachineList,
                        textureIndex,
                        7))
                .addElement('C', ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicToMachineList,
                        textureIndex,
                        1,
                        setCasing,
                        setMeta))
                .addElement('I', ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicInputToMachineList,
                        textureIndex,
                        2,
                        setCasing,
                        setMeta))
                .addElement('O', ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicOutputToMachineList,
                        textureIndex,
                        3,
                        setCasing,
                        setMeta))
                .addElement('E', ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addEnergyInputToMachineList,
                        textureIndex,
                        4,
                        setCasing,
                        setMeta))
                .addElement('D', ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addDynamoToMachineList,
                        textureIndex,
                        5,
                        setCasing,
                        setMeta))
                .addElement('P', ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicMufflerToMachineList,
                        textureIndex,
                        6,
                        setCasing,
                        setMeta))
                .addElement('M', ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicMaintenanceToMachineList,
                        textureIndex,
                        7,
                        setCasing,
                        setMeta))
                .build();
    }

    public enum DefaultStructureDefinitions {

        FREEZER_ALIKE(GeometricInstances.CUBE_3x3x3, GregTech_API.sBlockCasings2, 1, null, 0, 17);

        private final GeometricInstances geometrics;
        private final Block toBuildWith;
        private final int metaToBuildWith;
        private final Block specialBlock;
        private final int metaSpecialBlock;
        private final IStructureDefinition<?> iStructureDefinition;

        DefaultStructureDefinitions(GeometricInstances geometrics, Block toBuildWith, int metaToBuildWith, Block specialBlock, int metaSpecialBlock, int textureIndex) {
            this.geometrics = geometrics;
            this.toBuildWith = toBuildWith;
            this.metaToBuildWith = metaToBuildWith;
            this.iStructureDefinition = this.geometrics.getDefinition(textureIndex, this.toBuildWith, this.metaToBuildWith);
            this.specialBlock = specialBlock;
            this.metaSpecialBlock = metaSpecialBlock;
        }

        @SuppressWarnings("unchecked")
        public <T extends GT_MetaTileEntity_MultiblockBase_EM & IAddsBlocks> IStructureDefinition<T> getStructureDefinition() {
            return (IStructureDefinition<T>) iStructureDefinition;
        }

        public int getRequiredMufflers() {
            return geometrics.getAmount(MUFFLER);
        }

        public int getRequiredEnergyHatches() {
            return geometrics.getAmount(ENERGY);
        }

        public int getRequiredDynamos() {
            return geometrics.getAmount(DYNAMO);
        }

        public int getRequiredMaintenanceHatches() {
            return geometrics.getAmount(MAINTENANCE);
        }

        public int getRequiredInputs() {
            return geometrics.getAmount(INPUT);
        }

        public int getRequiredOutputs() {
            return geometrics.getAmount(OUTPUT);
        }

        public int getRequiredBlocks() {
            return geometrics.getAmount(BLOCK);
        }

        public int getRequiredSpecialBlocks() {
            return geometrics.getAmount(SPECIAL_BLOCK);
        }

        public int getHorizontalOffset() {
            return geometrics.horizontalOffset;
        }

        public int getVerticalOffset() {
            return geometrics.verticalOffset;
        }

        public int getDepthOffset() {
            return geometrics.depthOffset;
        }

        public int getDefaultHatches() {
            return geometrics.getAmount(DEFAULT_INPUT_OUTPUT_ENERGY);
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
                {"CCC", "CCC", "CCC"},
                {"C~C", "C-C", "CCC"},
                {"CCC", "CCC", "CCC"}
        },1,1,0),

        CUBE_3x3x3_WithMuffler(new String[][]{
                {"CCC", "CPC", "CCC"},
                {"C~C", "C-C", "CCC"},
                {"CCC", "CCC", "CCC"}
        },1,1,0),

        CUBE_5x5x5_WithMuffler(new String[][]{
                {"CCCCC", "CCCCC", "CCPCC", "CCCCC", "CCCCC"},
                {"CCCCC", "-----", "-----", "-----", "CCCCC"},
                {"CC~CC", "-----", "-----", "-----", "CCCCC"},
                {"CCCCC", "-----", "-----", "-----", "CCCCC"},
                {"CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC"},
        },2,2,0);

        private final String[][] structure;
        private final int horizontalOffset;
        private final int verticalOffset;
        private final int depthOffset;

        GeometricInstances(String[][] structure, int horizontalOffset, int verticalOffset, int depthOffset) {
            this.structure = structure;
            this.horizontalOffset = horizontalOffset;
            this.verticalOffset = verticalOffset;
            this.depthOffset = depthOffset;
        }

        private int getAmount(int toCompareAgainst) {
            return Arrays.stream(structure)
                    .flatMap(Arrays::stream)
                    .flatMapToInt(CharSequence::chars)
                    .filter(c -> c == toCompareAgainst).sum();
        }

        public <T extends GT_MetaTileEntity_MultiblockBase_EM & IAddsBlocks> IStructureDefinition<T> getDefinition(int textureIndex, Block setCasing, int setMeta) {
            return TT_Utils.getDefaultStructureDefinition(structure, textureIndex, setCasing, setMeta);
        }

        public String getDimensions() {
            return structure.length + "x" + structure[0].length + "x" + structure[0][0].length();
        }
    }

    public enum MultiBlockDefinition {
        FREEZER(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sVacuumRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!"));

        final DefaultStructureDefinitions structure;
        final GT_Recipe.GT_Recipe_Map recipe_map;
        final boolean isPerfectOC;
        final String[] tooltip;
        final int maxParalellsPerTier;

        MultiBlockDefinition(
                DefaultStructureDefinitions structure,
                GT_Recipe.GT_Recipe_Map recipe_map,
                boolean isPerfectOC,
                int maxParalellsPerTier,
                List<String> tooltipLines) {
            this.structure = structure;
            this.recipe_map = recipe_map;
            this.isPerfectOC = isPerfectOC;
            this.tooltip = getDescription(tooltipLines);
            this.maxParalellsPerTier = maxParalellsPerTier;
        }

        private String[] getDescription(List<String> tooltipLines) {
            List<String> stringList = structure.generateTooltip();
            stringList.addAll(0,tooltipLines);
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