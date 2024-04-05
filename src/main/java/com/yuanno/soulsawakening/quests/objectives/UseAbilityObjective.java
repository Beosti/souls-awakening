package com.yuanno.soulsawakening.quests.objectives;

import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.quests.Objective;
import com.yuanno.soulsawakening.quests.objectives.KillObjective;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

import java.util.function.Supplier;

public class UseAbilityObjective extends Objective {

    private Ability ability;
    private ICheckAbility checkAbility = ((player, target) -> {
        return false;
    });
    public UseAbilityObjective(String title, String description, int amount, Ability ability)
    {
        this.title = title;
        this.description = description;
        this.maxProgress = amount;
        this.ability = ability;
    }
    public UseAbilityObjective(String title, String description, int amount, Ability ability, ICheckAbility iCheckAbility)
    {
        this.title = title;
        this.description = description;
        this.maxProgress = amount;
        this.ability = ability;
        if (iCheckAbility != null)
            this.checkAbility = iCheckAbility;
    }


    public void setAbility(Ability ability)
    {
        this.ability = ability;
    }
    public Ability getAbility()
    {
        return this.ability;
    }
    public ICheckAbility getCheckAbility()
    {
        return this.checkAbility;
    }
    public boolean hasICheckAbility()
    {
        return this.checkAbility != null;
    }
    @FunctionalInterface
    public interface ICheckAbility
    {
        boolean test(PlayerEntity player, LivingEntity target);
        default ICheckAbility and(ICheckAbility check)
        {
            return (player, target) -> {
                return this.test(player, target) && check.test(player, target);
            };
        }

        default ICheckAbility or(ICheckAbility check)
        {
            return (player, target) -> {
                return this.test(player, target) || check.test(player, target);
            };
        }
    }
}
