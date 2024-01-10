package com.yuanno.soulsawakening.world.gen;

import com.google.common.collect.Lists;
import com.yuanno.soulsawakening.init.ModEntities;
import com.yuanno.soulsawakening.init.world.ModBiomes;
import com.yuanno.soulsawakening.init.world.ModDimensions;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.ArrayList;
import java.util.Set;

public class EntityGeneration {

    public static void onEntitySpawn(final BiomeLoadingEvent event)
    {
        RegistryKey key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        //RegistryKey dimensionKey = RegistryKey.create(Registry.DIMENSION_REGISTRY, event.)
        ArrayList<BiomeDictionary.Type> ALL_BIOMES = new ArrayList<>(BiomeDictionary.Type.getAll());

        Set types = BiomeDictionary.getTypes(key);

        if ((!types.contains(BiomeDictionary.Type.WET)
                && !types.contains(BiomeDictionary.Type.OCEAN)
                && !types.contains(BiomeDictionary.Type.RIVER)
                && !types.contains(BiomeDictionary.Type.WATER) && types.contains(ALL_BIOMES)) || types.contains(ModBiomes.HUECO_MUNDO.getId()))
        {
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.BEAST.get(), 1, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.CLAW.get(), 1, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.JET.get(), 1, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.BULK.get(), 1, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.CENTIPEDE.get(), 1, 1, 1));

        }
        if (!types.contains(BiomeDictionary.Type.WET)
                && !types.contains(BiomeDictionary.Type.OCEAN)
                && !types.contains(BiomeDictionary.Type.RIVER)
                && !types.contains(BiomeDictionary.Type.WATER) && types.contains(ALL_BIOMES))
        {
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.PLUS.get(), 20, 1, 1));
            event.getSpawns().addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.SHINIGAMI.get(), 20, 1, 1));

        }
    }


}
