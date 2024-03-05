package com.yuanno.soulsawakening.abilities.elements.wind;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IParticleEffect;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IWaveAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class WhirldWindDanceAbility extends Ability implements IRightClickAbility, IWaveAbility {
    public static final WhirldWindDanceAbility INSTANCE = new WhirldWindDanceAbility();
    private final static DamageSource WIND_DAMAGE = new ModDamageSource("wind_wave").setSourceTypes(SourceType.SHOCKWAVE).setSourceElement(SourceElement.WIND);

    public WhirldWindDanceAbility()
    {
        this.setName("Whirld Wind Dance");
        this.setMaxCooldown(8);
        this.setCategory(Category.ZANPAKUTO);
    }

    @Override
    public int getRadius()
    {
        return 10;
    }

    @Override
    public DamageSource getDamageSource()
    {
        return WIND_DAMAGE;
    }
    @Override
    public float getBaseDamage()
    {
        return 5;
    }

    @Override
    public boolean getShift()
    {
        return true;
    }
}
