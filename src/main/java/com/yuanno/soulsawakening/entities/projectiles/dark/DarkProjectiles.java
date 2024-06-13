package com.yuanno.soulsawakening.entities.projectiles.dark;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.ability.AbilityProjectileRenderer;
import com.yuanno.soulsawakening.entities.projectiles.fire.FireBallProjectile;
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
public class DarkProjectiles {

    public static final RegistryObject<EntityType<DarkBallProjectile>> DARKBALL = Beapi.registerEntityType("Darkball",
            () -> Beapi.createEntityType(DarkBallProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":darkball"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(DARKBALL.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                        .setScale(1, 1, 1)
                .setColor("#000000"));
    }
}
