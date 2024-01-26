package com.yuanno.soulsawakening.entities.projectiles.kido;

import com.yuanno.soulsawakening.entities.projectiles.hollow.HollowProjectiles;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.hollow.CeroProjectileParticleEffect;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ByakuraiProjectile extends AbilityProjectileEntity {


    public ByakuraiProjectile(EntityType entityType, World world)
    {
        super(entityType, world);
    }

    public ByakuraiProjectile(World world, LivingEntity player)
    {
        super(HadoProjectiles.BYAKURAI.get(), world, player);
        this.setDamage(8);
        this.setMaxLife(128);
        this.setPhysical(false);
    }


}
