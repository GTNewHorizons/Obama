package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure;

import com.github.technus.tectech.mechanics.constructable.IConstructable;
import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.util.Vec3Impl;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public interface IStructureProviderBase extends IStructureProvider, IConstructable {
    Map<Class<? extends IStructureProviderBase>, IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM>>
            structures = new HashMap<>();

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

    int getMaxParalells();

    Block getCasingBlock();

    short getCasingMeta();

    boolean checkMachine_TM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack);

    boolean structureCheck_TM(String piece, int horizontalOffset, int verticalOffset, int depthOffset);

    default boolean structureCheck_TM(String piece, Vec3Impl offset) {
        return structureCheck_TM(piece, offset.get0(), offset.get1(), offset.get2());
    }

    boolean structureBuild_TM(String piece, int horizontalOffset, int verticalOffset, int depthOffset,
                              boolean hintsOnly, ItemStack trigger);

    default boolean structureBuild_TM(String piece, Vec3Impl offset, boolean hintsOnly, ItemStack trigger) {
        return structureBuild_TM(piece, offset.get0(), offset.get1(), offset.get2(), hintsOnly, trigger);
    }
}
