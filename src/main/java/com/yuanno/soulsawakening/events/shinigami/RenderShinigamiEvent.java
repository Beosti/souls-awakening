package com.yuanno.soulsawakening.events.shinigami;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.client.renderers.overlay.HollowModelRenderer;
import com.yuanno.soulsawakening.client.renderers.overlay.ZanpakutoOverlayRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderShinigamiEvent {

    @SubscribeEvent
    public static void onSwordRender(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();

            for (Map.Entry<EntityType<?>, EntityRenderer<?>> entry : mc.getEntityRenderDispatcher().renderers.entrySet()) {
                EntityRenderer entityRenderer = entry.getValue();
                if (entityRenderer instanceof LivingRenderer) {
                    LivingRenderer renderer = (LivingRenderer) entityRenderer;
                    renderer.addLayer(new ZanpakutoOverlayRenderer(renderer));


                }
            }

            for (Map.Entry<String, PlayerRenderer> entry : mc.getEntityRenderDispatcher().getSkinMap().entrySet()) {
                PlayerRenderer renderer = entry.getValue();

                renderer.addLayer(new ZanpakutoOverlayRenderer(renderer));

            }
        });
    }
}
