package com.yuanno.soulsawakening.entities.projectiles.wind;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.ability.AbilityProjectileRenderer;
import com.yuanno.soulsawakening.client.renderers.entity.projectile.StretchingProjectileRenderer;
import com.yuanno.soulsawakening.entities.projectiles.water.TidalWaveProjectile;
import com.yuanno.soulsawakening.entities.projectiles.water.WaterPressureProjectile;
import com.yuanno.soulsawakening.entities.projectiles.water.WaterSlashProjectile;
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
public class WindProjectiles {

    public static final RegistryObject<EntityType<WindSlashProjectile>> WIND_SLASH = Beapi.registerEntityType("Wind Slash",
            () -> Beapi.createEntityType(WindSlashProjectile::new)
                    .sized(0.5f, 0.5f)
                    .build(Main.MODID + ":wind_slash"));


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(WIND_SLASH.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                .setColor("#00FF00")
                .setScale(0.5D, 0.5D, 0.5D));


    }
}
