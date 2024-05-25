package com.yuanno.soulsawakening;

import com.yuanno.soulsawakening.client.ClientHandler;
import com.yuanno.soulsawakening.client.overlay.InformationOverlay;
import com.yuanno.soulsawakening.client.overlay.KidoOverlay;
import com.yuanno.soulsawakening.client.overlay.AbilityOverlay;
import com.yuanno.soulsawakening.commands.ability.AbilityArgument;
import com.yuanno.soulsawakening.commands.quest.QuestArgument;
import com.yuanno.soulsawakening.init.*;
import com.yuanno.soulsawakening.init.world.ModBiomes;
import com.yuanno.soulsawakening.init.world.ModDimensions;
import com.yuanno.soulsawakening.util.ItemProperties;
import net.minecraft.block.Block;
import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MODID)
public class Main
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "soulsawakening";
    public Main() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEffects.EFFECTS.register(modEventBus);
        ModRegistry.ENTITY_TYPES.register(modEventBus);
        ModAbilities.ABILITIES.register(modEventBus);
        ModAbilities.register(modEventBus);
        ModQuests.QUESTS_DEFERRED_REGISTER.register(modEventBus);
        ModQuests.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModAttributes.ATTRIBUTES.register(modEventBus);
        ModStructures.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModFeatures.register(modEventBus);
        ModBiomes.register(modEventBus);
        ModAdvancements.register(modEventBus);
        ModParticleTypes.PARTICLE_TYPES.register(modEventBus);
        ModChallenges.register(modEventBus);
        ModTags.init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, com.yuanno.soulsawakening.init.ModConfig.SPEC, "soulsawakening.toml");

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        ModCapabilities.init();
        ModNetwork.init();
        ModStructures.setupStructures();
        event.enqueueWork(() -> {
            ModDimensions.setupDimensions();
            ModConfiguredStructures.registerConfiguredStructures();
        });

        ArgumentTypes.register("ability", AbilityArgument.class, new ArgumentSerializer<>(AbilityArgument::ability));
        ArgumentTypes.register("quest", QuestArgument.class, new ArgumentSerializer<>(QuestArgument::new));
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        ClientHandler.onSetup();

        ModKeyBinds.init();
        ItemProperties.register();
        MinecraftForge.EVENT_BUS.register(new AbilityOverlay());
        MinecraftForge.EVENT_BUS.register(new KidoOverlay());
        MinecraftForge.EVENT_BUS.register(new InformationOverlay());
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here

        }
    }
}
