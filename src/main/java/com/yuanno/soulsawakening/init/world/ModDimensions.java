package com.yuanno.soulsawakening.init.world;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.world.ChallengesBiomeProvider;
import com.yuanno.soulsawakening.world.ChallengesChunkGenerator;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ModDimensions {
    public static RegistryKey<World> HUECO_MUNDO = RegistryKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Main.MODID, "hueco_mundo"));
    public static void setupDimensions() {
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(Main.MODID, "challenges_biome_provider"), ChallengesBiomeProvider.CODEC);
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(Main.MODID, "challenges_chunk_generator"), ChallengesChunkGenerator.CODEC);
    }
}
