package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.api.Beapi;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public interface IBlockRayTrace {

    default void onBlockRayTrace(PlayerEntity player) {
        RayTraceResult rayTraceResult = Beapi.rayTraceBlocksAndEntities(player, getDistance());
        BlockPos blockPos = new BlockPos(rayTraceResult.getLocation());
        somethingAtDistance(player, blockPos);
    }
    default int getDistance()
    {
        return 0;
    }


    default void somethingAtDistance(PlayerEntity player, BlockPos blockPos)
    {

    }


}
