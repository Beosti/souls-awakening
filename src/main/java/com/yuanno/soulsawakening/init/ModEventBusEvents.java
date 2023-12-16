package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.entity.*;
import com.yuanno.soulsawakening.entity.hollow.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event)
    {
        //MOBS
        event.put(ModEntities.CENTIPEDE.get(), CentipedeEntity.setCustomAttributes().build());
        event.put(ModEntities.CLAW.get(), ClawEntity.setCustomAttributes().build());
        event.put(ModEntities.JET.get(), JetEntity.setCustomAttributes().build());
        event.put(ModEntities.BULK.get(), BulkEntity.setCustomAttributes().build());
        event.put(ModEntities.BEAST.get(), BeastEntity.setCustomAttributes().build());
        event.put(ModEntities.PLUS.get(), PlusEntity.setCustomAttributes().build());

    }
}
