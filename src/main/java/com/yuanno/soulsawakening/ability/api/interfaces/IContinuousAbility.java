package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.ability.api.Ability;
import net.minecraft.entity.player.PlayerEntity;

public interface IContinuousAbility {

    default void startContinuity(PlayerEntity player, Ability ability)
    {
        startContinuity(player);
    }
    default boolean startContinuity(PlayerEntity player)
    {
        return false;
    }
    default void duringContinuity(PlayerEntity player, Ability ability)
    {
        duringContinuity(player);
    }
    default void duringContinuity(PlayerEntity player)
    {

    }
    default void endContinuity(PlayerEntity player, Ability ability)
    {
        endContinuity(player);
    }
    default boolean endContinuity(PlayerEntity player)
    {
        return false;
    }
}
