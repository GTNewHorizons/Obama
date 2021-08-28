package com.gtnewhorizons.obama.main.tileentites.multi.definition;

import com.gtnewhorizon.structurelib.structure.StructureDefinition;

@FunctionalInterface
public interface IStructureExpander<T> {
    @SuppressWarnings("rawtypes")
    IStructureExpander IDENTITY = (d, b) -> b;

    /**
     * Used to Expand definition, applying more shapes is not really that well supported...
     *
     * @param definition    initial definition
     * @param toExpand      definition builder to expand on
     * @return              expanded structure
     */
    StructureDefinition.Builder<T> apply(DefaultStructureDefinition definition, StructureDefinition.Builder<T> toExpand);
}
