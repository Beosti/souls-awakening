package com.yuanno.soulsawakening.entities.projectiles.wind;

import com.yuanno.soulsawakening.entities.projectiles.water.WaterProjectiles;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WindSlashProjectile extends AbilityProjectileEntity {


    public WindSlashProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public WindSlashProjectile(World world, LivingEntity player)
    {
        super(WindProjectiles.WIND_SLASH.get(), world, player);
        this.setDamage(8);
        this.setMaxLife(64);
        this.setPhysical(false);

    }

}
