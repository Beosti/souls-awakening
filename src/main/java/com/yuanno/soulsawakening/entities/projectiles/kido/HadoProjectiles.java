package com.yuanno.soulsawakening.entities.projectiles.kido;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.client.renderers.AbilityProjectileRenderer;
import com.yuanno.soulsawakening.client.renderers.StretchingProjectileRenderer;
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
public class HadoProjectiles {

    public static final RegistryObject<EntityType<ShoProjectile>> SHO = Beapi.registerEntityType("Sho", () -> Beapi.createEntityType(ShoProjectile::new)
            .sized(1, 1)
            .build(Main.MODID + ":sho"));
    public static final RegistryObject<EntityType<ByakuraiProjectile>> BYAKURAI = Beapi.registerEntityType("Byakurai", () -> Beapi.createEntityType(ByakuraiProjectile::new)
            .sized(0.2f, 0.2f)
            .build(Main.MODID + ":byakurai"));
    public static final RegistryObject<EntityType<ShakkahoProjectile>> SHAKKAHO = Beapi.registerEntityType("Shakkaho", () -> Beapi.createEntityType(ShakkahoProjectile::new)
            .sized(1f, 1f)
            .build(Main.MODID + ":shakkaho"));
    public static final RegistryObject<EntityType<ShakkahoIncantationProjectile>> SHAKKAHO_INCANTATION = Beapi.registerEntityType("Shakkaho Incantation", () -> Beapi.createEntityType(ShakkahoIncantationProjectile::new)
            .sized(3f, 3f)
            .build(Main.MODID + ":shakkaho_incantation"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(SHO.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                .setScale(1, 1, 1)
                .setColor("FFFFFF"));
        RenderingRegistry.registerEntityRenderingHandler(BYAKURAI.get(), new StretchingProjectileRenderer.Factory(new CubeModel())
                .setStretchScale(0.2, 0.2).setColor("#ADD8E6"));
        RenderingRegistry.registerEntityRenderingHandler(SHAKKAHO.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                .setScale(1, 1, 1)
                .setColor("FF0000"));
        RenderingRegistry.registerEntityRenderingHandler(SHAKKAHO_INCANTATION.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                .setScale(3, 3, 3)
                .setColor("FF0000"));
    }
}
