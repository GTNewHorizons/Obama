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
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.Elwoodite;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Electrolyzer extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Electrolyzer> implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Electrolyzer(int aID) {
        super(aID, "multimachine.tm.large_electrolyzer", "Large Electrolyzer");
    }

    public GT_MetaTileEntity_TM_Large_Electrolyzer(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Electrolyzer(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"large electrolyzer desc"};
    }

    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Electrolyzer>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Electrolyzer>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Electrolyzer> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Electrolyzer>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                    {"BBB~BBB"},
                    {"BBBBBBB"},
                    {"BBBBBBB"},
                    {"BBBBBBB"},
                    {"BBBBBBB"},
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{

                    {"GGGGGGG"},
                    {"c-w-w-c"},
                    {"A-----A"},
                    {"c-w-w-c"},
                    {"GGGGGGG"},
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                    {"BBBBBBB"},
                    {"BBBBBBB"},
                    {"BBBBBBB"},
                    {"BBBBBBB"},
                    {"BBBBBBB"}
                })
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('w', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addWireCasingToMachineList, t.getTextureIndex(), 2)))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 3)))
                .build();
        }
    };

    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Electrolyzer> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        return null;
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(3, 0, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(3, 1, 0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 1, 0);
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
        return Elwoodite.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Classic Hatches",
                "2 - Wire Casing",
                "3 - Circuit Casing"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_ELECTROLYZER";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sElectrolyzerRecipes;
    }
}
