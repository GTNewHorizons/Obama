package com.gtnewhorizons.obama.main.tileentites.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.structure.IConstructableStructureSliceableCapped;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.ItemList;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockAdder;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockAnyMeta;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.MaragingSteel250;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Fluid_Heater extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Fluid_Heater> implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Fluid_Heater(int aID) {
        super(aID, "multimachine.tm.large_fluid_heater", "Large Fluid Heater");
    }

    public GT_MetaTileEntity_TM_Large_Fluid_Heater(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Fluid_Heater(mName);
    }


    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Fluid_Heater>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Fluid_Heater>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Fluid_Heater> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Fluid_Heater>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                    {"BBB", "B~B"},
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                    {"hGh", "BcB"}
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                    {"BBB", "BBB"}
                })
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('B', lazy(t -> ofChain(
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()),
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addToMachineList, t.getTextureIndex(), 1))))
                .addElement('h', lazy(t -> ofBlockAdder(GT_MetaTileEntity_TM_Factory::addCoilToMachineList, ItemList.Casing_Coil_Cupronickel.getBlock(), 2)))
                //for some reason using GT_Block_Casings5 doesent work so im using ItemList.Casing_Coil_Cupronickel.getBlock()
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 3)))
                .build();
        }
    };

    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Fluid_Heater> getStructureDefinition() {
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
        return 1;
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
                "1 - Classic Hatch",
                "2 - Heating Coils",
                "3 - Circuit Casing"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_FLUID_HEATER";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sFluidHeaterRecipes;
    }
}
