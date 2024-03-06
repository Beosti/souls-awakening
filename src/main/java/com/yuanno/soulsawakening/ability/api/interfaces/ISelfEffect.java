package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.events.ability.CustomInteractionEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

import java.util.ArrayList;

/**
 * Used for abilities that do something to oneself.
 * All the logic is handled in {@link #applyEffect(PlayerEntity, Ability)}, triggered here:
 * @see com.yuanno.soulsawakening.events.ability.AbilityEvents#customRightClickLogic(CustomInteractionEvent)
 * {@link #getEffectInstance()} an effectinstance that'll be added to the player when used
 * {@link #getEffectInstances()} if there are multiple effect instances they are done here
 * {@link #otherEffects(PlayerEntity)} for other very specific things you want to make happen when using the ability
 * {@link #healAmount()} amount the user gets healed if using
 */
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
        return new ArrayList<>();
    }

    default void otherEffects(PlayerEntity player) {};

    default float healAmount()
    {
        return 0;
    }

}
