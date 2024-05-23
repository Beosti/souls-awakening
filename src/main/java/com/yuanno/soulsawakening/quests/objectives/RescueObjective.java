package com.yuanno.soulsawakening.quests.objectives;

import com.yuanno.soulsawakening.quests.Objective;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;


public class RescueObjective extends Objective {

    private ICheckRescue rescue = ((player, target) -> {
        return false;
    });

    public RescueObjective(String title, String description, int amount, ICheckRescue rescue)
    {
        this.title = title;
        this.description = description;
        this.maxProgress = amount;
        if (rescue != null)
            this.rescue = rescue;
    }

    public ICheckRescue getRescue()
    {
        return this.rescue;
    }
    @FunctionalInterface
    public interface ICheckRescue
    {
        boolean test(PlayerEntity player, LivingEntity target);

        default ICheckRescue and(ICheckRescue check)
        {
            return (player, target) -> {
                return this.test(player, target) && check.test(player, target);
            };
        }

        default ICheckRescue or(ICheckRescue check)
        {
            return (player, target) -> {
                return this.test(player, target) || check.test(player, target);
            };
        }
    }
}
