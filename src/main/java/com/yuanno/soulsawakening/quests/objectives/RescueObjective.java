package com.yuanno.soulsawakening.quests.objectives;

import com.yuanno.soulsawakening.quests.Objective;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

import java.util.function.Supplier;

public class RescueObjective extends Objective {

    private Supplier<EntityType> entityType;
    private ICheckRescue rescue = ((player, target, source) -> {
        return false;
    });

    @FunctionalInterface
    public interface ICheckRescue
    {
        boolean test(PlayerEntity player, LivingEntity target, DamageSource source);

        default KillObjective.ICheckKill and(KillObjective.ICheckKill check)
        {
            return (player, target, source) -> {
                return this.test(player, target, source) && check.test(player, target, source);
            };
        }

        default KillObjective.ICheckKill or(KillObjective.ICheckKill check)
        {
            return (player, target, source) -> {
                return this.test(player, target, source) || check.test(player, target, source);
            };
        }
    }
}
