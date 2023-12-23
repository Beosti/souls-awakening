package com.yuanno.soulsawakening.abilities.elements.shadow;

import com.yuanno.soulsawakening.abilities.elements.fire.FireAttackAbility;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IAttackAbility;
import com.yuanno.soulsawakening.ability.api.IDuringCooldownAbility;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.init.ModResources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;

public class ShadowAttackAbility extends Ability implements IAttackAbility {
    public static final ShadowAttackAbility INSTANCE = new ShadowAttackAbility();
    private final static DamageSource SHADOW_DAMAGE = new ModDamageSource("shadow_damage").setSourceTypes(SourceType.SLASH).setSourceElement(SourceElement.SHADOW);


    public ShadowAttackAbility() {
        this.setName("Shadow Attack");
        this.setPassive(true);
        this.setActivationType(Ability.ActivationType.ATTACK);
        this.setZanpakutoState(ModResources.STATE.SHIKAI);
    }

    @Override
    public void activate(LivingEntity livingEntityTarget, PlayerEntity player)
    {
        if (!livingEntityTarget.hasEffect(Effects.CONFUSION))
            livingEntityTarget.addEffect(new EffectInstance(Effects.CONFUSION, 40, 0));
    }


}
