package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition;

import com.github.technus.tectech.mechanics.constructable.IConstructable;
import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.util.Vec3Impl;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public interface IStructureProviderImpl extends IStructureProvider, IConstructable {
    String TM_SLICE_START = "START";

    Map<Class<? extends IStructureProviderImpl>, IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM>> structures =
            new HashMap<>();

    default void addStructureToMap() {
        structures.put(this.getClass(), getMachineStructure());
    }

    default IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getStructureFromMap() {
        return structures.get(this.getClass());
    }

    @Override
    default void registerStructure_TM() {
        addStructureToMap();
    }

    @Override
    default IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getStructure_TM() {
        return getStructureFromMap();
    }

    IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure();

    Block getCasingBlock();

    short getCasingMeta();

    Vec3Impl getStartStructureOffset();

    default boolean checkMachine_TM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        return structureCheck_TM(TM_SLICE_START, getStartStructureOffset().get0(), getStartStructureOffset().get1(),
                getStartStructureOffset().get2());
    }

    @Override
    default void construct(ItemStack itemStack, boolean hintsOnly) {
        structureBuild_TM(TM_SLICE_START,
                getStartStructureOffset().get0(),
                getStartStructureOffset().get1(),
                getStartStructureOffset().get2(),
                hintsOnly, itemStack);
    }

    boolean structureCheck_TM(String piece, int horizontalOffset, int verticalOffset, int depthOffset);

    boolean structureBuild_TM(String piece, int horizontalOffset, int verticalOffset, int depthOffset, boolean hintsOnly, ItemStack trigger);
}
