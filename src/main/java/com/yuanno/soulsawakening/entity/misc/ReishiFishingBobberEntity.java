package com.yuanno.soulsawakening.entity.misc;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

/**
 * Custom class for the fishing bobber in the {@link com.yuanno.soulsawakening.items.spiritweapon.ReishiRodItem}
 */
public class ReishiFishingBobberEntity extends FishingBobberEntity {
    public ReishiFishingBobberEntity(PlayerEntity player, World world, int enchantmentSpeed, int enchantmentLuck) {
        super(player, world, enchantmentSpeed, enchantmentLuck);
    }

    @Override
    protected void bringInHookedEntity()
    {
        Entity entity = this.getOwner();
        if (entity != null) {
            Vector3d vector3d = (new Vector3d(entity.getX() - this.getX(), entity.getY() - this.getY(), entity.getZ() - this.getZ())).scale(0.2);
            this.getHookedIn().setDeltaMovement(this.getHookedIn().getDeltaMovement().add(vector3d));
        }
        if (this.getHookedIn() instanceof LivingEntity && this.getOwner() instanceof LivingEntity)
        {
            LivingEntity hookedInEntity = (LivingEntity) this.getHookedIn();
            LivingEntity hookingEntity = (LivingEntity) this.getOwner();
            IEntityStats entityStats = EntityStatsCapability.get(hookingEntity);
            hookedInEntity.hurt(DamageSource.MAGIC, (float) (entityStats.getReiatsuPoints() / 4));
        }
    }
}
