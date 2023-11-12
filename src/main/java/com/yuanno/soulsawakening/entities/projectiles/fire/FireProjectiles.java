package com.yuanno.soulsawakening.entities.projectiles.fire;

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
public class FireProjectiles {

    public static final RegistryObject<EntityType<FireBallProjectile>> FIREBALL = Beapi.registerEntityType("Fireball",
            () -> Beapi.createEntityType(FireBallProjectile::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":fireball"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(FIREBALL.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                .setColor("#FF0000"));
    }
}
