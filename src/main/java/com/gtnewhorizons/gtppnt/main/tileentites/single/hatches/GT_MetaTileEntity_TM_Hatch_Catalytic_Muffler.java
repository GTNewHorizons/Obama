package com.gtnewhorizons.gtppnt.main.tileentites.single.hatches;

import com.gtnewhorizons.gtppnt.main.CommonValues;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Muffler;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.technus.tectech.util.CommonValues.VN;


public class GT_MetaTileEntity_TM_Hatch_Catalytic_Muffler extends GT_MetaTileEntity_Hatch_Muffler {
    //TODO physically looks untidy.
    private static final String localizedDescFormat = GT_LanguageManager.addStringLocalization(
            "gt.blockmachines.hatch.muffler.desc.format",
            "Outputs the Pollution (Might cause ... things)%n" +
                    "DO NOT OBSTRUCT THE OUTPUT!%n" +
                    "Reduces Pollution to %d%%%n");
    private final int pollutionReduction = calculatePollutionReduction(100);
    private final List<String> description = Arrays.stream(String.format(localizedDescFormat, pollutionReduction)
            .split("\\R")).collect(Collectors.toList());

    public GT_MetaTileEntity_TM_Hatch_Catalytic_Muffler(int aID, int aTier) {
        super(aID, "singlemachine.tm.catalytic_muffler_hatch_" + VN[aTier],
                "Catalytic Muffler Hatch (" + VN[aTier] + ")", aTier);
    }

    public GT_MetaTileEntity_TM_Hatch_Catalytic_Muffler(String aName, int aTier, String[] aDescription,
                                                        ITexture[][][] aTextures) {
        super(aName, aTier, aDescription, aTextures);
    }

    //TODO make this cleaner?
    {
        description.add(CommonValues.TM_MARK);
    }

    @Override
    public String[] getDescription() {
        return description.toArray(new String[0]);
    }

    @Override
    public boolean polluteEnvironment(MetaTileEntity mte) {
        tickPollutionDamage(mte);
        return super.polluteEnvironment(mte);
    }

    private void tickPollutionDamage(MetaTileEntity mte) {
        //TODO Value to config.
        int damageChance = 10;
        if (!(mte instanceof GT_MetaTileEntity_MultiBlockBase))
            return;

        GT_MetaTileEntity_MultiBlockBase multiBlock = (GT_MetaTileEntity_MultiBlockBase) mte;
        if (mte.getBaseMetaTileEntity().getWorld().rand.nextInt(damageChance) == 0)
            multiBlock.doRandomMaintenanceDamage();
    }

    public int calculatePollutionReduction(int aPollution) {
        //TODO Value to config.
        int buffScale = 10;
        return super.calculatePollutionReduction(aPollution) / buffScale;
    }

    @Override
    public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_TM_Hatch_Catalytic_Muffler(this.mName, this.mTier, this.mDescriptionArray,
                this.mTextures);
    }

    public ItemStack getItem() {
        return this.getStackForm(1L);
    }
}
