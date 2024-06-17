package com.yuanno.soulsawakening.api.ability.interfaces;

import com.yuanno.soulsawakening.api.ability.Ability;
import net.minecraft.entity.player.PlayerEntity;

public interface IOnDeath {

    default void onDeathAbility(PlayerEntity player, Ability ability)
    {

    }

    default void onDeath(PlayerEntity player)
    {

    }

    default boolean getCancelDeath()
    {
        return false;
    }
}
