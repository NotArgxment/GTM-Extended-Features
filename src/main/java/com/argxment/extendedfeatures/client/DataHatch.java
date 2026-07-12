package com.argxment.extendedfeatures.client;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.common.machine.multiblock.part.DataAccessHatchMachine;

public class DataHatch extends DataAccessHatchMachine {

    public DataHatch(IMachineBlockEntity holder, int tier, boolean isCreative) {
        super(holder, tier, isCreative);
    }

    @Override
    protected int getInventorySize() {
        return 1;
    }
}
