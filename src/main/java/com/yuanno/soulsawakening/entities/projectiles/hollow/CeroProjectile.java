package com.yuanno.soulsawakening.entities.projectiles.hollow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class CeroProjectile extends AbilityProjectileEntity {

    public CeroProjectile(EntityType entityType, World world)
    {
        super(entityType, world);
    }

    public CeroProjectile(World world, LivingEntity player)
    {
        super(HollowProjectiles.CERO.get(), world, player);
        this.setDamage(8);
        this.setMaxLife(128);
        this.setPhysical(false);
    }
}
