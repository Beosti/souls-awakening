package com.yuanno.soulsawakening.abilities.elements.shadow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IAttackAbility;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.HoveringParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;

public class ShadowAttackAbility extends Ability implements IAttackAbility {
    public static final ShadowAttackAbility INSTANCE = new ShadowAttackAbility();
    private final static DamageSource SHADOW_DAMAGE = new ModDamageSource("shadow_damage").setSourceTypes(SourceType.SLASH).setSourceElement(SourceElement.SHADOW);
    public static final ParticleEffect PARTICLES_HOVER = new HoveringParticleEffect(1, 2);


    public ShadowAttackAbility() {
        this.setName("Shadow Attack");
        this.setSubCategory(SubCategory.SHIKAI);
    }

    @Override
    public void activate(LivingEntity livingEntityTarget, PlayerEntity player)
    {

        if (!livingEntityTarget.hasEffect(Effects.CONFUSION)) {
            livingEntityTarget.addEffect(new EffectInstance(Effects.CONFUSION, 40, 0));
            PARTICLES_HOVER.spawn(livingEntityTarget.level, livingEntityTarget.getX(), livingEntityTarget.getY(), livingEntityTarget.getZ(), 0, 0, 0, ModParticleTypes.DARK.get());

        }
    }


}
