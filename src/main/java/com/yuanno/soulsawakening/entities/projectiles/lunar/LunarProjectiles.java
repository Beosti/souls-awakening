package com.yuanno.soulsawakening.entities.projectiles.lunar;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.api.ability.AbilityProjectileRenderer;
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
public class LunarProjectiles {

    public static final RegistryObject<EntityType<LunarCrescentProjectile>> LUNAR_CRESCENT = Beapi.registerEntityType("Lunar Crescent",
            () -> Beapi.createEntityType(LunarCrescentProjectile::new)
                    .sized(0.5f, 3f)
                    .build(Main.MODID + ":lunar_crescent"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(LUNAR_CRESCENT.get(), new AbilityProjectileRenderer.Factory(new CubeModel())
                .setColor("#808080")
                .setScale(0.5D, 6.0D, 0.5D));
    }
}
