package com.yuanno.soulsawakening.abilities.elements.thunder;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.HoveringParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class ThunderStepAbility extends Ability implements IRightClickEmptyAbility {
    public static final ThunderStepAbility INSTANCE = new ThunderStepAbility();
    public static final ParticleEffect PARTICLES_HOVER = new HoveringParticleEffect(4, 2);

    public ThunderStepAbility()
    {
        this.setName("Lightning Step");
        this.setCooldown(12);
        this.setMaxCooldown(12);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setZanpakutoState(ModValues.STATE.SHIKAI);
    }

    @Override
    public void onRightClick(PlayerEntity player)
    {
        PARTICLES_HOVER.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0, ModParticleTypes.THUNDER.get());
        RayTraceResult rayTraceResult = Beapi.rayTraceBlocksAndEntities(player, 32);
        BlockPos blockPos = new BlockPos(rayTraceResult.getLocation());
        player.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        PARTICLES_HOVER.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0, ModParticleTypes.THUNDER.get());
    }


}
