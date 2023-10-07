package com.yuanno.soulsawakening.ability.api;

import net.minecraft.entity.LivingEntity;

public abstract class Ability {
    private String name;
    private int cooldown;
    private ActivationType activationType;

    public Ability(String name, int cooldown, ActivationType activationType) {
        this.name = name;
        this.cooldown = cooldown;
        this.activationType = activationType;
    }

    public Ability()
    {
        this.name = "Default Ability";
        this.cooldown = 0;
        this.activationType = ActivationType.ATTACK;
    }

    // do something when left click
    public void activate(LivingEntity targetEntity)
    {

    }

    public String getName() {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public int getCooldown() {
        return cooldown;
    }
    public void setCooldown(int cooldown)
    {
        this.cooldown = cooldown;
    }

    public ActivationType getActivationType() {
        return activationType;
    }
    public void setActivationType(ActivationType activationType)
    {
        this.activationType = activationType;
    }

    public enum ActivationType {
        RIGHT_CLICK,
        ATTACK,
        RIGHT_CLICK_BLOCK;  // Add more activation types as needed

        // You can add more information or methods to the enum values if needed
    }

}
