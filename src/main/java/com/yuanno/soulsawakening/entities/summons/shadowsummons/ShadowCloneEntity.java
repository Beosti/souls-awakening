package com.yuanno.soulsawakening.entities.summons.shadowsummons;

import com.yuanno.soulsawakening.entity.CloneEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ShadowCloneEntity extends CloneEntity {

    public ShadowCloneEntity(EntityType type, World world)
    {
        super(type, world);
    }

    public ShadowCloneEntity(World world)
    {
        super(ShadowSummons.SHADOW_CLONE.get(), world);
    }

    @Override
    public boolean hurt(DamageSource damageSource, float damageValue)
    {
        if(damageSource.getEntity() != null && damageSource.getEntity() instanceof PlayerEntity && damageSource.getEntity() == this.getOwner())
            return false;

        return super.hurt(damageSource, damageValue);
    }

}
