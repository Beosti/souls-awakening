package com.yuanno.soulsawakening.abilities.elements.heal;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IEntityRayTrace;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class HealingTouchingAbility extends Ability implements IEntityRayTrace, IRightClickAbility {
    public static final HealingTouchingAbility INSTANCE = new HealingTouchingAbility();

    public HealingTouchingAbility()
    {
        this.setName("Healing Touch");
        this.setCooldown(20);
        this.setMaxCooldown(20);
        this.setPassive(false);
        this.setActivationType(ActivationType.RIGHT_CLICK_ENTITY);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public int getDistance()
    {
        return 2;
    }

    @Override
    public void somethingAtEntity(PlayerEntity user, LivingEntity entity)
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
