package com.yuanno.soulsawakening.abilities.elements.thunder;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;

import java.util.List;

public class ThunderStrikeAbility extends Ability implements IRightClickEmptyAbility {
    public static final ThunderStrikeAbility INSTANCE = new ThunderStrikeAbility();
    private final static DamageSource LIGHTNING_DAMAGE = new ModDamageSource("lightning_wave").setSourceTypes(SourceType.SHOCKWAVE).setSourceElement(SourceElement.LIGHTNING);

    public ThunderStrikeAbility()
    {
        this.setName("Thunder Strike");
        this.setCooldown(16);
        this.setMaxCooldown(16);
        this.setPassive(false);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setZanpakutoState(ModValues.STATE.SHIKAI);
    }

    @Override
    public void onShiftRightClick(PlayerEntity player)
    {
        RayTraceResult rayTraceResult = Beapi.rayTraceBlocksAndEntities(player, 20);
        BlockPos blockPos = null;
        if (rayTraceResult instanceof EntityRayTraceResult)
        {
            EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult) rayTraceResult;
            if (entityRayTraceResult.getEntity() instanceof LivingEntity)
            {
                LivingEntity livingEntity = (LivingEntity) entityRayTraceResult.getEntity();
                blockPos = new BlockPos(livingEntity.blockPosition());
            }
        }
        else
            blockPos = new BlockPos(rayTraceResult.getLocation());
        LightningBoltEntity lightningBoltEntity = EntityType.LIGHTNING_BOLT.create(player.level);
        lightningBoltEntity.moveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        player.level.addFreshEntity(lightningBoltEntity);
        List<LivingEntity> entities = Beapi.getNearbyEntities(player.blockPosition(), player.level, 3, null, LivingEntity.class);
        for (LivingEntity livingEntity : entities)
        {
            livingEntity.setSecondsOnFire(5);
            livingEntity.hurt(LIGHTNING_DAMAGE, 7);
        }
    }
}
