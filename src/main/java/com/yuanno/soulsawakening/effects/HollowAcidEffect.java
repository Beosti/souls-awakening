package com.yuanno.soulsawakening.effects;

import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModDamageSource;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeMod;

public class HollowAcidEffect extends SpecialEffect{
    //private static final ParticleEffect PARTICLES = new ElectrocutedParticleEffect();
    public HollowAcidEffect()
    {
        super(EffectType.HARMFUL, Beapi.hexToRGB("#000000").getRGB());
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier)
    {
        livingEntity.hurt(ModDamageSource.ACID, 1.0F);
    }
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier)
    {
        return true;
    }
    @Override
    public boolean shouldRender(EffectInstance effect)
    {
        return false;
    }

    @Override
    public boolean shouldRenderHUD(EffectInstance effect)
    {
        return false;
    }

    @Override
    public float[] getOverlayColor()
    {
        return new float[] { 0.0f, 0.0f, 0.0f, 1f };
    }

    @Override
    public boolean hasBodyOverlayColor() {
        return false;
    }

    @Override
    public Block getBlockOverlay() {
        return null;
    }

    @Override
    public boolean isBlockingMovement() {
        return true;
    }

    @Override
    public ResourceLocation getResourceLocation(int duration) {
        return null;
    }

}
