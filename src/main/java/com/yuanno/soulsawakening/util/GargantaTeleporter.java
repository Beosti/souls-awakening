package com.yuanno.soulsawakening.util;

import com.yuanno.soulsawakening.init.ModAdvancements;
import com.yuanno.soulsawakening.init.world.ModDimensions;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import java.util.Collections;
import java.util.function.Function;

public class GargantaTeleporter implements ITeleporter {
    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean insideDimension = true;

    public GargantaTeleporter(BlockPos pos, boolean insideDim) {
        thisPos = pos;
        insideDimension = insideDim;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destinationWorld,
                              float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);
        double y = 0;
        //double y = destinationWorld.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, entity.blockPosition());

        if (!insideDimension) {
            y = thisPos.getY();
        }


        BlockPos oldPos = new BlockPos(thisPos.getX(), thisPos.getY(), thisPos.getZ());


        BlockPos destinationPos = new BlockPos(oldPos.getX(), oldPos.getY(), oldPos.getZ());

        while (!destinationWorld.getBlockState(destinationPos).getBlock().equals(Blocks.AIR)) {
            destinationPos = destinationPos.above(1);
        }

        entity.teleportTo(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());

        if (entity instanceof PlayerEntity)
        {
            ((ServerPlayerEntity) entity).connection.teleport(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ(),
                    entity.yRot,
                    entity.xRot, Collections.emptySet());
            PlayerEntity player = (PlayerEntity) entity;
            if (destinationWorld.dimension() == ModDimensions.HUECO_MUNDO)
                ModAdvancements.HUECO_MUNDO.trigger((ServerPlayerEntity) player);
            else if (destinationWorld.dimension() == ModDimensions.SOUL_SOCIETY)
                ModAdvancements.SOUL_SOCIETY.trigger((ServerPlayerEntity) player);
            else if (destinationWorld.dimension() == ModDimensions.SOUL_SOCIETY_SHADOW)
                ModAdvancements.SOUL_SOCIETY_SHADOW.trigger((ServerPlayerEntity) player);
        }
        //entity.teleportTo(destinationPos.getX(), destinationWorld.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, oldPos).getY(), destinationPos.getZ());

        return entity;
    }
}
