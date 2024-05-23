package com.yuanno.soulsawakening.particles.api;

import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.particles.GenericParticleData;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class HoveringParticleEffect extends ParticleEffect {
    private int life;
    private float size;
    public HoveringParticleEffect(int life, float size)
    {
        this.life = life;
        this.size = size;
    }
    public HoveringParticleEffect(int life)
    {
        this.life = life;
        this.size = 3f;
    }
    public HoveringParticleEffect()
    {
        this.life = 2;
        this.size = 3f;
    }
    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, ParticleType particleType) {
        for (int i = 0; i < 20; i++)
        {
            double offsetX = Beapi.randomDouble() * 2;
            double offsetY = Beapi.randomDouble() * 2;
            double offsetZ = Beapi.randomDouble() * 2;

            GenericParticleData data = new GenericParticleData(particleType);
            data.setLife(life);
            data.setSize(size);
            Beapi.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 1 + offsetY, posZ + offsetZ);
        }
    }
}
