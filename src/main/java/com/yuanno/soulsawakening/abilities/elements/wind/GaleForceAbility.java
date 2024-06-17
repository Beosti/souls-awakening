package com.yuanno.soulsawakening.abilities.elements.wind;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.api.AbilityDamageSource;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class GaleForceAbility extends Ability implements IRightClickAbility, IDuringCooldownAbility, ISelfEffect {
    public static final GaleForceAbility INSTANCE = new GaleForceAbility();

    public GaleForceAbility()
    {
        this.setName("Gale Force");
        this.setMaxCooldown(4);
        this.setSubCategory(SubCategory.SHIKAI);
        this.dependency = AbilityDependencies::shikaiDependance;
    }

    @Override
    public void otherEffects(PlayerEntity player)
    {
        Vector3d speed = Beapi.propulsion(player, 3, 3);
        player.setDeltaMovement(speed.x, 0.3, speed.z);
        player.hurtMarked = true;
        ((ServerWorld) player.level).getChunkSource().broadcastAndSend(player, new SAnimateHandPacket(player, 0));
    }

    @Override
    public void onCooldown(PlayerEntity user)
    {
        IEntityStats entityStats = EntityStatsCapability.get(user);
        if (this.canDealDamage())
        {
            List<LivingEntity> targets = Beapi.getNearbyEntities(user.blockPosition(), user.level, 5, null, LivingEntity.class);
            targets.remove(user);

            targets.forEach(entity ->
            {
                if(user.canSee(entity))
                    entity.hurt(AbilityDamageSource.causeAbilityDamage(user, this), 8 + (float) entityStats.getShinigamiStats().getZanjutsuPoints()/2);
            });
        }    }

    public boolean canDealDamage()
    {
        return this.getCooldown() > this.getMaxCooldown() * 0.9;
    }
}
