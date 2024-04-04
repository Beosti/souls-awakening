package com.yuanno.soulsawakening.entities.npc;

import com.yuanno.soulsawakening.client.screen.ShinigamiTeacherPrompt;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class ShinigamiTeacherEntity extends SoulNPCEntity {

    public ShinigamiTeacherEntity(EntityType type, World world)
    {
        super(type, world);
        this.setChatPrompt(new ShinigamiTeacherPrompt());
    }

}
