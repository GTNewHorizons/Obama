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
import gregtech.api.enums.ItemList;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockAdder;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockAnyMeta;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.MaragingSteel250;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class Obama_MetaTileEntity_Large_Extruder extends Obama_MetaTileEntity_Factory<Obama_MetaTileEntity_Large_Extruder> implements IConstructableStructureSliceableCapped {
    public Obama_MetaTileEntity_Large_Extruder(int aID) {
        super(aID, "multimachine.obama.large_extruder", "Large Extruder");
    }

    public Obama_MetaTileEntity_Large_Extruder(String aName) {
        super(aName);
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        preLoadCheck();
        return IConstructableStructureSliceableCapped.super.checkMachine(aBaseMetaTileEntity, aStack) && additionalCheck();
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new Obama_MetaTileEntity_Large_Extruder(mName);
    }


    private static final ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Extruder>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Extruder>>() {
        @Override
        protected IStructureDefinition<Obama_MetaTileEntity_Large_Extruder> computeValue(Class<?> type) {
            return StructureDefinition.<Obama_MetaTileEntity_Large_Extruder>builder()
                .addShape(TM_STRUCTURE_START, transpose(new String[][] { // Front
                    {" BBB ", " AOA ", " --- ", " AAA ", " --- ", " AAA ", " --- ", " III "},
                    {"B   B", "AOOOA", " --- ", "A---A", " --- ", "A---A", " --- ", "IAAAI"},
                    {"B   ~", "AO-OA", " --- ", "A---A", " --- ", "A---A", " --- ", "IA-AI"},
                    {"B   B", "AOOOA", "AAAAA", "AAAAA", "AAAAA", "AAAAA", "AAAAA", "IAAAI"},
                }))
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] { // Middle
                    {" hhh "},
                    {"h---h"},
                    {"Gp-pG"},
                    {"ccAcc"},
                }))
                .addShape(TM_STRUCTURE_CAP, transpose(new String[][] { // Back
                    {" BBB "},
                    {"BBBBB"},
                    {"BBBBB"},
                    {"BBBBB"},
                }))
                .addElement('A', lazy(t -> addTileCasing(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 1),
                    addTileCasing(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('I', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addInputToMachineList, t.getTextureIndex(), 2),
                    addTileCasing(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('O', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addOutputToMachineList, t.getTextureIndex(), 3),
                    addTileCasing(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('p', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 4)))
                .addElement('c', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 5)))
                .addElement('h', lazy(t -> ofBlockAdder(Obama_MetaTileEntity_Factory::addCoilToMachineList, ItemList.Casing_Coil_Cupronickel.getBlock(), 0)))
                .build();
        }
    };


    @Override
    public IStructureDefinition<Obama_MetaTileEntity_Large_Extruder> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Extruder")
            .addInfo("Controller block for the Large Extruder")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(5, 5, 4, 4, 9 + getMinSlices(), 9 + getMaxSlices(),  true)
            .addController("Right Side")
            .addCasingInfo("Large Extruder Casing", 10) // TODO (Count, and name)
            .addMaintenanceHatch("Front or Back casings", 1)
            .addEnergyHatch("Front or Back casings", 1)
            .addInputHatch("Middle Ring", 2)
            .addInputBus("Middle Ring", 2)
            .addOutputHatch("Front Face", 3)
            .addOutputBus("Front Face", 3)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Back Wall", 4, 5)
            .addOtherStructurePart("Cupronickel Coil Block", "Slice Top")
            .addStructureInfo("Reinforced Glass")
            .toolTipFinisher("Obama");
        return tt;
    }

    
    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(4, 2, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(4, 2, -8);
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
        return 6;
    }

    @Override
    public int getMinSlices() {
        return 1;
    }

    @Override
    public int getParalellsPerSlice() {
        return 32;
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
        return "TM_LARGE_EXTRUDER";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sExtruderRecipes;
    }
}
