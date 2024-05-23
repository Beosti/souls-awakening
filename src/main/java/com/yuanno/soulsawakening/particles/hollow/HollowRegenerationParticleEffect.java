package com.yuanno.soulsawakening.particles.hollow;

import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.GenericParticleData;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class HollowRegenerationParticleEffect extends ParticleEffect {
    private static final int NUM_PARTICLES = 30;
    private static final double PARTICLE_SPREAD = 0.5;
    private static final double OFFSET = 1.0;
    private static final float SIZE = 0.35f;

    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        for (int i = 0; i < NUM_PARTICLES; i++) {
            double offsetX = (Math.random() - 0.6) * PARTICLE_SPREAD;
            double offsetY = (Math.random() - 0.6) * PARTICLE_SPREAD;
            double offsetZ = (Math.random() - 0.6) * PARTICLE_SPREAD;
            double particlePosX = posX - OFFSET * motionX;
            double particlePosY = posY - OFFSET * motionY;
            double particlePosZ = posZ - OFFSET * motionZ;

            GenericParticleData healParticle = new GenericParticleData(ModParticleTypes.HOLLOW.get());
            healParticle.setLife(6);
            healParticle.setSize(SIZE);
            healParticle.setMotion(0, 0.1, 0);

            Beapi.spawnParticles(healParticle, (ServerWorld) world, particlePosX + offsetX, particlePosY + offsetY, particlePosZ + offsetZ);
        }
    }

}
