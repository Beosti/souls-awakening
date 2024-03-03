package com.yuanno.soulsawakening.abilities.elements.wind;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IDuringCooldownAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickEmptyAbility;
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

public class GaleForceAbility extends Ability implements IRightClickEmptyAbility, IDuringCooldownAbility {
    public static final GaleForceAbility INSTANCE = new GaleForceAbility();

    public GaleForceAbility()
    {
        this.setName("Gale Force");
        this.setCooldown(4);
        this.setMaxCooldown(4);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setCategory(Category.ZANPAKUTO);
    }

    @Override
    public void onRightClick(PlayerEntity player)
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
                    entity.hurt(AbilityDamageSource.causeAbilityDamage(user, this), 8 + (float) entityStats.getZanjutsuPoints()/2);
            });
        }    }

    public boolean canDealDamage()
    {
        return this.getCooldown() > this.getMaxCooldown() * 0.9;
    }
}
