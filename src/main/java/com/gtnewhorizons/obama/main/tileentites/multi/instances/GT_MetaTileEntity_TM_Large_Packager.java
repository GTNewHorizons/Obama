package com.gtnewhorizons.obama.main.tileentites.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.structure.IConstructableStructureSliceableCapped;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockAnyMeta;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.MaragingSteel250;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Packager extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Packager> implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Packager(int aID) {
        super(aID, "multimachine.tm.large_packager", "Large Packager");
    }

    public GT_MetaTileEntity_TM_Large_Packager(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Packager(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{""};
    }

    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Packager>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Packager>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Packager> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Packager>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                    {"~", "B"},
                    {"I", "B"},
                    {"A", "B"}
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                    {"c", "G", "B"},
                    {"A", "-", "v"},
                    {"c", "a", "B"}
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                    {"A", "B"},
                    {"O", "B"},
                    {"A", "B"}
                })
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('I', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addInputToMachineList, t.getTextureIndex(), 2)))
                .addElement('O', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addOutputToMachineList, t.getTextureIndex(), 3)))
                .addElement('a', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addArmCasingToMachineList, t.getTextureIndex(), 4)))
                .addElement('v', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addConveyorCasingToMachineList, t.getTextureIndex(), 5)))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 6)))
                .build();
        }
    };

    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Packager> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        return null;
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(0, 0, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(-1, 1, 0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(-1, 0, 0);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(0, -1, 0);
    }

    @Override
    public int getMaxSlices() {
        return 6;
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
                "1 - Energy/Maintenance Hatch",
                "2 - Input Hatch",
                "3 - Output Hatch",
                "4 - Robot Arm Casing",
                "5 - Conveyor Casing",
                "6 - Circuit Casing"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_PACKAGER";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sBoxinatorRecipes;
    }
}
