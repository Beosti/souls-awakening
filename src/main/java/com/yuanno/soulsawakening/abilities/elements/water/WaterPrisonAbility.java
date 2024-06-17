package com.yuanno.soulsawakening.abilities.elements.water;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class WaterPrisonAbility extends Ability implements IEntityRayTrace, IRightClickAbility {
    public static final WaterPrisonAbility INSTANCE = new WaterPrisonAbility();

    public WaterPrisonAbility()
    {
        this.setName("Water Prison");
        this.setMaxCooldown(10);
        this.setSubCategory(SubCategory.SHIKAI);
        this.dependency = AbilityDependencies::shikaiDependance;
    }

    @Override
    public int getDistance()
    {
        return 2;
    }

    @Override
    public void somethingAtEntity(PlayerEntity player, LivingEntity target)
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
