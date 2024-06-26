package com.yuanno.soulsawakening.abilities;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.init.world.ModDimensions;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;

public class SoulSocietyKeyAbility extends Ability implements IRightClickAbility, IDimensionTeleportAbility {
    public static final SoulSocietyKeyAbility INSTANCE = new SoulSocietyKeyAbility();

    public SoulSocietyKeyAbility()
    {
        this.setName("Soul Society Key");
        this.setDescription("Opens up a door and teleports you from and to soul society");
        this.setMaxCooldown(50);
        this.setSubCategory(SubCategory.SEALED);
    }

    @Override
    public boolean getAlt() {
        return true;
    }

    @Override
    public RegistryKey<World> getDimension1() {
        return ModDimensions.SOUL_SOCIETY;
    }

    @Override
    public RegistryKey<World> getDimension2() {
        return World.OVERWORLD;
    }
}
