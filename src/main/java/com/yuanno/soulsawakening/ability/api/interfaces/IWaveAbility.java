package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.api.Beapi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

import java.util.List;

public interface IWaveAbility {

    default void onWave(PlayerEntity player)
    {
        List<LivingEntity> targets = Beapi.getNearbyEntities(player.blockPosition(), player.level, getRadius(), null, LivingEntity.class);
        targets.remove(player);
        for (LivingEntity livingEntity : targets)
        {
            applyEffect(livingEntity);
            livingEntity.setSecondsOnFire(putOnFire());
            if (getDamageSource() != null)
                livingEntity.hurt(getDamageSource(), getDamage());
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
    default float getDamage()
    {
        return 0;
    }
    default void applyEffect(LivingEntity target) {};


}
