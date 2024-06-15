package com.yuanno.soulsawakening.abilities.elements.fire;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.ExplosionAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IAttackAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.particles.CommonExplosionParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public class FireProwessAbility extends Ability implements IAttackAbility, IContinuousAbility, IRightClickAbility {
    public static final FireProwessAbility INSTANCE = new FireProwessAbility();

    public FireProwessAbility()
    {
        this.setName("Fire Prowess");
        this.setDescription("The next attack");
        this.setMaxCooldown(10);
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public boolean getExplosion() {
        return true;
    }

    @Override
    public boolean getShift() {
        return true;
    }

    @Override
    public boolean getPassive() {
        return false;
    }
}
