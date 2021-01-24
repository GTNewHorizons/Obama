package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import com.github.bartimaeusnek.bartworks.util.Pair;
import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.gtnewhorizons.gtppnt.main.utils.IAddsBlocks;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.bartimaeusnek.bartworks.system.material.BW_GT_MaterialReference.*;
import static com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader.*;
import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;
import static com.github.technus.tectech.mechanics.structure.StructureUtility.ofHatchAdderOptional;
import static com.gtnewhorizons.gtppnt.main.CommonValues.TM_MARK;
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.*;
import static com.gtnewhorizons.gtppnt.main.loaders.CasingTextureLoader.getAdvancedCasingTextureIndex;
import static com.gtnewhorizons.gtppnt.main.loaders.CasingTextureLoader.getBasicCasingTextureIndex;
import static com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.GeometricInstance.*;

public enum DefaultStructureDefinition {
    LARGE_CENTRIFUGE(CUBE_3x3x3_WithMuffler, BWBlockCasings, MaragingSteel250.getmID(), getBasicCasingTextureIndex(MaragingSteel250.getmID())),
    LARGE_BENDING_MACHINE(CUBE_3x3x3_WithMuffler, BWBlockCasings, Titanium.getmID(), getBasicCasingTextureIndex(Titanium.getmID())),
    LARGE_FORMING_PRESS(CUBE_3x3x3_WithMuffler, BWBlockCasings, Titanium.getmID(), getBasicCasingTextureIndex(Titanium.getmID())),
    LARGE_ELECTROLYZER(CUBE_3x3x3_WithMuffler, BWBlockCasings, Elwoodite.getmID(), getBasicCasingTextureIndex(Elwoodite.getmID())),
    LARGE_MACERATOR(CUBE_3x3x3_WithMuffler, BWBlockCasings, TungstenCarbide.getmID(), getBasicCasingTextureIndex(TungstenCarbide.getmID())),
    LARGE_WIREMILL(CUBE_3x3x3_WithMuffler, BWBlockCasings, BlueSteel.getmID(), getBasicCasingTextureIndex(BlueSteel.getmID())),
    LARGE_MIXER(CUBE_3x3x3_WithMuffler, BWBlockCasings, ZirconiumCarbide.getmID(), getBasicCasingTextureIndex(ZirconiumCarbide.getmID())),
    LARGE_SIFTING_MACHINE(CUBE_3x3x3_WithMuffler, BWBlockCasings, EglinSteel.getmID(), getBasicCasingTextureIndex(EglinSteel.getmID())),
    LARGE_THERMAL_CENTRIFUGE(CUBE_3x3x3_WithMuffler, BWBlockCasings, RedSteel.getmID(), getBasicCasingTextureIndex(RedSteel.getmID())),
    LARGE_ORE_WASHING_PLANT(CUBE_3x3x3_WithMuffler, BWBlockCasings, Complainium.getmID(), getBasicCasingTextureIndex(Complainium.getmID())),
    LARGE_CHEMICAL_BATH(CUBE_3x3x3_WithMuffler, BWBlockCasingsAdvanced, Complainium.getmID(), getAdvancedCasingTextureIndex(Complainium.getmID())),
    LARGE_EXTRUDER(CUBE_3x3x3_WithMuffler, BWBlockCasings, Hereford690.getmID(), getBasicCasingTextureIndex(Hereford690.getmID())),
    LARGE_ARC_FURNACE(CUBE_3x3x3_WithMuffler, BWBlockCasings, Rezron100.getmID(), getBasicCasingTextureIndex(Rezron100.getmID())),
    LARGE_PLASMA_ARC_FURNACE(CUBE_3x3x3_WithMuffler, BWBlockCasingsAdvanced, Rezron100.getmID(), getAdvancedCasingTextureIndex(Rezron100.getmID())),
    LARGE_FLUID_HEATER(CUBE_3x3x3_WithMuffler, BWBlockCasings, TungstenSteel.getmID(), getBasicCasingTextureIndex(TungstenSteel.getmID())),
    LARGE_ASSEMBLING_MACHINE(CUBE_3x3x3_WithMuffler, BWBlockCasings, NickmolX.getmID(), getBasicCasingTextureIndex(NickmolX.getmID())),
    LARGE_PACKAGER(CUBE_3x3x3_WithMuffler, BWBlockCasings, TungstenCarbide.getmID(), getBasicCasingTextureIndex(TungstenCarbide.getmID())),
    LARGE_CUTTING_MACHINE(CUBE_3x3x3_WithMuffler, BWBlockCasings, MaragingSteel300.getmID(), getBasicCasingTextureIndex(MaragingSteel300.getmID())),
    LARGE_SLICING_MACHINE(CUBE_3x3x3_WithMuffler, BWBlockCasings, MaragingSteel350.getmID(), getBasicCasingTextureIndex(MaragingSteel350.getmID())),
    LARGE_PRECISION_LASER_ENGRAVER(CUBE_3x3x3_WithMuffler, BWBlockCasingsAdvanced, Staballoy.getmID(), getAdvancedCasingTextureIndex(Staballoy.getmID())),
    LARGE_COMPRESSOR(CUBE_3x3x3_WithMuffler, BWBlockCasingsAdvanced, Staballoy.getmID(), getAdvancedCasingTextureIndex(Staballoy.getmID())),
    LARGE_LATHE(CUBE_3x3x3_WithMuffler, BWBlockCasingsAdvanced, Staballoy.getmID(), getAdvancedCasingTextureIndex(Staballoy.getmID())),
    LARGE_AUTOCLAVE(CUBE_3x3x3_WithMuffler, BWBlockCasingsAdvanced, Staballoy.getmID(), getAdvancedCasingTextureIndex(Staballoy.getmID())),
    ;

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

    private final GeometricInstance geometrics;
    private final Block toBuildWith;
    private final int metaToBuildWith;
    private final Block specialBlock;
    private final int metaSpecialBlock;
    private final int textureIndex;
    private final IStructureDefinition<?> iStructureDefinition;
    private final Map<IStructureExpander<?>,IStructureDefinition<?>> map=new HashMap<>();

    DefaultStructureDefinition(GeometricInstance geometrics,
                               Block toBuildWith, int metaToBuildWith,
                               Block specialBlock, int metaSpecialBlock, int textureIndex) {
        this.geometrics = geometrics;
        this.toBuildWith = toBuildWith;
        this.metaToBuildWith = metaToBuildWith;
        this.specialBlock = specialBlock;
        this.metaSpecialBlock = metaSpecialBlock;
        this.textureIndex = textureIndex;
        this.iStructureDefinition = geometrics.addToDefinition(
                        getDefaultStructureDefinitionBuilder(textureIndex, specialBlock, metaSpecialBlock)
        ).build();
    }

    DefaultStructureDefinition(GeometricInstance geometrics, Block toBuildWith, int metaToBuildWith, int textureIndex) {
        this(geometrics, toBuildWith, metaToBuildWith, toBuildWith, metaToBuildWith, textureIndex);
    }

    @SuppressWarnings("unchecked")
    <T extends GT_MetaTileEntity_MultiblockBase_EM & IAddsBlocks> IStructureDefinition<T> getStructureDefinition(IStructureExpander<T> expander) {
        return (IStructureDefinition<T>)(expander==null? iStructureDefinition:
                map.computeIfAbsent(expander,e->
                        geometrics.addToDefinition(
                                expander.apply(
                                        getDefaultStructureDefinitionBuilder(
                                                textureIndex,
                                                specialBlock,
                                                metaSpecialBlock
                                        )
                                )
                        ).build()));
    }

    <T extends GT_MetaTileEntity_MultiblockBase_EM & IAddsBlocks> IStructureDefinition<T> getStructureDefinition() {
        return getStructureDefinition(null);
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
        return geometrics.getHorizontalOffset();
    }

    public int getVerticalOffset() {
        return geometrics.getVerticalOffset();
    }

    public int getDepthOffset() {
        return geometrics.getDepthOffset();
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
