package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
        System.out.println(entityStats.getRace());
        if (target instanceof LivingEntity)
        {
            LivingEntity livingEntityTarget = (LivingEntity) target;

            // when a player is a shinigami check the zanpakuto
            if (entityStats.getRace().equals(ModValues.SHINIGAMI))
            {
                if (player.getMainHandItem().getItem() instanceof ZanpakutoItem)
                {
                    ZanpakutoItem zanpakutoItem = (ZanpakutoItem) player.getMainHandItem().getItem();
                    if (zanpakutoItem.getZanpakutoState().equals(ZanpakutoItem.STATE.SHIKAI)) // if the zanpakuto is in shikai state
                    {
                        for (Ability ability : zanpakutoItem.getAbilities())
                        {
                            if (ability.getActivationType().equals(Ability.ActivationType.ATTACK))
                            {
                                ability.activate(livingEntityTarget, player);
                            }
                        }
                    }
                }
            }

            // when a player is a hollow
            else if (entityStats.getRace().equals(ModValues.HOLLOW))
            {
                IAbilityData abilityData = AbilityDataCapability.get(player);
                for (Ability ability : abilityData.getUnlockedAbilities())
                {
                    if (ability.getActivationType().equals(Ability.ActivationType.ATTACK))
                        ability.activate(livingEntityTarget, player);
                }
            }
        }
    }
}
