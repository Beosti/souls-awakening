package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.events.ability.api.AbilityUseEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;

public interface IContinuousAbility {

    default void duringContinuity(PlayerEntity player)
    {

    }
    default void endContinuity(PlayerEntity player, Ability ability)
    {
        endContinuity(player);
        AbilityUseEvent.Post abilityUseEventPost = new AbilityUseEvent.Post(player, ability);
        MinecraftForge.EVENT_BUS.post(abilityUseEventPost);
    }
    default boolean endContinuity(PlayerEntity player)
    {
        return true;
    }
    default void startContinuity(PlayerEntity player, Ability ability)
    {
        startContinuity(player);
    }
    default boolean startContinuity(PlayerEntity player)
    {
        return true;
    }
    default void duringContinuity(PlayerEntity player, Ability ability)
    {
        duringContinuity(player);
    }

    default boolean getEndAfterUse()
    {
        return false;
    }
}
