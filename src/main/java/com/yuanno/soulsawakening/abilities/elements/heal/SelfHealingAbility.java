package com.yuanno.soulsawakening.abilities.elements.heal;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IParticleEffect;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.ISelfEffect;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.HoveringParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class SelfHealingAbility extends Ability implements IRightClickAbility, ISelfEffect, IParticleEffect {
    public static final SelfHealingAbility INSTANCE = new SelfHealingAbility();
    public static final ParticleEffect PARTICLES_HOVER = new HoveringParticleEffect(3, 4);

    public SelfHealingAbility()
    {
        this.setName("Self Healing");
        this.setCooldown(10);
        this.setMaxCooldown(10);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public EffectInstance getEffectInstance()
    {
        return new EffectInstance(Effects.ABSORPTION, 120, 1);
    }

    @Override
    public ParticleEffect getSpawnParticles()
    {
        return PARTICLES_HOVER;
    }

    @Override
    public ParticleType getParticleType()
    {
        return ModParticleTypes.HEALING.get();
    }

    @Override
    public void otherEffects(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        float missingHealth = player.getMaxHealth() - player.getHealth();
        player.heal(missingHealth / 2 + 4 + (float) entityStats.getReiatsuPoints()/4);
    }

}
