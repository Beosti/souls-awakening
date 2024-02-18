package com.yuanno.soulsawakening.entities.projectiles.kido;

import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class ShakkahoIncantationProjectile extends AbilityProjectileEntity {

    public ShakkahoIncantationProjectile(EntityType entityType, World world)
    {
        super(entityType, world);
    }

    public ShakkahoIncantationProjectile(World world, LivingEntity player)
    {
        super(HadoProjectiles.SHAKKAHO_INCANTATION.get(), world, player);
        this.setDamage(20);
        this.setMaxLife(128);
        this.setPhysical(false);
    }
}
