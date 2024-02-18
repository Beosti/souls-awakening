package com.yuanno.soulsawakening.effects;

import com.yuanno.soulsawakening.api.ModEffect;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public class SaiEffect extends ModEffect {

    public SaiEffect() {
        super(EffectType.HARMFUL, 0);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "d5332ec8-ce14-11ee-a506-0242ac120002", -256, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, "5029ece2-ce11-11ee-a506-0242ac120002", -256, AttributeModifier.Operation.ADDITION);
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
