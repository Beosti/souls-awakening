package com.yuanno.soulsawakening.init.world;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.init.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModConfiguredSurfaceBuilders {

    public static ConfiguredSurfaceBuilder<?> HUECO_MUNDO = register("hueco_mundo", SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderConfig(
            ModBlocks.HOLLOW_SAND.get().defaultBlockState(), ModBlocks.HOLLOW_SAND.get().defaultBlockState(), ModBlocks.HOLLOW_SAND.get().defaultBlockState()
    )));
    public static ConfiguredSurfaceBuilder<?> SOUL_SOCIETY = register("soul_society", SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderConfig(
            Blocks.STONE_BRICKS.defaultBlockState(), Blocks.STONE_BRICKS.defaultBlockState(), Blocks.STONE_BRICKS.defaultBlockState()
    )));

    public static <SC extends ISurfaceBuilderConfig>ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> csb)
    {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(Main.MODID, name), csb);
    }
}
