package com.yuanno.soulsawakening.entities.npc;

import com.yuanno.soulsawakening.client.chatprompts.api.BakudoTeacherPrompt;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class BakudoTeacherEntity extends SoulNPCEntity {

    public BakudoTeacherEntity(EntityType type, World world)
    {
        super(type, world);
        this.setChatPrompt(new BakudoTeacherPrompt());
    }


    @Override
    public boolean removeWhenFarAway(double d)
    {
        return false;
    }
}
