package com.gtnewhorizons.obama.main.tileentites.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.obama.main.tileentites.multi.definition.structure.IConstructableStructureSliceableCapped;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import net.minecraft.item.ItemStack;

import static com.github.bartimaeusnek.bartworks.system.material.BW_GT_MaterialReference.Titanium;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Forming_Press extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Forming_Press> implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Forming_Press(int aID) {
        super(aID, "multimachine.tm.large_forming_press", "Large Forming Press");
    }

    public GT_MetaTileEntity_TM_Large_Forming_Press(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Forming_Press(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{""};
    }

    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Forming_Press>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Forming_Press>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Forming_Press> computeValue(Class<?> type) {
            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Forming_Press>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                    {"cIIIc", "A---A", "B~BBB"},
                    {"I-p-I", "-----", "BBBBB"},
                    {"IpApI", "-----", "BBBBB"},
                    {"I-p-I", "-----", "BBBBB"},
                    {"cIIIc", "A---A", "BBBBB"}

                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                    {"c---c"},
                    {"--p--"},
                    {"-pAp-"},
                    {"--p--"},
                    {"c---c"}
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                    {"A A"},
                    {"AAA"},
                    {" A "},
                    {"AAA"},
                    {"A A"}
                })
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyInputToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addMaintenanceToMachineList, t.getTextureIndex(), 1),
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addOutputToMachineList, t.getTextureIndex(), 1),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('I', lazy(t -> ofChain(
                    ofHatchAdder(GT_MetaTileEntity_TM_Factory::addInputToMachineList, t.getTextureIndex(), 2),
                    ofBlock(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('p', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addPistonCasingToMachineList, t.getTextureIndex(), 3)))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 4)))
                .build();
        }
    };
    
    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Forming_Press> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        return null;
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(1, 2, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(1, 3, 0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 1, 0);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(-1, 0, 0);
    }

    @Override
    public int getMaxSlices() {
        return 3;
    }

    @Override
    public int getMinSlices() {
        return 0;
    }

    @Override
    public int getParalellsPerSlice() {
        return 32;
    }

    @Override
    public int getMinParallel() {
        return 32;
    }

    @Override
    public short getCasingMeta() {
        return Titanium.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Energy/Input/Maintenance Hatch",
                "2 - Input Hatch",
                "3 - Piston Casing",
                "4 - Circuit Casing"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_FORMING_PRESS";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sPressRecipes;
    }
}
