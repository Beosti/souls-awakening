package com.yuanno.soulsawakening.ability.api.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public interface ISelfEffect {

    default void applyEffect(PlayerEntity player) {
        if (getEffectInstance() != null) {
            player.addEffect(getEffectInstance());
        }
        otherEffects(player);
    };

    default EffectInstance getEffectInstance()
    {
        return null;
    }

    default void otherEffects(PlayerEntity player) {};
}
