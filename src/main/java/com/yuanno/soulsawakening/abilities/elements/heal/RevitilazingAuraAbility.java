package com.yuanno.soulsawakening.abilities.elements.heal;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.List;

public class RevitilazingAuraAbility extends Ability implements IRightClickEmptyAbility {
    public static final RevitilazingAuraAbility INSTANCE = new RevitilazingAuraAbility();

    public RevitilazingAuraAbility()
    {
        this.setName("Revitilazing Aura");
        this.setCooldown(20);
        this.setMaxCooldown(20);
        this.setPassive(false);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void onShiftRightClick(PlayerEntity user)
    {
        List<LivingEntity> targets = Beapi.getNearbyEntities(user.blockPosition(), user.level, 10, null, LivingEntity.class);
        for (LivingEntity livingEntity : targets)
        {
            if (!livingEntity.hasEffect(Effects.REGENERATION))
                livingEntity.addEffect(new EffectInstance(Effects.REGENERATION, 240, 2));

        }

    }
}
