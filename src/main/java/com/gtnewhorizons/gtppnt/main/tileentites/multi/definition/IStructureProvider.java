package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;

public interface IStructureProvider {
    void registerStructure_TM();

    IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getStructure_TM();
}
