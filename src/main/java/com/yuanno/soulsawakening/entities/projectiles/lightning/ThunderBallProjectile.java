package com.yuanno.soulsawakening.entities.projectiles.lightning;

import com.yuanno.soulsawakening.init.ModEffects;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class ThunderBallProjectile extends AbilityProjectileEntity {

    public ThunderBallProjectile(EntityType entityType, World world)
    {
        super(entityType, world);
    }

    public ThunderBallProjectile(World world, LivingEntity player)
    {
        super(LightningProjectiles.THUNDERBALL.get(), world, player);
        this.setDamage(6);
        this.setMaxLife(64);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        entity.addEffect(new EffectInstance(ModEffects.ELECTROCUTED.get(), 100, 0));
    }
}
