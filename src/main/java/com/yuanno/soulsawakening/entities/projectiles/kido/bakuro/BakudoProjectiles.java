package com.yuanno.soulsawakening.entities.projectiles.kido.bakuro;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.client.renderers.entity.projectile.StretchingProjectileRenderer;
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
public class BakudoProjectiles {

    public static final RegistryObject<EntityType<HainawaProjectile>> HAINAWA = Beapi.registerEntityType("Hainawa", () -> Beapi.createEntityType(HainawaProjectile::new)
            .sized(0.3f, 0.3f)
            .build(Main.MODID + ":hainawa"));



    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(HAINAWA.get(), new StretchingProjectileRenderer.Factory(new CubeModel())
                .setStretchScale(0.3, 0.3).setColor("#FFFF00"));
    }
}
