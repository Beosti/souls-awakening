package com.yuanno.soulsawakening.entities.projectiles.water;

import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class TidalWaveProjectile extends AbilityProjectileEntity {
    private int knockback = 2;

    public TidalWaveProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public TidalWaveProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.TIDAL_WAVE.get(), world, player);
        this.setDamage(8);
        this.setMaxLife(64);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;

    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        Vector3d vector3d = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)this.knockback * 0.6D);
        if (vector3d.lengthSqr() > 0.0D) {
            entity.push(vector3d.x, 0.1D, vector3d.z);
        }
    }

}
