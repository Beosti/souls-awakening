package com.yuanno.soulsawakening.abilities.elements.shadow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.init.ModEffects;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class UmbralCloakAbility extends Ability {
    public static final UmbralCloakAbility INSTANCE = new UmbralCloakAbility();

    public UmbralCloakAbility()
    {
        this.setName("Umbral Cloak");
        this.setCooldown(16);
        this.setMaxCooldown(16);
        this.setPassive(false);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void onRightClick(PlayerEntity player)
    {
        if (!player.hasEffect(ModEffects.VANISH_INVISIBILITY.get()))
            player.addEffect(new EffectInstance(ModEffects.VANISH_INVISIBILITY.get(), 160, 2, false, false));
    }
}