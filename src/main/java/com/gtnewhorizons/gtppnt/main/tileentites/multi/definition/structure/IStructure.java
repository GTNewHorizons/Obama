package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure;

import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;

public interface IStructure {
    void registerStructure_TM();

    IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getStructure_TM();
}
