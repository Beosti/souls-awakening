package com.yuanno.soulsawakening.api.ability.interfaces;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.events.ability.api.AbilityUseEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;

/**
 * Abilities that are constantly ticking, doing something you got 3 methods that really handle it:
 * {@link #startContinuity(PlayerEntity)} that does something when it starts
 * {@link #duringContinuity(PlayerEntity)} that does something when it's going on
 * {@link #endContinuity(PlayerEntity)} that does something when it's over
 *  The rest is mostly boiler plate
 */
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
        AbilityUseEvent.Pre abilityUseEventPre = new AbilityUseEvent.Pre(player, ability);
        MinecraftForge.EVENT_BUS.post(abilityUseEventPre);

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
    default int getMaxTimer()
    {
        return -1;
    }
}
