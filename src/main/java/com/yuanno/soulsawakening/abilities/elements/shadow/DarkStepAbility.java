package com.yuanno.soulsawakening.abilities.elements.shadow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.HoveringParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class DarkStepAbility extends Ability implements IRightClickEmptyAbility {
    public static final DarkStepAbility INSTANCE = new DarkStepAbility();
    public static final ParticleEffect PARTICLES_HOVER = new HoveringParticleEffect(5, 3);

    public DarkStepAbility()
    {
        this.setName("Dark Step");
        this.setCooldown(4);
        this.setMaxCooldown(4);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setCategory(Category.ZANPAKUTO);
    }

    @Override
    public void onRightClick(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        PARTICLES_HOVER.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0, ModParticleTypes.DARK.get());
        RayTraceResult rayTraceResult = Beapi.rayTraceBlocksAndEntities(player, 12 + entityStats.getHohoPoints());
        BlockPos blockPos = new BlockPos(rayTraceResult.getLocation());
        player.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        PARTICLES_HOVER.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0, ModParticleTypes.DARK.get());

    }
}
