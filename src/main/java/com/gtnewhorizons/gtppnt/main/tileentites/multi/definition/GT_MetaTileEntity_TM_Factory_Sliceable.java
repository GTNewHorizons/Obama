package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure.IStructureProviderSliceable;

public abstract class GT_MetaTileEntity_TM_Factory_Sliceable extends GT_MetaTileEntity_TM_Factory_Base implements IStructureProviderSliceable {
    private int sliceCount;

    public GT_MetaTileEntity_TM_Factory_Sliceable(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public  GT_MetaTileEntity_TM_Factory_Sliceable(String aName) {
        super(aName);
    }

    @Override
    public int getSliceCount() {
        return sliceCount;
    }

    @Override
    public void setSliceCount(int sliceCount) {
        this.sliceCount = sliceCount;
    }

    @Override
    public int getMinSlices() {
        return 1;
    }
}
