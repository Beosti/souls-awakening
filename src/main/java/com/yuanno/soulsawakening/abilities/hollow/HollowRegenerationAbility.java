package com.yuanno.soulsawakening.abilities.hollow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IContinuousAbility;
import net.minecraft.entity.player.PlayerEntity;

public class HollowRegenerationAbility extends Ability implements IContinuousAbility {

    public static final HollowRegenerationAbility INSTANCE = new HollowRegenerationAbility();

    public HollowRegenerationAbility()
    {
        this.setName("Hollow Regeneration");
        this.setPassive(true);
        this.setShown(false);
    }

    @Override
    public void onStartAbility(PlayerEntity user)
    {
        System.out.println("STARTED");
    }

    @Override
    public void onContinuousAbility(PlayerEntity user)
    {
        if (user.getHealth() < 20)
            user.heal(0.5F);
    }

    @Override
    public void onEndContinuousAbility(PlayerEntity user)
    {
        System.out.println("ENDING");
    }
}
