package com.yuanno.soulsawakening.abilities.shinso;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IPunchAbility;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.entities.projectiles.shinso.WideBladeProjectile;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class LongRangeAbility extends Ability implements IPunchAbility {
    public static final LongRangeAbility INSTANCE = new LongRangeAbility();

    public LongRangeAbility()
    {
        this.setName("Long Range");
        this.setCooldown(45);
        this.setMaxCooldown(45);
        this.setPassive(false);
        this.setActivationType(ActivationType.ATTACK);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    public void startContinuity(PlayerEntity player) {
        System.out.println("STARTED CONTINUITY");
    }
    public void onHitEntity(LivingEntity target, PlayerEntity user) {
        System.out.println("HIT AN ENTITY");
    }
    public void startCooldown(PlayerEntity player) {
        System.out.println("STARTED COOLDOWN");
    }
}
