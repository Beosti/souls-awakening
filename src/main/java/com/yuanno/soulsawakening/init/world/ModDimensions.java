package com.yuanno.soulsawakening.init.world;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.world.challenges.ChallengesBiomeProvider;
import com.yuanno.soulsawakening.world.challenges.ChallengesChunkGenerator;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ModDimensions {
    public static RegistryKey<World> HUECO_MUNDO = RegistryKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Main.MODID, "hueco_mundo"));
    public static RegistryKey<World> SOUL_SOCIETY = RegistryKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Main.MODID, "soul_society"));
    /*
    public static RegistryKey<World> TEST = RegistryKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Main.MODID, "test"));

     */
    public static void setupDimensions() {
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(Main.MODID, "challenges_biome_provider"), ChallengesBiomeProvider.CODEC);
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(Main.MODID, "challenges_chunk_generator"), ChallengesChunkGenerator.CODEC);
    }


}
