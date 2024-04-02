package com.yuanno.soulsawakening.particles.api;

import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.particles.GenericParticleData;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BindParticleEffect extends ParticleEffect {
    private int life;
    private float size;
    private float velocity;
    private float radius;

    public BindParticleEffect(int life, float size, float velocity, float radius)
    {
        this.life = life;
        this.size = size;
        this.velocity = velocity;
        this.radius = radius;
    }

    public BindParticleEffect(int life)
    {
        this.life = life;
        this.size = 3f;
    }

    public BindParticleEffect()
    {
        this.life = 2;
        this.size = 3f;
        this.velocity = 0;
        this.radius = 1;
    }

    @Override
    public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, ParticleType particleType)
    {
        for (int i = 0; i < 20; i++)
        {
            double radiusParticle = this.radius; // adjus the radius, from distance
            double angle = world.getRandom().nextDouble() * Math.PI * 2; // random angle in radians

            double velocity = this.velocity; // Adjust the velocity as needed
            double motionAngle = angle + Math.PI / 2; // Perpendicular angle for circular motion

            // Calculate offsets using trigonometry
            double offsetX = radiusParticle * Math.cos(angle);
            double offsetY = Beapi.randomDouble();
            double offsetZ = radiusParticle * Math.sin(angle);

            double particleMotionX = velocity * Math.cos(motionAngle);
            double particleMotionY = motionY; // Keep the same Y motion
            double particleMotionZ = velocity * Math.sin(motionAngle);

            GenericParticleData data = new GenericParticleData(particleType);
            data.setLife(this.life);
            data.setSize(this.size);
            data.setMotion(particleMotionX, particleMotionY, particleMotionZ);
            Beapi.spawnParticles(data, (ServerWorld) world, posX + offsetX, posY + 0.3 + offsetY, posZ + offsetZ);
        }
    }
}
