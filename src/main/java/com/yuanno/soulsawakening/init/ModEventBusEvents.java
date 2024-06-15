package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.entities.hollow.*;
import com.yuanno.soulsawakening.entities.npc.BakudoTeacherEntity;
import com.yuanno.soulsawakening.entities.npc.KidoTeacherEntity;
import com.yuanno.soulsawakening.entities.npc.TraderEntity;
import com.yuanno.soulsawakening.entities.npc.ShinigamiTeacherEntity;
import com.yuanno.soulsawakening.entities.summons.shadowsummons.ShadowCloneEntity;
import com.yuanno.soulsawakening.entities.summons.shadowsummons.ShadowSummons;
import com.yuanno.soulsawakening.entity.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event)
    {
        //MOBS
        event.put(ModEntities.GOLEM.get(), GolemEntity.setCustomAttributes().build());
        event.put(ModEntities.SPIDER.get(), SpiderEntity.setCustomAttributes().build());
        event.put(ModEntities.THORNS.get(), ThornsEntity.setCustomAttributes().build());
        event.put(ModEntities.FLYING.get(), FlyingEntity.setCustomAttributes().build());
        event.put(ModEntities.APE.get(), ApeEntity.setCustomAttributes().build());
        event.put(ShadowSummons.SHADOW_CLONE.get(), ShadowCloneEntity.setCustomAttributes().build());
        event.put(ModEntities.PLUS.get(), PlusEntity.setCustomAttributes().build());
        event.put(ModEntities.SHINIGAMI.get(), ShinigamiEntity.setCustomAttributes().build());
        event.put(ModEntities.TRADER.get(), TraderEntity.setCustomAttributes().build());
        event.put(ModEntities.SHINIGAMI_TEACHER.get(), ShinigamiTeacherEntity.setCustomAttributes().build());
        event.put(ModEntities.KIDO_TEACHER.get(), KidoTeacherEntity.setCustomAttributes().build());
        event.put(ModEntities.BAKUDO_TEACHER.get(), BakudoTeacherEntity.setCustomAttributes().build());
        event.put(ModEntities.CHALLENGE_SHINIGAMI.get(), ChallengeShinigamiEntity.setCustomAttributes().build());
        event.put(ModEntities.SHIKAI.get(), InnerShikaiEntity.setCustomAttributes().build());

    }
}
