package com.yuanno.soulsawakening.abilities.hollow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IAttackAbility;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;

public class SlashAbility extends Ability implements IAttackAbility {
    public static final SlashAbility INSTANCE = new SlashAbility();
    private final static DamageSource SLASH_DAMAGE = new ModDamageSource("slash").setSourceTypes(SourceType.SLASH).setSourceElement(SourceElement.NONE);

    public SlashAbility()
    {
        this.setName("Slashing");
        this.setDescription("Slash your enemies dealing bonus damage based on the amount of hollow points");
        this.setSubCategory(SubCategory.BASE);
    }

    @Override
    public void activate(PlayerEntity player, LivingEntity livingEntityTarget)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        double slashDamage = 2 + entityStats.getHollowPoints()/10;
        livingEntityTarget.hurt(SLASH_DAMAGE, Math.round(slashDamage));
        double d0 = (double)(-MathHelper.sin(livingEntityTarget.yRot * ((float)Math.PI / 180F)));
        double d1 = (double)MathHelper.cos(livingEntityTarget.yRot * ((float)Math.PI / 180F));
        if (livingEntityTarget.level instanceof ServerWorld) {
            ((ServerWorld)livingEntityTarget.level).sendParticles(ParticleTypes.SWEEP_ATTACK, livingEntityTarget.getX() + d0, livingEntityTarget.getY(0.5D), livingEntityTarget.getZ() + d1, 0, d0, 0.0D, d1, 0.0D);
        }
    }
}
