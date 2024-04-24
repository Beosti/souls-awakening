package com.yuanno.soulsawakening.entities.projectiles.quincy;

import com.yuanno.soulsawakening.events.projectiles.AbilityProjectileHurtEvent;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BigReishiArrow extends AbilityProjectileEntity {

    public BigReishiArrow(EntityType entityType, World world)
    {
        super(entityType, world);
    }

    public BigReishiArrow(World world, LivingEntity player)
    {
        super(QuincyProjectiles.BIG_REISHI_ARROW.get(), world, player);
        this.setDamage(8);
        this.setMaxLife(64);
        this.setKnockbackStrength(0);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onHit;
    }

    public void onHit(LivingEntity hit)
    {
        hit.knockback(0.1f, hit.xRot, hit.yRot);
    }

    public boolean isPickable() {
        return true;
    }
    @Override
    protected boolean canHitEntity(Entity entity)
    {
        return true;
    }
    @Override
    public boolean hurt(DamageSource damageSource, float amount)
    {
        AbilityProjectileHurtEvent event = new AbilityProjectileHurtEvent(this, damageSource.getEntity());
        MinecraftForge.EVENT_BUS.post(event);
        return true;
    }
}
