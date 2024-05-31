package com.yuanno.soulsawakening.abilities.kido.hado;

import com.yuanno.soulsawakening.api.ability.KidoAbility;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.init.ModEffects;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.init.ModTags;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.HoveringParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;

public class TsuzuriRaidenAbility extends KidoAbility implements IContinuousAbility, IAttackAbility, IParticleEffect {
    public static final TsuzuriRaidenAbility INSTANCE = new TsuzuriRaidenAbility();
    public static final ParticleEffect PARTICLES_HOVER = new HoveringParticleEffect(1, 3);

    public TsuzuriRaidenAbility()
    {
        this.setName("Tsuzuri Raiden");
        this.setDescription("Envelops your weapon with lightning, dealing lightning damage and electrocuting the enemy");
        this.setMaxCooldown(10);
        this.setIncantation("Thy bless my weapon with lightning Hado number 11 Tsuzuri Raiden");
        this.setSubCategory(SubCategory.HADO);
    }

    @Override
    public boolean startContinuity(PlayerEntity player) {
        if (!player.getMainHandItem().getItem().is(ModTags.Items.CONDUCTOR)) {
            player.sendMessage(new TranslationTextComponent("Need to hold an item that can conduct electricity"), Util.NIL_UUID);
            return false;
        }
        return true;
    }
    @Override
    public void duringContinuity(PlayerEntity player) {
        if (!player.getMainHandItem().getItem().is(ModTags.Items.CONDUCTOR)) {
            player.sendMessage(new TranslationTextComponent("Need to hold an item that can conduct electricity"), Util.NIL_UUID);
            this.endContinuity(player, this);
        }
    }

    @Override
    public EffectInstance addedEffect() {
        return new EffectInstance(ModEffects.ELECTROCUTED.get(), 15, 0);
    }
    @Override
    public ParticleEffect getSpawnParticles() {
        return PARTICLES_HOVER;
    };
    @Override
    public ParticleType getParticleType() {
        return ModParticleTypes.THUNDER.get();
    }
}
