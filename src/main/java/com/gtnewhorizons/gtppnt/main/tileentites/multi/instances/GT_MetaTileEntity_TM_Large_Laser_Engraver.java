package com.gtnewhorizons.gtppnt.main.tileentites.multi.instances;

import com.github.technus.tectech.mechanics.structure.IStructureDefinition;
import com.github.technus.tectech.mechanics.structure.StructureDefinition;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.util.Vec3Impl;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.GT_MetaTileEntity_TM_Factory;
import com.gtnewhorizons.gtppnt.main.tileentites.multi.definition.structure.IConstructableStructureSliceable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Recipe;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import static com.github.technus.tectech.mechanics.structure.StructureUtility.*;
import static com.github.technus.tectech.mechanics.structure.StructureUtility.ofBlock;
import static com.gtnewhorizons.gtppnt.main.compat.bartworks.MaterialsClass.MaragingSteel250;

public class GT_MetaTileEntity_TM_Large_Laser_Engraver extends GT_MetaTileEntity_TM_Factory implements IConstructableStructureSliceable {

    public GT_MetaTileEntity_TM_Large_Laser_Engraver(int aID) {
        super(aID, "multimachine.tm.large_precision_laser_engraver", "Large Precision Laser Engraver");
    }

    public GT_MetaTileEntity_TM_Large_Laser_Engraver(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Large_Laser_Engraver(mName);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"laser engrave desc"};
    }

    @Override
    public IStructureDefinition<? extends GT_MetaTileEntity_MultiblockBase_EM> getMachineStructure() {
        Block reinforcedGlass = Block.getBlockFromName("IC2:blockAlloyGlass");
        return StructureDefinition.<GT_MetaTileEntity_TM_Factory>builder()
                .addShape(TM_STRUCTURE_START,new String[][]{
                        {"B","A","~","B"},
                        {"A","G","A","A"},
                        {"B","A","A","B"}
                })
                .addShape(TM_STRUCTURE_MIDDLE,new String[][]{
                        {"c ","pB","GA","IA","BB"},
                        {"c ","eA","-G","-A","AA"},
                        {"c ","pB","GA","OA","BB"},
                })
                .addElement('A', ofBlock(getCasingBlock(), getCasingMeta()))
                .addElement('B',ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicMaintenanceToMachineList,
                                getTextureIndex(),1),
                                ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEnergyIOToMachineList,
                                        getTextureIndex(),1),
                        ofBlock(getCasingBlock(), getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(reinforcedGlass))
                .addElement('I',ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicInputToMachineList,
                                getTextureIndex(),2),
                        ofBlock(getCasingBlock(),getCasingMeta())))
                .addElement('O',ofChain(
                        ofHatchAdder(GT_MetaTileEntity_TM_Factory::addClassicOutputToMachineList,
                                getTextureIndex(),3),
                        ofBlock(getCasingBlock(),getCasingMeta())))
                .addElement('p',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addPistonCasingToMachineList,
                        getTextureIndex(),4))
                .addElement('e',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addEmitterToMachineList,
                        getTextureIndex(),5))
                .addElement('c',ofHatchAdder(GT_MetaTileEntity_TM_Factory::addCircuitCasingToMachineList,
                        getTextureIndex(),6))
                .build();
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(0,2,0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(-1,3,0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(-2,0,0);
    }

    @Override
    public int getMaxSlices() {
        return 4;
    }

    @Override
    public int getParalellsPerSlice() {
        return 16;
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
                "2 - Input Hatch",
                "3 - Output Hatch",
                "4 - Piston Casing",
                "5 - Emitter Casing",
                "6 - Circuit Casing"
        };
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
