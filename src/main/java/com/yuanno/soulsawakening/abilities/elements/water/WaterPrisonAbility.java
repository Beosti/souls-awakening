package com.yuanno.soulsawakening.abilities.elements.water;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickEntityAbility;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class WaterPrisonAbility extends Ability implements IRightClickEntityAbility {
    public static final WaterPrisonAbility INSTANCE = new WaterPrisonAbility();

    public WaterPrisonAbility()
    {
        this.setName("Water Prison");
        this.setCooldown(10);
        this.setMaxCooldown(10);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_ENTITY);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public void onRightClickEntity(LivingEntity target, PlayerEntity user)
    {
        target.level.setBlockAndUpdate(target.blockPosition(), Blocks.WATER.defaultBlockState());
        target.level.setBlockAndUpdate(target.blockPosition().above(), Blocks.WATER.defaultBlockState());
        target.level.setBlockAndUpdate(target.blockPosition().below(), Blocks.ICE.defaultBlockState());
        target.level.setBlockAndUpdate(target.blockPosition().above().above(), Blocks.ICE.defaultBlockState());
        target.level.setBlockAndUpdate(target.blockPosition().east(), Blocks.ICE.defaultBlockState());
        target.level.setBlockAndUpdate(target.blockPosition().north(), Blocks.ICE.defaultBlockState());
        target.level.setBlockAndUpdate(target.blockPosition().south(), Blocks.ICE.defaultBlockState());
        target.level.setBlockAndUpdate(target.blockPosition().west(), Blocks.ICE.defaultBlockState());
        target.level.setBlockAndUpdate(target.blockPosition().above().east(), Blocks.ICE.defaultBlockState());
        target.level.setBlockAndUpdate(target.blockPosition().above().north(), Blocks.ICE.defaultBlockState());
        target.level.setBlockAndUpdate(target.blockPosition().above().south(), Blocks.ICE.defaultBlockState());
        target.level.setBlockAndUpdate(target.blockPosition().above().west(), Blocks.ICE.defaultBlockState());

    }
}
