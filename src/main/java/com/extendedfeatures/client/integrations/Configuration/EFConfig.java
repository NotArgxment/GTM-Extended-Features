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
    @Configurable.Comment("Configuration Toggles for Multiblocks")
    public MultiblocksToggles Multiblocks = new MultiblocksToggles();

    @Configurable
    @Configurable.Comment("Configuration Toggles for Optical Features")
    public OpticalToggle OpticalMachines = new OpticalToggle();

    public static class MultiblocksToggles {

        @Configurable
        @Configurable.Comment({
                "Whether the Robust Alloy Materializer is Enabled",
                "Default = True"
        })
        public boolean RobustAlloyMaterializer = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Large Cracking Machine is Enabled",
                "Default = True"
        })
        public boolean LargeCrackingMachine = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Synthesis Vessel is Enabled",
                "Default = True"
        })
        public boolean SynthesisVessel = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Large Pyrolysis Oven is Enabled",
                "Default = True"
        })
        public boolean LargePyrolysisOven = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Compact Assembly Line is Enabled",
                "Tip: Controller recipe should contain 4 Assembly Line controllers",
                "Default = True"
        })
        public boolean CompactAssemblyLine = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Rock Processing Plant is Enabled",
                "Tip: Controller recipe should contain a large macerator, a large centrifuge and an large electrolyzer",
                "Default = True"
        })
        public boolean RockProcessingPlant = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Industrial Greenhouse is Enabled",
                "Default = True"
        })
        public boolean IndustrialGreenhouse = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Tree Growing Chamber is Enabled",
                "Default = True"
        })
        public boolean TreeGrowingChamber = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Disassembler is enabled",
                "§cWarning: §rUniversal Circuits MUST be enabled",
                "otherwise it could lead to issues with their recipes",
                "Default = True"
        })
        public boolean Disassembler = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Large Air Collector is Enabled",
                "Default = True"
        })
        public boolean LargeAirCollector = true;

    }

    public static class OpticalToggle {

        @Configurable
        @Configurable.Comment({
                "Whether the Expanded Data Access Hatches are Enabled",
                "Default = True"
        })
        public boolean ExpandedDataAccessHatches = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Wireless Optical Hatches are Enabled",
                "Default = True"
        })
        public boolean WirelessOptical = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Cloud Transmission Database is Enabled",
                "§cNote: Requires Wireless Optical Hatches enabled to work",
                "Default = True"
        })
        public boolean CloudTransmissionDatabase = true;

        @Configurable
        @Configurable.Comment({
                "Whether the Expanded Data Bank is Enabled",
                "Default = True"
        })
        public boolean ExpandedDatabank = true;

    }

    @Configurable
    @Configurable.Comment({ "Whether the Universal Circuits are Enabled." })
    public boolean UniversalCircuits = true;

}
