package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entity.*;
import com.yuanno.soulsawakening.entity.hollow.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MODID);

    public static final RegistryObject<EntityType<CentipedeEntity>> CENTIPEDE = ENTITIES
            .register("centipede",
                    () -> EntityType.Builder.of(CentipedeEntity::new, EntityClassification.CREATURE)
                            .sized(1.3f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "centipede").toString()));
    public static final RegistryObject<EntityType<ClawEntity>> CLAW = ENTITIES
            .register("claw",
                    () -> EntityType.Builder.of(ClawEntity::new, EntityClassification.CREATURE)
                            .sized(2f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "claw").toString()));
    public static final RegistryObject<EntityType<JetEntity>> JET = ENTITIES
            .register("jet",
                    () -> EntityType.Builder.of(JetEntity::new, EntityClassification.CREATURE)
                            .sized(2f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "jet").toString()));
    public static final RegistryObject<EntityType<BulkEntity>> BULK = ENTITIES
            .register("bulk",
                    () -> EntityType.Builder.of(BulkEntity::new, EntityClassification.CREATURE)
                            .sized(2f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "bulk").toString()));
    public static final RegistryObject<EntityType<BeastEntity>> BEAST = ENTITIES
            .register("beast",
                    () -> EntityType.Builder.of(BeastEntity::new, EntityClassification.CREATURE)
                            .sized(2f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "beast").toString()));
    public static final RegistryObject<EntityType<PlusEntity>> PLUS = ENTITIES
            .register("plus",
                    () -> EntityType.Builder.of(PlusEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "plus").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
