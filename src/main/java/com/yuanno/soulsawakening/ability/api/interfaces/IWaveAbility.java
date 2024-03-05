package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.Beapi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;

import java.util.ArrayList;
import java.util.List;

public interface IWaveAbility {

    default void onWave(PlayerEntity player, Ability ability)
    {
        List<LivingEntity> targets = Beapi.getNearbyEntities(player.blockPosition(), player.level, getRadius(), null, LivingEntity.class);
        targets.remove(player);
        for (LivingEntity livingEntity : targets)
        {
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
        return null;
    }
    default float healAmount()
    {
        return 0;
    }
}
