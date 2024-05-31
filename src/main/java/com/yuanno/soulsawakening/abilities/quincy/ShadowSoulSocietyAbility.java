package com.yuanno.soulsawakening.abilities.quincy;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.init.world.ModDimensions;
import com.yuanno.soulsawakening.util.GargantaTeleporter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ShadowSoulSocietyAbility extends Ability implements IRightClickAbility, IDimensionTeleportAbility {
    public static final ShadowSoulSocietyAbility INSTANCE = new ShadowSoulSocietyAbility();
    public ShadowSoulSocietyAbility()
    {
        this.setName("Shadow Soul Society Key");
        this.setDescription("Teleports to and from the shadow of soul society");
        this.setMaxCooldown(50);
        this.setSubCategory(SubCategory.SHADOW);
    }

    @Override
    public RegistryKey<World> getDimension1() {
        return ModDimensions.SOUL_SOCIETY_SHADOW;
    }

    @Override
    public RegistryKey<World> getDimension2() {
        return World.OVERWORLD;
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
