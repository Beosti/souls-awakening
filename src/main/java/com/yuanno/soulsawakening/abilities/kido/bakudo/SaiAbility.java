package com.yuanno.soulsawakening.abilities.kido.bakudo;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IReiatsuAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.init.ModEffects;
import com.yuanno.soulsawakening.init.ModI18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Util;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;

// Seals an entity, the entity can break through easily
public class SaiAbility extends Ability implements IShootAbility, IReiatsuAbility {
    public static final SaiAbility INSTANCE = new SaiAbility();

    public SaiAbility()
    {
        this.setName("Sai");
        this.setDescription("Restraints the entities arms");
        this.setMaxCooldown(10);
        this.setSubCategory(SubCategory.BAKUDO);
    }

    @Override
    public void onUse(PlayerEntity player)
    {
        RayTraceResult result = Beapi.rayTraceBlocksAndEntities(player, 4 + EntityStatsCapability.get(player).getReiatsuPoints());
        if (result instanceof EntityRayTraceResult)
        {
            EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult) result;
            if (entityRayTraceResult.getEntity() instanceof LivingEntity)
            {
                LivingEntity entity = (LivingEntity)entityRayTraceResult.getEntity();
                if (EntityStatsCapability.get(player).getReiatsuPoints() > EntityStatsCapability.get(entity).getReiatsuPoints() * 2)
                    entity.addEffect(new EffectInstance(ModEffects.SAI.get(), 200, 0, false, false));
                else
                    player.sendMessage(ModI18n.REIATSU_TARGET, Util.NIL_UUID);
            }
        }
        else
        {
            player.sendMessage(ModI18n.NO_TARGET, Util.NIL_UUID);
            return;
        }
    }
}
