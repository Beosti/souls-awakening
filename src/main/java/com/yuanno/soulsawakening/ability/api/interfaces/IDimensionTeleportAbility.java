package com.yuanno.soulsawakening.ability.api.interfaces;

import com.yuanno.soulsawakening.init.world.ModDimensions;
import com.yuanno.soulsawakening.util.GargantaTeleporter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public interface IDimensionTeleportAbility {

    default void teleport(PlayerEntity player) {
        World world = player.level;
        MinecraftServer minecraftServer = world.getServer();
        if (minecraftServer == null)
            return;
        if (world.dimension() == getDimension1()) {
            ServerWorld overWorld = minecraftServer.getLevel(getDimension2());
            if (overWorld != null) {
                player.changeDimension(overWorld, new GargantaTeleporter(player.blockPosition(), false));
                return;
            }
        } else {
            ServerWorld huecoMundo = minecraftServer.getLevel(getDimension1());
            if (huecoMundo != null) {
                player.changeDimension(huecoMundo, new GargantaTeleporter(player.blockPosition(), true));

                return;
            }
        }
    }

    default RegistryKey<World> getDimension1()
    {
        return null;
    }
    default RegistryKey<World> getDimension2()
    {
        return null;
    }
}
