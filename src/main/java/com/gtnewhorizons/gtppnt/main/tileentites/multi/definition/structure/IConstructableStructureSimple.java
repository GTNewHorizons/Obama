package com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure;

import com.github.technus.tectech.util.Vec3Impl;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.item.ItemStack;

/**
 * <p>The simple constructable structure interface implementation used to construct and check machines.
 *
 * <p>It is meant to be used for multiblocks with just one state.
 */
public interface IConstructableStructureSimple extends IConstructableStructure {
    String TM_STRUCTURE_START = "START";

    /**
     * <p>Gets start structure offset.
     *
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the start structure offset
     */
    Vec3Impl getStartStructureOffset();

    /**
     * {@inheritDoc}
     */
    @Override
    default boolean checkMachine_TM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        return structureCheck_TM(TM_STRUCTURE_START, getStartStructureOffset());
    }

    /**
     * {@inheritDoc}
     *
     * <p>Construct the machine.
     *
     * @param itemStack the player held item stack
     * @param hintsOnly the hints only mode switch
     */
    @Override
    default void construct(ItemStack itemStack, boolean hintsOnly) {
        structureBuild_TM(TM_STRUCTURE_START, getStartStructureOffset(), hintsOnly, itemStack);
    }
}
