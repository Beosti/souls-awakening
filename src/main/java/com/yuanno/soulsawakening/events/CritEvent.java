package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import net.minecraft.entity.LivingEntity;
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

        Random random = new Random();
        int randomizer = random.nextInt(100) + 1;
        IEntityStats entityStats = EntityStatsCapability.get(event.getEntityLiving());
        int speedStat = (int) entityStats.getSpeedStat();
        if (randomizer <= speedStat)
        {
            event.setDamageModifier(1  + ((float) speedStat /10));
            event.setResult(Event.Result.ALLOW);
        }
        else
            event.setResult(Event.Result.DENY);
    }

}
