package com.yuanno.soulsawakening.quests;

import net.minecraft.entity.EntityType;

public class KillObjective extends Objective{

    private int amount;
    private EntityType entityType;
    public KillObjective(String title, String description, int amount, EntityType entityType)
    {
        this.title = title;
        this.description = description;
        this.maxProgress = amount;
        this.entityType = entityType;
    }
}
