package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.entities.hollow.*;
import com.yuanno.soulsawakening.entities.npc.BakudoTeacherEntity;
import com.yuanno.soulsawakening.entities.npc.KidoTeacherEntity;
import com.yuanno.soulsawakening.entities.npc.TraderEntity;
import com.yuanno.soulsawakening.entities.npc.ShinigamiTeacherEntity;
import com.yuanno.soulsawakening.entity.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MODID);

    public static final RegistryObject<EntityType<GolemEntity>> GOLEM = ENTITIES
            .register("golem",
                    () -> EntityType.Builder.of(GolemEntity::new, EntityClassification.CREATURE)
                            .sized(1.3f, 4f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "golem").toString()));
    public static final RegistryObject<EntityType<SpiderEntity>> SPIDER = ENTITIES
            .register("spider",
                    () -> EntityType.Builder.of(SpiderEntity::new, EntityClassification.CREATURE)
                            .sized(3f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "spider").toString()));
    public static final RegistryObject<EntityType<ThornsEntity>> THORNS = ENTITIES
            .register("thorns",
                    () -> EntityType.Builder.of(ThornsEntity::new, EntityClassification.CREATURE)
                            .sized(2f, 4f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "thorns").toString()));
    public static final RegistryObject<EntityType<FlyingEntity>> FLYING = ENTITIES
            .register("flying",
                    () -> EntityType.Builder.of(FlyingEntity::new, EntityClassification.CREATURE)
                            .sized(2f, 3f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "flying").toString()));
    public static final RegistryObject<EntityType<ApeEntity>> APE = ENTITIES
            .register("ape",
                    () -> EntityType.Builder.of(ApeEntity::new, EntityClassification.CREATURE)
                            .sized(2f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "ape").toString()));
    public static final RegistryObject<EntityType<PlusEntity>> PLUS = ENTITIES
            .register("plus",
                    () -> EntityType.Builder.of(PlusEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 1.6f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "plus").toString()));
    public static final RegistryObject<EntityType<ShinigamiEntity>> SHINIGAMI = ENTITIES
            .register("shinigami",
                    () -> EntityType.Builder.of(ShinigamiEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "shinigami").toString()));
    public static final RegistryObject<EntityType<ChallengeShinigamiEntity>> CHALLENGE_SHINIGAMI = ENTITIES
            .register("challenge_shinigami",
                    () -> EntityType.Builder.of(ChallengeShinigamiEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "challange_shinigami").toString()));
    public static final RegistryObject<EntityType<InnerShikaiEntity>> SHIKAI = ENTITIES
            .register("shikai",
                    () -> EntityType.Builder.of(InnerShikaiEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "shikai").toString()));
    public static final RegistryObject<EntityType<TraderEntity>> TRADER = ENTITIES
            .register("trader",
                    () -> EntityType.Builder.of(TraderEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "trader").toString()));
    public static final RegistryObject<EntityType<ShinigamiTeacherEntity>> SHINIGAMI_TEACHER = ENTITIES
            .register("shinigami_teacher",
                    () -> EntityType.Builder.of(ShinigamiTeacherEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "shinigami_teacher").toString()));
    public static final RegistryObject<EntityType<KidoTeacherEntity>> KIDO_TEACHER = ENTITIES
            .register("kido_teacher",
                    () -> EntityType.Builder.of(KidoTeacherEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "kido_teacher").toString()));
    public static final RegistryObject<EntityType<BakudoTeacherEntity>> BAKUDO_TEACHER = ENTITIES
            .register("bakudo_teacher",
                    () -> EntityType.Builder.of(BakudoTeacherEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "bakudo_teacher").toString()));
    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
