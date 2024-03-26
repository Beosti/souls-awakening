package com.yuanno.soulsawakening.teleport;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Dimension;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Locale;

import static net.minecraft.util.registry.Registry.DIMENSION_REGISTRY;
import static net.minecraft.util.registry.Registry.REGISTRY;

public class TeleportPosition {

    private BlockPos blockPos;
    private RegistryKey<World> dimension;
    private String name;

    public BlockPos getBlockPos()
    {
        return this.blockPos;
    }
    public void setBlockPos(BlockPos blockPos)
    {
        this.blockPos = blockPos;
    }
    public RegistryKey<World> getDimension()
    {
        return this.dimension;
    }
    public void setDimension(RegistryKey<World> serverWorld)
    {
        this.dimension = serverWorld;
    }
    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public CompoundNBT save()
    {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("xCoordinate", blockPos.getX());
        nbt.putInt("yCoordinate", blockPos.getY());
        nbt.putInt("zCoordinate", blockPos.getZ());
        nbt.putString("dimension", dimension.getRegistryName().toString());
        nbt.putString("name", name.toLowerCase(Locale.ROOT));
        return nbt;
    }

    public void load(CompoundNBT nbt)
    {
        int x = nbt.getInt("xCoordinate");
        int y = nbt.getInt("yCoordinate");
        int z = nbt.getInt("zCoordinate");
        this.blockPos = new BlockPos(x, y, z);
        ResourceLocation resourceLocation = new ResourceLocation(nbt.getString("dimension"));
        this.dimension = RegistryKey.create(DIMENSION_REGISTRY, resourceLocation);
        this.name = nbt.getString("name");
    }
}
