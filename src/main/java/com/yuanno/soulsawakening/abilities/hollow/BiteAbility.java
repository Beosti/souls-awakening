package com.yuanno.soulsawakening.abilities.hollow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModDamageSource;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public class BiteAbility extends Ability {
    public static final BiteAbility INSTANCE = new BiteAbility();
    private final static DamageSource BITE_DAMAGE = new ModDamageSource("bite").setSourceTypes(SourceType.SPIKE).setSourceElement(SourceElement.NONE);
    public BiteAbility()
    {
        this.setName("Bite");
        this.setCooldown(5);
        this.setMaxCooldown(5);
        this.setActivationType(ActivationType.RIGHT_CLICK_ENTITY);
        this.setState(STATE.READY);
        this.setPassive(false);
    }

    @Override
    public void onRightClickEntity(LivingEntity target, PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        int biteDamage = (6 + entityStats.getHollowPoints()/5);
        target.hurt(BITE_DAMAGE, Math.round(biteDamage));
        if (target.isDeadOrDying())
        {
            entityStats.alterHollowPoints(1);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        }
    }
}
