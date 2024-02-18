package com.yuanno.soulsawakening.entities.projectiles.kido;

import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class ShakkahoProjectile extends AbilityProjectileEntity {

    public ShakkahoProjectile(EntityType entityType, World world)
    {
        super(entityType, world);
    }

    public ShakkahoProjectile(World world, LivingEntity player)
    {
        super(HadoProjectiles.SHAKKAHO.get(), world, player);
        this.setDamage(4);
        this.setMaxLife(32);
        this.setPhysical(false);
    }
}
