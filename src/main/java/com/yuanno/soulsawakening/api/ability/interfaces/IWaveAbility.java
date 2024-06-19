package com.yuanno.soulsawakening.api.ability.interfaces;

import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.events.api.CustomInteractionEvent;
import com.yuanno.soulsawakening.events.ability.RightClickAbilityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Used for abilities that have an effect on entities in a radius.
 * Logic handled in {@link #onWave(PlayerEntity, Ability)}, triggered here:
 * @see RightClickAbilityEvents#customRightClickLogic(CustomInteractionEvent)
 * {@link #getRadius()} gets the radius of the entities around the player, defaults 0
 * {@link #putOnFire()} gets amount of seconds put on fire, defaults 0
 * {@link #getDamageSource()} sets the damage source if there's damage, defaults null
 * {@link #getBaseDamage()} sets the damage for the ability, defaults 0
 * {@link #applyEffect()} puts one effect on the entities in radius, defaults null
 * {@link #getEffectInstances()} puts multiple effects to entities in radius, defaults empty
 * {@link #healAmount()} heal amount of the entities in radius, default 0
 */
public interface IWaveAbility {

    default void onWave(PlayerEntity player, Ability ability)
    {
        List<LivingEntity> targets = Beapi.getNearbyEntities(player.blockPosition(), player.level, getRadius(), null, LivingEntity.class);
        targets.remove(player);
        for (LivingEntity livingEntity : targets)
        {
            doOnWave(livingEntity);
            if (applyEffect() != null)
                livingEntity.addEffect(applyEffect());
            if (!getEffectInstances().isEmpty())
            {
                for (int i = 0; i < getEffectInstances().size(); i++)
                {
                    livingEntity.addEffect(getEffectInstances().get(i));
                }
            }
            livingEntity.setSecondsOnFire(putOnFire());
            if (getDamageSource() != null) {
                if (ability instanceof IReiatsuAbility)
                    livingEntity.hurt(getDamageSource(), getBaseDamage() + ((IReiatsuAbility) ability).addedVariable(player));
                else
                    livingEntity.hurt(getDamageSource(), getBaseDamage());
            }
            if (healAmount() > 0)
            {
                if (ability instanceof IReiatsuAbility)
                    livingEntity.heal(healAmount() + ((IReiatsuAbility) ability).addedVariable(player));
                else
                    livingEntity.heal(healAmount());
            }
        }
    }

    default void doOnWave(LivingEntity target) {}
    default int getRadius()
    {
        return 0;
    }
    default int putOnFire()
    {
        return 0;
    };
    default DamageSource getDamageSource()
    {
        return null;
    }
    default float getBaseDamage()
    {
        return 0;
    }
    default EffectInstance applyEffect() {
        return null;
    };
    default ArrayList<EffectInstance> getEffectInstances()
    {
        return new ArrayList<>();
    }
    default float healAmount()
    {
        return 0;
    }
}
