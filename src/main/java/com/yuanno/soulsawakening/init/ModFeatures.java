package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import net.minecraft.block.Block;
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
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFeatures {
    private static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, Main.MODID);
    private static ConfiguredFeature<?, ?> registerOre(Block ore, OreFeatureConfig oreFeatureConfig, ConfiguredPlacement configuredPlacement, int count)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                ore.getRegistryName(),
                Feature.ORE
                        .configured(oreFeatureConfig)
                        .decorated(configuredPlacement)
                        .squared()
                        .count(count)
        );
    }

    public static void setupFeatures(final BiomeLoadingEvent event)
    {
        ConfiguredFeature<?, ?> kairosekiOre = registerOre(
                ModBlocks.REISHI_ORE.get(),
                new OreFeatureConfig(
                        OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                        ModBlocks.REISHI_ORE.get().defaultBlockState(),
                        6),
                Placement.RANGE.configured(new TopSolidRangeConfig(1, 1, 100)),
                3
        );
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, kairosekiOre);
    }

    public static void register(IEventBus eventBus)
    {
        REGISTRY.register(eventBus);
    }
}
