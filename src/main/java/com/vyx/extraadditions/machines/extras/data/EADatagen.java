package com.vyx.extraadditions.machines.extras.data;

import com.vyx.extraadditions.ExtraAdditionsCore;

import com.vyx.extraadditions.machines.extras.data.lang.EALangProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExtraAdditionsCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class EADatagen {

    @SubscribeEvent

    public static void onGatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();

        generator.addProvider(event.includeClient(), new EALangProvider(output));
    }
}