package com.extendedfeatures.client.integrations.Configuration;

import com.extendedfeatures.ExtendedFeaturesCore;
import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.ConfigHolder;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;

@Config(id = ExtendedFeaturesCore.MOD_ID)
public class EFConfig {

    public static EFConfig INSTANCE;
    public static ConfigHolder<EFConfig> CONFIG_HOLDER;

    public static void init() {
        CONFIG_HOLDER = Configuration.registerConfig(EFConfig.class, ConfigFormats.yaml());
        INSTANCE = CONFIG_HOLDER.getConfigInstance();
    }

    @Configurable
    @Configurable.Comment("Configuration values for Multiblocks")
    public MultiblocksToggles Multiblocks = new MultiblocksToggles();

    @Configurable
    @Configurable.Comment("Configuration Values for Machines and Items")
    public OtherToggles Miscellaneous = new OtherToggles();

    @Configurable
    @Configurable.Comment("Special section for Wireless Machines")
    public WirelessToggle Wireless = new WirelessToggle();

    public static class MultiblocksToggles {

        @Configurable
        @Configurable.Comment({ "Whether the Robust Alloy Materializer is Enabled." })
        public boolean RamEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Large Cracking Machine is Enabled." })
        public boolean LcmEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Enlarged Reaction Chamber is Enabled." })
        public boolean ErcEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Large Pyrolysis Oven is Enabled." })
        public boolean LpoEnabled = true;

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
        @Configurable.Comment({ "Whether the Expanded Data Bank is Enabled." })
        public boolean EdaEnabled = true;

        @Configurable
        @Configurable.Comment({ "Whether the Cloud Transmission Database is Enabled." })
        public boolean CtdEnabled = true;
    }

    public static class OtherToggles {

        @Configurable
        @Configurable.Comment({ "Whether the Universal Circuits are Enabled." })
        public boolean universalCircuits = true;

        @Configurable
        @Configurable.Comment({ "Whether the Expanded Data Access Hatches are Enabled." })
        public boolean expandedDataAccessHatches = true;
    }

    public static class WirelessToggle {

        @Configurable
        @Configurable.Comment({ "Whether the Wireless Optical Hatches & Cloud Transmission Database are Enabled." })
        public boolean wirelessUtils = true;
    }
}
