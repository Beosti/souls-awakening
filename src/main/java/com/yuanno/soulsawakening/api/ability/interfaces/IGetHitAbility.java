package com.yuanno.soulsawakening.api.ability.interfaces;

import com.yuanno.soulsawakening.api.ability.Ability;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IGetHitAbility {

    default void getHitAbility(PlayerEntity player, LivingEntity attacker, Ability ability, float damage)
    {
        getHit(player, attacker, damage);
    }

    default void getHitAbility(PlayerEntity player, LivingEntity attacker, Ability ability)
    {
        getHit(player, attacker);
    }

    default void getHit(PlayerEntity player, LivingEntity attacker, float damage)
    {

    }

    default void getHit(PlayerEntity player, LivingEntity attacker)
    {

    }

    default boolean getCancelEvent()
    {
        return false;
    }
}
