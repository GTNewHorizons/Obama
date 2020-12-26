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

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.github.bartimaeusnek.bartworks.util.Pair;
import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureUtility;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import gregtech.api.GregTech_API;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Recipe;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;
import static com.gtnewhorizons.gtppnt.main.CommonValues.TM_MARK;
import static com.gtnewhorizons.gtppnt.main.loaders.CasingTextureLoader.BASIC_START_INDEX;

//TODO: Test this SHIT
public class TT_Utils {

    public static final char REQUIRED_ANY_HATCH = 'c';
    public static final char INPUT = 'i';
    public static final char OUTPUT = 'o';
    public static final char ENERGY = 'e';
    public static final char DYNAMO = 'd';
    public static final char MUFFLER = 'p';
    public static final char MAINTENANCE = 'm';
    public static final char BLOCK = 'b';
    public static final char SPECIAL_BLOCK = 's';

    public static final char OPTIONAL_ANY_HATCH = 'C';
    public static final char OPTIONAL_INPUT = 'I';
    public static final char OPTIONAL_OUTPUT = 'O';
    public static final char OPTIONAL_ENERGY = 'E';
    public static final char OPTIONAL_DYNAMO = 'D';
    public static final char OPTIONAL_MUFFLER = 'P';
    public static final char OPTIONAL_MAINTENANCE = 'M';
    public static final char OPTIONAL_BLOCK = 'B';
    public static final char OPTIONAL_SPECIAL_BLOCK = 'S';

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
    @SuppressWarnings("deprecation")
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
        FREEZER_ALIKE(GeometricInstances.CUBE_3x3x3, WerkstoffLoader.BWBlockCasings, 30005, null, 0, 17),

        LARGE_CENTRIFUGE(GeometricInstances.CUBE_3x3x3_WithMuffler, WerkstoffLoader.BWBlockCasings, 30011, null, 0, BASIC_START_INDEX),
        LARGE_PROCCESSING_FACTORY(GeometricInstances.CUBE_3x3x3, GregTech_API.sBlockCasings2, 1, null, 0, 17)

        ;

        private final GeometricInstances geometrics;
        private final Block toBuildWith;
        private final int metaToBuildWith;
        private final Block specialBlock;
        private final int metaSpecialBlock;
        private final int textureIndex;
        private final IStructureDefinition<?> iStructureDefinition;

        DefaultStructureDefinitions(GeometricInstances geometrics, Block toBuildWith, int metaToBuildWith, Block specialBlock, int metaSpecialBlock, int textureIndex) {
            this.geometrics = geometrics;
            this.toBuildWith = toBuildWith;
            this.metaToBuildWith = metaToBuildWith;
            this.specialBlock = specialBlock;
            this.metaSpecialBlock = metaSpecialBlock;
            this.textureIndex = textureIndex;
            this.iStructureDefinition = this.geometrics.getDefinition(this.textureIndex, this.toBuildWith, this.metaToBuildWith);
        }

        @SuppressWarnings("unchecked")
        public <T extends GT_MetaTileEntity_MultiblockBase_EM & IAddsBlocks> IStructureDefinition<T> getStructureDefinition() {
            return (IStructureDefinition<T>) iStructureDefinition;
        }

        public int getOptionalMufflers() {
            return geometrics.getAmount(OPTIONAL_MUFFLER);
        }

        public int getOptionalEnergyHatches() {
            return geometrics.getAmount(OPTIONAL_ENERGY);
        }

        public int getOptionalDynamos() {
            return geometrics.getAmount(OPTIONAL_DYNAMO);
        }

        public int getOptionalMaintenanceHatches() {
            return geometrics.getAmount(OPTIONAL_MAINTENANCE);
        }

        public int getOptionalInputs() {
            return geometrics.getAmount(OPTIONAL_INPUT);
        }

        public int getOptionalOutputs() {
            return geometrics.getAmount(OPTIONAL_OUTPUT);
        }

        public int getOptionalBlocks() {
            return geometrics.getAmount(OPTIONAL_BLOCK);
        }

        public int getOptionalSpecialBlocks() {
            return geometrics.getAmount(OPTIONAL_SPECIAL_BLOCK);
        }

        public int getOptionalDefaultHatches() {
            return geometrics.getAmount(OPTIONAL_ANY_HATCH);
        }

        public int getRequiredMufflers() {
            return geometrics.getAmount(MUFFLER);
        }

        public int getRequiredEnergyHatches() {
            int hatches = geometrics.getAmount(ENERGY);
            return hatches > 0 ? hatches : 1;
        }

        public int getRequiredDynamos() {
            return geometrics.getAmount(DYNAMO);
        }

        public int getRequiredMaintenanceHatches() {
            int hatches = geometrics.getAmount(MAINTENANCE);
            return hatches > 0 ? hatches : 1;
        }

        public int getRequiredInputs() {
            int hatches = geometrics.getAmount(INPUT);
            return hatches > 0 ? hatches : 1;
        }

        public int getRequiredOutputs() {
            int hatches = geometrics.getAmount(OUTPUT);
            return hatches > 0 ? hatches : 1;
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

        public int getRequiredDefaultHatches() {
            return geometrics.getAmount(REQUIRED_ANY_HATCH);
        }

        public List<String> generateTooltip() {
            int required_in = getRequiredInputs(),
                    required_out = getRequiredOutputs(),
                    required_maint = getRequiredMaintenanceHatches(),
                    required_energy = getRequiredEnergyHatches(),
                    required_dynamo = getRequiredDynamos(),
                    required_muffler = getRequiredMufflers(),
                    required_special = getRequiredSpecialBlocks(),
                    required_blocks = getRequiredBlocks(),
                    required_defaults = getRequiredDefaultHatches();
            boolean has_requireds =
                    required_in > 0 ||
                            required_out > 0 ||
                            required_maint > 0 ||
                            required_energy > 0 ||
                            required_dynamo > 0 ||
                            required_muffler > 0 ||
                            required_special > 0 ||
                            required_blocks > 0 ||
                            required_defaults > 0;
            int optional_in = getOptionalInputs(),
                    optional_out = getOptionalOutputs(),
                    optional_maint = getOptionalMaintenanceHatches(),
                    optional_energy = getOptionalEnergyHatches(),
                    optional_dynamo = getOptionalDynamos(),
                    optional_muffler = getOptionalMufflers(),
                    optional_special = getOptionalSpecialBlocks(),
                    optional_blocks = getOptionalBlocks(),
                    optional_defaults = getOptionalDefaultHatches();
            boolean has_optional =
                    optional_in > 0 ||
                            optional_out > 0 ||
                            optional_maint > 0 ||
                            optional_energy > 0 ||
                            optional_dynamo > 0 ||
                            optional_muffler > 0 ||
                            optional_special > 0 ||
                            optional_blocks > 0 ||
                            optional_defaults > 0;
            String special = specialBlock != null ? GT_LanguageManager.getTranslation(GT_LanguageManager.getTranslateableItemStackName(new ItemStack(specialBlock, 1, metaSpecialBlock))) : null,
                    block = toBuildWith != null ? GT_LanguageManager.getTranslation(GT_LanguageManager.getTranslateableItemStackName(new ItemStack(toBuildWith, 1, metaToBuildWith))) : null;

            return Stream.of(
                    "Size(WxHxD): " + geometrics.getDimensions() + " (Hollow), Controller (Front centered)",
                    has_requireds ? "Required:" : null,
                    required_defaults + required_in > 0 ? required_in + required_defaults + "x Inputs" : null,
                    required_defaults + required_out > 0 ? required_out + required_defaults + "x Outputs" : null,
                    required_maint > 0 ? required_maint + "x Maintenance Hatches" : null,
                    required_defaults + required_energy > 0 ? required_energy + required_defaults + "x Energy Hatches" : null,
                    required_dynamo > 0 ? required_dynamo + "x Dynamo Hatches" : null,
                    required_muffler > 0 ? required_muffler + "x Muffler Hatches" : null,
                    required_special > 0 ? required_special + "x " + special : null,
                    required_blocks > 0 ? required_blocks + "x " + block : null,
                    has_optional ? "Optional, up to:" : null,
                    optional_defaults + optional_in > 0 ? optional_in + optional_defaults + "+ Inputs" : null,
                    optional_defaults + optional_out > 0 ? optional_out + optional_defaults + "+ Outputs" : null,
                    optional_maint > 0 ? optional_maint + "+ Maintenance Hatches" : null,
                    optional_defaults + optional_energy > 0 ? optional_energy + optional_defaults + "+ Energy Hatches" : null,
                    optional_dynamo > 0 ? optional_dynamo + "+ Dynamo Hatches" : null,
                    optional_muffler > 0 ? optional_muffler + "+ Muffler Hatches" : null,
                    optional_special > 0 ? optional_special + "+ " + special : null,
                    optional_blocks > 0 ? optional_blocks + "+ " + block : null,
                    "For more information use " + EnumChatFormatting.AQUA + "TecTech's Blueprint!",
                    TM_MARK
            ).filter(Objects::nonNull).collect(Collectors.toList());
        }

        public Pair<Block, Integer> getSpecialBlock() {
            return new Pair<>(specialBlock, metaSpecialBlock);
        }

        public int getTextureIndex() {
            return textureIndex;
        }
    }

    private enum GeometricInstances {

        CUBE_3x3x3(new String[][]{
                {"CCC", "CCC", "CCC"},
                {"C~C", "C-C", "CCC"},
                {"CCC", "CCC", "CCC"}
        },1,1,0),

        CUBE_3x3x3_WithMuffler(new String[][]{
                {"CCC", "CpC", "CCC"},
                {"C~C", "C-C", "CCC"},
                {"CCC", "CCC", "CCC"}
        },1,1,0),

        CUBE_5x5x5_WithMuffler(new String[][]{
                {"CCCCC", "CCCCC", "CCPCC", "CCCCC", "CCCCC"},
                {"CCCCC", "C---C", "C---C", "C---C", "CCCCC"},
                {"CC~CC", "C---C", "C---C", "C---C", "CCCCC"},
                {"CCCCC", "C---C", "C---C", "C---C", "CCCCC"},
                {"CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC"},
        },2,2,0);

        private final String[][] structure;
        private final int horizontalOffset;
        private final int verticalOffset;
        private final int depthOffset;

        GeometricInstances(String[][] structure, int horizontalOffset, int verticalOffset, int depthOffset) {
            this.structure = StructureUtility.transpose(structure);
            this.horizontalOffset = horizontalOffset;
            this.verticalOffset = verticalOffset;
            this.depthOffset = depthOffset;
        }

        private int getAmount(int toCompareAgainst) {
            return (int) Arrays.stream(structure)
                    .flatMap(Arrays::stream)
                    .flatMapToInt(CharSequence::chars)
                    .filter(c -> c == toCompareAgainst).count();
        }

        public <T extends GT_MetaTileEntity_MultiblockBase_EM & IAddsBlocks> IStructureDefinition<T> getDefinition(int textureIndex, Block setCasing, int setMeta) {
            return TT_Utils.getDefaultStructureDefinition(structure, textureIndex, setCasing, setMeta);
        }

        public String getDimensions() {
            return structure.length + "x" + structure[0].length + "x" + structure[0][0].length();
        }
    }

    //TODO locale for descriptions
    public enum MultiBlockDefinition {
        LARGE_CENTRIFUGE(DefaultStructureDefinitions.LARGE_CENTRIFUGE,
                GT_Recipe.GT_Recipe_Map.sCentrifugeRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_BENDING_MACHINE(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sBenderRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_ELECTROLYZER(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sElectrolyzerRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_MACERATOR(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sMaceratorRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_WIREMILL(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sWiremillRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_MIXER(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sMixerRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_SIFTING_MACHINE(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sSifterRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_THERMAL_CENTRIFUGE(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sThermalCentrifugeRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_ORE_WASHING_PLANT(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sOreWasherRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_EXTRUDER(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sExtruderRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_ARC_FURNACE(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sArcFurnaceRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_FLUID_HEATER(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sFluidHeaterRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_ASSEMBLING_MACHINE(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sAssemblerRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_CIRCUIT_ASSEMBLING_MACHINE(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sCircuitAssemblerRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_PACKAGER(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sBoxinatorRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_CUTTING_MACHINE(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sCutterRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_ALLOY_SMELTER(DefaultStructureDefinitions.FREEZER_ALIKE,
                GT_Recipe.GT_Recipe_Map.sAlloySmelterRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_COMPRESSOR(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sCompressorRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_LATHE(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sLatheRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_PRECISION_LASER_ENGRAVER(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sLaserEngraverRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_AUTOCLAVE(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sAutoclaveRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_FERMENTER(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sFermentingRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_EXTRACTOR(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sExtractorRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_FLUID_EXTRACTOR(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sFluidExtractionRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_POLARIZER(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sPolarizerRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_FLUID_SOLIFIER(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sFluidSolidficationRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_FORMING_PRESS(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sPressRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_SLICING_MACHINE(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sSlicerRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_RECYCLER(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sRecyclerRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_MICROWAVE(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sMicrowaveRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_PLASMA_ARC_FURNACE(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sPlasmaArcFurnaceRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_PRINTER(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sPrinterRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_ELECTROMAGNETIC_SEPARATOR(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sElectroMagneticSeparatorRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_CHEMICAL_BATH(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sChemicalBathRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_FLUID_CANNER(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sFluidCannerRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_BREWERY(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sBrewingRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_CANNING_MACHINE(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sCannerRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        LARGE_FORGE_HAMMER(DefaultStructureDefinitions.LARGE_PROCCESSING_FACTORY,
                GT_Recipe.GT_Recipe_Map.sHammerRecipes,
                false,
                1,
                Arrays.asList("Freezer!", "Cools down ingots!")),

        ;

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
            this.maxParalellsPerTier = maxParalellsPerTier;
            this.tooltip = getDescription(tooltipLines);
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