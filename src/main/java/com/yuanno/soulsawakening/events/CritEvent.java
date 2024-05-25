package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class CritEvent {

    @SubscribeEvent
    public static void onCrit(CriticalHitEvent event)
    {
        if (event.getTarget().level.isClientSide)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(event.getEntityLiving());
        int speedStat = entityStats.getSpeedStat();
        int critChance = (int) speedStat / 2;
        int critDamage = speedStat;
        Random random = new Random();
        int chance = random.nextInt(100) + 1;
        if (chance < critChance)
        {
            event.setDamageModifier(critDamage);
            event.setResult(Event.Result.ALLOW);
        }
        else {
            event.setResult(Event.Result.DENY);
        }
    }
}
