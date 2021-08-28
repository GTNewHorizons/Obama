package com.gtnewhorizons.obama.main.tileentites.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.structure.IConstructableStructureCells;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.EglinSteel;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Sifter extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Sifter> implements IConstructableStructureCells {
    public GT_MetaTileEntity_TM_Large_Sifter(int aID) {
        super(aID, "multimachine.tm.large_sifting_machine", "Large Sifting Machine");
    }

    public GT_MetaTileEntity_TM_Large_Sifter(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Sifter(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"large sifter desc"};
    }

    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Sifter>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Sifter>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Sifter> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Sifter>builder()
                .addShape(TM_STRUCTURE_A, new String[][]{
                    {"AppA", "AAAA", "A  A", "A  A", "A  A", "A  A", "~AAA", "A  A"},
                    {"cssc", "A  A", "    ", "    ", "    ", "    ", "A  A", "    "},
                    {"cssc", "A  A", "    ", "    ", "    ", "    ", "A  A", "    "},
                    {"AppA", "AAAA", "A  A", "A  A", "A  A", "A  A", "AAAA", "A  A"},
                })
                .addShape(TM_STRUCTURE_B, new String[][]{
                    {" pp ", "AAAA"},
                    {"cssc", "A  A", "    ", "    ", "A  A", "    "},
                    {"cssc", "A  A", "    ", "    ", "A  A", "    "},
                    {"AppA", "AAAA", "A  A", "A  A", "AAAA", "A  A"},
                })
                .addShape(TM_STRUCTURE_C, new String[][]{
                    {" pp "},
                    {"cssc", "    ", "A  A", "    "},
                    {"cssc", "    ", "A  A", "    "},
                    {"AppA", "A  A", "A  A", "A  A"},
                })
                .addShape(TM_STRUCTURE_D, new String[][]{
                    {"    ", " pp "},
                    {"    ", "cssc"},
                    {"    ", "cssc"},
                    {"AAAA", "AppA", "A  A"}

                })
                .addElement('A', lazy(t -> ofChain(
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()),
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addToMachineList, t.getTextureIndex(), 0))))
                .addElement('s', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addFilterCasingToMachineList, t.getTextureIndex(), 1)))
                .addElement('p', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addPistonCasingToMachineList, t.getTextureIndex(), 2)))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 3)))
                .build();
        }
    };

    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Sifter> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        return null;
    }

    @Override
    public Vec3Impl getStartOffsetA() {
        return new Vec3Impl(0, 6, 0);
    }

    @Override
    public Vec3Impl getStartOffsetB() {
        return new Vec3Impl(0, 4, -3);
    }

    @Override
    public Vec3Impl getStartOffsetC() {
        return new Vec3Impl(0, 2, -6);
    }

    @Override
    public Vec3Impl getStartOffsetD() {
        return new Vec3Impl(0, 1, -9);
    }

    @Override
    public int getParalellsA() {
        return 32;
    }

    @Override
    public int getParalellsB() {
        return 64;
    }

    @Override
    public int getParalellsC() {
        return 92;
    }

    @Override
    public int getParalellsD() {
        return 128;
    }

    @Override
    public short getCasingMeta() {
        return EglinSteel.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "Casing - Classic Hatches",
                "1 - Filter Casing",
                "2 - Piston Casing",
                "3 - Circuit Casing"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_SIFTING_MACHINE";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sSifterRecipes;
    }
}
