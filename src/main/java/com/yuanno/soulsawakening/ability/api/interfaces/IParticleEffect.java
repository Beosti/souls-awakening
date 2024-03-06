package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.events.ability.CustomInteractionEvent;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

/**
 * Used for particles with abilities, use two different types to handle it:
 * {@link #spawnParticles(PlayerEntity)} for particles spawning from the player
 * {@link #spawnParticles(LivingEntity)} for particles spawning from an other entity
 * use 2 methods there so an ability can have particles spawn around the player and the target
 * it is triggered at 2 different places:
 * @see com.yuanno.soulsawakening.events.ability.AbilityEvents#customRightClickLogic(CustomInteractionEvent)
 * @see com.yuanno.soulsawakening.events.ability.AbilityEvents#onAttackEvent(AttackEntityEvent)
 * for the attacking and right clicking abilities
 * {@link #getSpawnParticles()} for the effect, what it does like {@link com.yuanno.soulsawakening.particles.api.WaveParticleEffect}
 * {@link #getParticleType()} for the texture of the particle like {@link com.yuanno.soulsawakening.init.ModParticleTypes}
 */
public interface IParticleEffect {

    default void spawnParticles(PlayerEntity player) {
        getSpawnParticles().spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0,0, getParticleType());
    }
    default void spawnParticles(LivingEntity livingEntity) {
        getSpawnParticles().spawn(livingEntity.level, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 0, 0,0, getParticleType());

    }
    default ParticleEffect getSpawnParticles() {
        return null;
    };
    default ParticleType getParticleType() {
        return null;
    }
}
