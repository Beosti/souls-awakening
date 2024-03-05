package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.ability.api.Ability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

import java.util.ArrayList;

public interface ISelfEffect {

    default void applyEffect(PlayerEntity player, Ability ability) {
        if (getEffectInstance() != null) {
            player.addEffect(getEffectInstance());
        }
        if (!getEffectInstances().isEmpty())
        {
            for (int i = 0; i < getEffectInstances().size(); i++)
            {
                player.addEffect(getEffectInstances().get(i));
            }
        }
        if (healAmount() > 0) {
            if (ability instanceof IReiatsuAbility)
                player.heal(healAmount() + ((IReiatsuAbility) ability).addedVariable(player));
            else
                player.heal(healAmount());
        }
        otherEffects(player);
    };

    default EffectInstance getEffectInstance()
    {
        return null;
    }
    default ArrayList<EffectInstance> getEffectInstances()
    {
        return null;
    }

    default void otherEffects(PlayerEntity player) {};

    default float healAmount()
    {
        return 0;
    }

}
