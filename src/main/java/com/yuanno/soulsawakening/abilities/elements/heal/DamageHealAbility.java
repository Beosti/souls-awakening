package com.yuanno.soulsawakening.abilities.elements.heal;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IGetHitAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class DamageHealAbility extends Ability implements IGetHitAbility, IContinuousAbility, IRightClickAbility {
    public static final DamageHealAbility INSTANCE = new DamageHealAbility();

    public DamageHealAbility()
    {
        this.setName("Damage Heal");
        this.setDescription("Heals yourself immediately after getting hit for a period of time");
        this.setMaxCooldown(60);
        this.dependency = AbilityDependencies::shikaiDependance;
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public void getHit(PlayerEntity player, LivingEntity attacker, float damage) {
        player.heal(damage);
    }

    @Override
    public int getMaxTimer() {
        return 200;
    }

    @Override
    public boolean getShift()
    {
        return true;
    }
}
