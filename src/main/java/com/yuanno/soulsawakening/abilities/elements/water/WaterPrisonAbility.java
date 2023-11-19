package com.yuanno.soulsawakening.abilities.elements.water;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.entities.projectiles.water.WaterSlashProjectile;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class WaterPrisonAbility extends Ability {
    public static final WaterPrisonAbility INSTANCE = new WaterPrisonAbility();

    public WaterPrisonAbility()
    {
        this.setName("Water Prison");
        this.setCooldown(10);
        this.setMaxCooldown(10);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_ENTITY);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void onRightClickEntity(LivingEntity target, PlayerEntity user)
    {
        target.level.setBlockAndUpdate(target.blockPosition(), Blocks.WATER.defaultBlockState());
        target.level.setBlockAndUpdate(target.blockPosition().above(), Blocks.WATER.defaultBlockState());

    }
}