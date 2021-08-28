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
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.lazy;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockAnyMeta;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizons.obama.main.compat.bartworks.MaterialsClass.MaragingSteel250;
import static gregtech.api.util.GT_StructureUtility.ofHatchAdder;

public class GT_MetaTileEntity_TM_Large_Compressor extends GT_MetaTileEntity_TM_Factory<GT_MetaTileEntity_TM_Large_Compressor> implements IConstructableStructureSliceableCapped {
    public GT_MetaTileEntity_TM_Large_Compressor(int aID) {
        super(aID, "multimachine.tm.large_compressor", "Large Compressor");
    }

    public GT_MetaTileEntity_TM_Large_Compressor(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Compressor(mName);
    }


    private static final ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Compressor>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<GT_MetaTileEntity_TM_Large_Compressor>>() {
        @Override
        protected IStructureDefinition<GT_MetaTileEntity_TM_Large_Compressor> computeValue(Class<?> type) {

            return StructureDefinition.<GT_MetaTileEntity_TM_Large_Compressor>builder()
                .addShape(TM_STRUCTURE_START, new String[][]{
                        {" AAAAA ", "AGGGGGA", "AGGGGGA", "AGGGGGA", "BBBB~BB"},
                        {" AAAAA ", "A-A-A-A", "A-A-A-A", "A-A-A-A", "BAAAAAB"}
                })
                .addShape(TM_STRUCTURE_MIDDLE, new String[][]{
                        {" AAAAA ", "cpA-Apc", "A-A-A-A", "A-A-A-A", " AAAAA "},
                        {" AAAAA ", "A-A-A-A", "cpA-Apc", "A-A-A-A", " AAAAA "}
                })
                .addShape(TM_STRUCTURE_CAP, new String[][]{
                        {"", "", "", "", "BAAAAAB"},
                        {" AAAAA ", "AGGGGGA", "AGGGGGA", "AGGGGGA", "BBBBBBB"}
                })
                .addElement('A', lazy(t -> ofBlock(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('B', lazy(t -> ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addToMachineList, t.getTextureIndex(), Block.getBlockFromName("gt.blockhintTT"), 1),
                        ofBlock(t.getCasingBlock(), t.getCasingMeta())
                )))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('p', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addPistonCasingToMachineList, t.getTextureIndex(), 2)))
                .addElement('c', lazy(t -> ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 3)))
                .build();
        } 
    };

    @Override
    public IStructureDefinition<GT_MetaTileEntity_TM_Large_Compressor> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Compressor")
            .addInfo("Controller block for the Large Compressor")
            .toolTipFinisher("Obama");
        return tt;
    }
    
    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(4, 4, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(4, 4, -2);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 0, -2);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(0, 0, 1);
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
    public int getMinParallel() {
        return 0;
    }

    @Override
    public int getParalellsPerSlice() {
        return 48;
    }

    @Override
    public short getCasingMeta() {
        return MaragingSteel250.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[]{
                "1 - Classic Hatches",
                "2 - Piston Casing",
                "3 - Circuit Casing"
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_COMPRESSOR";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sCompressorRecipes;
    }
}
