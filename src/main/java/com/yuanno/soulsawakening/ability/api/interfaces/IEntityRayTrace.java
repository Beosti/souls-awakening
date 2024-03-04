package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.api.Beapi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;

public interface IEntityRayTrace {

    default void onEntityRayTrace(PlayerEntity player) {
        RayTraceResult rayTraceResult = Beapi.rayTraceBlocksAndEntities(player, getDistance());
        if (!(rayTraceResult instanceof EntityRayTraceResult))
            return;
        if (!(((EntityRayTraceResult) rayTraceResult).getEntity() instanceof LivingEntity))
            return;
        somethingAtEntity(player, (LivingEntity) ((EntityRayTraceResult) rayTraceResult).getEntity());
    }
    default int getDistance()
    {
        return 0;
    }

    default boolean gotTarget(PlayerEntity player)
    {
        RayTraceResult rayTraceResult = Beapi.rayTraceBlocksAndEntities(player, getDistance());
        if (!(rayTraceResult instanceof EntityRayTraceResult))
            return false;
        return ((EntityRayTraceResult) rayTraceResult).getEntity() instanceof LivingEntity;
    }

    default void somethingAtEntity(PlayerEntity player, LivingEntity entity)
    {

    }


}
