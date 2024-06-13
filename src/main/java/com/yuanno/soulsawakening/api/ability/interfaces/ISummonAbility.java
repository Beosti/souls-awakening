package com.yuanno.soulsawakening.api.ability.interfaces;

import com.yuanno.soulsawakening.api.ability.Ability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Random;

public interface ISummonAbility {

    default void onSummon(PlayerEntity player, Ability ability)
    {
        Entity entity = getSummon(player);
        Random random = new Random();
        for (int i = 0; i < getSummonAmount(); i++)
        {
            entity.setPos(player.getX() + random.nextInt(2), player.getY(), player.getZ() + random.nextInt(2));
            player.level.addFreshEntity(entity);
        }
    }

    default float getSummonAmount()
    {
        return 0;
    }
    default Entity getSummon(PlayerEntity player)
    {
        return null;
    }
}
