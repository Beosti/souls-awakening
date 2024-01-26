package com.yuanno.soulsawakening.entities.projectiles.kido;

import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class ShoProjectile extends AbilityProjectileEntity {

    public ShoProjectile(EntityType entityType, World world)
    {
        super(entityType, world);
    }

    public ShoProjectile(World world, LivingEntity player)
    {
        super(HadoProjectiles.SHO.get(), world, player);
        this.setDamage(0);
        this.setMaxLife(32);
        this.setPhysical(false);
        this.setKnockbackStrength(3);
    }
}
