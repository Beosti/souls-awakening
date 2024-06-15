package com.yuanno.soulsawakening.api.ability.interfaces;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.entity.CloneEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import java.util.Random;

public interface ISummonAbility {

    default void onSummon(PlayerEntity player, Ability ability)
    {
        Random random = new Random();
        for (int i = 0; i < getSummonAmount(); i++)
        {
            Entity entity = getSummon(player);
            entity.moveTo(player.getX() + random.nextInt(2), player.getY(), player.getZ() + random.nextInt(2));
            if (entity instanceof CloneEntity)
            {
                CloneEntity cloneEntity = (CloneEntity) entity;
                cloneEntity.setOwner(player.getUUID());
                for(EquipmentSlotType slot : EquipmentSlotType.values())
                {
                    ItemStack stack = player.getItemBySlot(slot);
                    cloneEntity.setItemSlot(slot, stack);
                }
            }
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
