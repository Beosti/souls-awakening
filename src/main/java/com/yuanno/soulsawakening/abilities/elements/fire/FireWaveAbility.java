package com.yuanno.soulsawakening.abilities.elements.fire;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

import java.util.List;

public class FireWaveAbility extends Ability implements IRightClickEmptyAbility {
    public static final FireWaveAbility INSTANCE = new FireWaveAbility();
    private final static DamageSource FIRE_DAMAGE = new ModDamageSource("fire_wave").setSourceTypes(SourceType.SHOCKWAVE).setSourceElement(SourceElement.FIRE);
    public static final ParticleEffect PARTICLES_FIRE = new WaveParticleEffect(1.6);

    public FireWaveAbility()
    {
        this.setName("Fire Wave");
        this.setDescription("Emit a wave of fire, damaging and putting enemies on fire");
        this.setCooldown(15);
        this.setMaxCooldown(15);
        this.setPassive(false);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public void onShiftRightClick(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        List<LivingEntity> targets = Beapi.getNearbyEntities(player.blockPosition(), player.level, 10 + entityStats.getReiatsuPoints()/2, null, LivingEntity.class);
        targets.remove(player);
        for (LivingEntity livingEntity : targets)
        {
            livingEntity.setSecondsOnFire(5);
            livingEntity.hurt(FIRE_DAMAGE, (float) (5+ entityStats.getReiatsuPoints()/3));
        }

        PARTICLES_FIRE.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0, ModParticleTypes.FIRE.get());
    }

    @Override
    public boolean getShift()
    {
        return true;
    }
}
