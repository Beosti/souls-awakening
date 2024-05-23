package com.yuanno.soulsawakening.abilities.elements.thunder;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IBlockRayTrace;
import com.yuanno.soulsawakening.ability.api.interfaces.IParticleEffect;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.HoveringParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class ThunderStepAbility extends Ability implements IRightClickAbility, IBlockRayTrace, IParticleEffect {
    public static final ThunderStepAbility INSTANCE = new ThunderStepAbility();
    public static final ParticleEffect PARTICLES_HOVER = new HoveringParticleEffect(4, 2);

    public ThunderStepAbility()
    {
        this.setName("Lightning Step");
        this.setMaxCooldown(12);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public int getDistance()
    {
        return 12;
    }


    @Override
    public ParticleEffect getSpawnParticles()
    {
        return PARTICLES_HOVER;
    }

    @Override
    public ParticleType getParticleType()
    {
        return ModParticleTypes.THUNDER.get();
    }

    @Override
    public void somethingAtDistance(PlayerEntity player, BlockPos blockPos)
    {
        player.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }
}
