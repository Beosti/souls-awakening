package com.yuanno.soulsawakening.entities.npc;

import com.yuanno.soulsawakening.client.chatprompts.KidoTeacherPrompt;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class KidoTeacherEntity extends SoulNPCEntity {

    public KidoTeacherEntity(EntityType type, World world)
    {
        super(type, world);
        this.setChatPrompt(new KidoTeacherPrompt());
    }


}
