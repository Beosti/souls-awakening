package com.yuanno.soulsawakening.world.challenges;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;

public class ChallengesChunkGenerator extends ChunkGenerator
{
	public static final Codec<ChallengesChunkGenerator> CODEC = RegistryLookupCodec.create(Registry.BIOME_REGISTRY).xmap(ChallengesChunkGenerator::new, ChallengesChunkGenerator::biomes).stable().codec();

	private final Registry<Biome> registry;

	public ChallengesChunkGenerator(Registry<Biome> registry)
	{
		super(new SingleBiomeProvider(registry.getOrThrow(Biomes.THE_VOID)), new DimensionStructuresSettings(false));
		this.registry = registry;
	}

	public Registry<Biome> biomes()
	{
		return this.registry;
	}

	@Override
	protected Codec<? extends ChunkGenerator> codec()
	{
		return CODEC;
	}

	@Override
	public ChunkGenerator withSeed(long seed)
	{
		return this;
	}

	@Override
	public void buildSurfaceAndBedrock(WorldGenRegion pLevel, IChunk pChunk)
	{

	}

	@Override
	public void fillFromNoise(IWorld p_230352_1_, StructureManager p_230352_2_, IChunk p_230352_3_)
	{

	}

	@Override
	public int getBaseHeight(int p_222529_1_, int p_222529_2_, Type p_222529_3_)
	{
		return 0;
	}

	@Override
	public IBlockReader getBaseColumn(int x, int z)
	{
		return new Blockreader(new BlockState[0]);
	}
}