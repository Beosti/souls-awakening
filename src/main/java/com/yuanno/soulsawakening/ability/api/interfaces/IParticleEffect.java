package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.particles.ParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleType;

public interface IParticleEffect {

    default void spawnParticles(PlayerEntity player) {
        getSpawnParticles().spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0,0, getParticleType());

    }
    default ParticleEffect getSpawnParticles() {
        return null;
    };
    default ParticleType getParticleType() {
        return null;
    }
}
