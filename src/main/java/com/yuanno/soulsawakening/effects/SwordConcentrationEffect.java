package com.yuanno.soulsawakening.effects;

import com.yuanno.soulsawakening.api.ModEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public class SwordConcentrationEffect extends ModEffect {

    public SwordConcentrationEffect() {
        super(EffectType.HARMFUL, 0);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "db8120c0-1480-11ef-9262-0242ac120002", 4, AttributeModifier.Operation.ADDITION);
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
