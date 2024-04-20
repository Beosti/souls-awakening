package com.yuanno.soulsawakening.entities.projectiles.quincy;

import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class ReishiArrow extends AbilityProjectileEntity {

    public ReishiArrow(EntityType entityType, World world)
    {
        super(entityType, world);
    }

    public ReishiArrow(World world, LivingEntity player)
    {
        super(QuincyProjectiles.REISHI_ARROW.get(), world, player);
        this.setDamage(4);
        this.setMaxLife(32);
        this.setPhysical(false);
    }
}
