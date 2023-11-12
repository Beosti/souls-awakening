package com.yuanno.soulsawakening.entities.projectiles.fire;

import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class FireBallProjectile extends AbilityProjectileEntity {


    public FireBallProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public FireBallProjectile(World world, LivingEntity player)
    {
        super(FireProjectiles.FIREBALL.get(), world, player);
        this.setDamage(7);
        this.setMaxLife(64);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;

    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        entity.setSecondsOnFire(5);
    }
}
