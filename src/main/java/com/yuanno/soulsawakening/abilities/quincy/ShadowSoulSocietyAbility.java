package com.yuanno.soulsawakening.abilities.quincy;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IRightClickAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.ISelfEffect;
import com.yuanno.soulsawakening.init.world.ModDimensions;
import com.yuanno.soulsawakening.util.GargantaTeleporter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ShadowSoulSocietyAbility extends Ability implements IRightClickAbility, ISelfEffect {
    public static final ShadowSoulSocietyAbility INSTANCE = new ShadowSoulSocietyAbility();
    public ShadowSoulSocietyAbility()
    {
        this.setName("Shadow Soul Society Key");
        this.setDescription("Teleports to and from the shadow of soul society");
        this.setMaxCooldown(50);
        this.setSubCategory(SubCategory.REISHI);
    }

    @Override
    public void otherEffects(PlayerEntity player)
    {
        World world = player.level;
        MinecraftServer minecraftServer = world.getServer();
        if (minecraftServer == null)
            return;
        if (world.dimension() == ModDimensions.SOUL_SOCIETY_SHADOW) {
            ServerWorld overWorld = minecraftServer.getLevel(World.OVERWORLD);
            if (overWorld != null) {
                player.changeDimension(overWorld, new GargantaTeleporter(player.blockPosition(), false));
                return;
            }
        } else {
            ServerWorld shadowSociety = minecraftServer.getLevel(ModDimensions.SOUL_SOCIETY_SHADOW);
            if (shadowSociety != null) {
                player.changeDimension(shadowSociety, new GargantaTeleporter(player.blockPosition(), true));

                return;
            }
        }
    }

    @Override
    public boolean getShift()
    {
        return true;
    }

    @Override
    public boolean getAlt() {
        return true;
    }
}
