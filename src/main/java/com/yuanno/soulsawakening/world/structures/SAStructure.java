package com.yuanno.soulsawakening.world.structures;

import com.mojang.serialization.Codec;
import com.yuanno.soulsawakening.world.util.StructuresHelper;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Iterator;

public abstract class SAStructure<C extends IFeatureConfig> extends Structure<C>
{
    public SAStructure(Codec<C> pCodec)
    {
        super(pCodec);
    }

    public abstract boolean biomeCheck(BiomeLoadingEvent event);

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, C featureConfig)
    {
        BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
        int oceanLevel = chunkGenerator.getSeaLevel();

        int dist = biome.getBiomeCategory() == Biome.Category.OCEAN ? 64 : 32;
        Iterator<BlockPos> iter = StructuresHelper.SPAWNED_STRUCTURES.iterator();
        while (iter.hasNext())
        {
            BlockPos pos = iter.next();
            if (Math.sqrt(centerOfChunk.distSqr(pos)) < dist)
            {
                return false;
            }
        }

        for (Biome biomeCheck : biomeSource.getBiomesWithin(chunkX * 16 + 9, oceanLevel, chunkZ * 16 + 9, 32))
        {
            if (!biomeCheck.getGenerationSettings().isValidStart(this))
            {
                return false;
            }
        }

        return true;
    }
}
