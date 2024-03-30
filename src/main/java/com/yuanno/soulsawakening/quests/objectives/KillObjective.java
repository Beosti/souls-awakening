package com.yuanno.soulsawakening.quests.objectives;

import com.yuanno.soulsawakening.quests.Objective;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

import java.util.function.Supplier;

public class KillObjective extends Objective {

    private ICheckKill kill = (player, target, source) -> {

        return false;
    };

    public KillObjective(String title, String description, int amount, ICheckKill kill)
    {
        this.title = title;
        this.description = description;
        this.maxProgress = amount;
        if (kill != null)
            this.kill = kill;
    }


    public ICheckKill getKill()
    {
        return this.kill;
    }

    @FunctionalInterface
    public interface ICheckKill
    {
        boolean test(PlayerEntity player, LivingEntity target, DamageSource source);

        default ICheckKill and(ICheckKill check)
        {
            return (player, target, source) -> {
                return this.test(player, target, source) && check.test(player, target, source);
            };
        }

        default ICheckKill or(ICheckKill check)
        {
            return (player, target, source) -> {
                return this.test(player, target, source) || check.test(player, target, source);
            };
        }
    }
}
