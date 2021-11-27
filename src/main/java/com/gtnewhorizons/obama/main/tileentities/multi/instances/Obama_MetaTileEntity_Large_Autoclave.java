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

public class Obama_MetaTileEntity_Large_Autoclave extends Obama_MetaTileEntity_Factory<Obama_MetaTileEntity_Large_Autoclave> implements IConstructableStructureSliceableCapped {

    public Obama_MetaTileEntity_Large_Autoclave(int aID) {
        super(aID, "multimachine.obama.large_autoclave", "Large Autoclave");
    }

    public Obama_MetaTileEntity_Large_Autoclave(String aName) {
        super(aName);
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        preLoadCheck();
        return IConstructableStructureSliceableCapped.super.checkMachine(aBaseMetaTileEntity, aStack) && additionalCheck();
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new Obama_MetaTileEntity_Large_Autoclave(mName);
    }


    private static final ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Autoclave>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Autoclave>>() {
        @Override
        protected IStructureDefinition<Obama_MetaTileEntity_Large_Autoclave> computeValue(Class<?> type) {
            return StructureDefinition.<Obama_MetaTileEntity_Large_Autoclave>builder()
                // Bottom two layers
                .addShape(TM_STRUCTURE_START, transpose(new String[][] {
                    {" GGG ", "G   G", "G   G", "G   G", "A   A", "AclcA"},
                    {" B~B ", "BBBBB", "BBBBB", "BBBBB", "BBBBB", "BBBBB"},
                }))
                // Middle Layer(s)
                .addShape(TM_STRUCTURE_MIDDLE, transpose(new String[][] {
                    {" GGG ", "G   G", "G - G", "G   G", "A   A", "AclcA"},
                }))
                // Top two layers
                .addShape(TM_STRUCTURE_CAP, transpose(new String[][] {
                    {" BBB ", "BBBBB", "BBBBB", "BBBBB", "BBBBB", "BBBBB"},
                    {" GGG ", "G   G", "G A G", "G   G", "A   A", "AclcA"},
                }))
                .addElement('A', lazy(t -> addTileCasing(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addToMachineList, t.getTextureIndex(), 1),
                    addTileCasing(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('l', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addPumpCasingToMachineList, t.getTextureIndex(), 2)))
                .addElement('c', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 3)))
                .build();
        }
    };
    
    @Override
    public IStructureDefinition<Obama_MetaTileEntity_Large_Autoclave> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }
    
    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Autoclave")
            .addInfo("Controller block for the Large Autoclave")
            .addInfo(String.format("Slicable - Min: %d  Max: %d", getMinSlices(), getMaxSlices()))
            .addInfo(String.format("Parallels per Slice: %d", getParalellsPerSlice()))
            .addSeparator()
            .beginVariableStructureBlock(5, 5, 4, 4 + getMaxSlices(), 6, 6,  true)
            .addController("Front Bottom")
            .addCasingInfo("Autoclave Casing", 10) // TODO (Count, and name)
            .addEnergyHatch("Any casing", 1)
            .addInputHatch("Any casing", 1)
            .addOutputHatch("Any Casing", 1)
            .addInputBus("Any Casing", 1)
            .addOutputBus("Any Casing", 1)
            .addMaintenanceHatch("Any Casing", 1)
            .addDynamoHatch("Any Casing", 1)
            .addOtherStructurePart(ObamaTooltips.TT_pumpCasing, "Back Wall", 2)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Back Wall", 3)
            .addStructureInfo("Reinforced Glass")
            .toolTipFinisher("Obama");
        return tt;
    }

    @Override
    public Vec3Impl getStartStructureOffset() {
        return new Vec3Impl(2, 1, 0);
    }

    @Override
    public Vec3Impl getSliceStructureOffset() {
        return new Vec3Impl(2, 2, 0);
    }

    @Override
    public Vec3Impl getPerSliceOffset() {
        return new Vec3Impl(0, 1, 0);
    }

    @Override
    public Vec3Impl getCapStructureOffset() {
        return new Vec3Impl(0, 1, 0);
    }

    @Override
    public int getMaxSlices() {
        return 6;
    }

    @Override
    public int getMinSlices() {
        return 0;
    }

    @Override
    public int getParalellsPerSlice() {
        return 16;
    }

    @Override
    public int getMinParallel() {
        return 32;
    }

    @Override
    public short getCasingMeta() {
        return MaragingSteel250.getmID();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineTextureName() {
        return "TM_LARGE_AUTOCLAVE";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sAutoclaveRecipes;
    }
}
