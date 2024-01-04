package com.yuanno.soulsawakening.init.world;

import com.yuanno.soulsawakening.Main;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ModDimensions {
    public static RegistryKey<World> HUECO_MUNDO = RegistryKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Main.MODID, "hueco_mundo"));
    public static RegistryKey<World> CHALLENGE = RegistryKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Main.MODID, "challenge"));
}
