package com.yuanno.soulsawakening.entity.misc;

import com.yuanno.soulsawakening.abilities.AbilityHelper;
import com.yuanno.soulsawakening.abilities.quincy.rod.ExplodingBobberAbility;
import com.yuanno.soulsawakening.abilities.quincy.rod.WeakeningBobberAbility;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.ExplosionAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.particles.CommonExplosionParticleEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
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

    @Override
    protected void onHitEntity(EntityRayTraceResult hitEntity) {
        super.onHitEntity(hitEntity);
        if (!this.level.isClientSide) {
            if (hitEntity.getEntity() instanceof LivingEntity)
            {
               LivingEntity livingEntity = (LivingEntity) hitEntity.getEntity();
                {
                    if (this.getOwner() instanceof PlayerEntity)
                    {
                        PlayerEntity player = (PlayerEntity) this.getOwner();
                        IAbilityData abilityData = AbilityDataCapability.get(player);
                        for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++) {
                            if (!(abilityData.getUnlockedAbilities().get(i) instanceof ExplodingBobberAbility) && !(abilityData.getUnlockedAbilities().get(i) instanceof WeakeningBobberAbility))
                                continue;
                            Ability ability = abilityData.getUnlockedAbilities().get(i);
                            if (!ability.getState().equals(Ability.STATE.CONTINUOUS))
                                continue;
                            else {
                                if (!this.level.isClientSide) {
                                    if (ability instanceof ExplodingBobberAbility)
                                    {
                                        ExplosionAbility explosion = new ExplosionAbility(this.getOwner(), this.level, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 3);
                                        explosion.setStaticDamage(5);
                                        explosion.setExplosionSound(true);
                                        explosion.setDamageOwner(false);
                                        explosion.setDestroyBlocks(true);
                                        explosion.setFireAfterExplosion(false);
                                        explosion.setSmokeParticles(new CommonExplosionParticleEffect(3));
                                        explosion.setDamageEntities(true);
                                        explosion.doExplosion();
                                    }
                                    else if (ability instanceof WeakeningBobberAbility)
                                    {
                                        livingEntity.addEffect(new EffectInstance(Effects.WEAKNESS, 240, 2));
                                        livingEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 240, 2));
                                    }
                                    ((IContinuousAbility) ability).endContinuity(player, ability);

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected void onHitBlock(BlockRayTraceResult p_230299_1_) {
        super.onHitBlock(p_230299_1_);
        this.setDeltaMovement(this.getDeltaMovement().normalize().scale(p_230299_1_.distanceTo(this)));
        if (this.getOwner() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) this.getOwner();
            IAbilityData abilityData = AbilityDataCapability.get(player);
            for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++) {
                if (!(abilityData.getUnlockedAbilities().get(i) instanceof ExplodingBobberAbility) && !(abilityData.getUnlockedAbilities().get(i) instanceof WeakeningBobberAbility))
                    continue;
                Ability ability = abilityData.getUnlockedAbilities().get(i);
                if (!ability.getState().equals(Ability.STATE.CONTINUOUS)) {
                    continue;
                }
                else {
                    if (!this.level.isClientSide) {
                        if (ability instanceof ExplodingBobberAbility)
                        {
                            ExplosionAbility explosion = AbilityHelper.newExplosion(this.getOwner(), this.getOwner().level, p_230299_1_.getBlockPos().getX(), p_230299_1_.getBlockPos().getY(), p_230299_1_.getBlockPos().getZ(), 3);
                            explosion.setStaticDamage(5);
                            explosion.setExplosionSound(true);
                            explosion.setDamageOwner(false);
                            explosion.setDestroyBlocks(true);
                            explosion.setFireAfterExplosion(false);
                            explosion.setSmokeParticles(new CommonExplosionParticleEffect(3));
                            explosion.setDamageEntities(true);
                            explosion.doExplosion();
                        }
                        ((IContinuousAbility) ability).endContinuity(player, ability);
                    }
                }
            }
        }
    }
}
