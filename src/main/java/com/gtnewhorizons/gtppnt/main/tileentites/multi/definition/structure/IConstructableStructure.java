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

/**
 * <p>The abstract constructable structure interface.
 */
public interface IConstructableStructure extends IStructure, IConstructable {
    Map<Class<? extends IConstructableStructure>, IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM>>
            structures = new HashMap<>();

    /**
     * <p>Add structure to static map.
     */
    default void addStructureToMap() {
        structures.put(this.getClass(), getMachineStructure());
    }

    /**
     * <p>Gets structure from static map.
     *
     * @return the registered structure
     */
    default IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getStructureFromMap() {
        return structures.get(this.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default void registerStructure_TM() {
        addStructureToMap();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getStructure_TM() {
        return getStructureFromMap();
    }

    /**
     * <p>Gets machine structure.
     *  
     * <p>Intended to be defined by the final machine class implementation.
     *  
     * <p>Suggested implementation is to create a structure definition and store it in a private final static, then
     * return it's reference on method call.
     *
     * @return the machine structure
     */
    IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure();

    /**
     * <p>Gets current structure offset.
     *  
     * <p>Should be coupled to a field in the abstract factory multiblock implementation.
     *
     * @return the structure offset
     */
    Vec3Impl getCurrentStructureOffset();

    /**
     * <p>Sets current structure offset.
     *  
     * <p>Should be coupled to a field in the abstract factory multiblock implementation.
     *
     * @param structureOffset the structure offset
     */
    void setCurrentStructureOffset(Vec3Impl structureOffset);

    /**
     * <p>Gets the structure counter.
     *  
     * <p>Should be coupled to a field in the abstract factory multiblock implementation.
     *
     * @return the count
     */
    int getStructureCounter();

    /**
     * <p>Sets the structure counter.
     *  
     * <p>Should be coupled to a field in the abstract factory multiblock implementation.
     *
     * @param structureCounter the count
     */
    void setStructureCounter(int structureCounter);

    /**
     * <p>Gets max paralells.
     *  
     * <p>Should be coupled to a field in the abstract factory multiblock implementation.
     *
     * @return the max paralells
     */
    int getMaxParalells();

    /**
     * <p>Set max paralells.
     *  
     * <p>Should be coupled to a field in the abstract factory multiblock implementation.
     *
     * @param maxParalells the max paralells
     */
    void setMaxParalells(int maxParalells);

    /**
     * <p>Gets casing block.
     *
     * <p>Intended to be defined in the abstract factory multiblock implementation.
     *
     * @return the casing block
     */
    Block getCasingBlock();

    /**
     * <p>Gets casing block meta.
     *
     * <p>Intended to be defined by the final machine class implementation.
     *
     * @return the casing block meta
     */
    short getCasingMeta();

    /**
     * <p>Check machine structure.
     *
     * @param iGregTechTileEntity the GregTech tile entity
     * @param itemStack           the internal item stack
     * @return true if check machine succeeds
     */
    boolean checkMachine_TM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack);

    /**
     * <p>Checks the machine structure.
     *  
     * <p>The expected implementation is delegation to structureCheck_TM(String, int, int, int) from the
     * GT_MetaTileEntity_MultiblockBase_EM located in TecTech.
     *
     * @param piece            the piece to check
     * @param horizontalOffset the horizontal offset
     * @param verticalOffset   the vertical offset
     * @param depthOffset      the depth offset
     * @return true if structure check succeeds
     */
    boolean structureCheck_TM(String piece, int horizontalOffset, int verticalOffset, int depthOffset);

    /**
     * <p>Checks the machine structure.
     *  
     * <p>A delegate to the other method of same name.
     *
     * @param piece  the piece to check
     * @param offset the offset vector
     * @return true if structure check succeeds
     */
    default boolean structureCheck_TM(String piece, Vec3Impl offset) {
        return structureCheck_TM(piece, offset.get0(), offset.get1(), offset.get2());
    }

    /**
     * <p>Builds the machine structure.
     *  
     * <p>The expected implementation is delegation to structureBuild_EM(String, int, int, int, boolean, ItemStack) from
     * the GT_MetaTileEntity_MultiblockBase_EM located in TecTech.
     *
     * @param piece            the piece to check
     * @param horizontalOffset the horizontal offset
     * @param verticalOffset   the vertical offset
     * @param depthOffset      the depth offset
     * @param hintsOnly        the hints only switch
     * @param trigger          the trigger item
     * @return the boolean
     */
    boolean structureBuild_TM(String piece, int horizontalOffset, int verticalOffset, int depthOffset,
                              boolean hintsOnly, ItemStack trigger);

    /**
     * <p>Builds the machine structure.
     *
     * <p>A delegate to the other method of same name.
     *
     * @param piece     the piece to check
     * @param offset    the offset vector
     * @param hintsOnly the hints only switch
     * @param trigger   the trigger item
     * @return the boolean
     */
    default boolean structureBuild_TM(String piece, Vec3Impl offset, boolean hintsOnly, ItemStack trigger) {
        return structureBuild_TM(piece, offset.get0(), offset.get1(), offset.get2(), hintsOnly, trigger);
    }
}
