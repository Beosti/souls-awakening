package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class CritEvent {

    @SubscribeEvent
    public static void onCrit(CriticalHitEvent event)
    {
        if (event.getEntityLiving().level.isClientSide)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(event.getEntityLiving());
        int speedStat = entityStats.get
        System.out.println("CRIT LANDED");
        System.out.println("Target: " + event.getTarget());
        System.out.println("Attacker: " + event.getEntityLiving());
        System.out.println("Current modifier: " + event.getDamageModifier());
        System.out.println("Old modifier: " + event.getOldDamageModifier());
        event.setResult(Event.Result.DENY);
    }
}
