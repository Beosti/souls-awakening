package com.yuanno.soulsawakening.abilities.hollow;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.hollow.HollowRegenerationParticleEffect;
import net.minecraft.entity.player.PlayerEntity;

public class HollowRegenerationAbility extends Ability implements IPassiveAbility {

    public static final HollowRegenerationAbility INSTANCE = new HollowRegenerationAbility();
    private static final ParticleEffect PARTICLES = new HollowRegenerationParticleEffect();

    public HollowRegenerationAbility()
    {
        this.setName("Hollow Regeneration");
        this.setDescription("Passively regenerate health as a hollow");
        this.setShown(false);
        this.setSubCategory(SubCategory.BASE);
    }

    @Override
    public void onContinuousAbility(PlayerEntity user)
    {
        if (user.getHealth() < 20) {
            user.heal(0.5F);
            PARTICLES.spawn(user.level, user.getX(), user.getY(), user.getZ(), 0, 0, 0);
        }
    }
}
