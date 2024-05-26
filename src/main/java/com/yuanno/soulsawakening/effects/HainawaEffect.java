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

public class HainawaEffect extends ModEffect {

    private static final ParticleEffect PARTICLES = new BindParticleEffect(4, 2, 0, 0.5f);
    public HainawaEffect() {
        super(EffectType.HARMFUL, 0);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "d5332ec8-ce14-11ee-a506-0242ac120002", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(Attributes.MOVEMENT_SPEED, "44c4f6ea-e30f-11ee-bd3d-0242ac120002", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "4a7e6576-e30f-11ee-bd3d-0242ac120002", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ModAttributes.JUMP_HEIGHT.get(), "4d6dbb6a-e30f-11ee-bd3d-0242ac120002", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(Attributes.ATTACK_SPEED, "5029ece2-ce11-11ee-a506-0242ac120002", -256, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier)
    {
        if (!livingEntity.level.isClientSide)
        {
            PARTICLES.spawn(livingEntity.level, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 0, 0, 0, ModParticleTypes.YELLOW.get());
        }
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
