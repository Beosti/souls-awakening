package com.yuanno.soulsawakening.world.structures;

import com.yuanno.soulsawakening.Main;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class BigRuinStructure extends Structure<NoFeatureConfig> {

    public BigRuinStructure()
    {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, net.minecraft.world.biome.provider.BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {



        BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
        int landHeight = chunkGenerator.getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(),
                Heightmap.Type.WORLD_SURFACE_WG);

        IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ());
        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));

        return topBlock.getFluidState().isEmpty();
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
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config)
        {
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            BlockPos blockpos = new BlockPos(x, 0, z);

            JigsawManager.addPieces(dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
                            .get(new ResourceLocation(Main.MODID, "ruins/big_ruin")),
                            10), AbstractVillagePiece::new, chunkGenerator, templateManagerIn,
                    blockpos, this.pieces, this.random,false,true);




            this.pieces.forEach(piece -> piece.move(0, -1, 0));
            this.pieces.forEach(piece -> piece.getBoundingBox().y0 -= 1);

            this.calculateBoundingBox();

            LogManager.getLogger().log(Level.DEBUG, "Big ruin at " +
                    this.pieces.get(0).getBoundingBox().x0 + " " +
                    this.pieces.get(0).getBoundingBox().y0 + " " +
                    this.pieces.get(0).getBoundingBox().z0);
        }
        /*
        @Override
public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config)
{
    int x = (chunkX << 4) + 7;
    int z = (chunkZ << 4) + 7;
    BlockPos blockpos = new BlockPos(x, 0, z);

    JigsawManager.addPieces(dynamicRegistryManager,
            new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
                    .get(new ResourceLocation(Main.MODID, "big_ruin")),
                    10), AbstractVillagePiece::new, chunkGenerator, templateManagerIn,
            blockpos, this.pieces, this.random,false,true);

    MutableBoundingBox structureBounds = this.pieces.get(0).getBoundingBox();

    for (int xi = structureBounds.x0; xi <= structureBounds.x1; xi++) {
        for (int yi = structureBounds.y0; yi <= structureBounds.y1; yi++) {
            for (int zi = structureBounds.z0; zi <= structureBounds.z1; zi++) {
                BlockPos pos = new BlockPos(xi, yi, zi);
                if (!chunkGenerator.getBaseColumn(pos.getX(), pos.getZ()).getBlockState(pos).isAir()) {
                    this.pieces.clear(); // If any block is not air, clear the pieces and stop the generation
                    return;
                }
            }
        }
    }

    this.pieces.forEach(piece -> piece.move(0, -1, 0));
    this.pieces.forEach(piece -> piece.getBoundingBox().y0 -= 1);

    this.calculateBoundingBox();

    LogManager.getLogger().log(Level.DEBUG, "Big ruin at " +
            this.pieces.get(0).getBoundingBox().x0 + " " +
            this.pieces.get(0).getBoundingBox().y0 + " " +
            this.pieces.get(0).getBoundingBox().z0);
}
         */
    }
}
