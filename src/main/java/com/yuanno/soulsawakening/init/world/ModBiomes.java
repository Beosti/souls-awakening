package com.yuanno.soulsawakening.init.world;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.init.world.ModConfiguredSurfaceBuilders;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Main.MODID);
    public static final RegistryObject<Biome> HUECO_MUNDO = BIOMES.register("hueco_mundo", () -> makeHuecoMundoBiome(() -> ModConfiguredSurfaceBuilders.HUECO_MUNDO, 0.18f, 0.15f));
    public static final RegistryObject<Biome> SOUL_SOCIETY = BIOMES.register("soul_society", () -> makeSoulSocietyBiome(() -> ModConfiguredSurfaceBuilders.SOUL_SOCIETY, -0.105f, -0.105f));

    public static Biome makeHuecoMundoBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale) {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.desertSpawns(mobspawninfo$builder);
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder().surfaceBuilder(surfaceBuilder));


        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.DESERT).depth(depth).scale(scale).temperature(2.0F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204)
                .waterFogColor(329011)
                .fogColor(12638463)
                .skyColor(calculateSkyColor())
                .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
                .build())
                .mobSpawnSettings(mobspawninfo$builder.build()).generationSettings(biomegenerationsettings$builder.build()).build();


    }

    public static Biome makeSoulSocietyBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale) {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.desertSpawns(mobspawninfo$builder);
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder().surfaceBuilder(surfaceBuilder));


        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.NONE).depth(depth).scale(scale).temperature(2.0F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(calculateSkyColor())
                        .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
                        .build())
                .mobSpawnSettings(mobspawninfo$builder.build()).generationSettings(biomegenerationsettings$builder.build()).build();


    }

    private static int calculateSkyColor() {
        float lvt_1_1_ = (float) 0.6 / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static void register(IEventBus eventBus)
    {
        BIOMES.register(eventBus);
    }
}
