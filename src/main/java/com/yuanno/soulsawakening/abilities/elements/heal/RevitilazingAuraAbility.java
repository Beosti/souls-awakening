package com.yuanno.soulsawakening.abilities.elements.heal;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.List;

public class RevitilazingAuraAbility extends Ability implements IRightClickEmptyAbility {
    public static final RevitilazingAuraAbility INSTANCE = new RevitilazingAuraAbility();
    public static final ParticleEffect PARTICLES_WAVE = new WaveParticleEffect(1.4);

    public RevitilazingAuraAbility()
    {
        this.setName("Revitilazing Aura");
        this.setCooldown(20);
        this.setMaxCooldown(20);
        this.setPassive(false);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setCategory(Category.ZANPAKUTO);
    }

    @Override
    public void onShiftRightClick(PlayerEntity user)
    {
        IEntityStats entityStats = EntityStatsCapability.get(user);
        PARTICLES_WAVE.spawn(user.level, user.getX(), user.getY(), user.getZ(), 0, 0, 0, ModParticleTypes.HEALING.get());
        List<LivingEntity> targets = Beapi.getNearbyEntities(user.blockPosition(), user.level, 10, null, LivingEntity.class);
        for (LivingEntity livingEntity : targets)
        {
            if (!livingEntity.hasEffect(Effects.REGENERATION))
                livingEntity.addEffect(new EffectInstance(Effects.REGENERATION, 240, 2+(int) (1 + Math.floor(entityStats.getReiatsuPoints()/4))));

        }

    }
}
