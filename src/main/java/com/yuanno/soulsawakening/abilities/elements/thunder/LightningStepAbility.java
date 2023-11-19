package com.yuanno.soulsawakening.abilities.elements.thunder;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class LightningStepAbility extends Ability implements IRightClickEmptyAbility {
    public static final LightningStepAbility INSTANCE = new LightningStepAbility();

    public LightningStepAbility()
    {
        this.setName("Lightning Step");
        this.setCooldown(12);
        this.setMaxCooldown(12);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void onRightClick(PlayerEntity player)
    {
        RayTraceResult rayTraceResult = Beapi.rayTraceBlocksAndEntities(player, 32);
        BlockPos blockPos = new BlockPos(rayTraceResult.getLocation());
        player.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }
}
