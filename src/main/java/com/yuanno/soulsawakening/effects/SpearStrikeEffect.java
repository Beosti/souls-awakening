package com.yuanno.soulsawakening.effects;

import com.yuanno.soulsawakening.api.ModEffect;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.BindParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import org.w3c.dom.Attr;

public class SpearStrikeEffect extends ModEffect {

    public SpearStrikeEffect() {
        super(EffectType.HARMFUL, 0);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "9b497848-12d4-11ef-9262-0242ac120002", -0.1, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.ARMOR, "7d409958-12d4-11ef-9262-0242ac120002", -3, AttributeModifier.Operation.ADDITION);
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
