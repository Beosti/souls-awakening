package com.yuanno.soulsawakening.world.gen;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.init.ModConfiguredStructures;
import com.yuanno.soulsawakening.init.ModStructures;
import com.yuanno.soulsawakening.world.structures.SAStructure;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StructureGeneration {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void generateStructures(BiomeLoadingEvent event)
    {
        RegistryKey key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set types = BiomeDictionary.getTypes(key);
        ResourceLocation biomeName = event.getName();
        if(event.getCategory() == Biome.Category.NETHER || event.getCategory() == Biome.Category.THEEND || event.getName() == Biomes.THE_VOID.getRegistryName()) {
            return;
        }
        if (biomeName.toString().equals("soulsawakening:hueco_mundo"))
        {
            event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_BIG_RUIN);
            event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_SMALL_RUIN);
        }
        if (biomeName.toString().equals("soulsawakening:soul_society"))
        {
            event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_ROADS);
            for(Map.Entry<Structure<?>, StructureFeature<?, ?>> entry : ModStructures.REGISTERED_STRUCTURES.entrySet())
            {
                if(entry.getKey() instanceof SAStructure && !((SAStructure)entry.getKey()).biomeCheck(event)) {
                    continue;
                }
                event.getGeneration().getStructures().add(() -> entry.getValue());
            }
        }
    }
}
