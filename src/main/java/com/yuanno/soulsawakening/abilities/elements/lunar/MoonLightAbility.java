package com.yuanno.soulsawakening.abilities.elements.lunar;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.ISelfEffect;
import net.minecraft.entity.player.PlayerEntity;

public class MoonLightAbility extends Ability implements ISelfEffect, IRightClickAbility {
    public static final MoonLightAbility INSTANCE = new MoonLightAbility();

    public MoonLightAbility()
    {
        this.setName("Moonlight");
        this.setDescription("Heal yourself, heals more during the night");
        this.setMaxCooldown(30);
        this.dependency = AbilityDependencies::shikaiDependance;
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public void otherEffects(PlayerEntity player) {
        float missingHealth = player.getMaxHealth() - player.getHealth();
        if (player.level.isNight())
            player.heal(missingHealth / 2 + 4);
        else
            player.heal(missingHealth / 2);
    }
}
