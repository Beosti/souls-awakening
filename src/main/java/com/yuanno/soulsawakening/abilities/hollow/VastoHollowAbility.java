package com.yuanno.soulsawakening.abilities.hollow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IEntityRayTrace;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.api.AbilityDamageSource;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entity.PlusEntity;
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
