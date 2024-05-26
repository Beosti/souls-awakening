package com.yuanno.soulsawakening.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.world.pieces.ShinigamiSchoolPieces;
import com.yuanno.soulsawakening.world.structures.BigRuinStructure;
import com.yuanno.soulsawakening.world.structures.RoadStructure;
import com.yuanno.soulsawakening.world.structures.ShinigamiSchoolStructure;
import com.yuanno.soulsawakening.world.structures.SmallRuinStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class ModStructures {

    public static final DeferredRegister<Structure<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Main.MODID);
    public static final HashMap<Structure<?>, StructureFeature<?, ?>> REGISTERED_STRUCTURES = new HashMap<>();

    public static final RegistryObject<Structure<NoFeatureConfig>> BIG_RUIN = DEFERRED_REGISTRY_STRUCTURE.register("big_ruin", BigRuinStructure::new);
    public static final RegistryObject<Structure<NoFeatureConfig>> SMALL_RUIN = DEFERRED_REGISTRY_STRUCTURE.register("small_ruin", SmallRuinStructure::new);
    public static final RegistryObject<Structure<NoFeatureConfig>> ROADS = DEFERRED_REGISTRY_STRUCTURE.register("roads", RoadStructure::new);
    public static final RegistryObject<Structure<NoFeatureConfig>> SHINIGAMI_SCHOOL = DEFERRED_REGISTRY_STRUCTURE.register("shinigami_school", ShinigamiSchoolStructure::new);

    public static void setupStructures()
    {
        // structure, seperation (average distance in chunks from other structure, minimum, seed large and different), transform surrounding land or not
        setupMapSpacingAndLand(BIG_RUIN.get(), new StructureSeparationSettings(15, 10, 1234534561), false);
        setupMapSpacingAndLand(SMALL_RUIN.get(), new StructureSeparationSettings(10, 5, 721689745), false);
        setupMapSpacingAndLand(ROADS.get(), new StructureSeparationSettings(12, 7, 643921737), false);
        setupMapSpacingAndLandOther(SHINIGAMI_SCHOOL.get().configured(IFeatureConfig.NONE), new StructureSeparationSettings(60, 30, 682297831), true);

        ModStructures.Pieces.registerAllPieces();
    }
    public static class Pieces
    {
        public static final IStructurePieceType SHINIGAMI_SCHOOL_MAIN_BODY_PIECE = ShinigamiSchoolPieces.MainBodyPiece::new;
        public static final IStructurePieceType SHINIGAMI_SCHOOL_SIDE_PIECE = ShinigamiSchoolPieces.SidePiece::new;
        public static void registerAllPieces()
        {
            registerStructurePiece(SHINIGAMI_SCHOOL_MAIN_BODY_PIECE, new ResourceLocation(Main.MODID, "shinigami_school_main_body_piece"));
            registerStructurePiece(SHINIGAMI_SCHOOL_SIDE_PIECE, new ResourceLocation(Main.MODID, "shinigami_school_side"));

        }
        static void registerStructurePiece(IStructurePieceType structurePiece, ResourceLocation res)
        {
            Registry.register(Registry.STRUCTURE_PIECE, res, structurePiece);
        }
    }



    public static <F extends Structure<?>, S extends StructureFeature<?, ?>> void setupMapSpacingAndLandOther(
            S configuredStructure,
            StructureSeparationSettings structureSeparationSettings,
            boolean transformSurroundingLand)
    {
        F structure = (F) configuredStructure.feature;
        REGISTERED_STRUCTURES.put(structure, configuredStructure);
        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
        if(transformSurroundingLand)
        {
            Structure.NOISE_AFFECTING_FEATURES =
                    ImmutableList.<Structure<?>>builder()
                            .addAll(Structure.NOISE_AFFECTING_FEATURES)
                            .add(structure)
                            .build();
        }
        DimensionStructuresSettings.DEFAULTS =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.DEFAULTS)
                        .put(structure, structureSeparationSettings)
                        .build();
        WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().structureSettings().structureConfig();
            if(structureMap instanceof ImmutableMap)
            {
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                settings.getValue().structureSettings().structureConfig();
            }
            else
            {
                structureMap.put(structure, structureSeparationSettings);
            }
        });
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, structure.getRegistryName(), configuredStructure);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(structure, configuredStructure);
    }
    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure, StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand)
    {
        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
        if (transformSurroundingLand)
        {
            Structure.NOISE_AFFECTING_FEATURES = ImmutableList.<Structure<?>>builder().addAll(Structure.NOISE_AFFECTING_FEATURES).add(structure).build();
        }
        DimensionStructuresSettings.DEFAULTS = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.DEFAULTS).put(structure, structureSeparationSettings).build();
        WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().structureSettings().structureConfig();
            if(structureMap instanceof ImmutableMap)
            {
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                settings.getValue().structureSettings().structureConfig();
            }
            else
            {
                structureMap.put(structure, structureSeparationSettings);
            }
        });
    }

    public static void register(IEventBus eventBus)
    {
        DEFERRED_REGISTRY_STRUCTURE.register(eventBus);
    }
}
