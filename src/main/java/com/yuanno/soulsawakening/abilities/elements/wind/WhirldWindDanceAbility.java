package com.yuanno.soulsawakening.abilities.elements.wind;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;

import java.util.List;

public class WhirldWindDanceAbility extends Ability {
    public static final WhirldWindDanceAbility INSTANCE = new WhirldWindDanceAbility();
    private final static DamageSource WIND_DAMAGE = new ModDamageSource("wind_wave").setSourceTypes(SourceType.SHOCKWAVE).setSourceElement(SourceElement.WIND);
    int propulsion = 5;

    public WhirldWindDanceAbility()
    {
        this.setName("Whirld Wind Dance");
        this.setCooldown(8);
        this.setMaxCooldown(8);
        this.setPassive(false);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void onRightClick(PlayerEntity player)
    {
        List<LivingEntity> targets = Beapi.getNearbyEntities(player.blockPosition(), player.level, 10, null, LivingEntity.class);
        targets.remove(player);

        targets.forEach(entityi ->
        {
            Vector3d speed = Beapi.propulsion(player, propulsion, propulsion, propulsion);
            entityi.setDeltaMovement(speed.x, speed.y, speed.z);
            entityi.hurtMarked = true;
            entityi.hasImpulse = true;


            entityi.hurt(WIND_DAMAGE, 5);
        });

    }
}
