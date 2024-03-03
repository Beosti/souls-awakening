package com.yuanno.soulsawakening.abilities.hollow;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickEmptyAbility;
import com.yuanno.soulsawakening.init.world.ModDimensions;
import com.yuanno.soulsawakening.util.GargantaTeleporter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GargantaAbility extends Ability implements IRightClickEmptyAbility {
    public static final GargantaAbility INSTANCE = new GargantaAbility();
    public GargantaAbility()
    {
        this.setName("Garganta");
        this.setDescription("Teleports to and from Hueco Mundo");
        this.setCooldown(240);
        this.setMaxCooldown(240);
        this.setActivationType(ActivationType.SHIFT_RIGHT_CLICK);
        this.setPassive(false);
        this.setSubCategory(SubCategory.ADJUCHA);
    }

    @Override
    public void onShiftRightClick(PlayerEntity player) {
        World world = player.level;
        MinecraftServer minecraftServer = world.getServer();
        if (minecraftServer == null)
            return;
        if (world.dimension() == ModDimensions.HUECO_MUNDO) {
            ServerWorld overWorld = minecraftServer.getLevel(World.OVERWORLD);
            if (overWorld != null) {
                player.changeDimension(overWorld, new GargantaTeleporter(player.blockPosition(), false));
                return;
            }
        } else {
            ServerWorld huecoMundo = minecraftServer.getLevel(ModDimensions.HUECO_MUNDO);
            if (huecoMundo != null) {
                player.changeDimension(huecoMundo, new GargantaTeleporter(player.blockPosition(), true));

                return;
            }
        }
    }
}
