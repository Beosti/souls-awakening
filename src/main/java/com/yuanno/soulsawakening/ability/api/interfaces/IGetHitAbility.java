package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.ability.api.Ability;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IGetHitAbility {

    default void getHitAbility(PlayerEntity player, LivingEntity attacker, Ability ability)
    {
        getHit(player, attacker);
    }

    default void getHit(PlayerEntity player, LivingEntity attacker)
    {

    }

    default boolean getCancelEvent()
    {
        return false;
    }
}
