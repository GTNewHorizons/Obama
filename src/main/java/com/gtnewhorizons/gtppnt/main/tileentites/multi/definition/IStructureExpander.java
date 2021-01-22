package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import com.github.technus.tectech.mechanics.structure.StructureDefinition;

@FunctionalInterface
public interface IStructureExpander<T> {
    /**
     * Used to Expand definition, applying more shapes is not really that well supported...
     *
     * @param toExpand definition builder to expand on
     * @return expanded structure
     */
    StructureDefinition.Builder<T> apply(StructureDefinition.Builder<T> toExpand);
}
