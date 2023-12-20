package com.yuanno.soulsawakening.abilities.hollow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEntityAbility;
import com.yuanno.soulsawakening.api.AbilityDamageSource;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entity.PlusEntity;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.hollow.BiteParticleEffect;
import com.yuanno.soulsawakening.particles.hollow.HollowRegenerationParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public class BiteAbility extends Ability implements IRightClickEntityAbility {
    public static final BiteAbility INSTANCE = new BiteAbility();
    private static final ParticleEffect PARTICLES = new BiteParticleEffect();

    public BiteAbility()
    {
        this.setName("Bite");
        this.setCooldown(5);
        this.setMaxCooldown(5);
        this.setActivationType(ActivationType.RIGHT_CLICK_ENTITY);
        this.setState(STATE.READY);
        this.setPassive(false);
    }

    @Override
    public void onRightClickEntity(LivingEntity target, PlayerEntity player)
    {
        if (target instanceof PlusEntity)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        int biteDamage = (6 + entityStats.getHollowPoints()/5);
        target.hurt(AbilityDamageSource.causeAbilityDamage(player, this), biteDamage);
        PARTICLES.spawn(target.level, target.getX(), target.getY(), target.getZ(), 0, 0, 0);
    }
}
