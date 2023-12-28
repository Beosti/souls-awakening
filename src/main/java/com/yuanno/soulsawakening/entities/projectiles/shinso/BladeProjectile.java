package com.yuanno.soulsawakening.entities.projectiles.shinso;

import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class BladeProjectile extends AbilityProjectileEntity {

    public BladeProjectile(EntityType entityType, World world)
    {
        super(entityType, world);
    }

    public BladeProjectile(World world, LivingEntity player)
    {
        super(ShinsoProjectiles.BLADE.get(), world, player);
        this.setDamage(18);
        this.setMaxLife(100);
        this.setPhysical(true);
    }
}
