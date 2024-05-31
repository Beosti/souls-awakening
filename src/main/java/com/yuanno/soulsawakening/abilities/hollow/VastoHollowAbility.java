package com.yuanno.soulsawakening.abilities.hollow;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.hollow.BiteParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class VastoHollowAbility extends Ability {
    public static final VastoHollowAbility INSTANCE = new VastoHollowAbility();
    private static final ParticleEffect PARTICLES = new BiteParticleEffect();

    public VastoHollowAbility()
    {
        this.setName("Vasto Hollow");
        this.setDescription("Bites the enemy, if you kill the enemy with this ability, you gain a hollow point");
        this.setMaxCooldown(5);
        this.setSubCategory(SubCategory.BASE);
    }
}
