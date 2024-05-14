package com.yuanno.soulsawakening.effects;

import com.yuanno.soulsawakening.api.ModEffect;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.BindParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.ForgeMod;

public class SpearThrustEffect extends ModEffect {

    private static final ParticleEffect PARTICLES = new BindParticleEffect(4, 2, 0, 0.5f);
    public SpearThrustEffect() {
        super(EffectType.HARMFUL, 0);
        this.addAttributeModifier(ModAttributes.ATTACK_RANGE.get(), "3ee75eda-1211-11ef-9262-0242ac120002", 5, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier)
    {

    }
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier)
    {
        return true;
    }
    @Override
    public boolean shouldUpdateClient() {
        return true;
    }

    @Override
    public boolean shouldRender(EffectInstance effect) {
        return false;
    }

    @Override
    public boolean shouldRenderHUD(EffectInstance effect) {
        return false;
    }

    @Override
    public boolean isBlockingRotations() {
        return true;
    }


    @Override
    public boolean isRemoveable() {
        return false;
    }
}
