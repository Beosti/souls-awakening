package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.IAttackAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModResources;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AttackAbilityEvent {

    @SubscribeEvent
    public static void onAttackEvent(AttackEntityEvent event)
    {
        PlayerEntity player = event.getPlayer();
        Entity target = event.getTarget();
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (event.getPlayer().level.isClientSide)
            return;
        if (target instanceof LivingEntity)
        {
            LivingEntity livingEntityTarget = (LivingEntity) target;
            IAbilityData abilityData = AbilityDataCapability.get(player);
            // when a player is a shinigami check the zanpakuto
            if (entityStats.getRace().equals(ModValues.SHINIGAMI) || entityStats.getRace().equals(ModValues.FULLBRINGER))
            {
                if (player.getMainHandItem().getItem() instanceof ZanpakutoItem)
                {
                    ZanpakutoItem zanpakutoItem = (ZanpakutoItem) player.getMainHandItem().getItem();
                    if (zanpakutoItem.getZanpakutoState().equals(ModResources.STATE.SHIKAI)) // if the zanpakuto is in shikai state
                    {
                        for (Ability ability : abilityData.getUnlockedAbilities())
                        {
                            if (!(ability instanceof IAttackAbility) || !ability.getZanpakutoState().equals(ModResources.STATE.SHIKAI))
                                continue;
                            ((IAttackAbility) ability).activate(livingEntityTarget, player);
                        }
                    }
                }
            }

            // when a player is a hollow
            else if (entityStats.getRace().equals(ModValues.HOLLOW))
            {
                for (Ability ability : abilityData.getUnlockedAbilities())
                {
                    if ((ability instanceof IAttackAbility)) {
                        ((IAttackAbility) ability).activate(livingEntityTarget, player);
                        double knockback = 1.5;
                        ((LivingEntity)livingEntityTarget).knockback((float)knockback * 0.5F, (double) MathHelper.sin(player.yRot * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(player.yRot * ((float)Math.PI / 180F))));
                    }
                }
            }
        }
    }
}
