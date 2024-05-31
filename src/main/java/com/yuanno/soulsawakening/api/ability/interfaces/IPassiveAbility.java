package com.yuanno.soulsawakening.api.ability.interfaces;

import com.yuanno.soulsawakening.events.ability.TickAbilityEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * Used for abilities that are constantly active and that constantly do something, it does so every second instead of every tick
 * Handled in {@link #onContinuousAbility(PlayerEntity)}, triggered here:
 * @see TickAbilityEvent#onTickEventAbility(LivingEvent.LivingUpdateEvent)
 * {@link #onContinuousAbility(PlayerEntity)} all the logic aka what happens every second is handled here
 */
public interface IPassiveAbility {

    default void onContinuousAbility(PlayerEntity user) {};
}
