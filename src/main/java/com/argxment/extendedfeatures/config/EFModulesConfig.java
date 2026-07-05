package com.argxment.extendedfeatures.config;

import com.argxment.extendedfeatures.ExtendedFeaturesCore;
import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.ConfigHolder;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;

@Config(id = ExtendedFeaturesCore.MOD_ID)
public class EFModulesConfig {

    public static EFModulesConfig INSTANCE;
    public static ConfigHolder<EFModulesConfig> CONFIG_HOLDER;

    public static void init() {
        CONFIG_HOLDER = Configuration.registerConfig(EFModulesConfig.class, ConfigFormats.yaml());
        INSTANCE = CONFIG_HOLDER.getConfigInstance();
    }

    @Configurable
    public FeatureConfigs features = new FeatureConfigs();

    public static class FeatureConfigs {
        @Configurable
        @Configurable.Comment({ "Whether the Robust Alloy Materializer is Enabled." })
            public boolean RamEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Advanced Cracking Unit is Enabled." })
            public boolean AcuEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Enlarged Reaction Chamber is Enabled." })
            public boolean ErcEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Large Pyrolysis Unit is Enabled." })
            public boolean LpuEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Advanced Fusion Reactors are Enabled." })
            public boolean AfrEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Compact Assembly Line is Enabled." })
            public boolean CalEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Rock Processing Facility is Enabled." })
            public boolean RpfEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Industrial Greenhouse is Enabled." })
            public boolean IghEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Tree Growing Chamber is Enabled." })
            public boolean TgcEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Disassembler is Enabled." })
            public boolean DaEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Disassembler is Enabled." })
        public boolean universalCircuits = true;
    }
}
