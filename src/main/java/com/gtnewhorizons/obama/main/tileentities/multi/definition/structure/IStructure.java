package com.gtnewhorizons.obama.main.tileentities.multi.definition.structure;

import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_EnhancedMultiBlockBase;


/**
 * <p>The abstract structure interface.
 */
public interface IStructure {
    /**
     * <p>Register machine structure.
     */
    void registerStructure();

    /**
     * <p>Gets registered machine structure
     *
     * @return the machine structure
     */
    IStructureDefinition<? extends GT_MetaTileEntity_EnhancedMultiBlockBase<?>> getStructureDefinition();
}
