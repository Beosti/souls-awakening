package com.yuanno.soulsawakening.events;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.IVanishEffect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;

public class EffectsEvent {

    @Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
    public static class Client{
        @SubscribeEvent
        public static void onEntityRendered(RenderLivingEvent.Pre event)
        {
            Minecraft mc = Minecraft.getInstance();

            ClientPlayerEntity clientPlayer = mc.player;


            LivingEntity entity =  event.getEntity();

            Iterator<EffectInstance> iter = entity.getActiveEffects().iterator();

            while (iter.hasNext()) {
                EffectInstance eff = iter.next();

                if (eff.getDuration() <= 0) {
                    iter.remove();
                }

                if (eff.getEffect() instanceof IVanishEffect && ((IVanishEffect) eff.getEffect()).isVanished(entity, eff.getDuration(), eff.getAmplifier())) {
                    event.setCanceled(true);

                    return;
                }
            }
        }
    }
}
