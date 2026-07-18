package com.extendedfeatures.init.utils.internal.optical;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.common.machine.multiblock.part.DataAccessHatchMachine;

public class ExpandedDataHatchLogic extends DataAccessHatchMachine {

    public ExpandedDataHatchLogic(IMachineBlockEntity holder, int tier, boolean isCreative) {
        super(holder, tier, isCreative);
    }

    @Override
    protected int getInventorySize() {
        return 1;
    }
}
