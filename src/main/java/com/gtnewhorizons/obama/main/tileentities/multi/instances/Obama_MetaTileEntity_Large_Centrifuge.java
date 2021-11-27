package com.gtnewhorizons.obama.main.tileentities.multi.instances;


import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizon.structurelib.util.Vec3Impl;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.Obama_MetaTileEntity_Factory;
import com.gtnewhorizons.obama.main.tileentities.multi.definition.structure.IConstructableStructureShapes;
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

public class Obama_MetaTileEntity_Large_Centrifuge extends Obama_MetaTileEntity_Factory<Obama_MetaTileEntity_Large_Centrifuge> implements IConstructableStructureShapes {

    public Obama_MetaTileEntity_Large_Centrifuge(int aID) {
        super(aID, "multimachine.obama.large_centrifuge", "Large Centrifuge");//TODO Set cooler name + .lang
    }

    public Obama_MetaTileEntity_Large_Centrifuge(String aName) {
        super(aName);
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        preLoadCheck();
        return IConstructableStructureShapes.super.checkMachine(aBaseMetaTileEntity, aStack) && additionalCheck();
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new Obama_MetaTileEntity_Large_Centrifuge(mName);
    }

    private static final ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Centrifuge>> STRUCTURE_DEFINITION  = new ClassValue<IStructureDefinition<Obama_MetaTileEntity_Large_Centrifuge>>() {
        @Override
        protected IStructureDefinition<Obama_MetaTileEntity_Large_Centrifuge> computeValue(Class<?> type) {
            return StructureDefinition.<Obama_MetaTileEntity_Large_Centrifuge>builder()
                .addShape(TM_STRUCTURE_A, transpose(new String[][] {
                    {" cc ", "cmmc", "cmmc", " cc "},
                    {" A~ ", "A--A", "A--A", " AA "},
                    {" BB ", "BBBB", "BBBB", " BB "},
                }))
                .addShape(TM_STRUCTURE_B, transpose(new String[][] {
                    {" cBc ", "cmmmc", "BmmmB", "cmmmc", " cBc "},
                    {" A~A ", "A---A", "A---A", "A---A", " AAA "},
                    {" cBc ", "cBBBc", "BBBBB", "cBBBc", " cBc "},
                }))
                .addShape(TM_STRUCTURE_C, transpose(new String[][] {
                    {"  ccc  ", " cmmmc ", "cmmBmmc", "cmBBBmc", "cmmBmmc", " cmmmc ", "  ccc  "},
                    {"  A~A  ", " A---A ", "A-----A", "A-----A", "A-----A", " A---A ", "  AAA  "},
                    {"  ccc  ", " cBBBc ", "cBBBBBc", "cBBBBBc", "cBBBBBc", " cBBBc ", "  ccc  "},
                }))
                .addShape(TM_STRUCTURE_D, transpose(new String[][] {
                    {"  ccccc  ", " ccmmmcc ", "ccmBBBmcc", "cmBBBBBmc", "cmBBBBBmc", "cmBBBBBmc", "ccmBBBmcc", " ccmmmcc ", "  ccccc  "},
                    {"  AA~AA  ", " A-----A ", "A-------A", "A-------A", "A-------A", "A-------A", "A-------A", " A-----A ", "  AAAAA  "},
                    {"  ccccc  ", " ccmmmcc ", "ccmBBBmcc", "cmBBBBBmc", "cmBBBBBmc", "cmBBBBBmc", "ccmBBBmcc", " ccmmmcc ", "  ccccc  "},
                }))
                .addElement('A', lazy(t -> addTileCasing(t.getCasingBlock(), t.getCasingMeta())))
                .addElement('G', ofBlockAnyMeta(GameRegistry.findBlock("IC2", "blockAlloyGlass")))
                .addElement('B', lazy(t -> ofChain(
                    ofHatchAdder(Obama_MetaTileEntity_Factory::addToMachineList, t.getTextureIndex(), 1),
                    addTileCasing(t.getCasingBlock(), t.getCasingMeta()))))
                .addElement('m', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addMotorCasingToMachineList, t.getTextureIndex(), 2)))
                .addElement('c', lazy(t -> ofHatchAdder(Obama_MetaTileEntity_Factory::addCircuitCasingToMachineList, t.getTextureIndex(), 3)))
                .build();
        }
    };
    
    @Override
    public IStructureDefinition<Obama_MetaTileEntity_Large_Centrifuge> getStructureDefinition() {
        return STRUCTURE_DEFINITION.get(getClass());
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("Centrifuge")
            .addInfo("Controller block for the Large Centrifuge")
            .addInfo("Four Sizes available")
            .addInfo(String.format("Parallels: %d, %d, %d, %d", getParalellsA(), getParalellsB(), getParalellsC(), getParalellsD()))
            .addSeparator()
            .beginVariableStructureBlock(4, 9, 3, 3, 4, 9, true)
            .addController("Front Middle")
            .addCasingInfo("Large Centrifuge Casing", 10) // TODO (Count, and name)
            .addEnergyHatch("Bottom Middle", 1)
            .addInputHatch("Bottom Middle", 1)
            .addOutputHatch("Bottom Middle", 1)
            .addInputBus("Bottom Middle", 1)
            .addOutputBus("Bottom Middle", 1)
            .addMaintenanceHatch("Bottom Middle", 1)
            .addDynamoHatch("Bottom Middle", 1)
            .addOtherStructurePart(ObamaTooltips.TT_motorCasing, "Top/Bottom second most outer ring", 2)
            .addOtherStructurePart(ObamaTooltips.TT_circuitCasing, "Top/Bottom outer ring", 3)
            .addStructureInfo("Reinforced Glass")
            .toolTipFinisher("Obama");
        return tt;
    }

    
    @Override
    public Vec3Impl getStartOffsetA() {
        return new Vec3Impl(2, 1, 0);
    }

    @Override
    public Vec3Impl getStartOffsetB() {
        return new Vec3Impl(2, 1, 0);
    }

    @Override
    public Vec3Impl getStartOffsetC() {
        return new Vec3Impl(3, 1, 0);
    }

    @Override
    public Vec3Impl getStartOffsetD() {
        return new Vec3Impl(4, 1, 0);
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
        return 128;
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
    public String getMachineTextureName() {
        return "TM_LARGE_CENTRIFUGE";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getMachineSoundName() {
        return "fx_lo_freq";
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GT_Recipe.GT_Recipe_Map.sCentrifugeRecipes;
    }
}
