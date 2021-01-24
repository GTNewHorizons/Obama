package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureUtility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum GeometricInstance {
    CUBE_3x3x3(1, 1, 0, new String[][]{
            {"CCC", "CCC", "CCC"},
            {"C~C", "C-C", "CCC"},
            {"CCC", "CCC", "CCC"}}),
    CUBE_3x3x3_WithMuffler(1, 1, 0, new String[][]{
            {"CCC", "CpC", "CCC"},
            {"C~C", "C-C", "CCC"},
            {"CCC", "CCC", "CCC"}}),
    CUBE_3x3x3_WithFunctional(1, 1, 0, new String[][]{
            {"CCC", "CfC", "CCC"},
            {"C~C", "C-C", "CCC"},
            {"CCC", "CCC", "CCC"}}),
    CUBE_5x5x5_WithMuffler(2, 2, 0, new String[][]{
            {"CCCCC", "CCCCC", "CCPCC", "CCCCC", "CCCCC"},
            {"CCCCC", "C---C", "C---C", "C---C", "CCCCC"},
            {"CC~CC", "C---C", "C---C", "C---C", "CCCCC"},
            {"CCCCC", "C---C", "C---C", "C---C", "CCCCC"},
            {"CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC"},}),
    ;

    private final String[][] structure;
    private final int horizontalOffset;
    private final int verticalOffset;
    private final int depthOffset;
    private final Map<Integer, Integer> amounts = new HashMap<>();

    GeometricInstance(int horizontalOffset, int verticalOffset, int depthOffset, String[][] structure) {
        this.structure = StructureUtility.transpose(structure);
        this.horizontalOffset = horizontalOffset;
        this.verticalOffset = verticalOffset;
        this.depthOffset = depthOffset;
    }

    public <T> StructureDefinition.Builder<T> addToDefinition(StructureDefinition.Builder<T> definition){
        return definition.addShape("main",structure);
    }

    public int getHorizontalOffset() {
        return horizontalOffset;
    }

    public int getVerticalOffset() {
        return verticalOffset;
    }

    public int getDepthOffset() {
        return depthOffset;
    }

    public int getAmount(int charToCount) {
        return amounts.computeIfAbsent(charToCount, k -> (int) Arrays.stream(structure)
                .flatMap(Arrays::stream)
                .flatMapToInt(CharSequence::chars)
                .filter(c -> c == k)
                .count());
    }

    public String getDimensions() {
        return structure.length + "x" + structure[0].length + "x" + structure[0][0].length();
    }
}
