package com.yuanno.soulsawakening.entities.projectiles.hollow;

import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.hollow.CeroProjectileParticleEffect;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CeroProjectile extends AbilityProjectileEntity {

    private static final ParticleEffect PARTICLES = new CeroProjectileParticleEffect();

    public CeroProjectile(EntityType entityType, World world)
    {
        super(entityType, world);
    }

    public CeroProjectile(World world, LivingEntity player)
    {
        super(HollowProjectiles.CERO.get(), world, player);
        this.setDamage(16);
        this.setMaxLife(128);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
        this.onBlockImpactEvent = this::onBlockImpactEvent;
    }
    //TODO add explosion
    private void onEntityImpactEvent(LivingEntity entity)
    {
        if (!entity.level.isClientSide)
            PARTICLES.spawn(entity.level, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
    }

    private void onBlockImpactEvent(BlockPos blockPos)
    {
        if (this.getOwner() != null)
            if (!this.getOwner().level.isClientSide)
                PARTICLES.spawn(this.getOwner().level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0, 0, 0);
    }

}
