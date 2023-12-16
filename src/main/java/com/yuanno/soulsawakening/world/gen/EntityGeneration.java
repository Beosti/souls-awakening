package com.yuanno.soulsawakening.world.gen;

import com.yuanno.soulsawakening.init.ModEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Set;

public class EntityGeneration {

    public static void onEntitySpawn(final BiomeLoadingEvent event)
    {
        RegistryKey key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set types = BiomeDictionary.getTypes(key);

        if (!types.contains(BiomeDictionary.Type.WET)
                && !types.contains(BiomeDictionary.Type.OCEAN)
                && !types.contains(BiomeDictionary.Type.RIVER)
                && !types.contains(BiomeDictionary.Type.WATER))
        {
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.BEAST.get(), 1, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.CLAW.get(), 1, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.JET.get(), 1, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.BULK.get(), 1, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.CENTIPEDE.get(), 1, 1, 1));

        }
    }
}
