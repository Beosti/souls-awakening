package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.events.ability.CustomInteractionEvent;
import com.yuanno.soulsawakening.events.ability.RightClickAbilityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;

/**
 * Used for abilities that target specifically an entity, it doesn't do anything with the block position for that: {@link IBlockRayTrace}
 * Handled in {@link #onEntityRayTrace(PlayerEntity, Ability)}, triggered here:
 * @see RightClickAbilityEvents#customRightClickLogic(CustomInteractionEvent)
 * {@link #getDistance()} max distance entity targeted
 * {@link #gotTarget(PlayerEntity)} check if it actually got a target, can be used but mostly used for {@link RightClickAbilityEvents}
 * {@link #healAmount()} for healing abilities, heals the entity targeted
 * {@link #addEffect()} add an effect instance to the entity targeted
 * {@link #somethingAtEntity(PlayerEntity, LivingEntity)} for every specific case for the use of this interface
 */
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
    default boolean gotTarget(PlayerEntity player)
    {
        RayTraceResult rayTraceResult = Beapi.rayTraceBlocksAndEntities(player, getDistance());
        if (!(rayTraceResult instanceof EntityRayTraceResult))
            return false;
        return ((EntityRayTraceResult) rayTraceResult).getEntity() instanceof LivingEntity;
    }
    default int getDistance()
    {
        return 0;
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
