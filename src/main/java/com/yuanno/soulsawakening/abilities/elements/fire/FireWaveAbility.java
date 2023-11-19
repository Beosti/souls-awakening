package com.yuanno.soulsawakening.abilities.elements.fire;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

import java.util.List;

public class FireWaveAbility extends Ability implements IRightClickEmptyAbility {
    public static final FireWaveAbility INSTANCE = new FireWaveAbility();
    private final static DamageSource FIRE_DAMAGE = new ModDamageSource("fire_wave").setSourceTypes(SourceType.SHOCKWAVE).setSourceElement(SourceElement.FIRE);
    public FireWaveAbility()
    {
        this.setName("Fire Wave");
        this.setCooldown(15);
        this.setMaxCooldown(15);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setPassive(false);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void onShiftRightClick(PlayerEntity player)
    {
        List<LivingEntity> targets = Beapi.getNearbyEntities(player.blockPosition(), player.level, 10, null, LivingEntity.class);
        for (LivingEntity livingEntity : targets)
        {
            livingEntity.setSecondsOnFire(5);
            livingEntity.hurt(FIRE_DAMAGE, 5);
        }
    }
}
