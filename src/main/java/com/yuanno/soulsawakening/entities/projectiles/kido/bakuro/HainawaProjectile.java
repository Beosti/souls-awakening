package com.yuanno.soulsawakening.entities.projectiles.kido.bakuro;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.init.ModEffects;
import com.yuanno.soulsawakening.projectiles.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class HainawaProjectile extends AbilityProjectileEntity {

    public HainawaProjectile(EntityType entityType, World world)
    {
        super(entityType, world);
    }

    public HainawaProjectile(World world, LivingEntity player)
    {
        super(BakudoProjectiles.HAINAWA.get(), world, player);
        this.setDamage(0);
        this.setMaxLife(64);
        this.setPhysical(false);
        this.onEntityImpactEvent = this::onEntityImpactEvent;
    }

    public void onEntityImpactEvent(LivingEntity livingEntity)
    {
        System.out.println(EntityStatsCapability.get(livingEntity).getReiatsuPoints());
        if ((EntityStatsCapability.get(livingEntity).getReiatsuPoints() / 4) * 3 > EntityStatsCapability.get((LivingEntity) this.getOwner()).getReiatsuPoints())
            return;
        System.out.println("affected");
        livingEntity.addEffect(new EffectInstance(ModEffects.HAINAWA.get(), (int) (60 + EntityStatsCapability.get((LivingEntity) this.getOwner()).getReiatsuPoints()), 0));
    }

}
