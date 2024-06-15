package com.yuanno.soulsawakening.abilities.elements.shadow;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.HoveringParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.BlockPos;

public class DarkStepAbility extends Ability implements IRightClickAbility, IBlockRayTrace, IParticleEffect {
    public static final DarkStepAbility INSTANCE = new DarkStepAbility();
    public static final ParticleEffect PARTICLES_HOVER = new HoveringParticleEffect(5, 3);
    public DarkStepAbility()
    {
        this.setName("Dark Step");
        this.setMaxCooldown(6);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public boolean getShift() {
        return true;
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
        return ModParticleTypes.DARK.get();
    }

    @Override
    public void somethingAtDistance(PlayerEntity player, BlockPos blockPos)
    {
        player.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }
}
