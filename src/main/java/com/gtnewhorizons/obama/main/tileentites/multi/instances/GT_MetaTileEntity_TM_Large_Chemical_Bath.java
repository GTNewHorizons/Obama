package com.gtnewhorizons.obama.main.tileentites.multi.instances;


import com.gtnewhorizons.obama.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.structure.IConstructableStructureSliceableCapped;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import com.gtnewhorizon.structurelib.util.Vec3Impl;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;

import net.minecraft.item.ItemStack;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.MaragingSteel250;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Chemical_Bath extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Chemical_Bath> implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Chemical_Bath(int aID) {
        super(aID, "multimachine.tm.large_chemical_bath", "Large Chemical Bath");
    }

    public GT_MetaTileEntity_TM_Large_Chemical_Bath(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Chemical_Bath(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"Large Chemical Bath desc"};
    }

    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Chemical_Bath>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Chemical_Bath>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Chemical_Bath> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Chemical_Bath>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                        {"-GG-", "B~BB", "BBBB"}
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                        {"GGGG", "f--A", "cvvc"}
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                        {"-GG-", "BBBB", "BBBB"}
                })
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('B', lazy(t -> ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addToMachineList, t.getTextureIndex(), 1),
                        ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('v', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addConveyorCasingToMachineList, t.getTextureIndex(), 2)))
                .addElement('f', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addPumpCasingToMachineList, t.getTextureIndex(), 3)))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 4)))
                .build();
        } 
    };
    
    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Chemical_Bath> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        return null;
    }


    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(1, 1, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(1, 1, -1);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 0, -1);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(0, 0, 0);
    }

    @Override
    public int getMaxSlices() {
        return 8;
    }

    @Override
    public int getMinSlices() {
        return 2;
    }

    @Override
    public int getParalellsPerSlice() {
        return 16;
    }

    @Override
    public int getMinParallel() {
        return 0;
    }

    @Override
    public short getCasingMeta() {
        return MaragingSteel250.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Classic Hatches",
                "2 - Conveyor Casing",
                "3 - Pump Casing",
                "4 - Circuit Casing"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_CHEMICAL_BATH";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sChemicalBathRecipes;
    }
}
