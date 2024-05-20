package com.yuanno.soulsawakening.world.gen;

import com.yuanno.soulsawakening.init.ModEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Set;

public class EntityGeneration {

    public static void onEntitySpawn(final BiomeLoadingEvent event)
    {
        RegistryKey key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        //RegistryKey dimensionKey = RegistryKey.create(Registry.DIMENSION_REGISTRY, event.)
        /*
        ArrayList<BiomeDictionary.Type> ALL_BIOMES = new ArrayList<>(BiomeDictionary.Type.getAll());
        ArrayList<String> NOT_BIOMES = new ArrayList<>();
        NOT_BIOMES.add("minecraft:deep_ocean");
        NOT_BIOMES.add("minecraft:lukewarm_ocean");
        NOT_BIOMES.add("minecraft:ocean");
        NOT_BIOMES.add("minecraft:cold_ocean");
        NOT_BIOMES.add("minecraft:deep_cold_ocean");
        NOT_BIOMES.add("minecraft:deep_frozen_ocean");

         */
        // !NOT_BIOMES.contains(biomeName.toString())

        Set types = BiomeDictionary.getTypes(key);
        ResourceLocation biomeName = event.getName();
        // hollow don't spawn in: ocean, river and soul society
        // spawns in hueco mundo
        if (!biomeName.toString().equals("beach") && !biomeName.toString().contains("ocean") && !biomeName.toString().contains("river") && !biomeName.toString().equals("minecraft:the_void") && !biomeName.toString().equals("soulsawakening:soul_society") && !biomeName.toString().equals("soulsawakening:soul_society_shadow") || biomeName.toString().equals("soulsawakening:hueco_mundo"))
        {
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.APE.get(), 3, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.SPIDER.get(), 3, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.THORNS.get(), 3, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.FLYING.get(), 3, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.GOLEM.get(), 3, 1, 1));

        }
        // spirits, shinigami, traders don't spawn in: ocean, river and soul society
        // spawns in soul society
        if (!biomeName.toString().equals("beach") && !biomeName.toString().contains("ocean") && !biomeName.toString().contains("river") && !biomeName.toString().equals("minecraft:the_void") && !biomeName.toString().equals("soulsawakening:hueco_mundo") && !biomeName.toString().equals("soulsawakening:soul_society_shadow"))
        {
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.PLUS.get(), 20, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.SHINIGAMI.get(), 20, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.TRADER.get(), 10, 1, 1));

        }
    }


}
