package com.gtnewhorizons.obama.main.tileentities.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.Obama_MetaTileEntity_Factory;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.structure.IConstructableStructureSliceableCapped;
import com.gtnewhorizons.obama.main.utils.ObamaTooltips;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockAnyMeta;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.MaragingSteel250;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class Obama_MetaTileEntity_Large_Packager extends Obama_MetaTileEntity_Factory<Obama_MetaTileEntity_Large_Packager> implements IConstructableStructureSliceableCapped {
    public Obama_MetaTileEntity_Large_Packager(int aID) {
        super(aID, "multimachine.obama.large_packager", "Large Packager");
    }

    public Obama_MetaTileEntity_Large_Packager(String aName) {
        super(aName);
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        preLoadCheck();
        return IConstructableStructureSliceableCapped.super.checkMachine(aBaseMetaTileEntity, aStack) && additionalCheck();
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new Obama_MetaTileEntity_Large_Packager(mName);
    }


    private static final ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Packager>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Packager>>() {
        @Override
        protected IStructureDefinition<Obama_MetaTileEntity_Large_Packager> computeValue(Class<?> type) {
            return StructureDefinition.<Obama_MetaTileEntity_Large_Packager>builder()
                .addShape(TM_STRUCTURE_START, transpose(new String[][] {  // Left
                    {"~", "I", "A"},
                    {"B", "B", "B"},
                }))
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] {  // Middle
                    {"c", "A", "c"},
                    {"G", "-", "a"},
                    {"B", "v", "B"},
                }))
                .addShape(TM_STRUCTURE_CAP, transpose(new String[][] {  // Right
                    {"A", "O", "A"},
                    {"B", "B", "B"},
                }))
                .addElement('A', lazy(t -> addTileCasing(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 1),
                    addTileCasing(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('I', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addInputToMachineList, t.getTextureIndex(), 2)))
                .addElement('O', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addOutputToMachineList, t.getTextureIndex(), 3)))
                .addElement('a', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addRobotArmCasingToMachineList, t.getTextureIndex(), 4)))
                .addElement('v', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addConveyorCasingToMachineList, t.getTextureIndex(), 5)))
                .addElement('c', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 6)))
                .build();
        }
    };

    @Override
    public IStructureDefinition<Obama_MetaTileEntity_Large_Packager> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Packager")
            .addInfo("Controller block for the Large Packager")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(2 + getMinSlices(), 2 + getMaxSlices(), 3, 3, 3, 3, true)
            .addController("Left Side")
            .addMaintenanceHatch("Bottom Layer", 1)
            .addEnergyHatch("Bottom Layer", 1)
            .addInputHatch("Left Middle", 2)
            .addInputBus("Left Middle", 2)
            .addOutputHatch("Right Middle", 3)
            .addOutputBus("Right Middle", 3)
            .addOtherStructurePart(ObamaTooltips.TT_robotArmCasing, "", 4)
            .addOtherStructurePart(ObamaTooltips.TT_conveyorCasing, "", 5)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "", 6)
            .addStructureInfo("Reinforced Glass")
            .toolTipFinisher("Obama");
        return tt;
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
