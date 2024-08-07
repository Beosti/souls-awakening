package com.yuanno.soulsawakening.abilities.hollow;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.api.AbilityDamageSource;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entity.PlusEntity;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.hollow.BiteParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class BiteAbility extends Ability implements IEntityRayTrace, IRightClickAbility {
    public static final BiteAbility INSTANCE = new BiteAbility();
    private static final ParticleEffect PARTICLES = new BiteParticleEffect();

    public BiteAbility()
    {
        this.setName("Bite");
        this.setDescription("Bites the enemy, if you kill the enemy with this ability, you gain a hollow point");
        this.setMaxCooldown(5);
        this.setSubCategory(SubCategory.BASE);
    }

    @Override
    public int getDistance()
    {
        return 3;
    }

    @Override
    public void somethingAtEntity(PlayerEntity player, LivingEntity entity)
    {
        if (entity instanceof PlusEntity)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        double biteDamage = (6 + (double) entityStats.getHollowStats().getHollowPoints() /5);
        entity.hurt(AbilityDamageSource.causeAbilityDamage(player, this), (float) biteDamage);
        PARTICLES.spawn(entity.level, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
    }
}
