package com.yuanno.soulsawakening.quests;

import com.yuanno.soulsawakening.ability.api.Ability;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

import java.util.function.Supplier;

public class UseAbilityObjective extends Objective{

    private Ability ability;
    public UseAbilityObjective(String title, String description, int amount, Ability ability)
    {
        this.title = title;
        this.description = description;
        this.maxProgress = amount;
        this.ability = ability;
    }

    public void setAbility(Ability ability)
    {
        this.ability = ability;
    }
    public Ability getAbility()
    {
        return this.ability;
    }
}
