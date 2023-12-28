package com.yuanno.soulsawakening.abilities.elements.lunar;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.init.ModResources;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.List;

public class LunarWaveAbility extends Ability implements IRightClickEmptyAbility {
    public static final LunarWaveAbility INSTANCE = new LunarWaveAbility();
    public static final ParticleEffect PARTICLES_WAVE = new WaveParticleEffect(1.4);

    public LunarWaveAbility()
    {
        this.setName("Lunar Wave");
        this.setCooldown(15);
        this.setMaxCooldown(15);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setState(STATE.READY);
        this.setPassive(false);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void onShiftRightClick(PlayerEntity player)
    {
        PARTICLES_WAVE.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0, ModParticleTypes.LUNAR.get());
        List<LivingEntity> targets = Beapi.getNearbyEntities(player.blockPosition(), player.level, 10, null, LivingEntity.class);
        targets.remove(player);
        for (LivingEntity livingEntity : targets)
        {
            livingEntity.addEffect(new EffectInstance(Effects.BLINDNESS, 120, 0));
        }
    }
}
