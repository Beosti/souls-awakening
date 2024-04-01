package com.yuanno.soulsawakening.abilities.kido.bakudo;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.KidoAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IEntityRayTrace;
import com.yuanno.soulsawakening.ability.api.interfaces.IReiatsuAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.ModEffect;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.effects.SaiEffect;
import com.yuanno.soulsawakening.init.ModEffects;
import com.yuanno.soulsawakening.init.ModI18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Util;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;

// Seals an entity, the entity can break through easily
public class SaiAbility extends KidoAbility implements IEntityRayTrace, IReiatsuAbility {
    public static final SaiAbility INSTANCE = new SaiAbility();

    public SaiAbility()
    {
        this.setName("Sai");
        this.setDescription("Restraints the entities arms");
        this.setMaxCooldown(10);
        this.setIncantation("Ye thy stop moving Bakudo number 1 Sai");
        this.setSubCategory(SubCategory.BAKUDO);
    }

    @Override
    public void somethingAtEntity(PlayerEntity player, LivingEntity entity) {
        IEntityStats entityStatsTarget = EntityStatsCapability.get(entity);
        IEntityStats entityStatsUser = EntityStatsCapability.get(player);
        if (entityStatsTarget.getReiatsuPoints() * 2 > entityStatsUser.getReiatsuPoints())
            return;
        entity.addEffect(new EffectInstance(ModEffects.SAI.get(), 120, 0));
    }
}
