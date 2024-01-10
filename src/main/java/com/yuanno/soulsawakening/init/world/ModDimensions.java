package com.yuanno.soulsawakening.init.world;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.world.ChallengesBiomeProvider;
import com.yuanno.soulsawakening.world.ChallengesChunkGenerator;
import com.yuanno.soulsawakening.world.DynamicDimensionManager;
import net.minecraft.item.Item;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.world.ForgeWorldType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModDimensions {
    public static RegistryKey<World> HUECO_MUNDO = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(Main.MODID, "hueco_mundo"));


    public static void setupDimensionChallenges() {
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(Main.MODID, "challenges_biome_provider"), ChallengesBiomeProvider.CODEC);
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(Main.MODID, "challenges_chunk_generator"), ChallengesChunkGenerator.CODEC);
    }

}
