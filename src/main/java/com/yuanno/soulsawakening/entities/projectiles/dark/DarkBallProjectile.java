package com.yuanno.soulsawakening.entities.projectiles.dark;

import com.yuanno.soulsawakening.entities.projectiles.fire.FireProjectiles;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class DarkBallProjectile extends AbilityProjectileEntity {


    public DarkBallProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public DarkBallProjectile(World world, LivingEntity player)
    {
        super(DarkProjectiles.DARKBALL.get(), world, player);
        this.setDamage(6);
        this.setMaxLife(64);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;

    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        entity.addEffect(new EffectInstance(Effects.WEAKNESS, 120, 0));
    }
}
