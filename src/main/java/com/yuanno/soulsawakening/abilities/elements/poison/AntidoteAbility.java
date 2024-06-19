package com.yuanno.soulsawakening.abilities.elements.poison;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.ISelfEffect;
import net.minecraft.entity.player.PlayerEntity;

public class AntidoteAbility extends Ability implements IRightClickAbility, ISelfEffect {
    public static final AntidoteAbility INSTANCE = new AntidoteAbility();

    public AntidoteAbility()
    {
        this.setName("Antidote");
        this.setDescription("Negates all effects and fire");
        this.setMaxCooldown(16);
        this.dependency = AbilityDependencies::shikaiDependance;
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public void otherEffects(PlayerEntity player) {
        if (player.isOnFire())
            player.clearFire();
        player.removeAllEffects();
    }

    @Override
    public boolean getShift() {
        return true;
    }
}
