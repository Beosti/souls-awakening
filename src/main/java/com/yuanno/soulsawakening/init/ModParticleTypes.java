package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.particles.GenericParticleData;
import com.yuanno.soulsawakening.particles.SimpleParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Main.MODID);
    public static final RegistryObject<ParticleType<GenericParticleData>> HOLLOW = PARTICLE_TYPES.register("hollow", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> CERO = PARTICLE_TYPES.register("cero", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> FIRE = PARTICLE_TYPES.register("fire", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> HEALING = PARTICLE_TYPES.register("healing", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> POISON = PARTICLE_TYPES.register("poison", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> THUNDER = PARTICLE_TYPES.register("thunder", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> DARK = PARTICLE_TYPES.register("dark", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> LUNAR = PARTICLE_TYPES.register("lunar", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> YELLOW = PARTICLE_TYPES.register("yellow", GenericParticleData::new);
    public static final RegistryObject<ParticleType<GenericParticleData>> WATER = PARTICLE_TYPES.register("water", GenericParticleData::new);

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event)
    {
        final ParticleManager manager = Minecraft.getInstance().particleEngine;

        manager.register(ModParticleTypes.HOLLOW.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/hollow_particle.png")));
        manager.register(ModParticleTypes.CERO.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/cero_particle.png")));
        manager.register(ModParticleTypes.FIRE.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/fire_particle.png")));
        manager.register(ModParticleTypes.HEALING.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/healing_particle.png")));
        manager.register(ModParticleTypes.POISON.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/poison_particle.png")));
        manager.register(ModParticleTypes.THUNDER.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/thunder_particle.png")));
        manager.register(ModParticleTypes.DARK.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/dark_particle.png")));
        manager.register(ModParticleTypes.LUNAR.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/lunar_particle.png")));
        manager.register(ModParticleTypes.YELLOW.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/yellow_particle.png")));
        manager.register(ModParticleTypes.WATER.get(), new SimpleParticle.Factory(new ResourceLocation(Main.MODID, "textures/particle/water_particle.png")));

    }

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }

}
