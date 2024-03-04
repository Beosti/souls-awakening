package com.yuanno.soulsawakening.abilities.elements.lunar;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IEntityRayTrace;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class LunarBlessingAbility extends Ability implements IEntityRayTrace, IRightClickAbility {
    public static final LunarBlessingAbility INSTANCE = new LunarBlessingAbility();

    public LunarBlessingAbility()
    {
        this.setName("Lunar Curse");
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
    public void somethingAtEntity(PlayerEntity user, LivingEntity target)
    {
        IEntityStats entityStats = EntityStatsCapability.get(user);

        user.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 200, 1+(int) (1 + Math.floor(entityStats.getReiatsuPoints()/4))));
        user.addEffect(new EffectInstance(Effects.DIG_SPEED, 200, 1+(int) (1 + Math.floor(entityStats.getReiatsuPoints()/4))));

        target.addEffect(new EffectInstance(Effects.WEAKNESS, 200, 1+(int) (1 + Math.floor(entityStats.getReiatsuPoints()/4))));
        target.addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 200, 1+(int) (1 + Math.floor(entityStats.getReiatsuPoints()/4))));

    }
}
