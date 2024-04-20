package com.yuanno.soulsawakening.entities.projectiles.quincy;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.AbilityProjectileRenderer;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.models.CubeModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class QuincyProjectiles {

    public static final RegistryObject<EntityType<ReishiArrow>> REISHI_ARROW = Beapi.registerEntityType("reishi", () -> Beapi.createEntityType(ReishiArrow::new)
            .sized(0.35f, 0.35f)
            .build(Main.MODID + ":reishi_arrow"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(REISHI_ARROW.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                .setColor("#ADD8E6").setScale(0.35f, 0.35f, 1.5f));
    }
}