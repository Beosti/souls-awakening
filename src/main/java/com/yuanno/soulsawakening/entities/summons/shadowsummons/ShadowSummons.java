package com.yuanno.soulsawakening.entities.summons.shadowsummons;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.client.renderers.entity.summons.shadow.ShadowCloneRenderer;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShadowSummons {

    public static final RegistryObject<EntityType<ShadowCloneEntity>> SHADOW_CLONE = Beapi.registerEntityType("Shadow Clone",
            () -> Beapi.createEntityType(ShadowCloneEntity::new)
                    .sized(0.8f, 1.8f)
                    .build(Main.MODID + ":shadow_clone"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(SHADOW_CLONE.get(), new ShadowCloneRenderer.Factory());
    }
}
