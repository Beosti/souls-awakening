package com.yuanno.soulsawakening.abilities.elements.thunder;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.HoveringParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.BlockPos;

public class ThunderStepAbility extends Ability implements IRightClickAbility, IBlockRayTrace, IParticleEffect {
    public static final ThunderStepAbility INSTANCE = new ThunderStepAbility();
    public static final ParticleEffect PARTICLES_HOVER = new HoveringParticleEffect(4, 2);

    public ThunderStepAbility()
    {
        this.setName("Lightning Step");
        this.setMaxCooldown(12);
        this.setSubCategory(SubCategory.SHIKAI);
        this.dependency = AbilityDependencies::shikaiDependance;
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

    @Override
    public boolean getShift() {
        return true;
    }
}
