package com.yuanno.soulsawakening.entities.projectiles.hollow;

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
public class HollowProjectiles {

    public static final RegistryObject<EntityType<CeroProjectile>> CERO = Beapi.registerEntityType("Cero", () -> Beapi.createEntityType(CeroProjectile::new)
            .sized(2.5F, 2.5F)
            .build(Main.MODID + ":cero"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(CERO.get(), new StretchingProjectileRenderer
                .Factory(new CubeModel()).setStretchScale(2.5, 2.5).setColor("#FF0000"));
    }
}
