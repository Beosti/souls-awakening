package com.yuanno.soulsawakening.world.gen;

import com.yuanno.soulsawakening.world.OreType;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration {
    public static void generateOres(final BiomeLoadingEvent event)
    {
        for (OreType oreType : OreType.values())
        {
            OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(
                    OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                    oreType.getBlock().get().defaultBlockState(), oreType.getMaxVeinSize());
            ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configured(
                    new TopSolidRangeConfig(oreType.getMinHeight(), oreType.getMinHeight(), oreType.getMaxHeight()));

           ConfiguredFeature<?, ?> oreFeature = registerOreFeature(oreType, oreFeatureConfig, configuredPlacement);

            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreFeature);
        }
    }

    private static ConfiguredFeature<?, ?> registerOreFeature(OreType oreType, OreFeatureConfig oreFeatureConfig,
                                                              ConfiguredPlacement configuredPlacement)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, oreType.getBlock().get().getRegistryName(),
                Feature.ORE.configured(oreFeatureConfig).decorated(configuredPlacement)
                        .squared());
    }
}
