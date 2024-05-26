package com.yuanno.soulsawakening.events;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * Server side event that gets called when {@link com.yuanno.soulsawakening.ability.api.interfaces.IEntityRayTrace} is updated somewhere
 * This event happens for every entity where the stat upgrades, could add stuff to it or add visual effects
 */
@Cancelable
public class UpdateStatEvent extends LivingEvent {
    public UpdateStatEvent(LivingEntity entity) {
        super(entity);
    }
}
