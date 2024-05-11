package com.yuanno.soulsawakening.abilities;

import com.yuanno.soulsawakening.ability.api.ExplosionAbility;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class AbilityHelper {
    public static ExplosionAbility newExplosion(Entity entity, World world, double posX, double posY, double posZ, float size)
    {
        return new ExplosionAbility(entity, world, posX, posY, posZ, size);
    }
}
