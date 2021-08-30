package com.gtnewhorizons.obama.main.tileentities.multi.definition;

import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizons.obama.main.utils.IAddsBlocks;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_EnhancedMultiBlockBase;
import gregtech.api.util.GT_Recipe;

import java.util.Arrays;
import java.util.List;

import static com.gtnewhorizons.obama.main.tileentities.multi.definition.IStructureExpander.IDENTITY;

//TODO locale for descriptions
public enum MultiBlockDefinition {
    //region Instances
    LARGE_CENTRIFUGE(DefaultStructureDefinition.LARGE_CENTRIFUGE,
            GT_Recipe.GT_Recipe_Map.sCentrifugeRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),
//            FUNCTIONAL_CASING_STRUCTURE_EXPANDER),

    LARGE_BENDING_MACHINE(DefaultStructureDefinition.LARGE_BENDING_MACHINE,
            GT_Recipe.GT_Recipe_Map.sBenderRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_FORMING_PRESS(DefaultStructureDefinition.LARGE_FORMING_PRESS,
            GT_Recipe.GT_Recipe_Map.sPressRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_ELECTROLYZER(DefaultStructureDefinition.LARGE_ELECTROLYZER,
            GT_Recipe.GT_Recipe_Map.sElectrolyzerRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_MACERATOR(DefaultStructureDefinition.LARGE_MACERATOR,
            GT_Recipe.GT_Recipe_Map.sMaceratorRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_WIREMILL(DefaultStructureDefinition.LARGE_WIREMILL,
            GT_Recipe.GT_Recipe_Map.sWiremillRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_MIXER(DefaultStructureDefinition.LARGE_MIXER,
            GT_Recipe.GT_Recipe_Map.sMixerRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_SIFTING_MACHINE(DefaultStructureDefinition.LARGE_SIFTING_MACHINE,
            GT_Recipe.GT_Recipe_Map.sSifterRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_THERMAL_CENTRIFUGE(DefaultStructureDefinition.LARGE_THERMAL_CENTRIFUGE,
            GT_Recipe.GT_Recipe_Map.sThermalCentrifugeRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_ORE_WASHING_PLANT(DefaultStructureDefinition.LARGE_ORE_WASHING_PLANT,
            GT_Recipe.GT_Recipe_Map.sOreWasherRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_CHEMICAL_BATH(DefaultStructureDefinition.LARGE_CHEMICAL_BATH,
            GT_Recipe.GT_Recipe_Map.sChemicalBathRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_EXTRUDER(DefaultStructureDefinition.LARGE_EXTRUDER,
            GT_Recipe.GT_Recipe_Map.sExtruderRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_ARC_FURNACE(DefaultStructureDefinition.LARGE_ARC_FURNACE,
            GT_Recipe.GT_Recipe_Map.sArcFurnaceRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_PLASMA_ARC_FURNACE(DefaultStructureDefinition.LARGE_PLASMA_ARC_FURNACE,
            GT_Recipe.GT_Recipe_Map.sPlasmaArcFurnaceRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_FLUID_HEATER(DefaultStructureDefinition.LARGE_FLUID_HEATER,
            GT_Recipe.GT_Recipe_Map.sFluidHeaterRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_ASSEMBLING_MACHINE(DefaultStructureDefinition.LARGE_ASSEMBLING_MACHINE,
            GT_Recipe.GT_Recipe_Map.sAssemblerRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_PACKAGER(DefaultStructureDefinition.LARGE_PACKAGER,
            GT_Recipe.GT_Recipe_Map.sBoxinatorRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_CUTTING_MACHINE(DefaultStructureDefinition.LARGE_CUTTING_MACHINE,
            GT_Recipe.GT_Recipe_Map.sCutterRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_SLICING_MACHINE(DefaultStructureDefinition.LARGE_SLICING_MACHINE,
            GT_Recipe.GT_Recipe_Map.sSlicerRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_COMPRESSOR(DefaultStructureDefinition.LARGE_COMPRESSOR,
            GT_Recipe.GT_Recipe_Map.sCompressorRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_LATHE(DefaultStructureDefinition.LARGE_LATHE,
            GT_Recipe.GT_Recipe_Map.sLatheRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_PRECISION_LASER_ENGRAVER(DefaultStructureDefinition.LARGE_PRECISION_LASER_ENGRAVER,
            GT_Recipe.GT_Recipe_Map.sLaserEngraverRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    LARGE_AUTOCLAVE(DefaultStructureDefinition.LARGE_AUTOCLAVE,
            GT_Recipe.GT_Recipe_Map.sAutoclaveRecipes,
            false,
            1,
            Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_CIRCUIT_ASSEMBLING_MACHINE(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sCircuitAssemblerRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_ALLOY_SMELTER(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sAlloySmelterRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_FERMENTER(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sFermentingRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_EXTRACTOR(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sExtractorRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_FLUID_EXTRACTOR(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sFluidExtractionRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_POLARIZER(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sPolarizerRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_FLUID_SOLIFIER(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sFluidSolidficationRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_RECYCLER(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sRecyclerRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_MICROWAVE(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sMicrowaveRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_PRINTER(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sPrinterRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_ELECTROMAGNETIC_SEPARATOR(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sElectroMagneticSeparatorRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_FLUID_CANNER(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sFluidCannerRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_BREWERY(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sBrewingRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_CANNING_MACHINE(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sCannerRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!")),

    //LARGE_FORGE_HAMMER(DefaultStructureDefinitions.DEFAULT_CUBE,
    //        GT_Recipe.GT_Recipe_Map.sHammerRecipes,
    //        false,
    //        1,
    //        Arrays.asList("Freezer!", "Cools down ingots!"))

    ;
    //endregion

    private final DefaultStructureDefinition structure;
    private final GT_Recipe.GT_Recipe_Map recipe_map;
    private final boolean isPerfectOC;
    private final String[] tooltip;
    private final int maxParalellsPerTier;
    private final IStructureDefinition<?> structureDefinition;

    <T extends GT_MetaTileEntity_EnhancedMultiBlockBase<T> & IAddsBlocks> MultiBlockDefinition(
            DefaultStructureDefinition structure,
            GT_Recipe.GT_Recipe_Map recipe_map,
            boolean isPerfectOC,
            int maxParalellsPerTier,
            List<String> tooltipLines,
            IStructureExpander<T> expander) {
        this.structure = structure;
        this.recipe_map = recipe_map;
        this.isPerfectOC = isPerfectOC;
        this.maxParalellsPerTier = maxParalellsPerTier;
        this.structureDefinition = structure.getStructureDefinition(expander);
        this.tooltip = getDescription(tooltipLines);
    }

    @SuppressWarnings("unchecked")
    MultiBlockDefinition(
            DefaultStructureDefinition structure,
            GT_Recipe.GT_Recipe_Map recipe_map,
            boolean isPerfectOC,
            int maxParalellsPerTier,
            List<String> tooltipLines) {
        this(structure, recipe_map, isPerfectOC, maxParalellsPerTier, tooltipLines, IDENTITY);
    }

    private String[] getDescription(List<String> tooltipLines) {
        List<String> stringList = structure.generateTooltip();
        stringList.addAll(0, tooltipLines);
        return stringList.toArray(new String[0]);
    }

    public DefaultStructureDefinition getStructure() {
        return structure;
    }

    @SuppressWarnings("unchecked")
    public <T extends GT_MetaTileEntity_EnhancedMultiBlockBase<T> & IAddsBlocks> IStructureDefinition<T> getStructureDefinition() {
        return (IStructureDefinition<T>) structureDefinition;
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
