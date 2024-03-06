package com.yuanno.soulsawakening.ability.api.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * Used for abilities that are constantly active and that constantly do something, it does so every second instead of every tick
 * Handled in {@link #onContinuousAbility(PlayerEntity)}, triggered here:
 * @see com.yuanno.soulsawakening.events.TickAbilityEvent#onTickEventAbility(LivingEvent.LivingUpdateEvent)
 * {@link #onContinuousAbility(PlayerEntity)} all the logic aka what happens every second is handled here
 */
public interface IPassiveAbility {

    default void onContinuousAbility(PlayerEntity user) {};
}
