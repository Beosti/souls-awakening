package com.yuanno.soulsawakening.ability.elements.hollow;

import com.yuanno.soulsawakening.ability.api.Ability;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public class BiteAbility extends Ability {
    public static final BiteAbility INSTANCE = new BiteAbility();

    public BiteAbility()
    {
        this.setName("Bite");
        this.setCooldown(5);
        this.setActivationType(ActivationType.RIGHT_CLICK_ENTITY);
    }

    @Override
    public void onRightClickEntity(LivingEntity target, PlayerEntity player)
    {
        target.hurt(DamageSource.CACTUS, 5);
    }
}
