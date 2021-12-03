package com.gtnewhorizons.obama.main.tileentities.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.Obama_MetaTileEntity_Factory;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.structure.IConstructableStructureSliceable;
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

public class Obama_MetaTileEntity_Large_Laser_Engraver extends Obama_MetaTileEntity_Factory<Obama_MetaTileEntity_Large_Laser_Engraver> implements
        IConstructableStructureSliceable {

    public Obama_MetaTileEntity_Large_Laser_Engraver(int aID) {
        super(aID, "multimachine.obama.large_precision_laser_engraver", "Large Precision Laser Engraver");
    }

    public Obama_MetaTileEntity_Large_Laser_Engraver(String aName) {
        super(aName);
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        preLoadCheck();
        return IConstructableStructureSliceable.super.checkMachine(aBaseMetaTileEntity, aStack) && additionalCheck();
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new Obama_MetaTileEntity_Large_Laser_Engraver(mName);
    }


    private static final ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Laser_Engraver>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Laser_Engraver>>() {
        @Override
        protected IStructureDefinition<Obama_MetaTileEntity_Large_Laser_Engraver> computeValue(Class<?> type) {
            return StructureDefinition.<Obama_MetaTileEntity_Large_Laser_Engraver>builder()
                .addShape(TM_STRUCTURE_START, transpose(new String[][] {  // Left
                    {"B", "A", "B"},
                    {"A", "G", "A"},
                    {"~", "A", "A"},
                    {"B", "A", "B"},
                }))
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] { // Right
                    {"c ", "c ", "c "},
                    {"pB", "eA", "pB"},
                    {"GA", "-G", "GA"},
                    {"IA", "-A", "OA"},
                    {"BB", "AA", "BB"},
                }))
                .addElement('A', lazy(t -> addTileCasing(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 1),
                    addTileCasing(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('I', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addInputToMachineList, t.getTextureIndex(), 2),
                    addTileCasing(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('O', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addOutputToMachineList, t.getTextureIndex(), 3),
                    addTileCasing(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('p', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addPistonCasingToMachineList, t.getTextureIndex(), 4)))
                .addElement('e', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addEmitterCasingToMachineList, t.getTextureIndex(), 5)))
                .addElement('c', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 6)))
                .build();
        }
    };

    @Override
    public IStructureDefinition<Obama_MetaTileEntity_Large_Laser_Engraver> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Laser Engraver")
            .addInfo("Controller block for the Large Laser Engraver")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(3, 3 + (2 * getMaxSlices()), 4, 4, 3, 3,  false)
            .addController("Left Bottom")
            .addCasingInfo("Laser Engraver Casing", 10) // TODO (Count, and name)
            .addMaintenanceHatch("Bottom Layer", 1)
            .addEnergyHatch("Bottom Layer", 1)
            .addInputBus("Second Layer from Bottom", 2)
            .addInputHatch("Second Layer from Bottom", 2)
            .addOutputBus("Second Layer from Bottom", 3)
            .addOutputHatch("Second Layer from Bottom", 3)
            .addOtherStructurePart(ObamaTooltips.TT_pistonCasing, "Second Layer from Top", 4)
            .addOtherStructurePart(ObamaTooltips.TT_emitterCasing, "Second Layer from Top - middle", 5)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Top", 6)
            .addStructureInfo("Reinforced Glass")
            .toolTipFinisher("Obama");
        return tt;
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(0, 2, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(-1, 3, 0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(-2, 0, 0);
    }

    @Override
    public int getMaxSlices() {
        return 4;
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
        return "TM_LARGE_PRECISION_LASER_ENGRAVER";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sLaserEngraverRecipes;
    }
}
