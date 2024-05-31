package com.yuanno.soulsawakening.entities.projectiles.water;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.ability.AbilityProjectileRenderer;
import com.yuanno.soulsawakening.entities.projectiles.lunar.LunarCrescentProjectile;
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
public class WaterProjectiles {

    public static final RegistryObject<EntityType<WaterSlashProjectile>> WATER_SLASH = Beapi.registerEntityType("Water Slash",
            () -> Beapi.createEntityType(WaterSlashProjectile::new)
                    .sized(0.5f, 3f)
                    .build(Main.MODID + ":water_slash"));
    public static final RegistryObject<EntityType<TidalWaveProjectile>> TIDAL_WAVE = Beapi.registerEntityType("Tidal Wave",
            () -> Beapi.createEntityType(TidalWaveProjectile::new)
                    .sized(0.5f, 3f)
                    .build(Main.MODID + ":tidal_wave"));
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(WATER_SLASH.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                .setColor("#1ca3ec")
                .setScale(0.5D, 6.0D, 0.5D));

        RenderingRegistry.registerEntityRenderingHandler(TIDAL_WAVE.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                .setColor("#1ca3ec")
                .setScale(4D, 6.0D, 2D));
    }
}
