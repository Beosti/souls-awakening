package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ModConfiguredStructures {

    public static StructureFeature<?, ?> CONFIGURED_BIG_RUIN = ModStructures.BIG_RUIN.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SMALL_RUIN = ModStructures.SMALL_RUIN.get().configured(IFeatureConfig.NONE);

    public static void registerConfiguredStructures()
    {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(Main.MODID, "big_ruin"), CONFIGURED_BIG_RUIN);
        Registry.register(registry, new ResourceLocation(Main.MODID, "small_ruin"), CONFIGURED_SMALL_RUIN);

        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.BIG_RUIN.get(), CONFIGURED_BIG_RUIN);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.SMALL_RUIN.get(), CONFIGURED_SMALL_RUIN);
    }
}
