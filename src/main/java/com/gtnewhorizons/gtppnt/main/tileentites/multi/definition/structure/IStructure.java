package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure;

import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;

/**
 * <p>The abstract structure interface.
 */
public interface IStructure {
    /**
     * <p>Register machine structure.
     */
    void registerStructure_TM();

    /**
     * <p>Gets registered machine structure
     *
     * @return the machine structure
     */
    IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getStructure_TM();
}
