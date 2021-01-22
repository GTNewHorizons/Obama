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

package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.gtnewhorizons.gtppnt.main.utils.IAddsBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;

//TODO: Test this SHIT
public class StructureDefaultProvider {
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
     * for structures you should make a new String[][]
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
     *                     <p>(and other defined in expander)</p>
     *                     <p></p>
     * @param textureIndex the texture for the hatches
     * @param setCasing    Casing Block which is used to build the Multiblock
     * @param setMeta      Casing Block Meta which is used to build the Multiblock
     * @return IStructureDefinition for your Multiblock
     */
    @SuppressWarnings("deprecation")
    public static <T extends GT_MetaTileEntity_MultiblockBase_EM & IAddsBlocks> StructureDefinition.Builder<T> getDefaultStructureDefinitionBuilder(int textureIndex, Block setCasing, int setMeta) {
        return StructureDefinition.<T>builder()
                .addElement(BLOCK, ofBlock(setCasing, setMeta))
                .addElement(OPTIONAL_BLOCK, ofBlock(setCasing, setMeta, Blocks.air, 0))
                .addElement(SPECIAL_BLOCK, ofBlockAdder(IAddsBlocks::addBlockToMachine, 8))
                .addElement(OPTIONAL_SPECIAL_BLOCK, ofBlockAdder(IAddsBlocks::addBlockToMachine, Blocks.air, 0))
                .addElement(REQUIRED_ANY_HATCH, ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicToMachineList,
                        textureIndex,
                        1))
                .addElement(INPUT, ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicInputToMachineList,
                        textureIndex,
                        2))
                .addElement(OUTPUT, ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicOutputToMachineList,
                        textureIndex,
                        3))
                .addElement(ENERGY, ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addEnergyInputToMachineList,
                        textureIndex,
                        4))
                .addElement(DYNAMO, ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addDynamoToMachineList,
                        textureIndex,
                        5))
                .addElement(MUFFLER, ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicMufflerToMachineList,
                        textureIndex,
                        6))
                .addElement(MAINTENANCE, ofHatchAdder(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicMaintenanceToMachineList,
                        textureIndex,
                        7))
                .addElement(OPTIONAL_ANY_HATCH, ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicToMachineList,
                        textureIndex,
                        1,
                        setCasing,
                        setMeta))
                .addElement(OPTIONAL_INPUT, ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicInputToMachineList,
                        textureIndex,
                        2,
                        setCasing,
                        setMeta))
                .addElement(OPTIONAL_OUTPUT, ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicOutputToMachineList,
                        textureIndex,
                        3,
                        setCasing,
                        setMeta))
                .addElement(OPTIONAL_ENERGY, ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addEnergyInputToMachineList,
                        textureIndex,
                        4,
                        setCasing,
                        setMeta))
                .addElement(OPTIONAL_DYNAMO, ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addDynamoToMachineList,
                        textureIndex,
                        5,
                        setCasing,
                        setMeta))
                .addElement(OPTIONAL_MUFFLER, ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicMufflerToMachineList,
                        textureIndex,
                        6,
                        setCasing,
                        setMeta))
                .addElement(OPTIONAL_MAINTENANCE, ofHatchAdderOptional(
                        GT_MetaTileEntity_MultiblockBase_EM::addClassicMaintenanceToMachineList,
                        textureIndex,
                        7,
                        setCasing,
                        setMeta));
    }

}