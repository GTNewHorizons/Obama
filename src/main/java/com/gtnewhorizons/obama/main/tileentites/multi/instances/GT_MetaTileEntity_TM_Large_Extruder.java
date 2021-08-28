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

public class GT_MetaTileEntity_TM_Large_Extruder extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Extruder> implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Extruder(int aID) {
        super(aID, "multimachine.tm.large_extruder", "Large Extruder");
    }

    public GT_MetaTileEntity_TM_Large_Extruder(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Extruder(mName);
    }


    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Extruder>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Extruder>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Extruder> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Extruder>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                    {" BBB ", "B   B", "B   ~", "B   B"},
                    {" AOA ", "AOOOA", "AO-OA", "AOOOA"},
                    {" --- ", " --- ", " --- ", "AAAAA"},
                    {" AAA ", "A---A", "A---A", "AAAAA"},
                    {" --- ", " --- ", " --- ", "AAAAA"},
                    {" AAA ", "A---A", "A---A", "AAAAA"},
                    {" --- ", " --- ", " --- ", "AAAAA"},
                    {" III ", "IAAAI", "IA-AI", "IAAAI"}
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                    {" hhh ", "h---h", "Gp-pG", "ccAcc"}
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                    {" BBB ", "BBBBB", "BBBBB", "BBBBB"}
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
                .addElement('p', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 4)))
                .addElement('h', lazy(t -> ofBlockAdder(GT_MetaTileEntity_TM_Factory::addCoilToMachineList, ItemList.Casing_Coil_Cupronickel.getBlock(), 0)))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 5)))
                .build();
        }
    };


    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Extruder> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Forming Press")
            .addInfo("Controller block for the Large Forming Press")
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
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Energy/Maintenance Hatch",
                "2 - Input Hatach",
                "3 - Output Hatch",
                "4 - Piston Casing",
                "5 - circuit Casing",
                "Coil - Coil of tier"
        };
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
