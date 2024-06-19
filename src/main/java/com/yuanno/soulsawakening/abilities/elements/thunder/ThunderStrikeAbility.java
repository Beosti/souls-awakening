package com.yuanno.soulsawakening.abilities.elements.thunder;

import com.yuanno.soulsawakening.abilities.util.AbilityDependencies;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.init.ModDamageSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class ThunderStrikeAbility extends Ability implements IRightClickAbility, IBlockRayTrace, IReiatsuAbility {
    public static final ThunderStrikeAbility INSTANCE = new ThunderStrikeAbility();
    private final static DamageSource LIGHTNING_DAMAGE = new ModDamageSource("lightning_wave").setSourceTypes(SourceType.SHOCKWAVE).setSourceElement(SourceElement.LIGHTNING);

    public ThunderStrikeAbility()
    {
        this.setName("Thunder Strike");
        this.setMaxCooldown(16);
        this.setSubCategory(SubCategory.SHIKAI);
        this.dependency = AbilityDependencies::shikaiDependance;
    }

    @Override
    public int getDistance()
    {
        return 20;
    }

    @Override
    public float addedVariable(PlayerEntity player) {
        return (float) EntityStatsCapability.get(player).getReiatsuPoints()/4;
    }

    @Override
    public void somethingAtDistance(PlayerEntity player, BlockPos blockPos)
    {
        LightningBoltEntity lightningBoltEntity = EntityType.LIGHTNING_BOLT.create(player.level);
        lightningBoltEntity.moveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        List<LivingEntity> entities = Beapi.getNearbyEntities(blockPos, player.level, 3, null, LivingEntity.class);
        for (LivingEntity livingEntity : entities)
        {
            livingEntity.setSecondsOnFire(5);
            livingEntity.hurt(LIGHTNING_DAMAGE, 7);
        }
        player.level.addFreshEntity(lightningBoltEntity);
    }

    @Override
    public boolean getControl()
    {
        return true;
    }

}
