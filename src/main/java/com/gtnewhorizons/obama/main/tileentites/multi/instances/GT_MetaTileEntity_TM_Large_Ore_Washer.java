package com.gtnewhorizons.obama.main.tileentites.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.structure.IConstructableStructureCells;
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

public class GT_MetaTileEntity_TM_Large_Ore_Washer extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Ore_Washer> implements
        IConstructableStructureCells {
    public GT_MetaTileEntity_TM_Large_Ore_Washer(int aID) {
        super(aID, "multimachine.tm.large_ore_washing_plant", "Large Ore Washing Plant");
    }

    public GT_MetaTileEntity_TM_Large_Ore_Washer(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Ore_Washer(mName);
    }


    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Ore_Washer>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Ore_Washer>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Ore_Washer> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Ore_Washer>builder()
                .addShape(TM_STRUCTURE_A, new String[][]{
                    {"   Acc ", "   AGG ", "AGG~cc ", "BBBAAA "},
                    {"   A--c", "   f--G", "G--f--c", "BBBAmmA"},
                    {"   A--c", "   f--G", "G--f--c", "BBBAmmA"},
                    {"   AFF ", "   AAI ", "AGGAAI ", "BBBAAA "}
                })
                .addShape(TM_STRUCTURE_B, new String[][]{
                    {"AAA ", "Aff ", "Aff ", "    ", "    ", "    "},
                    {"F--c", "A--G", "A--c", "AmmA", "    ", "    "},
                    {"F--c", "I--G", "I--c", "AmmA", "    ", "    "},
                    {" cc ", " GG ", " cc ", " AA ", "A  A", "A  A"}
                })
                .addShape(TM_STRUCTURE_C, new String[][]{
                    {" FFA", " IA ", " IA ", " AA ", "A   ", "A   ", "A   ", "A   "},
                    {"c--A", "G--f", "c--f", "Amm ", "    ", "    ", "    ", "    "},
                    {"c--A", "G--f", "c--f", "Amm ", "    ", "    ", "    ", "    "},
                    {" cc ", " GG ", " cc ", " AA ", "A   ", "A   ", "A   ", "A   "}

                })
                .addShape(TM_STRUCTURE_D, new String[][]{
                    {" cc ", " GG ", " cc ", " AA ", "A  A", "A  A", "A   ", "A   "},
                    {"c--A", "G--I", "c--I", "AmmA"},
                    {"c--A", "G--A", "c--A", "AmmA"},
                    {" AA ", " ff ", " ff ", "    "}
                })
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addOutputToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('I', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addInputToMachineList, t.getTextureIndex(), 2),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('m', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMotorCasingToMachineList, t.getTextureIndex(), 3)))
                .addElement('f', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addPumpCasingToMachineList, t.getTextureIndex(), 4)))
                .addElement('F', lazy(t -> ofChain(
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()),
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addPumpCasingToMachineList, t.getTextureIndex(), 10))))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 5)))
                .build();
        }
    };

    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Ore_Washer> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Ore Washer")
            .addInfo("Controller block for the Large Ore Washer")
            .toolTipFinisher("Obama");
        return tt;
    }

    @Override
    public Vec3Impl getStartOffsetA() {
        return new Vec3Impl(3, 2, 0);
    }

    @Override
    public Vec3Impl getStartOffsetB() {
        return new Vec3Impl(0, 4, -3);
    }

    @Override
    public Vec3Impl getStartOffsetC() {
        return new Vec3Impl(3, 6, -3);
    }

    @Override
    public Vec3Impl getStartOffsetD() {
        return new Vec3Impl(3, 8, 0);
    }

    @Override
    public int getParalellsA() {
        return 64;
    }

    @Override
    public int getParalellsB() {
        return 128;
    }

    @Override
    public int getParalellsC() {
        return 192;
    }

    @Override
    public int getParalellsD() {
        return 256;
    }

    @Override
    public short getCasingMeta() {
        return MaragingSteel250.getmID();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Output/Maintenance/Energy Hatch",
                "2 - Input Hatch",
                "3 - Motor Casing",
                "4 - Pump Casing",
                "5 - Circuit Casing"
        };
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getMachineTextureName() {
        return "TM_LARGE_ORE_WASHING_PLANT";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sOreWasherRecipes;
    }
}
