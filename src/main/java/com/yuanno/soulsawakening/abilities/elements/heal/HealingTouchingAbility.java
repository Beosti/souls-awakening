package com.yuanno.soulsawakening.abilities.elements.heal;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEntityAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class HealingTouchingAbility extends Ability implements IRightClickEntityAbility {
    public static final HealingTouchingAbility INSTANCE = new HealingTouchingAbility();

    public HealingTouchingAbility()
    {
        this.setName("Healing Touch");
        this.setCooldown(20);
        this.setMaxCooldown(20);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_ENTITY);
        this.setZanpakutoState(ModValues.STATE.SHIKAI);
        this.setCategory(Category.ZANPAKUTO);
    }
    @Override
    public void onRightClickEntity(LivingEntity entity, PlayerEntity user)
    {
        IEntityStats entityStats = EntityStatsCapability.get(user);

        if (entity.hasEffect(Effects.ABSORPTION))
        {
            entity.removeEffect(Effects.ABSORPTION);
            entity.addEffect(new EffectInstance(Effects.ABSORPTION, 120, (int) (1 + Math.floor(entityStats.getReiatsuPoints()/4))));
        }
        else
            entity.addEffect(new EffectInstance(Effects.ABSORPTION, 120, (int) (1 + Math.floor(entityStats.getReiatsuPoints()/4))));
        float missingHealth = entity.getMaxHealth() - entity.getHealth();
        entity.heal(missingHealth / 2 + 4 + (float) entityStats.getReiatsuPoints()/4);
    }
}
