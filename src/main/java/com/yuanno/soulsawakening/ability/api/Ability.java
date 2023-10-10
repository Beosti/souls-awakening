package com.yuanno.soulsawakening.ability.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Locale;

public class Ability<T> extends ForgeRegistryEntry<Ability<?>> {
    private String name;
    private int cooldown;
    private ActivationType activationType;
    private boolean isPassive;
    private boolean isReady = true;
    private int maxCooldown;

    public Ability(String name, int cooldown, ActivationType activationType) {
        this.name = name;
        this.cooldown = cooldown;
        this.activationType = activationType;
    }
    public Ability(String name, int cooldown, ActivationType activationType, boolean isPassive) {
        this.name = name;
        this.cooldown = cooldown;
        this.activationType = activationType;
        this.isPassive = isPassive;
    }

    public Ability()
    {
        this.name = "Default Ability";
        this.cooldown = 0;
        this.activationType = ActivationType.ATTACK;
        this.isPassive = false;
        this.maxCooldown = cooldown;
    }

    // do something when left click
    public void activate(LivingEntity targetEntity, PlayerEntity user)
    {}
    // do something when right click entity
    public void onRightClickEntity(LivingEntity targetEntity, PlayerEntity user)
    {}

    public boolean isReady()
    {
        return this.isReady;
    }
    public void isReadySet(boolean isReady)
    {
        this.isReady = isReady;
    }
    public boolean getPassive()
    {
        return this.isPassive;
    }
    public void setPassive(boolean passive)
    {
        this.isPassive = passive;
    }
    public String getName() {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getMaxCooldown()
    {
        return maxCooldown;
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

    public CompoundNBT save() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putString("id", this.getRegistryName().toString());
        compoundNBT.putString("displayname", this.getName());
        compoundNBT.putInt("cooldown", this.getCooldown());
        compoundNBT.putString("type", this.getActivationType().toString());

        return compoundNBT;
    }

    public void load(CompoundNBT compoundNBT)
    {
        this.setName(compoundNBT.getString("displayname"));
        this.setCooldown(compoundNBT.getInt("cooldown"));
        this.setActivationType(Ability.ActivationType.valueOf(compoundNBT.getString("type")));
    }

    public enum ActivationType {
        RIGHT_CLICK_EMPTY,
        ATTACK,
        RIGHT_CLICK_BLOCK,
        SHIFT_RIGHT_CLICK,
        RIGHT_CLICK_ENTITY;  // Add more activation types as needed

        // You can add more information or methods to the enum values if needed
    }

}
