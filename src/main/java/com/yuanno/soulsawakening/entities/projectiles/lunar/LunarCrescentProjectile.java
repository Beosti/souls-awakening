package com.yuanno.soulsawakening.entities.projectiles.lunar;

import com.yuanno.soulsawakening.entities.projectiles.fire.FireProjectiles;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class LunarCrescentProjectile extends AbilityProjectileEntity {


    public LunarCrescentProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public LunarCrescentProjectile(World world, LivingEntity player)
    {
        super(LunarProjectiles.LUNAR_CRESCENT.get(), world, player);
        this.setDamage(8);
        this.setMaxLife(64);
        this.setPhysical(false);

    }

}
