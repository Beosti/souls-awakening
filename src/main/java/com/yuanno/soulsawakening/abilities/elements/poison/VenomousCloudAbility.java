package com.yuanno.soulsawakening.abilities.elements.poison;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;

import java.util.List;

public class VenomousCloudAbility extends Ability implements IRightClickEmptyAbility {

    public static final VenomousCloudAbility INSTANCE = new VenomousCloudAbility();
    private final static DamageSource POISON_DAMAGE = new ModDamageSource("venomous_cloud").setSourceTypes(SourceType.SHOCKWAVE).setSourceElement(SourceElement.POISON);
    public static final ParticleEffect PARTICLES_POISON = new WaveParticleEffect(1.2);

    public VenomousCloudAbility()
    {
        this.setName("Venomous Cloud");
        this.setCooldown(15);
        this.setMaxCooldown(15);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setState(STATE.READY);
        this.setPassive(false);
        this.setZanpakutoState(ModValues.STATE.SHIKAI);
    }

    @Override
    public void onShiftRightClick(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);

        PARTICLES_POISON.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0, ModParticleTypes.POISON.get());
        List<LivingEntity> targets = Beapi.getNearbyEntities(player.blockPosition(), player.level, 10, null, LivingEntity.class);
        targets.remove(player);
        for (LivingEntity livingEntity : targets)
        {
            if (!livingEntity.hasEffect(Effects.POISON))
                livingEntity.addEffect(new EffectInstance(Effects.POISON, 120, (int) (1 + Math.floor(entityStats.getReiatsuPoints()/4))));
            livingEntity.hurt(POISON_DAMAGE, 5);
        }
    }}
