package com.yuanno.soulsawakening.world.pieces;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.init.ModStructures;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.*;

import java.util.*;

public class ShinigamiSchoolPieces {
    private static final ResourceLocation SHINIGAMI_SCHOOL = new ResourceLocation(Main.MODID, "shinigami_school");
    private static final ResourceLocation SHINIGAMI_SCHOOL_SIDE = new ResourceLocation(Main.MODID, "shinigami_school_side");

    private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
            .put(SHINIGAMI_SCHOOL, new BlockPos(0, 0, 0))
            .put(SHINIGAMI_SCHOOL_SIDE, new BlockPos(0, 0, 128))
            .build();
    private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
            .put(SHINIGAMI_SCHOOL, new BlockPos(0, 0, 0))
            .put(SHINIGAMI_SCHOOL_SIDE, new BlockPos(0, 0, 0))
            .build();

    public static void addMainComponent(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components)
    {
        components.add(new MainBodyPiece(templateManager, pos));
        //components.add(new SidePiece(templateManager, pos));
    }
    public static void addSideComponent(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components)
    {
        components.add(new SidePiece(templateManager, pos));
    }

    public static class SidePiece extends Piece
    {
        public SidePiece(TemplateManager template, CompoundNBT nbt)
        {
            super(template, ModStructures.Pieces.SHINIGAMI_SCHOOL_SIDE_PIECE, nbt);
        }

        public SidePiece(TemplateManager template, BlockPos pos)
        {
            super(template, ModStructures.Pieces.SHINIGAMI_SCHOOL_SIDE_PIECE, SHINIGAMI_SCHOOL_SIDE, pos, BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
        }

        @Override
        public boolean postProcess(ISeedReader world, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos)
        {
            boolean flag = super.postProcess(world, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos, pPos);

            List<Template.BlockInfo> infos = new ArrayList<Template.BlockInfo>();
            infos.addAll(this.template.filterBlocks(this.templatePosition, this.placeSettings, Blocks.COBBLESTONE_WALL));
            infos.addAll(this.template.filterBlocks(this.templatePosition, this.placeSettings, Blocks.COBBLESTONE_STAIRS));
            infos.addAll(this.template.filterBlocks(this.templatePosition, this.placeSettings, Blocks.IRON_BARS));
            infos.addAll(this.template.filterBlocks(this.templatePosition, this.placeSettings, Blocks.LANTERN));

            for(Template.BlockInfo info : infos)
                world.setBlock(info.pos, info.state.setValue(BlockStateProperties.WATERLOGGED, false), 3);

            return flag;
        }
    }
    public static class MainBodyPiece extends Piece
    {
        public MainBodyPiece(TemplateManager template, CompoundNBT nbt)
        {
            super(template, ModStructures.Pieces.SHINIGAMI_SCHOOL_MAIN_BODY_PIECE, nbt);
        }

        public MainBodyPiece(TemplateManager template, BlockPos pos)
        {
            super(template, ModStructures.Pieces.SHINIGAMI_SCHOOL_MAIN_BODY_PIECE, SHINIGAMI_SCHOOL, pos, BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
        }

        @Override
        public boolean postProcess(ISeedReader world, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos)
        {
            boolean flag = super.postProcess(world, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos, pPos);

            List<Template.BlockInfo> infos = new ArrayList<Template.BlockInfo>();
            infos.addAll(this.template.filterBlocks(this.templatePosition, this.placeSettings, Blocks.JUNGLE_STAIRS));

            for(Template.BlockInfo info : infos)
                world.setBlock(info.pos, info.state.setValue(BlockStateProperties.WATERLOGGED, false), 3);

            return flag;
        }
    }

    public static class Piece extends TemplateStructurePiece
    {
        protected ResourceLocation resourceLocation;
        protected Rotation rotation;
        protected StructureProcessor processor;

        public Piece(TemplateManager template, IStructurePieceType type, CompoundNBT nbt)
        {
            super(type, nbt);
            this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
            this.rotation = Rotation.valueOf(nbt.getString("Rot"));
            this.processor = BlockIgnoreStructureProcessor.STRUCTURE_AND_AIR;
            this.build(template);
        }

        public Piece(TemplateManager template, IStructurePieceType type, ResourceLocation res, BlockPos pos, StructureProcessor proc)
        {
            super(type, 0);
            this.rotation = Rotation.NONE;
            this.resourceLocation = res;
            BlockPos blockpos = POSITION_OFFSET.get(this.resourceLocation);
            this.templatePosition = pos.offset(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            this.processor = BlockIgnoreStructureProcessor.STRUCTURE_AND_AIR;
            this.build(template);
        }

        @Override
        protected void addAdditionalSaveData(CompoundNBT nbt)
        {
            super.addAdditionalSaveData(nbt);
            nbt.putString("Template", this.resourceLocation.toString());
            nbt.putString("Rot", this.rotation.name());
        }

        private void build(TemplateManager templateManager)
        {
            Template template = templateManager.getOrCreate(this.resourceLocation);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setRotationPivot(CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
            this.setup(template, this.templatePosition, placementsettings);
        }

        @Override
        public boolean postProcess(ISeedReader world, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos)
        {
            boolean flag = super.postProcess(world, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos, pPos);
            return flag;
        }

        @Override
        protected void handleDataMarker(String s, BlockPos blockPos, IServerWorld iServerWorld, Random random, MutableBoundingBox mutableBoundingBox) {
        }
    }

}
