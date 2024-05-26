package com.yuanno.soulsawakening.events.ability;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.events.projectiles.ProjectileShootEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityShootEvent {

    @SubscribeEvent
    public static void onShoot(ProjectileShootEvent event)
    {
        LivingEntity owner = event.getOwner();
        // todo maybe rework?
    }
}
