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

public class GT_MetaTileEntity_TM_Large_Assembler extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Assembler> implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Assembler(int aID) {
        super(aID, "multimachine.tm.large_assembling_machine", "Large Assembling Machine");
    }

    public GT_MetaTileEntity_TM_Large_Assembler(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Assembler(mName);
    }

    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Assembler>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Assembler>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Assembler> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Assembler>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                    {"    ", "GGGA", "GGGA", "GGGA", "cc~B"},
                    {"GGGA", "---A", "---A", "---A", "ccBB"},
                    {"GGGA", "-a-A", "-a-A", "---A", "vvBB"},
                    {"AAAA", "a--A", "a--A", "---A", "vvBB"},
                    {"AAAA", "AAAA", "AAAA", "IIAA", "OOOO"}
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                    {"    ", "GGGA", "GGGA", "GGGA", "cccB"},
                    {"GGGA", "----", "----", "----", "cccB"},
                    {"GGGA", "-a--", "-a--", "----", "vvvB"},
                    {"AAAA", "a-a-", "a-a-", "----", "vvvB"},
                    {"AAAA", "AAAA", "AAAA", "IIII", "OOOO"}
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                    {" ", "A", "A", "A", "A"},
                    {"A", "A", "A", "A", "A"},
                    {"A", "A", "A", "A", "A"},
                    {"A", "A", "A", "A", "A"},
                    {"A", "A", "A", "A", "A"}
                })
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('I', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addInputToMachineList, t.getTextureIndex(), 2),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('O', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addOutputToMachineList, t.getTextureIndex(), 3),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('a', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addArmCasingToMachineList, t.getTextureIndex(), 4)))
                .addElement('v', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addConveyorCasingToMachineList, t.getTextureIndex(), 5)))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 6)))
                .build();
        }
    };
    
    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Assembler> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Assembler")
            .addInfo("Controller block for the Large Assembler")
            .toolTipFinisher("Obama");
        return tt;
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(2, 4, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(6, 4, 0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(4, 0, 0);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(-3, 0, 0);
    }

    @Override
    public int getMaxSlices() {
        return 2;
    }

    @Override
    public int getMinSlices() {
        return 0;
    }

    @Override
    public int getParalellsPerSlice() {
        return 48;
    }

    @Override
    public int getMinParallel() {
        return 32;
    }

    @Override
    public short getCasingMeta() {
        return MaragingSteel250.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Maintenance/Energy Hatch",
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
        return "TM_LARGE_ASSEMBLING_MACHINE";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sAssemblerRecipes;
    }

}
