package com.yuanno.soulsawakening.ability.elements.hollow;

import com.yuanno.soulsawakening.ability.api.Ability;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public class SlashAbility extends Ability {
    public static final SlashAbility INSTANCE = new SlashAbility();

    public SlashAbility()
    {
        this.setName("Slash");
        this.setPassive(true);
        this.setActivationType(ActivationType.ATTACK);
    }

    @Override
    public void activate(LivingEntity livingEntityTarget, PlayerEntity player)
    {
        livingEntityTarget.hurt(DamageSource.CACTUS, 2);
    }
}
