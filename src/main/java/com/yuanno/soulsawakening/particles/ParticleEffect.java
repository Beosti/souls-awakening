package com.yuanno.soulsawakening.particles;

import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;

import java.io.Serializable;

public abstract class ParticleEffect implements Serializable
{
	// Implementing only the first method
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
		// Custom implementation for the first method
	}

	// Implementing only the second method
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, ParticleType particleType) {
		// Custom implementation for the second method
	}

}