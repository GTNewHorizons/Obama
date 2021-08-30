package com.gtnewhorizons.obama.main.tileentites.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.structure.IConstructableStructureSliceableCapped;
import com.gtnewhorizons.obama.main.utils.ObamaTooltips;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockAnyMeta;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.ZirconiumCarbide;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Mixer extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Mixer> implements
        IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Mixer(int aID) {
        super(aID, "multimachine.tm.large_mixer", "Large Mixer");
    }

    public GT_MetaTileEntity_TM_Large_Mixer(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Mixer(mName);
    }


    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Mixer>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Mixer>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Mixer> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Mixer>builder()
                .addShape(TM_STRUCTURE_START, transpose(new String[][] {
                    {" BBB "},
                    {"B   B"},
                    {"B ~ B"},
                    {"B   B"},
                    {" BBB "},
                    {"E   E"},
                    {"E   E"},
                }))
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] {
                    {" cmc "},
                    {"cAAAc"},
                    {"mAAAm"},
                    {"cAAAc"},
                    {" cmc "},
                }))
                .addShape(TM_STRUCTURE_CAP, transpose(new String[][] {
                    {"         ", "         ", "   AAA   ", "   AAA   ", "   AAA   ", "   AAA   ", "   AAA   "},
                    {"         ", "         ", " AAAAAAA ", " AAAAAAA ", " AAAAAAA ", " AAAAAAA ", " AAAAAAA "},
                    {"   AAA   ", "  A   A  ", " AA   AA ", " AAAAAAA ", " AA   AA ", " AAGGGAA ", " AA   AA "},
                    {"  A   A  ", "         ", "AA     AA", "IAAAAAAAI", "IA  r  AI", "AAGGGGGAA", "AA     AA"},
                    {"  A A A  ", "    A    ", "AA  A  AA", "IAAAAAAAI", "IA rrr AI", "AAGGGGGAA", "AA     AA"},
                    {"  A   A  ", "         ", "AA     AA", "IAAAAAAAI", "IA  r  AI", "AAGGGGGAA", "AA     AA"},
                    {"   AAA   ", "  A   A  ", " AA   AA ", " AAAAAAA ", " AA   AA ", " AAGGGAA ", " AA   AA "},
                    {"         ", "         ", "AAAAAAAAA", " AAAAAAA ", " AAAAAAA ", " AAAAAAA ", "AAAAAAAAA"},
                    {"         ", "         ", "A  AAA  A", "   OOO   ", "   OOO   ", "   AAA   ", "A  AAA  A"},
                }))
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('E', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 2),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('I', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addInputToMachineList, t.getTextureIndex(), 3),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('O', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addOutputToMachineList, t.getTextureIndex(), 4),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('m', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMotorCasingToMachineList, t.getTextureIndex(), 5)))
                .addElement('r', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addRotorCasingToMachineList, t.getTextureIndex(), 6)))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 7)))
                .build();
        }
    };

    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Mixer> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Mixer")
            .addInfo("Controller block for the Large Mixer")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(9, 9, 9, 9, 8 + getMinSlices(), 8 + getMaxSlices(),  true)
            .addController("Front Center")
            .addCasingInfo("Mixer Casing", 10) // TODO (Count, and name)
            .addMaintenanceHatch("Front Top", 1)
            .addEnergyHatch("Front Bottom", 2)
            .addInputHatch("Left/Right Middle", 3)
            .addInputBus("Left/Right Middle", 3)
            .addOutputHatch("Bottom Middle", 4)
            .addOutputBus("Bottom Middle", 4)
            .addOtherStructurePart(ObamaTooltips.TT_motorCasing, "Back Wall", 5)
            .addOtherStructurePart(ObamaTooltips.TT_rotorCasing, "Back Wall", 6)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Back Wall", 7)
            .addStructureInfo("Reinforced Glass")
            .toolTipFinisher("Obama");
        return tt;
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(2, 2, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(2, 2, -1);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 0, -1);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(2, 2, 0);
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
        return 64;
    }

    @Override
    public int getMinParallel() {
        return 0;
    }

    @Override
    public short getCasingMeta() {
        return ZirconiumCarbide.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_MIXER";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sMixerRecipes;
    }
}
