package com.yuanno.soulsawakening.entities.projectiles.water;

import com.yuanno.soulsawakening.entities.projectiles.lunar.LunarProjectiles;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class WaterSlashProjectile extends AbilityProjectileEntity {


    public WaterSlashProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public WaterSlashProjectile(World world, LivingEntity player)
    {
        super(WaterProjectiles.WATER_SLASH.get(), world, player);
        this.setDamage(9);
        this.setMaxLife(64);
        this.setPhysical(false);

    }

}
