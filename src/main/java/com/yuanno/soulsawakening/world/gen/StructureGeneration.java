package com.yuanno.soulsawakening.world.gen;

import com.yuanno.soulsawakening.init.ModConfiguredStructures;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Set;

public class StructureGeneration {

    public static void generateStructures(final BiomeLoadingEvent event)
    {
        RegistryKey key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set types = BiomeDictionary.getTypes(key);
        ResourceLocation biomeName = event.getName();

        if (biomeName.toString().equals("soulsawakening:hueco_mundo"))
        {
            event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_BIG_RUIN);
            event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_SMALL_RUIN);
        }
        if (biomeName.toString().equals("soulsawakening:soul_society"))
        {
            event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_ROADS);
            event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_SHINIGAMI_SCHOOL);
        }
    }
}
