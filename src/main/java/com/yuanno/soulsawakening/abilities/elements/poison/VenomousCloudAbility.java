package com.yuanno.soulsawakening.abilities.elements.poison;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;

import java.util.List;

public class VenomousCloudAbility extends Ability {

    public static final VenomousCloudAbility INSTANCE = new VenomousCloudAbility();
    private final static DamageSource FIRE_DAMAGE = new ModDamageSource("venomous_cloud").setSourceTypes(SourceType.SHOCKWAVE).setSourceElement(SourceElement.POISON);
    public VenomousCloudAbility()
    {
        this.setName("Venomous Cloud");
        this.setCooldown(15);
        this.setMaxCooldown(15);
        this.setActivationType(ActivationType.RIGHT_CLICK_EMPTY);
        this.setState(STATE.READY);
        this.setPassive(false);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void onRightClick(PlayerEntity player)
    {
        List<LivingEntity> targets = Beapi.getNearbyEntities(player.blockPosition(), player.level, 10, null, LivingEntity.class);
        for (LivingEntity livingEntity : targets)
        {
            if (!livingEntity.hasEffect(Effects.POISON))
                livingEntity.addEffect(new EffectInstance(Effects.POISON, 120, 1));
            livingEntity.hurt(FIRE_DAMAGE, 5);
        }
    }}
