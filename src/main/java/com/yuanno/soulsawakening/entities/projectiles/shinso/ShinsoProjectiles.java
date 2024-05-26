package com.yuanno.soulsawakening.entities.projectiles.shinso;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.AbilityProjectileRenderer;
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
public class ShinsoProjectiles {

    public static final RegistryObject<EntityType<BladeProjectile>> BLADE = Beapi.registerEntityType("Blade", () -> Beapi.createEntityType(BladeProjectile::new)
            .sized(1F, 1F)
            .build(Main.MODID + ":blade"));

    public static final RegistryObject<EntityType<WideBladeProjectile>> WIDE_BLADE = Beapi.registerEntityType("Wide Blade", () -> Beapi.createEntityType(WideBladeProjectile::new)
            .sized(20, 1F)
            .build(Main.MODID + ":wide_blade"));
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(BLADE.get(), new StretchingProjectileRenderer.Factory(new CubeModel()).setStretchScale(1, 1).setColor("#FFFFFF"));
        RenderingRegistry.registerEntityRenderingHandler(WIDE_BLADE.get(), new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(20, 1, 0.5).setColor("#FFFFFF"));
    }
}
