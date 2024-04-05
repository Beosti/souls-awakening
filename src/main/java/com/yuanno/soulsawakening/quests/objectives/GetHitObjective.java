package com.yuanno.soulsawakening.quests.objectives;

import com.yuanno.soulsawakening.quests.Objective;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public class GetHitObjective extends Objective {

    private IGetHit getHit = (player, damageSource) -> {

        return false;
    };

    public GetHitObjective(String title, String description, int amount, IGetHit getHit)
    {
        this.title = title;
        this.description = description;
        this.maxProgress = amount;
        if (getHit != null)
            this.getHit = getHit;
    }


    public IGetHit getKill()
    {
        return this.getHit;
    }

    @FunctionalInterface
    public interface IGetHit
    {
        boolean test(PlayerEntity player, DamageSource damageSource);

        default IGetHit and(IGetHit check)
        {
            return (player, damageSource) -> {
                return this.test(player, damageSource) && check.test(player, damageSource);
            };
        }

        default IGetHit or(IGetHit check)
        {
            return (player, damageSource) -> {
                return this.test(player, damageSource) || check.test(player, damageSource);
            };
        }
    }
}
