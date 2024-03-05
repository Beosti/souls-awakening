package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.Beapi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;

public interface IEntityRayTrace {

    default void onEntityRayTrace(PlayerEntity player, Ability ability) {
        RayTraceResult rayTraceResult = Beapi.rayTraceBlocksAndEntities(player, getDistance());
        if (!(rayTraceResult instanceof EntityRayTraceResult))
            return;
        if (!(((EntityRayTraceResult) rayTraceResult).getEntity() instanceof LivingEntity))
            return;
        somethingAtEntity(player, (LivingEntity) ((EntityRayTraceResult) rayTraceResult).getEntity());
        if (healAmount() > 0)
        {
            if (ability instanceof IReiatsuAbility)
                ((LivingEntity) ((EntityRayTraceResult) rayTraceResult).getEntity()).heal(healAmount() + (((IReiatsuAbility) ability).addedVariable(player)));
            else
                ((LivingEntity) ((EntityRayTraceResult) rayTraceResult).getEntity()).heal(healAmount());
        }
        if (addEffect() != null)
        {
            ((LivingEntity) ((EntityRayTraceResult) rayTraceResult).getEntity()).addEffect(addEffect());
        }
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

    default float healAmount()
    {
        return 0;
    }

    default EffectInstance addEffect()
    {
        return null;
    }
    default void somethingAtEntity(PlayerEntity player, LivingEntity entity)
    {

    }

}
