package com.yuanno.soulsawakening.abilities.elements.heal;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEntityAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.List;

public class HealingTouchingAbility extends Ability implements IRightClickEntityAbility {
    public static final HealingTouchingAbility INSTANCE = new HealingTouchingAbility();

    public HealingTouchingAbility()
    {
        this.setName("Healing Touch");
        this.setCooldown(20);
        this.setMaxCooldown(20);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_ENTITY);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }
    @Override
    public void onRightClickEntity(LivingEntity entity, PlayerEntity user)
    {
        if (entity.hasEffect(Effects.ABSORPTION))
        {
            entity.removeEffect(Effects.ABSORPTION);
            entity.addEffect(new EffectInstance(Effects.ABSORPTION, 120, 1));
        }
        else
            entity.addEffect(new EffectInstance(Effects.ABSORPTION, 120, 1));
        float missingHealth = entity.getMaxHealth() - entity.getHealth();
        entity.heal(missingHealth / 2 + 4);
    }
}
