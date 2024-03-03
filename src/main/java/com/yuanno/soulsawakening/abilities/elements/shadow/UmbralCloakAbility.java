package com.yuanno.soulsawakening.abilities.elements.shadow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.init.ModEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public class UmbralCloakAbility extends Ability implements IRightClickEmptyAbility {
    public static final UmbralCloakAbility INSTANCE = new UmbralCloakAbility();

    public UmbralCloakAbility()
    {
        this.setName("Umbral Cloak");
        this.setCooldown(16);
        this.setMaxCooldown(16);
        this.setPassive(false);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setCategory(Category.ZANPAKUTO);
    }

    @Override
    public void onShiftRightClick(PlayerEntity player)
    {
        if (!player.hasEffect(ModEffects.VANISH_INVISIBILITY.get()))
            player.addEffect(new EffectInstance(ModEffects.VANISH_INVISIBILITY.get(), 160, 2, false, false));
    }
}
