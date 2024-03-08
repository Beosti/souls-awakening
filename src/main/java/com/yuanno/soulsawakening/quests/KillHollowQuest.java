package com.yuanno.soulsawakening.quests;

import com.yuanno.soulsawakening.entities.hollow.BeastEntity;
import com.yuanno.soulsawakening.entities.hollow.HollowEntity;
import com.yuanno.soulsawakening.init.ModEntities;
import net.minecraft.entity.EntityType;

import java.util.function.Supplier;

public class KillHollowQuest extends Quest {

    Supplier<EntityType> entityTypeSupplier = () -> {
        EntityType<BeastEntity> entityType = ModEntities.BEAST.get();

      return entityType;
    };

    public static final KillObjective.ICheckKill HOLLOW_CHECK = ((player, target, source) ->
    {
       return target instanceof HollowEntity;
    });

    private Objective objective = new KillObjective("Kill Hollow", "Kill one hollow", 1, HOLLOW_CHECK);

    public KillHollowQuest()
    {
        this.setTitle("Kill a hollow");
        this.setDescription("You have been tasked to kill your first hollow");
        this.addObjective(objective);
    }
}
