package com.yuanno.soulsawakening.api.ability.interfaces;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.ExplosionAbility;
import com.yuanno.soulsawakening.particles.CommonExplosionParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

/**
 * Used for abilities that do something when hitting an enemy, handled in {@link #activate(PlayerEntity, LivingEntity)} that is triggered
 * @see AttackEntityEvent
 * {@link #secondsOnFire()} if you want to put the entity targeted on fire
 * {@link #addedEffect()} if you want to add an effect to the entity targeted
 */
public interface IAttackAbility {

    default void activateBack(PlayerEntity player, LivingEntity target, Ability ability)
    {
        activate(player, target);
    }
    default void activate(PlayerEntity player, LivingEntity target) {
        if (secondsOnFire() > 0)
            target.setSecondsOnFire(secondsOnFire());
        if (addedEffect() != null && !target.hasEffect(addedEffect().getEffect()))
            target.addEffect(addedEffect());
        if (getExplosion())
        {
            if (!player.level.isClientSide)
            {
                ExplosionAbility explosionAbility = new ExplosionAbility(player, player.level, target.getX(), target.getY(), target.getZ(), 3);
                explosionAbility.setStaticDamage(5);
                explosionAbility.setExplosionSound(true);
                explosionAbility.setDamageOwner(false);
                explosionAbility.setDestroyBlocks(false);
                explosionAbility.setFireAfterExplosion(false);
                explosionAbility.setSmokeParticles(new CommonExplosionParticleEffect(3));
                explosionAbility.setDamageEntities(true);
                explosionAbility.doExplosion();
            }
        }
    };

    default int secondsOnFire()
    {
        return 0;
    }

    default EffectInstance addedEffect()
    {
        return null;
    }

    default boolean getPassive()
    {
        return true;
    }

    default boolean getExplosion()
    {
        return false;
    }
}
