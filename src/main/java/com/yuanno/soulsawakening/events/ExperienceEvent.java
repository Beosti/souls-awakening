package com.yuanno.soulsawakening.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ExperienceEvent extends LivingEvent {
    private int amount;
    public ExperienceEvent(LivingEntity player, int amount) {
        super(player);
        this.amount = amount;
    }

    public int getAmount()
    {
        return this.amount;
    }


}
