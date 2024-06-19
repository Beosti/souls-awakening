package com.yuanno.soulsawakening.entities.projectiles.water;

import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WaterPressureProjectile extends AbilityProjectileEntity {


    public WaterPressureProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public WaterPressureProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.WATER_PRESSURE.get(), world, player);
        this.setDamage(14);
        this.setMaxLife(128);
        this.setPhysical(false);

    }
}
