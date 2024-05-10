package com.yuanno.soulsawakening.world.structures;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.world.pieces.ShinigamiSchoolPieces;
import com.yuanno.soulsawakening.world.util.StructuresHelper;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.util.Iterator;

public class ShinigamiSchoolStructure extends SAStructure<NoFeatureConfig> {

    public ShinigamiSchoolStructure()
    {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    public boolean biomeCheck(BiomeLoadingEvent event) {
        return true;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, net.minecraft.world.biome.provider.BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        return true;
    }

    @Override
    public GenerationStage.Decoration step()
    {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory()
    {
        return Start::new;
    }

    public static class Start extends StructureStart<NoFeatureConfig>
    {
        public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int reference, long seed)
        {
            super(structure, chunkX, chunkZ, mutableBoundingBox, reference, seed);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig config)
        {
            Rotation rotation = Rotation.getRandom(this.random);
            BlockPos blockpos = null;
            for(int i = 0; i < 4; i++)
            {
                for(int j = 0; j < 4; j++)
                {
                    BlockPos centerOfChunk = new BlockPos(((chunkX + i) << 4) - 7, 0, ((chunkZ + j) << 4) - 7);
                    int landHeight = chunkGenerator.getBaseHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
                    if(blockpos == null || (blockpos != null && blockpos.getY() > landHeight))
                        blockpos = new BlockPos(centerOfChunk.getX(), landHeight, centerOfChunk.getZ());
                }
            }
            ShinigamiSchoolPieces.addMainComponent(templateManager, blockpos, this.pieces);
            this.pieces.get(0).move(-32, -2, -120);

            this.calculateBoundingBox();
            LogManager.getLogger().log(Level.DEBUG, "Shinigami school at " +
                    this.pieces.get(0).getBoundingBox().x0 + " " +
                    this.pieces.get(0).getBoundingBox().y0 + " " +
                    this.pieces.get(0).getBoundingBox().z0);
        }
    }
}
